<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <% String urlTop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon page" type="jpg" href="<%=urlTop %>/img/the_end.jpg">
    <link rel="stylesheet" href="<%=urlTop %>/css/main.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/product.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/category.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/cart.css">
    <link rel="stylesheet" href="<%=urlTop %>/Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">
    <title>Đặt hàng thành công</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<%
		Customer customer = (Customer) session.getAttribute("customer");
	%>
    <div class="wrap hiden-center">
        <div class="order_success">
            <div class="order_success-all">
                <div class="img_success-form">
                    <img src="<%=urlTop %>/img/icons8-good-quality.gif" alt="">
                </div>
                <h3 class="text_success">Đặt hàng thành công</h3>
                <span class="name-order-succes" style="display: inline-block;
                vertical-align: middle;" >Đơn hàng của bạn sẽ được giao tới<p class="name_customer-success" style="display: inline; margin: 0 5px;"><%=customer.getfullName() %></p> trong vòng 15 phút! Hãy giữ điện thoại nhé.</span>
                <div class="back_store hover_button-red">
                    <a href="<%=urlTop %>/products/store.jsp">Quay lại cửa hàng</a>
                </div>
            </div>
        </div>
    </div>
    
	<jsp:include page="../footer.jsp" />


     
</body>
</html>