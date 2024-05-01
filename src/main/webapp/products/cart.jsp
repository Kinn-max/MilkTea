<%@page import="database.ProductDAO"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.OrderDAO"%>
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
    <link rel="icon page" type="jpg" href="<%=urlTop %>/img/the_end.jpg">
    <link rel="stylesheet" href="<%=urlTop %>/css/main.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/product.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/category.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/cart.css">
    <link rel="stylesheet" href="<%=urlTop %>/Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">
    <title>Mua hàng</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<%
		Customer customer = (Customer) session.getAttribute("customer");
		OrderDAO orDAO = new OrderDAO();
		ProductDAO proDAO = new ProductDAO();
		ArrayList<Order> arrOrder = orDAO.selectOrderCustomer(new Order("",customer,null,"",0,null,"",0,0,0));
		String addressCus = "";
		String phoneNumber ="";
		String notify ="";
		if(request.getAttribute("notify") != null){
			notify += request.getAttribute("notify");
		}else{
			notify +="";
		}
		
	%>
		<div class="notify_among">
	        <span class="text-notify_among"></span>
	        <div class="control_notify"></div>
   		 </div>
	    <div class="wrap">
        <div class="title_center ">
            <div class="option_checkout">
                <div class="option_checkout-here">
                    <h3>Thông tin giao hàng</h3>
                    <form action="<%=urlTop %>/orders?order-option=order-now" method="post" id="myForm">
	                    <div class="information_client">
	                        <i class="fa-solid fa-user"></i>
	                        <input  type="text" name="consigneename"  value="<%=customer.getfullName()%>">
	                    </div>
	                    <div class="information_client">
	                        <i class="fa-solid fa-phone"></i>
	                        <input  type="text" name="phonenumber" <%
	                                if(customer.getphoneNumber() != ""){
	                                    phoneNumber+= customer.getphoneNumber();
	                                
	                                %>
	                                value="<%=phoneNumber %>"
	                                <% 
	                                }else{
	                                %>
	                         placeholder="Vui lòng điền số điện thoại người nhận"
	                         <%} %>>
	                    </div>
                        <div class="delivery">Giao đến</div>
                        <div class="information_client">
                            <i class="fa-solid fa-location-dot"></i>
		                         <input  type="text" name="deliveryaddress" <%
		                              if(customer.getAddress() != ""){
		                                  addressCus+= customer.getAddress();
		                              %>
		                              value="<%=addressCus %>"
		                              <% 
		                              }else{
		                              %>
		                       placeholder="Vui lòng điền địa chỉ(chi tiết) nhận hàng"
		                       <%} %>
		                      >
                        </div>
                        <div class="information_client">
                            <i class="fa-solid fa-note-sticky"></i>
                            <input  type="text" name="note" id="" value="" placeholder="Ghi chú thêm (địa chỉ, lời nhắn...)">
                        </div>
                        <div class="option_pay">
                            <div class="option_pay-text">Phương thức thanh toán</div>
                            <div class="option_pay-pick">
                                <div class="size_product-pick--name">
                                    <input class="option-size" type="radio" value="afterorder" id="radio1" name="payment" />
                                    <label for="radio1">Thanh toán khi nhận hàng</label>
                                </div>
                                <div class="size_product-pick--name">
                                    <input class="option-size" type="radio" value="befororder"id="radio2" name="payment" />
                                    <label for="radio2">Thanh toán bằng momo</label>
                                </div>
                            </div>
                        </div>
                        </form>
                </div>
                <div class="option_checkout-store">
                    <div class="both-title--delivery">
                        <h3>Thông tin đơn hàng</h3>
                        <a href="<%=urlTop%>/orders?id_order=all&order-option=delete">Xóa tất cả</a>
                    </div>
                    <div class="all_delivery">
                    	<%if(arrOrder.isEmpty()) {%>
                    	<div class="delivery_both">
                    		<div class="empty_order">
	                    		<i class="fa-solid fa-gears"></i>
	                    		<span>Thêm sản phẩm mới nào!</span>
                    		</div>
                    	</div>
                    	<%} %>
                             <%
                             	int totalCup = 0;
                                double totalPrice = 0;
			                    for(Order order : arrOrder){
			                        String codeTakeImg = order.getProduct().getCategory().getCodeCategory()+"";
			                        ProductDAO searchFile = new ProductDAO();
			                        totalCup += order.getQuantity();
			                        totalPrice += order.getTotalPrice();
			                        

			                %>
	                    <div class="delivery_both">
	                    	<div class="left_delivery_both">
		                        <div class="delivery_both-img">
		                            <img src="<%=urlTop %>/img/<%= order.getProduct().getImgProduct()%>" alt="<%=order.getProduct().getNameProduct() %>" >
		                        </div>
		                        <%
		                               String nameSize ="";
		                               if(order.getProduct().getSizeProduct().getSizeL() == Double.parseDouble(order.getSizeProduct())){
		                                   nameSize +="L";
		                               }
		                               if(order.getProduct().getSizeProduct().getSizeM() == Double.parseDouble(order.getSizeProduct())){
		                                   nameSize +="M";
		                               }
		                                    
	                                   	String topping = order.getFullNameTopping();
	                                   	if( topping.equals("")){
	                                   		topping += "0";
	                                   	}
		                            %>
		                        <div class="delivery_both-texxt">
		                            <span><%=order.getProduct().getNameProduct() %> + Size(<%=nameSize %>)</span>
		                            <p>Topping: <%= topping %></p>
		                            <p>Đường: <%= order.getSuger().getNameSuger() %></p>
		                                <% 
		                               String priceReal= proDAO.formatCurrency(Double.parseDouble(order.getSizeProduct())+order.getPricetopping());	
		                                %>
									<div class="dentail_tmp">
									    <%= priceReal %> x <%= order.getQuantity() %> = <%=proDAO.formatCurrency(order.getTotalPrice()) %>
									</div>

		                        </div>
	                    	
	                    	</div>
	                        <div class="minus-cart">
	                         <a href="<%= urlTop %>/orders?id_order=<%=order.getCodeOrder()%>&order-option=delete">
	                                <i class="fa-solid fa-minus"></i>
	                            </a>
	                        </div>
	                        
	                    </div>
	                    <%} %>


                    </div>
                    <div class="quantity_all-glass">
                        <div class="quantity_and_price">
                            <div class="quantity_total">
                                Số lượng cốc : <span> <%=totalCup %> cốc</span>
                            </div>
                            <div class="price_total">
                                Tổng cộng : <span><%= proDAO.formatCurrency(totalPrice) %></span>
                            </div>
                        </div>
                        <div class="order_now add-option--now hover-pointer">
                            <button id ="outsideButton">ĐẶT HÀNG</button>
                        </div>
                        <div class="add-option--now continue_buy-product ">
                              <a href="<%=urlTop %>/products/store.jsp" >TIẾP TỤC MUA HÀNG</a>
                        </div>
                    </div>
                 
                </div>
            </div>

        </div>
    </div>
    <script>
    document.getElementById("outsideButton").addEventListener("click", function() {
        var form = document.getElementById("myForm");
        form.submit();
    });
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
	var widthDele = width / 200;
    var interval = setInterval(function() {
        if (width <= 0) {
            clearInterval(interval);
        } else {
            width -= widthDele;
            progressBar.style.width = width + "px";
        }
    }, 20);


    
    </script>

	<jsp:include page="../footer.jsp" />

    
    
</body>
</html>