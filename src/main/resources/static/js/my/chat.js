// 변수 선언
var chatItems = document.querySelectorAll('.chat-item');
var message = document.querySelector('#message');
var btnSend = document.querySelector('#send-message');
var receiverEmail = '';
var isClicked = false;
var socket = null;
var stompClient = null;

// 채팅 대상자 클릭시
chatItems.forEach((chatItem, index) => {
  chatItem.addEventListener('click', () => {
    isClicked = true;
    // 채팅 대상자 색상 변경
    chatActive(index);

    // 채팅 방 생성
    receiverEmail = chatItem.querySelector('input').value;
    makeChatRoom(receiverEmail);

    // 웹소켓 연결
    socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      // 메세지 전송
      stompClient.subscribe('/public/message', (response) => {
        console.log(JSON.parse(response.body));
      });
    });
  });
});

// 방 생성
async function makeChatRoom(receiverEmail) {
  var response = await axios.get(`/chats/${receiverEmail}`);
  return response;
}

// 채팅 대상자 색상 변경
function chatActive(index) {
  chatItems.forEach((chatItem) => {
    chatItem.classList.remove('chat-active');
  });
  chatItems[index].classList.add('chat-active');
}

// 메세지 보내기 버튼 클릭
btnSend.addEventListener('click', () => {
  // 유효성 검사: 채팅 상대 선택 여부
  if (!isClicked) {
    alert("채팅상대를 선택하세요");
    return;
  }

  // 유효성 검사: 메시지 입력 여부
  if (message.value.trim().length === 0) {
    alert("메세지를 입력하세요");
    return;
  }

  // 메세지 내용
  var chatMessage = {
    'message': message.value.trim()
  };

  // 메세지 발송
  sendChatMessage(receiverEmail, chatMessage).then((response) => {
    console.log(response.data);
  });
});

// 메시지 발송
async function sendChatMessage(receiverEmail, chatMessage) {
  var response = await axios.post(`/chats/${receiverEmail}`, chatMessage);
  return response;
}

