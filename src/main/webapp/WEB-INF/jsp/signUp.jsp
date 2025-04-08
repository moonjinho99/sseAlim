<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/js/signUp.js"></script>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 300px; text-align: center; }
        input { width: 90%; padding: 10px; margin: 5px 0; border: 1px solid #ccc; border-radius: 5px; }
        button { width: 100%; padding: 10px; background: green; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button:hover { background: darkgreen; }
    </style>
</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <input type="text" name="member_name" id="member_name" placeholder="이름" required>
    <input type="text" name="member_id" id="member_id" placeholder="아이디" required>
    <input type="password" name="member_pw" id="member_pw" placeholder="비밀번호" required>

    <div style="text-align: left; margin: 10px 0;">
        <label><input type="radio" name="member_role" value="MEMBER" checked style="width: 50px;"> 일반회원</label><br>
        <label><input type="radio" name="member_role" value="ADMIN" style="width: 50px;"> 관리자</label>
    </div>

    <button id="sendBtn">회원가입</button>
    <p>이미 회원이신가요? <a href="login">로그인</a></p>
</div>

</body>
</html>
