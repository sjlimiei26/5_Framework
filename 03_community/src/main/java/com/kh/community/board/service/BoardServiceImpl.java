package com.kh.community.board.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.community.board.model.dto.BoardDTO;
import com.kh.community.board.model.dto.BoardImageDTO;
import com.kh.community.board.model.mapper.BoardMapper;
import com.kh.community.common.util.FileUploadUtil;
import com.kh.community.common.util.SavedFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;
	
	private final FileUploadUtil fileUploadUtil;
	
	@Value("${file.upload-dir.board}")
	private String boardUploadDir;

	@Override
	public List<BoardDTO> getBoardList() {
		return mapper.selectBoardList();
	}

	@Override
	public Long writeBoard(BoardDTO board, List<MultipartFile> images) throws IllegalStateException, IOException {
		// DB에 게시글 정보 저장
		mapper.insertBoard(board);
		// => 매퍼가 실행된 후 BoardDTO에는 boardId 값이 채워짐
		
		Long boardId = board.getBoardId();
		// 이미지 파일 서버에 저장
		saveImages(boardId, images);
		
		// 게시글 id 값 리턴
		return boardId;
	}
	
	private void saveImages(Long boardId, List<MultipartFile> images) throws IllegalStateException, IOException {
		// 이미지가 없으면 메소드 종료
		if (images == null || images.isEmpty()) {
			return;
		}
		int order = 0;
		for (MultipartFile file : images) {
			// 서버에 이미지 파일 저장
			SavedFile saved = fileUploadUtil.save(file, boardUploadDir, "/uploads/board");
			if (saved == null) {
				continue;
			}
			
			// 저장된 이미지 정보를 기준으로 DTO 객체 생성
			BoardImageDTO boardImage = new BoardImageDTO(
										null,
										boardId,
										saved.getOriginalName(),
										saved.getSaveName(),
										saved.getPath(),
										order++,
										null
										);
										
			// DB에 게시글 이미지 저장
			mapper.insertBoardImage(boardImage);
		}
		
		
	}

	@Override
	public BoardDTO getBoardDetail(Long boardId) {
		// 상세페이지 접근 시 조회 수 1 증가 (업데이트)
		mapper.increaseViewCount(boardId);		
		
		// boardId에 해당하는 게시글 조회
		BoardDTO board = mapper.selectBoardDetail(boardId);
		
		// boardId에 해당하는 게시글 이미지 조회
		board.setImages( mapper.selectImagesByBoardId(boardId) );
		
		return board;
	}

	@Override
	public void deleteBoard(Long boardId) {
		// 삭제 전 이미지 정보 조회
		List<BoardImageDTO> images = mapper.selectImagesByBoardId(boardId);
		
		// 게시글 삭제
		mapper.deleteBoard(boardId);
		
		// 회원탈퇴처럼.. 이미지 서버에서 삭제
		deleteImageFiles(images);
	}

	@Override
	public void updateBoard(Long boardId, BoardDTO board, List<MultipartFile> newImages, String requestMemberId)
			throws IOException {
		// 기존에 저장된 게시글 정보 조회
		BoardDTO original = mapper.selectBoardDetail(boardId);
		
		// 게시글 및 작성자 검증
		if (original == null) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}
		if (original.getMemberId() == null || !original.getMemberId().equals(requestMemberId)) {
			throw new SecurityException("본인이 작성한 게시글만 수정할 수 있습니다.");
		}
		
		// 게시글 정보 수정
		board.setBoardId(boardId);
		mapper.updateBoard(board);
		
		// 추가된 이미지가 있을 경우 처리 (서버에 반영, 디비에 반영)
		if (hasImages(newImages)) {
			
			List<BoardImageDTO> images = mapper.selectImagesByBoardId(boardId);
			// 해당 게시글의 이미지 정보를 조회한 후 서버에서 삭제
			deleteImageFiles(images);
			
			// DB에서도 삭제
			mapper.deleteBoardImage(boardId);

			// 새로운 이미지 저장
			saveImages(boardId, newImages);
		}
	}
	
	private boolean hasImages(List<MultipartFile> images) {
		boolean hasImage = false;
		
		for(MultipartFile img : images) {
			if (img != null && !img.isEmpty()) hasImage = true;
		}
	
		return hasImage;
	}
	
	private void deleteImageFiles(List<BoardImageDTO> images) {
		if (images != null && !images.isEmpty()) {
			for(BoardImageDTO img : images) {
				fileUploadUtil.delete(img.getImagePath(), boardUploadDir);
			}
		}
	}
	

}





