// 변수 선언
var form = document.getElementById('review-edit-form');
var reviewId = document.getElementById('review_id');
var btnEdit = document.getElementById('edit-review');
var btnRemove = document.getElementById('delete-review');
var btnCancel = document.getElementById('cancel-review');

// 삭제 버튼 클릭
btnRemove.addEventListener('click', () => {
  if (confirm('리뷰를 삭제하시겠습니까?')) {
    location.replace('/review/remove?review_id=' + reviewId.value);
  }
});

// 취소 버튼 클릭
btnCancel.addEventListener('click', () => {
  history.back();
});

// 변경 버튼 클릭
btnEdit.addEventListener('click', (e) => {
  e.preventDefault();

  let isValid = true;
  const errors = document.querySelectorAll('.error');
  let storeName = document.getElementById('storeName');
  let storeAddress = document.getElementById('storeAddress');
  let ckEditor = document.getElementsByClassName('ck-content')[0];
  let imgTag = ckEditor.getElementsByTagName('img')[0];
  let thumbnail = document.getElementById('thumbnailUrl');
  let thumbnailFileName = document.getElementById('thumbnailFileName');
  let star = document.getElementById('star');

  // 에러 표시 모두 닫기
  errors.forEach(error => {
    error.style.display = 'none';
  });

  // 입력사항 유효성 검사
  if (storeName.value.trim().length === 0) {
    // 가게 이름 입력 여부
    storeName.nextElementSibling.style.display = 'block';
    isValid = false;
  } else if (storeAddress.value.trim().length === 0) {
    // 가게 주소 입력 여부
    storeAddress.nextElementSibling.style.display = 'block';
    isValid = false;
  } else if (title.value.trim().length === 0) {
    // 제목 입력 여부
    title.nextElementSibling.style.display = 'block';
    isValid = false;
  } else if (imgTag === undefined) {
    console.log('imgTag !== undefined');
    // 글 내용에 이미지 삽입 여부
    document.getElementById('editor-error').style.display = 'block';
    isValid = false;
  } else if (imgTag !== undefined) {
    // 글 내용 입력 여부
    let pTag = ckEditor.getElementsByTagName('p');
    if (pTag.length === 0) {
      document.getElementById('editor-error').style.display = 'block';
      isValid = false;
    } else {
      let textLength = 0;

      for (let i = 0; i < pTag.length; i++) {
        textLength += pTag[i].textContent.trim().length;
      }

      if (textLength === 0) {
        document.getElementById('editor-error').style.display = 'block';
        isValid = false;
      } else if (star.value.trim().length === 0) {
        // 리뷰어 평점 선택 여부
        star.parentNode.nextElementSibling.style.display = 'block';
        isValid = false;
      }
    }
  }

  // 리뷰 목록 페이지에서 보여줄 이미지 경로 추출
  getThumbnailUrl(imgTag, thumbnail, thumbnailFileName);

  if (isValid) {
    form.action = '/review/edit?review_id=' + reviewId.value;
    form.method = 'post';
    form.submit();
  }
});

// 리뷰 목록 페이지에서 보여줄 이미지 경로 추출
function getThumbnailUrl(imgTag, thumbnail, thumbnailFileName) {
   if (imgTag !== null && imgTag !== undefined) {
     // 이미지 경로
     thumbnail.value = imgTag.src;
     thumbnail.value = thumbnail.value.replace(location.protocol + '//' + location.host, '');
     // 이미지 파일 이름
     thumbnailFileName.value = imgTag.src;
     thumbnailFileName.value = thumbnailFileName.value.substring(thumbnailFileName.value.lastIndexOf('/') + 1);
   } else {
     thumbnail.value = '';
     thumbnailFileName.value = '';
   }
}

// 평점 선택
const input = document.getElementById('star');
const stars = [...document.querySelector('#store-star').children];
stars.forEach((star) => {
  star.addEventListener('click', () => {
    let score = star.getAttribute('data-star');
    input.value = score;
    changeStarColor(stars, score);
  });
});

// 평점 별표 색상 변경
function changeStarColor(stars, score) {
  stars.forEach((star, index) => {
    if (index < score) {
      star.style.color = 'rgb(249, 199, 53)';
    } else {
      star.style.color = 'rgb(200, 200, 200)';
    }
  });
}

// 등록된 평점 색상 처리
const reviewerStarList = document.getElementById('store-star');
const reviewerStarItems = [...reviewerStarList.children];
const reviewerStarScore = reviewerStarList.getAttribute('data-reviewer-star');
reviewerStarItems.forEach((reviewerStarItem, index) => {
    if (index < reviewerStarScore) {
      reviewerStarItem.style.color = 'rgb(249, 199, 53)';
    }
});

