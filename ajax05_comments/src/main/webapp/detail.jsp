<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.comm {width: 400px; height: 100px; border: 1px solid #aaa;}
</style>
<script type="text/javascript">
	function commList(pageNum)
	{
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4 && xhr.status == 200)
			{
				let result = xhr.responseText;
				let data = JSON.parse(result);
				
				let txt = "";
				for (let dat of data.list)
				{
					txt += "<div class=\"comm\">"
					txt += "아이디: " + dat.id + "<br>";
					txt += "내용: " + dat.comments + "<br>";
					txt += '<a href="javascript:delComm(' + dat.num + ')">삭제</a>';
					txt += "</div>"
				
				}
				document.getElementById("commList").innerHTML = txt;
				let startPage = data.startPage;
				let endPage = data.endPage;
				let pageCount = data.pageCount;
				let pageHTML = "";
				if (startPage > 5)
					pageHTML += "<a href='javascript:commList(" + (startPage - 1) + ")'>이전</a>";
				for (let i = startPage; i <= endPage; i++)
				{
					if (i == pageNum)
						pageHTML += "<a href='javascript:commList(" + i + ")'><span style='color:blue'>[" + i + "]</span></a>";
					else
						pageHTML += "<a href='javascript:commList(" + i + ")'><span style='color:gray'>[" + i + "]</span></a>";
				}
				if (endPage < pageCount)
					pageHTML += "<a href='javascript:commList(" + (endPage + 1) + ")'>다음</a>";
				let page = document.getElementById("page");
				page.innerHTML = pageHTML;
			}
		};
		xhr.open('get', "comm/list?mnum=" + ${vo.mnum} + "&pageNum=" + pageNum, true);
		xhr.send();
	}
	window.onload = function()
	{
		commList(1);
	}
	
	function addComm()
	{
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4 && xhr.status == 200)
			{
				let result = xhr.responseText;
				let data = JSON.parse(result);
				console.log(data);
				alert(data.code);
				
				commList();
				// document.getElementById("result").innerHTML = res;
			}
		};
		let id = document.getElementById("id");
		let comments = document.getElementById("comments");
		xhr.open("get", "/ajax05_comments/comm/insert?mnum=" + ${vo.mnum} + "&id=" + id.value + "&comments=" + comments.value, true);
		xhr.send();
	}
	function delComm(n)
	{
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function()
		{
			if (xhr.readyState == 4 && xhr.status == 200)
			{
				let result = xhr.responseText;
				let data = JSON.parse(result);
				console.log(data);
				alert(data.code);
				
				commList(1);
			}
		};
		xhr.open("get", "/ajax05_comments/comm/delete?num=" + n, true);
		xhr.send();
	}
</script>
</head>
<body>
	<div id="movie" style="width:500px; background-color: lightgray;">
		<h1>${vo.title}</h1>
		내용: ${vo.content}<br>
		감독: ${vo.director}<br>	
	</div>
	
	<div>
		<div id="commList"></div>
		<div id="page"></div>
		<div id="commAdd">
			아이디<br>
			<input type="text" id="id"><br>
			영화평<br>
			<textarea rows="3" cols="50" id="comments"></textarea><br>
			<input type="button" value="등록" onclick="addComm()">
		</div>
	</div>
</body>
</html>