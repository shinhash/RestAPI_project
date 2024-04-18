<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>chat test!!!</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function (){
				var ws;
				
				$("#sendBtn").on("click", function(e) {
					sendMessage();
					$('#message').val('');
				});
				$("#InConnBtn").on("click", function(e){
					ws = new WebSocket("ws://183.107.121.180:8066/ws/chat");
					ws.onmessage = onMessage;
					ws.onopen = onOpen;
					ws.onclose = onClose;
				});
				$("#DisConnBtn").on("click", function(e){
					ws.close();
				});
				

				function sendMessage() {
					ws.send($("#message").val());
				}
			
				function onMessage(msg) {
					var data = msg.data;
					console.log(data);
					var sessionId = null;
					var message = null;
			
					var arr = data.split(":");
			
					for(var i = 0; i < arr.length; i++) {
						console.log('arr[' + i + ']: ' + arr[i]);
					}
					var data = msg.data;
					$("#messageArea").append(data + "<br/>");
			
// 					var cur_session = '${userid}';
// 					console.log("cur_session : " + cur_session);
			
// 					sessionId = arr[0];
// 					message = arr[1];
			
// 					console.log("sessionID : " + sessionId);
// 					console.log("cur_session : " + cur_session);
			
// 					if(sessionId == cur_session) {
// 						var str = "<div class='chat'><div class='chat-body'><div class='chat-message' id='id_chat'>";
// 						str += sessionId + " : " + message;
// 						str += "</div></div></div>";
// 						$("#messageArea").append(str);
// 					} else {
// 						var str = "<div class='chat chat-left'><div class='chat'><div class='chat-body'><div class='chat-message' id='id_chat'>";
// 						str += sessionId + " : " + message;
// 						str += "</div></div></div></div>";
// 						$("#messageArea").append(str);
// 					}
				}
				
				function onClose(evt) {
					var str = '${userid}' + " 님이 방을 나가셨습니다.<br>";
					$("#messageArea").append(str);
				}

				function onOpen(evt) {
					var str = '${userid}' + " 님이 입장하셨습니다.<br>";
					$("#messageArea").append(str);
				}
			});
			
		</script>
	</head>
	<body>
		<p>Hello World!</p>
		<div id="connectArea">
			<input type="button" id="InConnBtn" value="접속">
			<input type="button" id="DisConnBtn" value="종료">
		</div>
		<div id="inputArea">
			<input type="text" id="message" />
			<input type="button" id="sendBtn" value="SEND">
		</div>
		<div id="messageArea"></div>
	</body>
</html>