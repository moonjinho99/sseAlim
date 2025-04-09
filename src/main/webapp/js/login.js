$(document).ready(function(){
		
	$('#loginBtn').on('click', function () {
		const bodyData = {
			'member_id':$('#member_id').val(),
			'member_pw':$('#member_pw').val()			
		};
		
		$.ajax({
			url:'http://localhost:8888/member/login',
			type:'POST',
			data: JSON.stringify(bodyData),
			contentType: 'application/json',
			success: function(response){
				console.log(JSON.stringify(response));
				
				if(response.message == "success")
					location.href="/";
				else if(response.message == "fail")
				{
					alert("아이디 또는 패스워드가 일치하지 않습니다.");
				}
					
			},
			error: function(error){
				console.error(error);
				alert("서버 에러");
			}
		});

	});
});	