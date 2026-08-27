/***** 웹소켓 객체 *****/
let ws;

/***** 요소 접근 *****/
const chatForm = document.getElementById('chatForm');
const messageInput = document.getElementById('messageInput');
const chatMessages = document.getElementById('chatMessages');

initWebSocket();		// 초기 웹 소켓 연결


/**
 * 메시지를 화면에 표시하는 함수
 * @param {string} text 메시지 내용
 * @param {string} [sender='bot'] 전송자 타입 (bot: 시스템, user: 현재 접속한 사용자, other: 다른 사용자)
 * @param {string} [senderName=''] 전송자명
 */
function appendMessage(text, sender = 'bot', senderName = '') {
    const wrapper = document.createElement('div');
    wrapper.classList.add('message-wrapper', sender);

    // 상대방 메시지일 경우 상단에 이름 표시
    if (sender !== 'user' && senderName) {
        const nameSpan = document.createElement('span');
        nameSpan.classList.add('sender-name');
        nameSpan.textContent = senderName;
        wrapper.appendChild(nameSpan);
    }

    const bubble = document.createElement('div');
    bubble.classList.add('message-bubble');
    bubble.textContent = text;

    const time = document.createElement('span');
    time.classList.add('message-time');
    time.textContent = getCurrentTime();

    wrapper.appendChild(bubble);
    wrapper.appendChild(time);
    chatMessages.appendChild(wrapper);

    chatMessages.scrollTop = chatMessages.scrollHeight;
}

/**
 * 웹소켓으로 메시지를 전송하는 함수
 * @param {string} message 웹소켓으로 전송할 메시지
 */
function sendMessage(message) {
    if (!ws || ws.readyState !== WebSocket.OPEN) {
        alert("서버와 연결이 원활하지 않습니다.");
        return;
    }
    ws.send(message);
}

/**
 * 웹소켓 초기화하는 함수
 */
function initWebSocket() {
    // 서버에서 지정한 주소와 함께 웹소켓 객체 생성
    ws = new WebSocket("ws://" + location.host + "/chat");

	ws.onopen = function (event) {
	    //소켓이 열리면 초기화 세팅하기
		
		ws.send(`${CURRENT_USER}님이 입장하였습니다.`)
	}
	
    ws.onmessage = function (event) {
        const rawMsg = event.data;
        if (!rawMsg || rawMsg.trim() === "") return;

		// "닉네임: 메시지" 형식에서 첫 번째 콜론(:)을 기준으로 파싱
        const separatorIndex = rawMsg.indexOf(":");

        if (separatorIndex !== -1) {
            const sender = rawMsg.substring(0, separatorIndex).trim();
            const message = rawMsg.substring(separatorIndex + 1).trim();

            // 내가 보낸 메시지는 submit 시 이미 표시했으므로 무시
            if (sender === CURRENT_USER) {
                return;
            }

            // 상대방 메시지는 좌측 말풍선 + 닉네임 표시
            appendMessage(message, 'other', sender);
        } else {
            // 시스템 알림 등의 일반 텍스트인 경우
            appendMessage(rawMsg, 'bot', '채팅 관리자');
        }
    };

    ws.onerror = function (error) {
        console.error("웹소켓 에러:", error);
    };

    ws.onclose = function () {
        console.warn("웹소켓 연결 종료됨");
    };
}

/**
 * 채팅 화면에 표시할 시간 포맷을 적용하여 반환하는 함수
 * @returns 현재 시간 (포맷 지정)
 */
function getCurrentTime() {
    const now = new Date();
    return now.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit', hour12: true });
}

/********* 이벤트 핸들러 *********/
/**
 * 연결된 사용자가 채팅 전송 시 이벤트 핸들러
 */
chatForm.addEventListener('submit', (e) => {
    e.preventDefault();         // 기존 이벤트 동작 방지 (폼 전송 x)

    // 입력 데이터 추출
    const text = messageInput.value.trim();
    if (!text) return;

    // 화면 처리 (채팅창에 표시 및 입력값 초기화)
    appendMessage(text, 'user');
    messageInput.value = '';
    messageInput.focus();

    // 웹 소켓으로 메시지 전송
    sendMessage(CURRENT_USER + ": " + text);
});