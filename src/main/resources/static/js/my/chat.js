// 변수 선언
var chatItems = document.querySelectorAll('.chat-item');
var message = document.querySelector('#message');
var btnSend = document.querySelector('#send-message');
var conversationView = document.querySelector('#conversation-view');
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

    // 채팅 대상자 아이디(email) 가져오기
    receiverEmail = chatItem.querySelector('input').value;

    // 웹 소켓 연결
    socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      // 메세지 전송
      stompClient.subscribe(`/user/private/message`, (response) => {
        //console.log(JSON.parse(response.body));
        var message = JSON.parse(response.body);
        var html = '';

        console.log(message);
        console.log(message.receiverEmail);
        console.log(receiverEmail);
        console.log(senderEmail);
        console.log(message.message);

        // 내가 보낸 메세지
        if (message.receiverEmail === receiverEmail) {
          html += `
              <div class="sender-message-box">
                <p class="sender-message">${message.message}</p>
              </div>
          `;
        }

        // 상대방이 보낸 메세지
        if (message.senderEmail === receiverEmail) {
          html += `
              <div class="receiver-message-box">
                <p class="receiver-message">${message.message}</p>
              </div>
          `;
        }

        conversationView.innerHTML += html;
        conversationView.scrollTop = conversationView.scrollHeight;
      });
    });

    // 채팅 기록 불러오기
    getChatMessages(receiverEmail).then((response) => {
      var messages = response.data;
      var html = '';
      for (var i = 0; i < messages.length; i++) {
        if (messages[i].receiverEmail === receiverEmail) {
          html += `
              <div class="sender-message-box">
                <p class="sender-message">${messages[i].message}</p>
              </div>
          `;
        } else {
          html += `
              <div class="receiver-message-box">
                <p class="receiver-message">${messages[i].message}</p>
              </div>
          `;
        }
      }
      conversationView.innerHTML = html;
      conversationView.scrollTop = conversationView.scrollHeight;
    });
  });
});

// 채팅 대화 불러오기
async function getChatMessages(receiverEmail) {
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

// 보내기 버튼 눌렀을 때, 방생성
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

  // 채팅 방 생성
  makeChatRoom(receiverEmail);

  // 메세지 내용
  var chatMessage = {
    'senderEmail': 'admin@test.com',
    'receiverEmail': receiverEmail,
    'message': message.value.trim()
  };

  // 메세지 발송
  sendChatMessage(receiverEmail, chatMessage);
  message.value = '';
});

// 방 생성
async function makeChatRoom(receiverEmail) {
  var response = await axios.get(`/chats/make_room/${receiverEmail}`);
  return response;
}

// 메시지 발송
async function sendChatMessage(receiverEmail, chatMessage) {
/*  var response = await axios.post(`/chats/${receiverEmail}`, chatMessage);
  return response;*/

  stompClient.send('/app/chat_service', {}, JSON.stringify(chatMessage));
}

