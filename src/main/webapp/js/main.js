// SSE 수신 코드
window.onload = function () {
    const source = new EventSource('/sse/connect');

    source.onmessage = function(event) {
        const data = JSON.parse(event.data);
        
        // 브라우저 알림 표시
        if (Notification.permission === "granted") {
            new Notification("📬 새 알림", {
                body: data.message,
                icon: "/images/notification_icon.png"
            });
        } else if (Notification.permission !== "denied") {
            Notification.requestPermission().then(permission => {
                if (permission === "granted") {
                    new Notification("📬 새 알림", {
                        body: data.message,
                        icon: "/images/notification_icon.png"
                    });
                }
            });
        }
    };
};
