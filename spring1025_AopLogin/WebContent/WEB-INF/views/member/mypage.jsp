<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">
		<h1>My Page</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3">·Î±×ÀÎ???</li>
	</ul>
	
	<div class="container">
		<div id="chart"></div>	
	</div>
</article>
<%@include file="../temp/footer.jsp"%>

<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
			console.log("±ò±ò");
		}
	})
	
})

</script>


