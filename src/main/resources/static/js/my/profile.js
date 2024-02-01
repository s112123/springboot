// 변수 선언
var email = document.getElementById('email');
var nickName = document.getElementById('nickName');
var password = document.getElementById('password');
var errors = document.querySelectorAll('.error');
var btnUpdate = document.getElementById('update-my-info');
var btnChangePassword = document.getElementById('btn-change-pass');
var updatedMessage = document.getElementById('updated-message');
var profileImage = document.getElementById('profile-image');
var file = document.getElementById('profile-file');
var fileName = document.getElementById('fileName');
var imageUrl = document.getElementById('imageUrl');
var isChangePassword = false;
var isValid = true;

// 입력 폼
var form = {
  'email': email,
  'nickName': nickName,
  'password': password,
  'imageUrl': imageUrl,
  'fileName': fileName
};

// 회원 정보 수정
btnUpdate.addEventListener('click', () => {
  // 유효성 검사
  errors.forEach(error => {
    error.style.display = 'none';
  });

  if (!validateForm(form)) {
    return;
  }

  // 수정하기
  // if (confirm("회원정보를 변경하시겠습니까?")) {
  editMember(form).then(response => {
    result = response.data;
    if (result === 'updated') {
      updatedMessage.style.display = 'block';
      setTimeout(() => {
        updatedMessage.style.display = 'none';
      }, 3000);
      password.value = '';
    }
  });
  //}
});

// 프로필 이미지 파일 변경
file.addEventListener('change', (event) => {
  //fileName.value = file.value.replace(/^C:\\fakepath\\/i, '');

  // 파일 담기
  var formData = new FormData();
  formData.append('profile-file', event.target.files[0]);

  // 파일 저장
  saveProfileImage(formData).then(response => {
    // 응답받은 이미지 경로
    var $imageUrl = response.data;
    // 프로필 이미지에 변경된 이미지 표시
    profileImage.src = $imageUrl;
    // 이미지 경로
    imageUrl.value = $imageUrl;
    // 이미지 파일 이름
    fileName.value = $imageUrl;
    fileName.value = fileName.value.substring(fileName.value.lastIndexOf('/') + 1);
  });
});

// 비밀번호 입력 버튼 클릭
btnChangePassword.addEventListener('click', () => {
  // 비밀번호 입력 창 활성화
  password.style.border = '1px solid rgb(210, 40, 40)';
  password.readOnly = false;
  password.focus();

  // 비밀번호 입력 창 비활성화
  password.addEventListener('blur', () => {
    password.style.border = '1px solid rgb(180, 180, 180)';
    password.readOnly = true;
  });
});

// 유효성 검사
function validateForm(form) {
  isValid = true;
  if (form.nickName.value.trim().length === 0) {
    // 닉네임 입력 여부
    form.nickName.nextElementSibling.style.display = 'block';
    isValid = false;
  }
  return isValid;
}

// 수정하기
async function editMember(form) {
  var editForm = {
    'nickName': form.nickName.value.trim(),
    'password': form.password.value.trim(),
    'imageUrl': form.imageUrl.value.trim(),
    'fileName': form.fileName.value.trim(),
  };
  var response = await axios.patch(`/members/${form.email.value}`, editForm);
  return response;
}

// 이미지 파일 저장
async function saveProfileImage(formData) {
  var headers = {
    'Content-Type': 'multipart/form-data'
  };
  var response = await axios.post('/members/image/save', formData, {headers: headers});
  return response;
}