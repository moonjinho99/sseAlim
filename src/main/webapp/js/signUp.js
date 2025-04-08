$(document).ready(function(){
		
	$('#sendBtn').on('click', function () {
		const bodyData = {
			'member_id':$('#member_id').val(),
			'member_name':$('#member_name').val(),
			'member_pw':$('#member_pw').val(),
			'member_role': $('input[name="member_role"]:checked').val()			
		};
		
		$.ajax({
			url:'http://localhost:8888/member/signUp',
			type:'POST',
			data: JSON.stringify(bodyData),
			contentType: 'application/json',
			success: function(response){
				console.log("success");
				location.href="/member/loginPage";
			},
			error: function(error){
				console.error(error);
			}
		});

	});
});	
	
	