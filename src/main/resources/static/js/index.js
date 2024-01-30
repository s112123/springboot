// 리뷰 검색
const search = document.querySelector('#search');
const sortOption = document.querySelector('#sortOption');
const btnSearch = document.querySelector('#btn-search');

// 검색어 입력 input 에서 Enter 키 처리
search.addEventListener('keypress', () => {
  if (event.keyCode === 13) {
    if (search !== null && search.value !== '') {
      location.href = '?sort_option=' + sortOption.value + '&search=' + search.value.trim();
    }
  }
});

// 검색어 입력 후, 검색 버튼 클릭 처리
btnSearch.addEventListener('click', () => {
  if (search !== null && search.value !== '') {
    location.href = '?sort_option=' + sortOption.value + '&search=' + search.value.trim();
  }
});

// 리뷰 정렬
const orderItems = document.querySelectorAll('.order-item');
orderItems.forEach((orderItem, index) => {
  orderItem.addEventListener('click', () => {
    // 정렬 처리
    orderReviews(index, search);
  });
});

// 정렬 처리
function orderReviews(sortOption, search) {
  console.log(search.value.trim().length);
  let searchQuery = (search.value.trim().length > 0) ? ('&search=' + search.value.trim()) : '';

  // sort_option: 전체 (0), 최근 순 (1), 평점 순 (2)
  switch (sortOption) {
    case 0:
      location.href = '?sort_option=0';
      break;
    case 1:
      location.href = '?sort_option=1' + searchQuery;
      break;
    case 2:
      location.href = '?sort_option=2' + searchQuery;
      break;
  }
}

// 리뷰 등록 화면으로 이동
const btnReview = document.getElementById('btn-review');
btnReview.addEventListener('click', () => {
  location.href = '/review/add';
});

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

// 타이틀 글자 수 줄이기
const titles = document.querySelectorAll('.review-summary-title');
titles.forEach(title => {
  if (title.innerText.length > 14) {
    title.textContent = title.textContent.substring(0, 14) + '...';
  }
});

// 내용 글자 수 줄이기
const contents = document.querySelectorAll('.review-summary-content');
contents.forEach(content => {
  const reviewContent = removeHTMLTag(content.textContent);
  if (reviewContent.length > 32) {
    content.textContent = reviewContent.substring(0, 32) + '...';
  } else {
    content.textContent = reviewContent;
  }
});

function removeHTMLTag(content) {
  let result = content.replaceAll('</p><p>', ' ');
  result = result.replace(/(<([^>]+)>)/gi, '');
  result = result.replace(/\s\s+/ig, '');
  result = result.replace(/&nbsp;/ig, '');
  result = result.replace(/ +/ig, ' ');
  result = result.replaceAll('&gt;', '>');
  result = result.replaceAll('&lt;', '<');
  result = result.replaceAll('&lt;', '<');
  result = result.replaceAll('&quot;', "");
  result = result.replaceAll('&amp;', '&');
  return result;
}




