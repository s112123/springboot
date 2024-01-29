// 평점 색상 처리
const reviewerStarList = document.getElementById('reviewer-star');
const reviewerStarItems = [...reviewerStarList.children];
const reviewerStarScore = reviewerStarList.getAttribute('data-reviewer-star');
reviewerStarItems.forEach((reviewerStarItem, index) => {
    if (index < reviewerStarScore) {
      reviewerStarItem.style.color = 'rgb(249, 199, 53)';
    }
});