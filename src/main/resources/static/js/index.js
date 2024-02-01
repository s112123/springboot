// 변수 선언
var middle = document.getElementById('reviews-middle');
var orderItems = document.querySelectorAll('.order-item');
var btnReview = document.getElementById('btn-review');

// HTML 로드
document.addEventListener('DOMContentLoaded', () => {
  // 리뷰 목록 반환
  getReviews(0).then(response => {
    console.log(response.data);

    // 리뷰 목록 반환
    var reviews = getReviewsHTML(response.data.reviews);
    middle.innerHTML = reviews;
  });
});

// 리뷰 정렬
orderItems.forEach((orderItem, index) => {
  defaultSortButtonColor();

  // 정렬 버튼 클릭시 동작
  orderItem.addEventListener('click', () => {
    // 색상 변경
    defaultSortButtonColor();
    orderItem.classList.replace('sort-deactive', 'sort-active');

    // 정렬 옵션에 따른 리뷰 목록 반환
    getReviews(index).then(response => {
      var reviews = getReviewsHTML(response.data.reviews);
      middle.innerHTML = reviews;
    });
  });
});
orderItems[0].classList.add('sort-active');

// 정렬 버튼 기본 색상
function defaultSortButtonColor() {
  orderItems.forEach((orderItem) => {
    orderItem.classList.add('sort-deactive');
  });
}

// 리뷰 등록 화면으로 이동
btnReview.addEventListener('click', () => {
  location.href = '/review/add';
});

// 리뷰 목록
async function getReviews(sortOption) {
  var response = await axios.get(`/reviews?sort_option=${sortOption}`);
  return response;
}

// 리뷰 DOM 반환
function getReviewsHTML(reviews) {
  var html = ``;

  if (reviews.length === 0) {
    console.log("no-data");
  } else {
    for (var review of reviews) {
      html += `<div class="review-wrap" onclick="viewReview(${review.reviewId})">`;
      html += `  <div class="review-box">`;
      html += `    <div class="review-image">`;
      html += `      <img src="${review.thumbnailUrl}" />`;
      html += `    </div>`;
      html += `    <div class="review-summary">`;
      html += `      <div class="review-summary-star">`;
      html += `        <ul class="store-star">`;
      for (var i = 0; i < 5; i++) {
        if (i < review.star) {
          html += `      <li class="star-active"><i class="fa-solid fa-star"></i></li>`;
        } else {
          html += `      <li><i class="fa-solid fa-star"></i></li>`;
        }
      }
      html += `        </ul>`;
      html += `      </div>`;
      html += `      <div class="review-summary-title">${shortTitle(review.title)}</div>`;
      html += `      <div class="review-summary-content">${shortContent(removeHTMLTag(review.content))}</div>`;
      html += `    </div>`;
      html += `  </div>`;
      html += `</div>`;
    }
  }

  return html;
}

// 조회 페이지 이동
function viewReview(reviewId) {
  location.href = "/review/view?review_id=" + reviewId;
}

// HTML 태그 제거
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

// 타이틀 글자 수 줄임
function shortTitle(title) {
  var result = title;
  if (title.length > 14) {
    result = title.substring(0, 14) + '...';
  }
  return result;
}

// 글 내용 글자 수 줄임
function shortContent(content) {
  var result = content;
  if (content.length > 32) {
    result = content.substring(0, 32) + '...';
  }
  return result;
}





///////////////////////////////////////////////////////////////////////////
// 리뷰 검색
const search = document.querySelector('#search');
const btnSearch = document.querySelector('#btn-search');

// 검색어 입력 input 에서 Enter 키 처리
search.addEventListener('keypress', () => {
  if (event.keyCode === 13) {
    if (search !== null && search.value !== '') {
      //location.href = '?sort_option=' + sortOption.value + '&search=' + search.value.trim();
    }
  }
});

// 검색어 입력 후, 검색 버튼 클릭 처리
btnSearch.addEventListener('click', () => {
  if (search !== null && search.value !== '') {
    //location.href = '?sort_option=' + sortOption.value + '&search=' + search.value.trim();
  }
});

// 정렬 처리
/*function orderReviews(sortOption, search) {
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
}*/









