<%@page import="model.SlideIndex"%>
<%@page import="model.SizeProduct"%>
<%@page import="model.Topping"%>
<%@page import="database.OrderDAO"%>
<%@page import="model.Order"%>
<%@page import="database.DetailOrderDAO"%>
<%@page import="model.DetailOrder"%>
<%@page import="java.net.URI"%>
<%@page import="model.Product"%>
<%@page import="model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="database.CustomerDAO"%>
<%@page import="database.ToppingDAO"%>
<%@page import="database.SizeProductDAO"%>
<%@page import="database.SlideIndexDAO"%>
<%@page import="database.ProductDAO"%>
<%@page import="database.CategoryDAO"%>
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
    <link rel="stylesheet" href="<%=urlTop %>/admin/css/admin.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/main.css">
    <link rel="stylesheet" href="<%=urlTop %>/css/category.css">
    <link rel="stylesheet" href="<%=urlTop %>/Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">

    <title>Quản lý website</title>
</head>
<body>
	<%
		CategoryDAO caDAO = new CategoryDAO();
		ProductDAO proDAO = new ProductDAO();
		SlideIndexDAO sliDAO = new SlideIndexDAO();
		SizeProductDAO sizeDAO = new SizeProductDAO();
		ToppingDAO topDAO = new ToppingDAO();
		CustomerDAO cusDAO = new CustomerDAO();
		DetailOrderDAO detailDAO = new DetailOrderDAO();
		OrderDAO orDAO = new OrderDAO();
		String options = request.getParameter("option");
		String notify ="";
		if (request.getAttribute("notify") != null) {
			notify += request.getAttribute("notify");
		}else{
			notify +="";
		}
		
	%>
	<jsp:include page="header.jsp" />
    <div class="menu_show-more">
       <div class="notify_among">
           <span class="text-notify_among"><%=notify %></span>
           <div class="control_notify"></div>
    </div>
    <div class=" title_center admin_padding">
		<jsp:include page="menu.jsp" />
        <div class="menu_show menu_show-ad">

                <div class="table_add">
                <%if(options == null){ 
         			 String codeCategory = request.getParameter("id_category");
         			 String nameCategory = "";
         			 String codeCall = "";
         			 if(codeCategory != null){
         				Category category = caDAO.selectById(new Category(codeCategory,"",""));
         				nameCategory = category.getNameCategory();
         				codeCall = category.getCodeCategory();
         			 }
                %>
                
	                   <form action="<%=urlTop %>/admin-category" method="post" enctype="multipart/form-data" >
	                    	<div class="both-label--input">
	                            <label for="namedanhmuc">Mã danh mục:</label><br>
	                            <input type="text" class="width-input" id="namedanhmuc" placeholder="" value="<%=codeCall %>" name="code-category"><br>
	                        </div>
	                        <div class="both-label--input">
	                            <label for="namedanhmuc">Tên danh mục:</label><br>
	                            <input type="text" class="width-input" name="name-category" id="namedanhmuc" value="<%=nameCategory %>"><br>
	                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-category">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh danh mục<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
	                        <button class="add-cart  add-cart--ad">Thêm danh mục</button>
	                      </form>
              
                <%}else{ %>
                	 <%if(options.equals("list-product")){ %>
    		           <table>
                        <tr>
                          <th>Mã sản phẩm</th>
                          <th>Tên sản phẩm</th>
                          <th>Ảnh sản phẩm</th>
                           <th>Thuộc danh mục</th>
                           <th>Thuộc loại mã size</th>
                           <th>Tùy chọn</th>
                        </tr>
                        <% ArrayList<Product> arrProduct = proDAO.selectAll();
                        	for(Product product : arrProduct){
                        %>
                        <tr>
                            <td><%=product.getCodeProduct() %></td>
                            <td><%=product.getNameProduct()%></td>
                            <td >
                                <img src="<%=urlTop %>/img/<%=product.getImgProduct() %>" alt="Girl" width="80" height="80">
                            </td>
                            <td><%=product.getCategory().getNameCategory()%></td>
                            <td>
                                <%=product.getSizeProduct().getCodeSize() %>
                            </td>
                              <td>
                            <a class="color-icon" href="<%=urlTop %>/admin/index.jsp?option=update-product&id_product=<%=product.getCodeProduct()%>"><i class="fa-solid fa-pen-to-square"></i></a>/<a class="color-icon" href="<%=urlTop %>/admin-product?o=delete&id_product=<%=product.getCodeProduct()%>"><i class="fas fa-trash"></i></a>
                              </td>
                          </tr>
        					<%} %>
                      </table>
              		<%} %>
              		<%if(options.equals("list-order")){ %>
                    <table>
                        <tr>
                          <th>STT</th>
                          <th>Ngày đặt</th>
                          <th>Khách đặt</th>
                          <th>Thanh toán</th>
                          <th class="width-min">Đơn hàng</th>
                          <th>Khách nhận</th>
                          <th>Sdt khách nhận</th>
                          <th>Địa chỉ nhận</th>
                          <th> Tổng tiền </th>
                          <th>Ghi chú</th>
                          
                           <th>Tùy chọn</th>
                        </tr>
                        <% ArrayList<DetailOrder > arrDetail = detailDAO.selectAll();
                        	int stt = 0;
                        	int n = arrDetail.size();
							String allOrder = "";
							double total = 0;
                        	for(int i = 0; i < n; i++){
								stt++;
                        		DetailOrder detailOrder = arrDetail.get(i);
								total += detailOrder.getOrder().getTotalPrice();
                        		allOrder+= "<span>"+detailOrder.getOrder().getProduct().getNameProduct()+"</span>"+ "<br>" +
                        				" - Số lượng: "+ detailOrder.getOrder().getQuantity()+"<br>"
                        				+ " - Đường: " + detailOrder.getOrder().getSuger().getNameSuger()+"<br>"
                        				+ " - Topping: " + detailOrder.getOrder().getFullNameTopping()+"<br>"
                        				+ " - Hết: " + proDAO.formatCurrency(detailOrder.getOrder().getTotalPrice())+"<br>";
                        		for(int j = i+1 ; j < n; j++){
                        			DetailOrder detailOrderNext = arrDetail.get(j);
                        			if(detailOrder.getCodeDetailOrder().equals(detailOrderNext.getCodeDetailOrder())){
                        				allOrder+="<span>"+detailOrderNext.getOrder().getProduct().getNameProduct()+"</span>"+"<br>"
                        						+ " - Số lượng: "+ detailOrderNext.getOrder().getQuantity()+"<br>"
                                				+ " - Đường: " + detailOrderNext.getOrder().getSuger().getNameSuger()+"<br>"
                                				+ " - Topping: " + detailOrderNext.getOrder().getFullNameTopping()+"<br>"
                                				+ " - Hết: " +proDAO.formatCurrency( detailOrderNext.getOrder().getTotalPrice())+"<br>";
                        				i = i+1;
                        				total += detailOrderNext.getOrder().getTotalPrice();
                        			}else{
                        				break;
                        			}
                       
                        }
								 
                        %>
                         <tr>
                          <td><%=stt %></td>
                          <td><%=detailOrder.getDateOrder() %></td>
                          <td><%=detailOrder.getOrder().getCustomer().getfullName() %></td>
                          <td><%= (detailOrder.getPayment().equals("befororder"))? "Trả trước" : "Trả sau" %></td>
                          <td class="text-align--order"><%=allOrder %></td>
                          <td><%=detailOrder.getConsignneeName() %></td>
                          <td><%=detailOrder.getPhoneNumber() %></td>
                          <td><%=detailOrder.getDeliveryAddress() %></td>
                          <td><%= proDAO.formatCurrency(total) %></td>
                          <td><%=detailOrder.getNote() %></td>
                          <td>
                           	<a class="color-icon" href="<%=urlTop %>/detail-order?o=delete&id_orderDetail=<%=detailOrder.getCodeDetailOrder() %>" ><i class="fas fa-trash"></i></a>
                            </td>
                        </tr>
                        <%
                        		allOrder ="";
                        		total = 0;
                        		} %>
        
                      </table>
              		<%} %>
              		 <%if(options.equals("add-category")){
              			 String codeCategory = request.getParameter("id_category");
              			 String nameCategory = "";
              			 String codeCall = "";
              			 if(codeCategory != null){
              				Category category = caDAO.selectById(new Category(codeCategory,"",""));
              				nameCategory = category.getNameCategory();
              				codeCall = category.getCodeCategory();
              			 }
              			%>
              			  <form action="<%=urlTop %>/admin-category" method="post" enctype="multipart/form-data" >
	                    	<div class="both-label--input">
	                            <label for="namedanhmuc">Mã danh mục:</label><br>
	                            <input type="text" class="width-input" id="namedanhmuc" placeholder=""  name="code-category"><br>
	                        </div>
	                        <div class="both-label--input">
	                            <label for="namedanhmuc">Tên danh mục:</label><br>
	                            <input type="text" class="width-input" name="name-category" id="namedanhmuc" ><br>
	                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-category">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh danh mục<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
	                        <button class="add-cart  add-cart--ad " value="add-category" name="o">Thêm danh mục</button>
	                      </form>

              		<%} %>
              		 <%if(options.equals("update-category")){
              			 String codeCategory = request.getParameter("id_category");
              			 String nameCategory = "";
              			 String codeCall = "";
              			 if(codeCategory != null){
              				Category category = caDAO.selectById(new Category(codeCategory,"",""));
              				nameCategory = category.getNameCategory();
              				codeCall = category.getCodeCategory();
              			 }
              			%>
              			  <form action="<%=urlTop %>/admin-category" method="post" enctype="multipart/form-data" >
	                    	<div class="both-label--input">
	                            <label for="namedanhmuc">Mã danh mục:</label><br>
	                            <input type="text" class="width-input" id="namedanhmuc" placeholder="" value="<%=codeCall %>" name="code-category"><br>
	                        </div>
	                        <div class="both-label--input">
	                            <label for="namedanhmuc">Tên danh mục:</label><br>
	                            <input type="text" class="width-input" name="name-category" id="namedanhmuc" value="<%=nameCategory %>"><br>
	                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-category">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh danh mục<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
	                        <button class="add-cart  add-cart--ad " value="update-category" name="o">Thêm danh mục</button>
	                      </form>

              		<%} %>
              		<%if(options.equals("add-product")){ 
              			ArrayList<Category> arrCate = caDAO.selectAll();
              			ArrayList<SizeProduct> arrSize = sizeDAO.selectAll();
              		%>
 					<form action="<%=urlTop %>/admin-product" method="post" enctype="multipart/form-data">
 					     <div class="both-label--input">
                            <label for="namedanhmuc">Mã sản phẩm:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" name="codeproduct"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Tên sản phẩm:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" name="nameproduct"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="cars">Chọn danh mục:</label>
                            <select id="cars" class="width-input" name="codecategory">
                          	  	<option value=""></option>
                            	<% for(Category category: arrCate){ %>
                              	<option value="<%=category.getCodeCategory() %>"><%=category.getNameCategory() %></option>
                              <%} %>
                            </select>
                        </div>
                        <div class="both-label--input">
                            <label for="cars">Chọn mã size:</label>
                            <select id="cars" name="codesize" class="width-input">
                              <option value="">Vào phần tùy chọn giá thuộc size để xem nếu không biết</option>
                              <% for(SizeProduct size : arrSize){ %>
                              <option value="<%=size.getCodeSize() %>"><%=size.getCodeSize() %></option>
                              <%} %>
                            </select>
                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-product">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh sản phẩm<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
                        <button class="add-cart  add-cart--ad"  value="add-product" name="o">Thêm Sản phẩm</button>
                      </form>
              		<%} %>
              		<%if(options.equals("update-product")){ 
             			 String codeProduct = request.getParameter("id_product");
             			 String nameProduct = "";
             			 String categoryGet = "";
             			 String codeCate = "";
             			 String sizeGet = "";
             			 if(codeProduct != null){
								Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
								nameProduct = product.getNameProduct();
								categoryGet = product.getCategory().getNameCategory();
								sizeGet = product.getSizeProduct().getCodeSize();
								codeCate = product.getCategory().getCodeCategory();
             			 }
              			ArrayList<Category> arrCate = caDAO.selectAll();
              			ArrayList<SizeProduct> arrSize = sizeDAO.selectAll();
              		%>
 					<form action="<%=urlTop %>/admin-product" method="post" enctype="multipart/form-data">
 					     <div class="both-label--input">
                            <label for="namedanhmuc">Mã sản phẩm:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" name="codeproduct" value="<%=codeProduct%>"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Tên sản phẩm:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" name="nameproduct" value="<%=nameProduct%>"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="cars">Chọn danh mục:</label>
                            <select id="cars" class="width-input" name="codecategory">
                          	  	<option value="<%=codeCate%>"><%=categoryGet%></option>
                            	<% for(Category category: arrCate){ %>
                              	<option value="<%=category.getCodeCategory() %>"><%=category.getNameCategory() %></option>
                              <%} %>
                            </select>
                        </div>
                        <div class="both-label--input">
                            <label for="cars">Chọn mã size:</label>
                            <select id="cars" name="codesize" class="width-input">
                              <option value="<%=sizeGet%>"> <%=sizeGet%></option>
                              <% for(SizeProduct size : arrSize){ %>
                              <option value="<%=size.getCodeSize() %>"><%=size.getCodeSize() %></option>
                              <%} %>
                            </select>
                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-product">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh sản phẩm<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
                        <button class="add-cart  add-cart--ad"  value="update-product" name="o">Cập nhật sản phẩm</button>
                      </form>
              		<%} %>
              		 <%if(options.equals("list-category")){ %>
                    <table>
                        <tr>
                          <th>Mã danh mục</th>
                          <th>Tên danh mục</th>
                          <th>Ảnh danh mục</th>
                           <th>Tùy chọn</th>
                        </tr>
                        <% ArrayList<Category> arrCategory = caDAO.selectAll();
                        	for(Category category : arrCategory){

                        %>
                        <tr>
                          <td><%=category.getCodeCategory() %></td>
                          <td><%=category.getNameCategory() %></td>
                          <td >
                            <img src="<%=urlTop %>/img/<%=category.getImgCategory() %>" alt="Girl" width="80" height="80">
                          </td>
                            <td>
                           <a class="color-icon" href="<%=urlTop %>/admin/index.jsp?option=update-category&id_category=<%=category.getCodeCategory()%>"><i class="fa-solid fa-pen-to-square"></i></a>/<a class="color-icon" href="<%=urlTop %>/admin-category?o=delete&id_category=<%=category.getCodeCategory()%>"><i class="fas fa-trash"></i></a>
                            </td>
                        </tr>
                        <%} %>
        
                      </table>
              		<%} %>
              		 <%if(options.equals("list-slide")){ %>
 					<form action="<%=urlTop %>/admin-slide" method="post" enctype="multipart/form-data">
 					     <div class="both-label--input">
                            <label for="namedanhmuc">Mã slide:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" name="codeslide" ><br>
                        </div>
	                        <div class="both-label--input file-show-center">
	                            <label for="file-name" id="area-file">
					                <input type="file" hidden id="file-name" name="file-slide">
					                <div id="img-view">
					                    <img src="<%=urlTop %>/img/508-icon.png" alt="">
					                    <p>Tải ảnh slide<br> lên ở đây</p>
					                    <span>Chọn ảnh từ thư viện</span>
					                </div>
					            </label>
	                        </div>
                        <button class="add-cart  add-cart--ad"  value="add-slide" name="o">Thêm ảnh slide</button>
                      </form>
                     <table>
                        <tr>
                        	<th> Stt </th>
                          <th>Mã slide</th>
                          <th>Ảnh slide</th>
                          <th>Tùy chọn</th>
                        </tr>
                        <% ArrayList<SlideIndex>  slideArr = sliDAO.selectAll();
                        	int stt = 0;
                        	for(SlideIndex slide : slideArr){
                        		++stt;
                        %>
                        <tr>
                        	<td><%=stt %></td>
                            <td><%=slide.getCodeImg() %></td>
                            <td >
                                <img src="<%=urlTop %>/img/<%=slide.getTakeImg() %>" alt="Girl" width="200px">
                            </td>
                              <td>
                               <a class="color-icon" href="<%=urlTop %>/admin-slide?o=delete&id_slide=<%=slide.getCodeImg()%>"><i class="fas fa-trash"></i></a>
                              </td>
                          </tr>
                              <%} %>
        
                      </table>
              		<%} %>
              		<%if(options.equals("list-topping")){ %>
              			   <form action="<%=urlTop %>/admin-topping" method="post"  >
	                    	<div class="both-label--input">
	                            <label for="namedanhmuc">Mã topping:</label><br>
	                            <input type="text" class="width-input" id="namedanhmuc" placeholder=""  name="code-topping"><br>
	                        </div>
	                        <div class="both-label--input">
	                            <label for="namedanhmuc">Tên Topping:</label><br>
	                            <input type="text" class="width-input" name="name-topping" id="namedanhmuc"><br>
	                        </div>
	                          <div class="both-label--input">
	                            <label for="namedanhmuc">Giá Topping:</label><br>
	                            <input type="text" class="width-input" name="price-topping" id="namedanhmuc" ><br>
	                        </div>
	                        <button class="add-cart  add-cart--ad" name="option" value="more">Thêm Topping</button>
	                      </form>
                     <table>
                        <tr>
                          <th>Mã topping</th>
                          <th>Tên topping</th>
                          <th>Giá</th>
                           <th>Tùy chọn</th>
                        </tr>
                        <tr>
                        	<% ArrayList<Topping> arrTopping = topDAO.selectAll();
                        		for(Topping topping : arrTopping){
                        			
                    
                        	%>
                            <td><%=topping.getCodeTopping() %></td>
                            <td >
                            <%=topping.getNameTopping() %>
                            </td>
                              <td >
                            <%=proDAO.formatCurrency(topping.getPriceTopping()) %>
                            </td>
                              <td>
                              <a class="color-icon" href="#"><i class="fa-solid fa-pen-to-square"></i></a>/<a class="color-icon" href="#"><i class="fas fa-trash"></i></a>
                              </td>
                          </tr>
                          <%} %>
        
                      </table>
              		<%} %>
              		<%if(options.equals("list-size")){ %>
                     <table>
                        <tr>
                          <th>Mã size giá thuộc sản phẩm</th>
                          <th>Size M</th>
                          <th>Size L</th>
                          <th> Tùy chọn </th>
                        </tr>
                        <tr>
                        	<%
                        		ArrayList<SizeProduct> arrSize = sizeDAO.selectAll();
                        		for(SizeProduct size : arrSize){
                        			
                        	%>
                        	<td><%=size.getCodeSize() %></td>
                            <td><%=proDAO.formatCurrency(size.getSizeM()) %></td>
				        	<td><%=proDAO.formatCurrency(size.getSizeL()) %></td>
                              <td>
                              <a class="color-icon" href="#"><i class="fa-solid fa-pen-to-square"></i></a>/<a class="color-icon" href="#"><i class="fas fa-trash"></i></a>
                              </td>
                          </tr>
                          <%} %>
        
                      </table>
              		<%} %>
              		<%if(options.equals("add-customer")){
             			 String codeCustomer = "";
             			 String nameUser = "";
             			 String passWord = "";
             			 String fullName = "";
             			 String address = "";
             			 String phoneNumber = "";
             			 String email = "";
             			 if(request.getParameter("id_customer") != null){
             				Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
             				codeCustomer = customer.getCodeCustomer();
             				nameUser = customer.getUserCustomer();
             				passWord = customer.getPassword();
             				fullName = customer.getfullName();
             				address = customer.getAddress();
             				phoneNumber = customer.getphoneNumber();
             				email = customer.getEmail();
             			 }
              		%>
             			<form>
                    	<div class="both-label--input">
                            <label for="namedanhmuc">Mã khách hàng:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc" placeholder="Mã khách hàng không được giống nhau" value="<%=codeCustomer %>" name="fname"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Tên đăng nhập:</label><br>
                            <input type="text" class="width-input" id="namedanhmuc"value="<%=nameUser %>" name="fname"><br>
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Mật khẩu:</label><br>
                            <input type="file"  id="namedanhmuc" value="<%=passWord %>" name="lname">
                        </div>
                         <div class="both-label--input">
                            <label for="namedanhmuc">Họ và tên:</label><br>
                            <input type="file"  id="namedanhmuc" value="<%=fullName %>" name="lname">
                        </div>
                       <div class="both-label--input">
                            <label for="namedanhmuc">Địa chỉ:</label><br>
                            <input type="file"  id="namedanhmuc" value="<%=address %>" name="lname">
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Sđt:</label><br>
                            <input type="file"  id="namedanhmuc" value="<%=phoneNumber %>" name="lname">
                        </div>
                        <div class="both-label--input">
                            <label for="namedanhmuc">Email:</label><br>
                            <input type="file"  id="namedanhmuc" value="<%=email %>" name="lname">
                        </div>
                        <button class="add-cart  add-cart--ad">Thêm khách hàng</button>
                      </form>
              		
              		<%} %>
              		<%if(options.equals("list-customer")){ %>
                     <table>
                        <tr>
                          <th>Mã</th>
                          <th>User name</th>
                          <th>Password</th>
                          <th>Họ và tên</th>
                          <th>Địa chỉ</th>
                          <th>Sđt</th>
                          <th>Email</th>
                          <th>Tùy chỉnh</th>
                        </tr>
                        <% ArrayList<Customer> arrCustomer = cusDAO.selectAll();
                        	for(Customer customer : arrCustomer){
                        %>
                        <tr>
                        	<td><%=customer.getCodeCustomer() %></td>
                        	<td><%=customer.getUserCustomer() %></td>
                        	<td><%=customer.getPassword() %></td>
                        	<td><%=customer.getfullName() %></td>
                        	<td><%=customer.getAddress() %></td>
                        	<td><%=customer.getphoneNumber() %></td>
                        	<td><%=customer.getEmail() %></td>
                              <td>
                              <a class="color-icon" href="<%=urlTop %>/admin/index.jsp?option=add-customer&id_customer=<%=customer.getCodeCustomer()%>"><i class="fa-solid fa-pen-to-square"></i></a>/<a class="color-icon" href="<%=urlTop %>/danh-muc?o=delete&id_customer=<%=customer.getCodeCustomer()%>"><i class="fas fa-trash"></i></a>
                              </td>
                          </tr>
                              <%} %>
        
                      </table>
              		<%} %>
  
                <%} %>
                  </div>
            </div>


        </div>
    </div>
 	<script>
	 	const categoryOption = document.querySelector('.category-ad');
	 	var divParent = document.querySelectorAll('.both_icon');
	 	var iconUp = document.querySelectorAll('.round-round');
	 	var both_first = document.querySelector('.both_first');
	
	 	var isToggled = false; 
	
	 	document.querySelector('.click_option').addEventListener('click', function(){
	 	    if (!isToggled) { 
	 	        categoryOption.classList.add('togle-cate');
	 	        divParent.forEach((e) => {
	 	            var pElement = e.querySelector('p');
	 	            pElement.style.display = "none";
	 	        });
	 	        iconUp.forEach((e) => {
	 	            e.style.display = "none";
	 	        });
	 	        both_first.querySelector('h3').style.display = "none";
	 	        isToggled = true; 
	 	    } else { 
	 	        categoryOption.classList.remove('togle-cate');
	 	        divParent.forEach((e) => {
	 	            var pElement = e.querySelector('p');
	 	            pElement.style.display = "flex";
	 	        });
	 	        iconUp.forEach((e) => {
	 	            e.style.display = "flex"; 
	 	        });
	 	        both_first.querySelector('h3').style.display = "flex";
	 	        isToggled = false; 
	 	    }
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

		<script src="<%=urlTop %>/admin/js/style.js"></script>
    
</body>
</html>