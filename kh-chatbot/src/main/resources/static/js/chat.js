document.addEventListener('DOMContentLoaded', () => {
    const chatInput = document.getElementById('chat-input');
    const sendBtn = document.getElementById('send-btn');
    const chatMessages = document.getElementById('chat-messages');

    if (!chatInput || !sendBtn || !chatMessages) return;

    async function sendMessage() {
        const text = chatInput.value.trim();
        if (text) {
            const now = new Date();
            const timeStr = now.getHours() >= 12 ? '오후' : '오전';
            const hour = now.getHours() % 12 || 12;
            const minute = String(now.getMinutes()).padStart(2, '0');
            const timestamp = `${timeStr} ${hour}:${minute}`;

            // 사용자 메시지 추가
            appendMessage('sent', '나', timestamp, text);
            chatInput.value = '';

            try {
                // 서버에 메시지 전송
                const response = await fetch('/chat/send', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        role: 'user',
                        content: text
                    })
                });

                if (response.ok) {
                    const data = await response.json();
                    appendMessage('received', '🤖 K-Bot', timestamp, data.content);
                } else {
                    throw new Error('서버 응답 오류');
                }
            } catch (error) {
                console.error('Error:', error);
                appendMessage('received', '시스템 알림', timestamp, '서버와의 통신 중 오류가 발생했습니다.');
            }
        }
    }

    function appendMessage(type, sender, time, text) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${type}`;
        messageDiv.innerHTML = `
            <span class="message-info">${sender} • ${time}</span>
            ${text}
        `;
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    sendBtn.addEventListener('click', sendMessage);
    chatInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') sendMessage();
    });

    // 초기 스크롤
    chatMessages.scrollTop = chatMessages.scrollHeight;
});
