<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Main - SSE 테스트</title>
    <script src="/js/sse.js"></script>
    <style>
        #notificationIcon {
            position: relative;
            cursor: pointer;
        }
        #notificationIcon .dot {
            width: 10px;
            height: 10px;
            background: red;
            border-radius: 50%;
            position: absolute;
            top: 0;
            right: 0;
            display: none;
        }
        #notificationList {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 10px;
            top: 25px;
            right: 0;
            width: 250px;
        }
    </style>
</head>
<body>
    <h2>실시간 알림 테스트 (SSE)</h2>

    <!-- 알림 아이콘 및 알림 리스트 -->
    <div id="notificationIcon" onclick="toggleNotifications()">
        🔔
        <div class="dot" id="alertDot"></div>
        <div id="notificationList"></div>
    </div>

    <br/><br/>

    <!-- 알림 전송 영역 -->
    <label>수신자 memberNum:</label>
    <input type="text" id="memberNum" placeholder="예: 1"><br/>

    <label>알림 메시지:</label>
    <input type="text" id="message" placeholder="내용 입력"><br/>

    <button onclick="sendNotification()">알림 전송</button>

    <script>
        let eventSource;

        // SSE 구독
        window.onload = function () {
            eventSource = new EventSource('/sse/connect');
            eventSource.onmessage = function(event) {
                const data = JSON.parse(event.data);
                const notificationList = document.getElementById("notificationList");

                // 빨간 점 표시
                document.getElementById("alertDot").style.display = "block";

                // 알림 추가
                const newDiv = document.createElement("div");
                newDiv.textContent = data.message;
                notificationList.appendChild(newDiv);
            };
        }

        // 알림 리스트 토글
        function toggleNotifications() {
            const list = document.getElementById("notificationList");
            const dot = document.getElementById("alertDot");
            list.style.display = list.style.display === "none" ? "block" : "none";
            dot.style.display = "none";
        }

        // 알림 전송 (관리자 테스트용)
        function sendNotification() {
            const memberNum = document.getElementById("memberNum").value;
            const message = document.getElementById("message").value;

            fetch("/sse/send", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ memberNum, message })
            }).then(res => res.text()).then(console.log);
        }
    </script>
</body>
</html>
