<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.alim.entity.Member" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>알림 발송</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/js/send.js"></script>
        <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f9f9f9;
            padding-top: 80px;
            padding-bottom: 180px;
            color: #333;
            margin: 0;
        }

        /* 헤더 스타일 */
        .header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            background-color: #ffffff;
            padding: 20px 40px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 1000;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .username {
            font-weight: bold;
        }

        #logoutBtn {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        #logoutBtn:hover {
            background: #c0392b;
        }

        h2 {
            font-size: 24px;
            margin: 20px 40px;
        }

        #memberList_wrapper {
            margin: 0 40px 30px 40px;
        }

        /* 하단 고정 영역 */
        .bottom-fixed {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            background: white;
            border-top: 1px solid #ccc;
            box-shadow: 0 -2px 6px rgba(0, 0, 0, 0.1);
            padding: 20px 40px;
            box-sizing: border-box;
        }

        .bottom-fixed textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            resize: none;
            font-size: 14px;
            box-sizing: border-box;
        }

        #sendBtn {
            margin-top: 10px;
            padding: 12px 24px;
            background: #27ae60;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            float: right;
        }

        #sendBtn:hover {
            background: #1e8449;
        }
    </style>
</head>
<%
    Member loginMember = (Member) session.getAttribute("loginMember");
%>
<body>
    <!-- 상단 헤더 영역 -->
    <div class="header">
        <div class="logo">📢 알림 관리자</div>
        <div class="user-info" style="margin-right: 80px;">
            <span class="username"><%= loginMember != null ? loginMember.getMemberName() : "게스트" %>님</span>          
            <button id="logoutBtn">로그아웃</button>
         
        </div>
    </div>

    <!-- 본문 -->
    <h2>알림 발송</h2>

    <table id="memberList" class="display">
        <thead>
            <tr>
                <th>선택</th>
                <th>아이디</th>
                <th>이름</th>
            </tr>
        </thead>
        <tbody>
            <!-- JS에서 채워짐 -->
        </tbody>
    </table>

    <!-- 하단 고정 -->
    <div class="bottom-fixed">
    <label style="font-weight: bold;">알림 유형:</label><br>
    <label><input type="radio" name="alimType" value="NORMAL" checked> 일반</label>
    <label style="margin-left: 20px;"><input type="radio" name="alimType" value="EMERGENCY"> 긴급</label>

    <textarea id="message" placeholder="알림 내용을 입력하세요"></textarea>
    <button id="sendBtn">알림 발송</button>
</div>

  
</body>
</html>
