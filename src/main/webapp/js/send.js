$(document).ready(function(){
    $.ajax({
        url:'http://localhost:8888/member/memberList',
        type:'GET',
        contentType: 'application/json',
        success: function(response){
            if(response.message === "success")
            {
                const memberList = response.body;
                const $tbody = $('#memberList tbody');
                $tbody.empty(); 

                memberList.forEach(member => {
                    const row = `
                        <tr>
                            <td>
                                <input type="checkbox" class="member-checkbox" value="${member.memberNum}">
                                <input type="hidden" name="memberNum" id="memberNum" value="${member.memberNum}">
                            </td>
                            <td>${member.memberId}</td>
                            <td>${member.memberName}</td>
                        </tr>
                    `;
                    $tbody.append(row);
                });

                // DataTables 적용
                $('#memberList').DataTable();
            }		
        },
        error: function(error){
            console.error(error);
            alert("서버 에러");
        }
    });
	
	
	$('#sendBtn').on('click', function() {
            const selectedMembers = [];
            $('.member-checkbox:checked').each(function() {
                selectedMembers.push($(this).val());
            });

            const message = $('#message').val();
			const type = $('input[name="alimType"]:checked').val();

            if (selectedMembers.length === 0 || !message) {
                alert("수신자와 알림 내용을 입력하세요.");
                return;
            }

            $.ajax({
                url: '/alim/send',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    receivers: selectedMembers,
                    message: message,
					type: type
                }),
                success: function() {
                    alert("알림이 전송되었습니다.");
                },
                error: function() {
                    alert("알림 전송 실패");
                }
            });
        });
	
	
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
