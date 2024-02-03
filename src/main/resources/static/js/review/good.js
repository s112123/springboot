// 변수 선언
var storeGood = document.getElementById('store-heart');
var good = document.getElementById('good');
var notGood = document.getElementById('not-good');

// 찜 버튼 상태
var isGood = storeGood.getAttribute('data-is-good');
if (isGood === 'true') {
  good.style.display = 'block';
  notGood.style.display = 'none';
}

// 찜하기
notGood.addEventListener('click', () => {
  var reviewId = good.getAttribute('data-review-id');

  addGood(reviewId).then(response => {
    var responseData = response.data;
    console.log(responseData);
    if (responseData.message > 0) {
      good.style.display = 'block';
      notGood.style.display = 'none';
    } else {
      // 로그인이 안되어 있는 경우
      alert('찜하기는 로그인이 필요합니다');
    }
  });
});

// 찜하기
async function addGood(reviewId) {
  var response = await axios.get(`/goods/${reviewId}`);
  return response;
}

// 찜취소
good.addEventListener('click', () => {
  var reviewId = notGood.getAttribute('data-review-id');

  cancelGood(reviewId).then(response => {
    var responseData = response.data;

    if (responseData.message > 0) {
      notGood.style.display = 'block';
      good.style.display = 'none';
    } else {
      // 로그인이 안되어 있는 경우
      alert('찜하기는 로그인이 필요합니다');
    }
  });
});

// 찜취소
async function cancelGood(reviewId) {
  var response = await axios.get(`/goods/cancel/${reviewId}`);
  return response;
}