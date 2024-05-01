<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.ProductDAO"%>
<%@page import="database.OrderDAO"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon page" type="jpg" href="<%=url %>/img/the_end.jpg">
    <link rel="stylesheet" href="<%=url %>/css/main.css">
    <link rel="stylesheet" href="<%=url %>/css/product.css">
    <link rel="stylesheet" href="<%=url %>/css/login.css">
    <link rel="stylesheet" href="<%=url %>/Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">
    <title>Thông tin tài khoản</title>
</head>
<body>
		<jsp:include page="../header.jsp" />
	<div class="notify_among">
        <span class="text-notify_among"></span>
        <div class="control_notify"></div>
   	</div>
    <div class="wrap">
        <div class="title_center text_center background_boddy">
            <div class="update_user">
                <div class="heading__title textUser-left">
          <%
	        		String notify ="";
	        		if(request.getAttribute("notify") != null){
	        			notify += request.getAttribute("notify");
	        		}else{
	        			notify +="";
	        		}
			    	Object obj = session.getAttribute("customer");
			    	Customer customer = null;
			    	if(obj != null){
			    		customer = (Customer)obj;
			    	}
			    	if(customer != null){
			   
		 %>
               
                    <p class="title_signIn  center_text-update">Cập nhật thông tin</p>
                    <form class="form" action="<%=url %>/customer"  method="post">
                        <div class="input-group inpu_bottom">
                            <label for="fullname" class="text_label">Họ và tên</label>
                            <input class="checkRed" type="text" value="<%=customer.getfullName() %>" name="fullname" id="fullname" placeholder="">
                        </div>
                        <div class="input-group inpu_bottom">
                            <label for="username" class="text_label">Số điện thoại</label>
                            <input class="checkRed" type="text" value="<%=customer.getphoneNumber() %>" name="phone-number" id="username" placeholder="">
                        </div>
                        <div class="input-group inpu_bottom">
                            <label for="email" class="text_label">Email</label>
                            <input type="text" name="email" value="<%=customer.getEmail() %>" id="email" placeholder="">
                            <span class="formEr"></span>
    
                        </div>
                        <div class="input-group inpu_bottom">
                            <label for="password" class="text_label">Địa chỉ</label>
                            <input type="text" name="address" value="<%=customer.getAddress()%>" id="password" placeholder="">
                        </div>
                        <button class="sign" name="submit" value="update">Cập nhật</button>
                    </form>
                    <%} %>
                </div>
            </div>
        </div>

    </div>

    <!-- footer -->
            <script>
        	const notifyAmong =  document.querySelector(".notify_among");
        	var textNotifyAmong = document.querySelector(".text-notify_among")
  		    var progressBar = document.querySelector(".control_notify");
        	var notifyAmongValue = "<%=notify%>";
        	
        	if(notifyAmongValue.length >0){
        		notifyAmong.classList.add("active-show");
        		textNotifyAmong.innerHTML = notifyAmongValue;
        	}
        		// hiden tag span after 3s
            function hideNotification() {
            	notifyAmong.classList.remove("active-show");
        	}
        	setTimeout(hideNotification, 2000);
       		var width =  progressBar.clientWidth;
            var interval = setInterval(function() {
                if (width <= 0) {
                    clearInterval(interval);
                } else {
                    width -= 2;
                    progressBar.style.width = width + "px";
                }
            }, 20);
  

            
            </script>
		<jsp:include page="../footer.jsp" />
    <script src="js/style.js"></script>
    
</body>
</html>