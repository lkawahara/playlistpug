<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Song</title>
</head>
<body>
<nav>
<a href="/pugs/index">Home</a>
<a href="/pugs/upload">Upload</a>
<a href="/pugs/search">Search</a>
</nav>
<p>Upload</p>
<form action="pug/song/upload" method="post">
  <input type="file" name="pic" accept="image/*">
  <input type="submit">
</form>
</body>
</html>