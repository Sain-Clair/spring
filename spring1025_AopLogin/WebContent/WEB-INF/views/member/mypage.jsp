<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<c:set var="sessionID" value="${sessionScope.sessionID}" />
<article>
	<header style="color: white">
		<h1>My Page</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3">로그인???</li>
	</ul>

	<div class="container">
		<div id="chart"></div>
		<!-- 로그인 기록 -->
		<h2>log 영역</h2>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>접속 아이피</th>
					<th>접속 환경</th>
					<th>접속 상태</th>
					<th>접속 날짜</th>
				</tr>
			</thead>
			<tbody id ="logList">
			</tbody>
		</table>
		<!-- 로그인 기록 -->
	</div>
</article>
<%@include file="../temp/footer.jsp"%>

<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function (){
	$.ajaxSetup({cache: false});
	$.ajax({
		url: "${cPath}/restListJson2",
		success : function(jsondata){
			console.log(jsondata[0].sub);
			console.log(jsondata[1]);
			console.log("__________________");
			var chart = c3.generate({
			    bindto: '#chart',
			    data: {
			      columns: [
			        ['data1', 30, 200, 100, 400, 150, 250],
			        ['data2', 50, 20, 10, 40, 15, 25]
			      ],
			      axes: {
			        data2: 'y2' // ADD
			      }
			    },
			    axis: {
			      y2: {
			        show: true // ADD
			      }
			    }
			});
			//____________________________________________________
		},
		error : function(e){
			console.log("깔깔");
		}
	})
})
$(function (){
	$.ajax({
		url:"${cPath}/mypageRest",
		success : function(map){
			let logList = $('#logList');
			logList.empty(); //  값 입력 전 비우기
			let list = map.list;
			let max = map.max;
			var html='';
			
			for(var i = 0; i<list.length; i++){
				var e = list[i];
				if(e.num == max){
					html += '<tr style="background-color: orange;">';
				}else{
					html +='<tr>';
				}
				html += '<td>' + e.num +'</td>';
				if($('sessionScope.sessionID' == 'admin')){
					html += '<td>' + e.idn + '</td>';
				}
	            html += '<td>' + e.reip + '</td>';
	            html += '<td>' + e.uagent + '</td>';
	            html += '<td>' + e.status + '</td>';
	            html += '<td>' + e.logtime + '</td>';
	            html += '</tr>';
			}
			  logList.append(html);
		}
	})
})


</script>


