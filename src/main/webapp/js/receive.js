$(document).ready(function(){
   
	const table = $('#alimTable').DataTable();
	const memberNum = parseInt($('#memberNum').val());
	
	
	loadAlim();
	function loadAlim() {
	        $.ajax({
	            url: '/alim/list?memberNum='+memberNum,
	            type: 'GET',
				contentType: 'application/json',
	            success: function(response) {
	                if (response.message === "success") {
	                    table.clear();
	                    response.body.forEach(alim => {
	                        const rowNode = table.row.add([
	                            alim.alimNum,
	                            alim.type,
	                            alim.content,
	                            new Date(alim.sendTime).toLocaleString(),
	                            alim.readChk ? '읽음' : '안읽음'
	                        ]).draw(false).node();

	                        if (alim.readChk) {
	                            $(rowNode).addClass("read-row");
	                        }

	                        $(rowNode).css("cursor", "pointer").click(function() {
	                            if (!alim.readChk) {
	                                $.ajax({
	                                    url: 'alim/read',
	                                    type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({ alimNum: parseInt(alim.alimNum) }),
	                                    success: function() {
											alert(alim.alimNum+"번 알림이 읽음 처리 되었습니다.");
	                                        loadNotifications(); 
                                    },
						            error: function (error) {
						                console.error(error);
						                alert("서버 에러");
						            }
	                                });
	                            }
	                        });
	                    });
	                }
	            }
	        });
	    }
	
				
		// SSE - 실시간 알림 수신
		const sse = new EventSource(`/alim/subscribe/${memberNum}`);

		// 서버에서 이름 없이 보낸 기본 메시지 처리 (현재 사용 안함)
		sse.onmessage = function(event) {
		    console.log("기본 메시지:", event.data);
		};

		// 서버에서 .name("connect")로 보낸 이벤트 처리
		sse.addEventListener("connect", function(event) {
		    console.log("SSE 연결됨:", event.data);
		    // 필요 시 연결 알림 등 처리 가능
		});

		// 서버에서 .name("alim")으로 보낸 알림 이벤트 처리
		sse.addEventListener("alim", function(event) {
		    const data = JSON.parse(event.data);

		    // 알림 도착 시 효과
		    $('#alimIcon').addClass("blink");
		    showToast(data.content);

		    // 테이블 갱신
		    loadAlim();

		    // 일정 시간 후 아이콘 반짝임 제거
		    setTimeout(() => {
		        $('#alimIcon').removeClass("blink");
		    }, 3000);
		});

		function showToast(msg) {
		    const $toast = $('#toast');
		    $toast.text(msg).fadeIn();

		    setTimeout(() => {
		        $toast.fadeOut();
		    }, 4000);
		}

				
	
	$('#logoutBtn').on('click', function () {
		
	    const confirmLogout = confirm("로그아웃 하시겠습니까?");
	    if (confirmLogout) {
	        $.ajax({
	            url: 'http://localhost:8888/member/logout',
	            type: 'GET',
	            contentType: 'application/json',
	            success: function () {
	                alert("로그아웃되었습니다.");
					location.href = "/member/loginPage";
	            },
	            error: function (error) {
	                console.error(error);
	                alert("서버 에러");
	            }
	        });
	    } else {
	        console.log("로그아웃 취소");
	    }
	});

	
	
});
