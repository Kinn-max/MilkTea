<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% String urlTop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Lỗi 404</title>
 <link rel="icon page" type="jpg" href="img/the_end.jpg">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Smart Error Page Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="//fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
<link href="<%=urlTop %>/css/error.css" rel="stylesheet" type="text/css" media="all" />
<body>

<div class="container demo-2">
	<div class="content">
        <div id="large-header" class="large-header">
			<h1 class="header-w3ls">Lỗi không tìm được tài nguyên, vui lòng quay lại trang chủ</h1>
			<p class="w3-agileits1">Oops!</p>
            <canvas id="demo-canvas"></canvas>
			<img src="<%=urlTop %>/img/owl.gif" alt="flashy" class="w3l">
            <h2 class="main-title">404</span></h2>
			<p class="w3-agileits2">Error, please return to the home page</p>
			<p class="copyright">© 2024 Flashy Error Page. All Rights Reserved | Design by <a href="https://www.facebook.com/baby7th1" target="_blank">KIEN TRAN</a></p>
        </div>
	</div>
</div>	
<script src="<%=urlTop %>/js/error.js"></script>
</body>
</html>