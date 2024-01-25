// 채팅 대상자 클릭시, 항목 색상 변경
const chatItems = document.querySelectorAll('.chat-item');
chatItems.forEach((chatItem, index) => {
  chatItem.addEventListener('click', () => {
    chatActive(index);
  });
});

function chatActive(index) {
  chatItems.forEach((chatItem) => {
    chatItem.classList.remove('chat-active');
  });
  chatItems[index].classList.add('chat-active');
}