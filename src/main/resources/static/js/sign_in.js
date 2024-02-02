// 변수 선언
var email = document.getElementById('email');
var password = document.getElementById('password');
var errors = document.querySelectorAll('.error');
var errorEmail = document.getElementById('error-email');
var errorPassword = document.getElementById('error-password');
var errorGlobal = document.getElementById('error-global');
var btnSignIn = document.getElementById('btn-sign-in');
var isValid = true;

// 로그인 버튼 클릭
btnSignIn.addEventListener('click', () => {
  var formData = {
    'email': email.value.trim(),
    'password': password.value.trim()
  };

  // 로그인 처리
  login(formData).then((response) => {
    var results = response.data;
    // 유효성 검사
    isValid = validateLogin(results);
    // 로그인 성공시
    if (isValid) {
      location.replace('/');
    }
  });
});

// 로그인 처리
async function login(formData) {
  var response = axios.post('/login', formData);
  return response;
}

// 유효성 검사
function validateLogin(results) {
  errors.forEach((error) => {
    error.style.display = 'none';
  });

  if (Array.isArray(results)) {
    for (var i = 0; i < results.length; i++) {
      if (results[i].field === 'email') {
        errorEmail.innerText = results[i].defaultMessage;
        errors[0].style.display = 'block';
      }
      if (results[i].field === 'password') {
        errorPassword.innerText = results[i].defaultMessage;
        errors[1].style.display = 'block';
      }
    }
    return false;
  }

  if (results.res === -1) {
    errorGlobal.innerText = results.defaultMessage;
    errorGlobal.style.display = 'block';
    return false;
  }

  return true;
}
