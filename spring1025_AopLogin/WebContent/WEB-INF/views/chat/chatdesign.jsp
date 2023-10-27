<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%-- chatdesign.jsp --%>
<p>
	<span class="error-message"></span>
</p>

<p>
	login : <input type="text" id="username" name="username"
		class="login-package">
	<button id="loginBtn" class="login-package">login</button>
</p>

<p>
	<input id="textMessage" type="text" 
		class="chat-package">
	<button id="sendBtn"  class="chat-package">Send</button>
</p>

<p>
	<textarea id="messageTextArea" rows="10" cols="50" ></textarea>
</p>
<script>
	$(function(){
// 		console.log("test");
// 		WebSocket 변수
		let webSocket = null;
		// connect() 함수를 사용해서 서버로 접속
		// ws://localhost/spring1025_26_27AopLoginSocket/chat
		function connect(){
			// 1. 소켓을 생성하고
			webSocket = new WebSocket ("ws://localhost:8089/chat");
			// 2. 웹 소켓이 open
			webSocket.onopen = function(message){
				console.log("로그인 했냐" + message);
			}
			webSocket.onclose = function(message){
				console.log("닫았냐" + message);
			}
			webSocket.onerror = function(message){
				console.log("에러냐" + message);
			}
	        // 서버로 부터 메시지가 오면 실행되는 이벤트
	        webSocket.onmessage = function(message) {
	          console.log("Me:"+$("#username").val());   
	             // 채팅 내용 박스 객체 취득
	          let messageTextArea = document.getElementById("messageTextArea");
	          var arr = message.data.split(":");
	          if(arr[0] == $("#username").val()){
	             console.log("같은 사람 "+$("#username").val()+"Message:"+message.data);
	           // 데이터 작성
	              messageTextArea.value += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+message.data;
	          }else{
	             console.log("다른 사람"+arr[0]+"Message:"+message.data);   
	           // 데이터 작성
	              messageTextArea.value += message.data;
	          }
	        };
			
		}
		$('#loginBtn').click(function(){
			// 아이디 값이 없으면 에러 메세지를 표시
			if($.trim($('#username').val()) == ''){
				$(".error-message").html("아이디 입력");
				return false;
			}
			$(".error-message").html("");
			// 입력, 버튼을 비활성화
			$(".login-package").attr("disabled","disabled");
			connect();
		});
		// 메세지를 전 송
		$('#sendBtn').click(function(){
			let message = $('#textMessage').val();
			// 메세지 전송 객체 만들기
			let key = {
					id : $.trim($('#username').val()) ,
					state : 1,
					value : message
			}
			// 웹 소켓으로 전송 - json type으로 
			webSocket.send(JSON.stringify(key));

			// .empty 가능?
			$('#textMessage').val("");
		});
		
	});
</script>