// 리뷰 정렬
const orderItems = document.querySelectorAll('.order-item');
orderItems.forEach((orderItem, index) => {
  orderItem.addEventListener('click', () => {
    // 정렬 처리
    orderReviews(index);
  });
});

// 정렬 처리
function orderReviews(sortOption) {
  // sort_option: 최근 순 (0), 평점 순 (1)
    switch (sortOption) {
      case 0:
        location.href = '?sort_option=0'; break;
      case 1:
        location.href = '?sort_option=1'; break;
    }
}

// 리뷰 목록
const reviews = document.querySelectorAll('.review-wrap');
reviews.forEach(review => {
  // 리뷰 목록에서 평점 색상 처리
  // <ul>에서 점수 확인 → <li> 배열 번호와 점수 비교 후 색상 변경
  let starList = review.getElementsByTagName('ul')[0];
  let starItems = [...starList.children];
  let starScore = starList.getAttribute('data-star');
  starItems.forEach((starItem, index) => {
    if (index < starScore) {
      starItem.style.color = 'rgb(249, 199, 53)';
    }
  });

  // 리뷰 조회 페이지로 이동
  review.addEventListener('click', () => {
    let reviewId = review.getAttribute('data-review-num');
    location.href = "/review/view?review_id=" + reviewId;
  });
});


// 리뷰 등록 화면으로 이동
const btnReview = document.getElementById('btn-review');
btnReview.addEventListener('click', () => {
  location.href = '/review/add';
});



