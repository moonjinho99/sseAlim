<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.alim.entity.Member" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>알림 수신</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/js/receive.js"></script>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding-top: 60px; background: #f9f9f9; }
        .header {
            position: fixed; top: 0; left: 0; right: 0;
            display: flex; justify-content: space-between; align-items: center;
            background: #333; color: white; padding: 10px 20px; height: 50px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            z-index: 10;
        }
        .logo { font-size: 20px; font-weight: bold; }
        .user-info { display: flex; align-items: center; gap: 10px; }
        .username { color: #ffffff; }
        #logoutBtn { background: red; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; }

        .alim-container {
            width: 80%;
            margin: auto;
            margin-top: 20px;
            background: white;
            padding: 20px;
            border-radius: 10px;
        }

        .alim-icon {
            font-size: 24px;
            color: #fff;
            margin-right: 10px;
            animation: none;
        }

        .alim-icon.blink {
            animation: blink 1s infinite;
        }

        @keyframes blink {
            0%, 100% { opacity: 1; }
            50% { opacity: 0.4; }
        }

        .toast {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background: #444;
            color: #fff;
            padding: 15px 20px;
            border-radius: 10px;
            display: none;
            z-index: 1000;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
        }

        .read-row { background-color: #f0f0f0 !important; }
    </style>
</head>
<body>

<%
    Member loginMember = (Member) session.getAttribute("loginMember");
%>

<div class="header">
    <div class="logo">📥 알림 수신</div>
    <div class="user-info">
        <span class="alim-icon" id="alimIcon">🔔</span>
        <span class="username"><%= loginMember != null ? loginMember.getMemberName() : "게스트" %>님</span>  
        <input type="hidden" id="memberNum" value=<%=loginMember.getMemberNum() %>/>
        <button id="logoutBtn">로그아웃</button>

    </div>
</div>

<div class="alim-container">
    <h2>알림 목록</h2>
    <table id="alimTable" class="display">
        <thead>
            <tr>
                <th>번호</th>
                <th>종류</th>
                <th>내용</th>
                <th>시간</th>
                <th>읽음</th>
            </tr>
        </thead>
        <tbody>
            <!-- JS에서 알림 목록 채워짐 -->
        </tbody>
    </table>
</div>

<div class="toast" id="toast"></div>


</body>
</html>
