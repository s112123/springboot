// 변수 선언
var email = document.getElementById('email');
var password = document.getElementById('password');
var rePassword = document.getElementById('re-password');
var errors = document.querySelectorAll('.error');
var errorEmail = document.getElementById('error-email');
var errorPassword = document.getElementById('error-password');
var errorRePassword = document.getElementById('error-re-password');
var errorDuplicated = document.getElementById('error-duplicated');
var errorVerifyCode = document.getElementById('error-verify-code');
var btnVerifyCode = document.getElementById('btn-verify-code');
var btnSignUp = document.getElementById('btn-sign-up');
var isValid = true;

// 1. 이메일 입력여부 및 이메일 중복 확인
// 2. 이메일 인증 코드 확인
// 3. 비밀번호 입력여부 확인
// 4. 비밀번호 확인 입력여부 및 일치여부 확인

var formData = {
  'email': email.value.trim(),
  'password': password.value.trim()
};

email.addEventListener("change", () => {

});

// 이메일 인증 버튼 클릭
btnVerifyCode.addEventListener('click', () => {
  // readonly 풀기
  errorVerifyCode.innerText = '이메일로 발송된 인증코드를 입력하세요';
  errorVerifyCode.style.display = 'block';
});

// 회원가입 버튼 클릭
btnSignUp.addEventListener('click', () => {
  // 회원가입 처리
  addMember(formData).then((response) => {
    var results = response.data;
    // 유효성 검사
    isValid = validateSignUp(results);
    // 회원가입 성공시
    if (isValid) {
      location.replace('/login');
    }
  });
});

// 회원 등록
async function addMember(formData) {
  var response = axios.post('/member/add', formData);
  return response;
}

// 유효성 검사
function validateSignUp(results) {
  errors.forEach((error) => {
    error.style.display = 'none';
  });

  // 중복된 이메일 여부
  if (results.res === -1) {
    errorDuplicated.innerText = results.defaultMessage;
    errorDuplicated.style.display = 'block';
    return false;
  }

  // 이메일, 비밀번호 입력 여부 및 이메일 형식 확인
  if (Array.isArray(results)) {
    for (var i = 0; i < results.length; i++) {
      if (results[i].field === 'email') {
        errorEmail.innerText = results[i].defaultMessage;
        errors[0].style.display = 'block';
      }
      if (results[i].field === 'password') {
        errorPassword.innerText = results[i].defaultMessage;
        errors[2].style.display = 'block';
      }
    }
    return false;
  }

  // 비밀번호 확인 여부
  if (rePassword.value.trim().length === 0) {
    errorRePassword.innerText = "비밀번호를 확인해주세요";
    errorRePassword.style.display = 'block';
    return false;
  }

  // 비밀번호 확인 일치 여부
  if (password.value.trim() !== rePassword.value.trim()) {
    errorRePassword.innerText = "비밀번호가 일치하지 않습니다";
    errorRePassword.style.display = 'block';
    return false;
  }

  return true;
}
