// 항목 클릭시, 항목 색상 변경
const items = document.querySelectorAll('.item');
items.forEach((item, index) => {
  item.addEventListener('click', () => {
    active(index);
  });
});

function active(index) {
  items.forEach((item) => {
    item.classList.remove('active');
  });
  items[index].classList.add('active');
}