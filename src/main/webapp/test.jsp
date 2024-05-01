<%@page import="model.Order"%>
<%@page import="database.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@page import="model.Product"%>
<%@page import="model.Suger"%>
<%@page import="model.Customer"%>
<%@page import="database.SugerDAO"%>
<%@page import="database.ProductDAO"%>
<%@page import="model.Customer"%>
<%@page import="database.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
<%
		 String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
			String  codeDanhMuc= "";
			String nameDanhMuc = "";
			String imgDanhMuc = "";
			if(request.getAttribute("codeDanhMuc") != null){
				codeDanhMuc+= request.getAttribute("codeDanhMuc");
			}
			if(request.getAttribute("nameDanhMuc") != null){
				nameDanhMuc+= request.getAttribute("nameDanhMuc");
			}
			if(request.getAttribute("imgDanhMuc") != null){
				imgDanhMuc+= request.getAttribute("imgDanhMuc");
			}
			
		

		 %>

<div>
	<h2>mã danh muc: <%=codeDanhMuc %></h2>
	<h2>tên danh muc: <%=nameDanhMuc %></h2>
	<img src="<%=url1 %>/img/<%=imgDanhMuc%>" alt="Chưa có chi">
</div>
<form action="<%=url1 %>/test" method="post" >
       	<div class="both-label--input">
               <label for="namedanhmuc">Mã danh mục:</label><br>
               <input type="text" class="width-input" id="namedanhmuc" placeholder=""  name="code-category"><br>
           </div>
           <div class="both-label--input">
               <label for="namedanhmuc">Tên danh mục:</label><br>
               <input type="text" class="width-input" id="namedanhmuc"  name="name-category"><br>
           </div>

    <input type="submit" value="Upload">
</form>



</body>
</html>