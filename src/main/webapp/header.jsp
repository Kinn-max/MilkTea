<%@page import="database.ProductDAO"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.OrderDAO"%>
<%@page import="model.Customer"%>
 <% String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <header>
        <nav class="title">
            <div class="title_center">
                <div class="title_center-all">
                    <a href="<%=url%>/index.jsp">
                        <div class="img_shop">
                            <img src="<%=url %>/img/logo.png" alt="" class="img_shop-picture">
                        </div>
                    </a>
                    <ul class="title_list">
                        <li class="title_list-item">
                            <a href="#" class="title_list-new">TRANG CHỦ</a>
                        </li>
                        <li class="title_list-item">
                            <a href="#" class="title_list-new">GIỚI THIỆU</a>
                        </li>
                        <li class="title_list-item item_product-all">
                            <a href="#" class="title_list-new">SẢN PHẨM</a>
    
                        </li>
                        <li class="title_list-item">
                            <a href="#" class="title_list-new">TIN TỨC</a>
                        </li>
                    </ul>
                    <div class="both_right">
                     	<form action="<%=url %>/products" method="get">
	                        <div class="title_search">
	                            <input type="text" class="title_search-ip" name="s" placeholder="Tìm kiếm!" >
	                            <div class="title_search-glass">
	                                <i class="fa-solid fa-magnifying-glass"></i>
	                            </div>
	                             <button type="submit" style="display: none;" name="product-option" value="search"></button>
	                        </div>
                     	</form>
                        <div class="title_signCart">
                            <div class="title_signCart-user">
                                <%
							    	Object obj = session.getAttribute("customer");
							    	Customer customer = null;
							    	if(obj != null){
							    		customer = (Customer)obj;
							    	}
							    	if(customer != null){
							   
								 %>
								<span class="text_name-user"><%=customer.getfullName() %></span>
                                <i class="fa-solid fa-user"></i>
                                  <ul class="title_user-list">
                                    <li class="title_user-list-li">
                                        <a href="<%=url %>/customers/update.jsp">Thông tin tài khoản</a>
                                    </li>
                                    <li class="title_user-list-li">
                                        <a href="<%=url %>/customers/change.jsp">Thay đổi mật khẩu</a>
                                    </li>
                                    <li class="title_user-list-li">
                                        <a href="<%=url %>/customer?submit=log-out">Đăng xuất</a>
                                    </li>
                                </ul>
                                <%
                                }else{
                                %>
                                <i class="fa-solid fa-user"></i>
                                <ul class="title_user-list">
                                    <li class="title_user-list-li">
                                        <a href="<%=url %>/customers/register.jsp">Đăng ký</a>
                                    </li>
                                    <li class="title_user-list-li">
                                        <a href="<%=url %>/customers/signIn.jsp">Đăng nhập</a>
                                    </li>
                                </ul>
                                <%} %>
                            </div>
                            <div class="title_signCart-cart">
                                <i class="fa-solid fa-cart-shopping"></i>
                                <ul class="title_cart-list">
                                <%if(customer != null){ 
                                	OrderDAO orDAO = new OrderDAO();
                                	ArrayList<Order> arrOrder = orDAO.selectOrderCustomer(new Order("",customer,null,"",0,null,"",0,0,0));
                                	if(arrOrder.isEmpty()){	
                                %>
                     				<li class="title_cart-list--all">
                                        <div class="title_cart-list--name">
                                            <p style="text-align: center;width: 100%;">Chưa có sản phẩm nào được thêm vào</p>
                                        </div>
                                    </li>	
                                  <%
                                	}
                              		for(Order order : arrOrder){
                                    	ProductDAO searchFile = new ProductDAO();
                                    	String codeTakeImg = order.getProduct().getCategory().getCodeCategory();
                                    	String topping = order.getFullNameTopping();
                                    	if( topping.equals("")){
                                    		topping += "0";
                                    	}
                                	%>
                                
                                    <li class="title_cart-list--all">
                                        <div class="title_cart-list--img">
                                            <img src="<%=url %>/img/<%= order.getProduct().getImgProduct() %>" alt="">
                                        </div>
                                        <div class="title_cart-list--name">
                                            <span><%=order.getProduct().getNameProduct() %></span>
                                             <p>Topping: <%=topping %></p>
                                             <p>Đường: <%=order.getSuger().getNameSuger() %></p>
                                        </div>
                                    </li>
                                    <%} %>
                                    <div class="title_cart-list--more">
                                        <a href="<%=url %>/products/cart.jsp">Xem thêm</a>
                                    </div>
                                <%} else{%>
                                    <li class="title_cart-list--all">
                                        <div class="title_cart-list--name">
                                            <p style="text-align: center;width: 100%;">Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng</p>
                                        </div>
                                    </li>
                                    
                                    <%} %>
                                </ul>
                            </div>
                            <div class="title_signCart-bar">
                                <i class="fa-solid fa-bars"></i>
                             
                            </div>
                        </div>
                    </div>
                </div>

            </div>
    
        </nav>

    </header>