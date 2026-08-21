package com.kh.community.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.community.board.model.dto.BoardDTO;
import com.kh.community.board.model.dto.CommentDTO;
import com.kh.community.board.service.BoardService;
import com.kh.community.board.service.CommentService;
import com.kh.community.common.SessionConst;
import com.kh.community.member.model.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/*
 * "게시판" 관련 화면 이동, 폼 처리 등을 담당할 컨트롤러
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	private final CommentService commentService;
	
	// ------- 화면 이동 요청 ---------
	@GetMapping("/list")
	public String boardList(Model model) {
		
		// DB에서 데이터를 조회하여 저장
		model.addAttribute("boardList", service.getBoardList());
		
		return "board/list";
	}
	
	@GetMapping("/write")
	public String writeForm() {
		return "board/form";
	}
	
	@GetMapping("/detail/{boardId}")
	public String detail(@PathVariable Long boardId,
						Model model, HttpSession session) {
		BoardDTO board = service.getBoardDetail(boardId);
		
		// 댓글 목록 조회
		List<CommentDTO> comments = commentService.getComments(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("comments", comments);
		
		// 로그인한 회원이 작성자인지 여부
		MemberDTO loginMember = (MemberDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		boolean isOwner = loginMember != null && loginMember.getMemberId().equals(board.getMemberId());
		model.addAttribute("isOwner", isOwner);
		
		
		return "board/detail";
	}
	
	@GetMapping("/edit/{boardId}")
	public String editBoard(@PathVariable Long boardId,
							HttpSession session,
							Model model) {
		// 해당 게시글 정보 조회
		BoardDTO board = service.getBoardDetail(boardId);
		
		// 로그인 한 사용자와 게시글 작성자 일치 여부 확인
		MemberDTO loginMember = (MemberDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);
		if (loginMember == null || !loginMember.getMemberId().equals(board.getMemberId()) ) {
			throw new SecurityException("본인이 작성한 게시글만 수정할 수 있습니다.");
		}
		
		// 게시글 정보와 수정 모드 저장
		model.addAttribute("mode", "edit");
		model.addAttribute("board", board);
		
		return "board/form";
	}
	// -----------------------------
	
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDTO board,
						@RequestParam(value="imageFiles", required=false) List<MultipartFile> images,
						HttpSession session) throws IllegalStateException, IOException {
		// 세션 영역에서 로그인 정보를 추출하여 DTO에 저장
		MemberDTO loginMember = (MemberDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);
		board.setMemberId( loginMember.getMemberId() );
		
		// 서비스를 통해 DB에 게시글 추가 + 업로드된 이미지는 서버에 저장
		service.writeBoard(board, images);
		
		return "redirect:/board/list";	// TODO: 상세페이지 추가 후 변경
	}
	
	@PostMapping("/delete/{boardId}")
	public String delete(@PathVariable Long boardId) {
		service.deleteBoard(boardId);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/edit/{boardId}")
	public String edit(@PathVariable Long boardId,
						@ModelAttribute BoardDTO board,
						@RequestParam(value="imageFiles", required=false) List<MultipartFile> images,
						HttpSession session) throws IOException {
		MemberDTO loginMember = (MemberDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		service.updateBoard(boardId, board, images, loginMember.getMemberId());
		
		// 해당 게시글 상세 페이지로 리다이렉트
		return "redirect:/board/detail/" + boardId;
	}
}





