<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
	
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 300px; text-align: center; }
        input { width: 90%; padding: 10px; margin: 5px 0; border: 1px solid #ccc; border-radius: 5px; }
        button { width: 100%; padding: 10px; background: blue; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button:hover { background: darkblue; }
    </style>
    
</head>
<body>
    <div class="container">
        <h2>로그인</h2>
            <input type="text" name="username" id="member_id" placeholder="아이디" required>
            <input type="password" name="password" id="member_pw" placeholder="비밀번호" required>
            <button id="loginBtn">로그인</button>
        <p>회원이 아니신가요? <a href="signUpPage">회원가입</a></p>
    </div>
</body>
</html>
