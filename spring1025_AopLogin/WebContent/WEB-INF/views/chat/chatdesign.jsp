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
// 		WebSocket ����
		let webSocket = null;
		// connect() �Լ��� ����ؼ� ������ ����
		// ws://localhost/spring1025_26_27AopLoginSocket/chat
		function connect(){
			// 1. ������ �����ϰ�
			webSocket = new WebSocket ("ws://localhost:8089/chat");
			// 2. �� ������ open
			webSocket.onopen = function(message){
				console.log("�α��� �߳�" + message);
			}
			webSocket.onclose = function(message){
				console.log("�ݾҳ�" + message);
			}
			webSocket.onerror = function(message){
				console.log("������" + message);
			}
	        // ������ ���� �޽����� ���� ����Ǵ� �̺�Ʈ
	        webSocket.onmessage = function(message) {
	          console.log("Me:"+$("#username").val());   
	             // ä�� ���� �ڽ� ��ü ���
	          let messageTextArea = document.getElementById("messageTextArea");
	          var arr = message.data.split(":");
	          if(arr[0] == $("#username").val()){
	             console.log("���� ��� "+$("#username").val()+"Message:"+message.data);
	           // ������ �ۼ�
	              messageTextArea.value += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+message.data;
	          }else{
	             console.log("�ٸ� ���"+arr[0]+"Message:"+message.data);   
	           // ������ �ۼ�
	              messageTextArea.value += message.data;
	          }
	        };
			
		}
		$('#loginBtn').click(function(){
			// ���̵� ���� ������ ���� �޼����� ǥ��
			if($.trim($('#username').val()) == ''){
				$(".error-message").html("���̵� �Է�");
				return false;
			}
			$(".error-message").html("");
			// �Է�, ��ư�� ��Ȱ��ȭ
			$(".login-package").attr("disabled","disabled");
			connect();
		});
		// �޼����� �� ��
		$('#sendBtn').click(function(){
			let message = $('#textMessage').val();
			// �޼��� ���� ��ü �����
			let key = {
					id : $.trim($('#username').val()) ,
					state : 1,
					value : message
			}
			// �� �������� ���� - json type���� 
			webSocket.send(JSON.stringify(key));

			// .empty ����?
			$('#textMessage').val("");
		});
		
	});
</script>