<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${name}</title>
</head>
<body>
<nav>
<a href="/pugs/index">Home</a>
<a href="/pugs/upload">Upload</a>
<a href="/pugs/search">Search</a>
</nav>
<p>Song</p>
<audio  src="<%=request.getContextPath()%>/song/1" type="audio/wav" controls preload="auto">		
</audio>

<div id="songPlayer"><p>Song player here</p></div>
<div id="playerButtonBar">
<p>Play/Pause Button</p>
<form action='/pugs/nextSong/${sid}' method='post'>
<input type="submit" value="Next Song"/>
</form>
</div>
</body>
</html>