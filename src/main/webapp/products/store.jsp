<%@page import="database.SizeProductDAO"%>
<%@page import="model.SizeProduct"%>
<%@page import="model.Suger"%>
<%@page import="model.Customer"%>
<%@page import="database.SugerDAO"%>
<%@page import="model.Topping"%>
<%@page import="database.ToppingDAO"%>
<%@page import="model.Product"%>
<%@page import="database.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.CategoryDAO"%>
<%@page import="model.Category"%>
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
    <link rel="stylesheet" href="<%=urlTop %>/css/login.css">
    <link rel="stylesheet" href="<%=urlTop %>/Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">
    <title>Cửa hàng kinn</title>
</head>
<body>
		<jsp:include page="../header.jsp" />
    	<%
    		CategoryDAO caDAO = new CategoryDAO();
    		ArrayList<Category> arrCategory = caDAO.selectAll();
    		ProductDAO proDAO = new ProductDAO();
    		ArrayList<Product> arrProduct = proDAO.selectAll();
    		ToppingDAO topDAO = new ToppingDAO();
    		ArrayList<Topping> arrTopping  = topDAO.selectAll();
    		SugerDAO sugDAO = new SugerDAO();
    		ArrayList<Suger> arrSuger = sugDAO.selectAll();
    		SizeProductDAO sizeDAO = new SizeProductDAO();
    	%>
    	<% 
		String notifyTotal = "";
		if (request.getAttribute("notify") != null) {
			notifyTotal += request.getAttribute("notify");
		}else{
			notifyTotal +="";
		}

	%>
	<div class="notify_among">
        <span class="text-notify_among"></span>
        <div class="control_notify"></div>
   
    </div>
    <div class="wrap hiden-center">
        <div class="center_product " id="product-details">
            <div class="center_product-dentail">
              <form action="<%=urlTop %>/products" method="post" id="productForm" >
               <input type="hidden" id="action" name="action" value="">
                <div class="top-teaMilk">
                    <div class="top-teaMilk-img">
                        <img id="img_center-sp" src="" alt="">
                    </div>
                    <div class="top-teaMilk-infor">
                            <div class="name_product-title">
                                <h3 id="name_center-sp"></h3>
                                <input type="hidden" id="code-product-take" name="codeProductTake">
                                <div class="close_dentail">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="price_product">
                                <span id="price_center-sp"></span>
                            </div>
                            <div class="both-quantity">
                                <div class="quantity_product">
                                    <div class="quantity_product-mul" >
                                        <i class="fa-solid fa-minus"></i>
                                    </div>
                                    <div class="quantity_product-get">
                                    	<input type="hidden" id="code-quantity-take" name="codeQuantityTake">
                                        <input type="text"    id="quantity_product-show" value="">
                                        <input type="hidden" id="code-price-topping-take" name="codePriceAfterTake">
                                    </div>
                                    <div class="quantity_product-plus">
                                        <i class="fa-solid fa-plus"></i>
                                    </div>
                                </div>
                                <div class="add_product">
                              		 <input type="hidden" id="code-totalPrice-take" name="codePriceTake">
                                     <input  class="add_product-click width_total" type="text"  >
                                </div>
                            </div>
                            <div class="total_price-center">
                                <div class="add-option--now">
                                    <button class="add-cart hover_button-red" name="product-option" value="add-cart">Thêm vào giỏ hàng</button>
                                </div>
                                <div class="add-option--now">
                                    <button class="buy-now-click hover_button-red" name="product-option" value="buy-now">Mua ngay</button>
                                </div>
                            </div>
                    
                    </div>
                </div>
                <div class="control_pointer">
                    <div class="size_product">
                        <h3>Chọn size</h3>
                        <div class="size_product-pick">
                            <div class="size_product-pick--name arrSize">
                                <input class="option-size" type="radio" id="radio1" name="codeSizeTake" />
                                <label for="radio1">Size M</label>
                            </div>
                             <div class="size_product-pick--name arrSize">
                                <input class="option-size" type="radio" id="radio2" name="codeSizeTake" />
                                <label for="radio2">Size L</label>
                            </div>
 
                        </div>
                    </div>
                    <div class="size_product">
                        <h3>Chọn đường</h3>
                        <div class="size_product-pick">
                        <%
                        	for(Suger suger : arrSuger){
                        	
                        %>
                            <div class="size_product-pick--name">
                                <input class="option-size" value="<%= suger.getCodeSuger()%>" type="radio" id="<%= suger.getNameSuger() %>" name="type-suger" />
                                <label for="<%= suger.getCodeSuger() %>"><%= suger.getNameSuger() %></label>
                            </div>
                        <%
                        	}
                        %>
                        </div>
                    </div>
                    <div class="size_product">
                        <h3>Chọn topping</h3>
                        <%
                        	for(Topping topping: arrTopping){
                        		
                        %>
                        <div class="size_product-pick font_more-price">
                            <div class="three_pick-more">
								<div class="size_product-pick--name">
								    <input type="hidden" id="code-allTopping-take" name="allTopping">
								    <input class="option-size takeTopping" value="<%= topping.getPriceTopping()%>" type="checkbox" id="<%= topping.getNameTopping() %>" onchange='updateHiddenInput(this, "<%= topping.getNameTopping() %>")' name="optionTopping" />
								    <label for="<%= topping.getNameTopping() %>"><%= topping.getNameTopping() %></label>
								</div>
                            </div>
                            <p><%= proDAO.formatCurrency(topping.getPriceTopping()) %></p>
                        </div>
                        <% } %>
                    </div>
                </div>
               </form>
            </div>
        </div>
        <div class="title_center text_center background_boddy">
            <div class="category">
                <h3>Danh mục</h3>
                <ul class="category_list">
                        <a href="#">
                            <li class="category_list-item">
                                <p>Món nổi bật</p>
                            </li>
                        </a>
                        <%
                        	for(Category category : arrCategory){
                        		
                        %>
                        <li class="category_list-item menu_ts">
                            <div class="show_category-both">
                                <p><%=category.getNameCategory() %></p>
                                <div class="round-round">
                                    <i class="fa-solid fa-chevron-down"></i>
                                </div>
                            </div>
                            <div class="list-dentail" >
                                <ul>
                                <%
                                	String searchCategory = category.getCodeCategory() + "";
                                	ArrayList<Product> arrProductTmp = proDAO.selectProductCategory(searchCategory);
                                	for(Product product : arrProductTmp){
                             
                                		String urlCategoryURL = proDAO.takeFileImg(searchCategory);
                                %>
                                    <a href="<%= urlTop %>/products/store.jsp?product_id=<%= product.getCodeProduct() %>">
                                        <li class="list-dentail--item"><%= product.getNameProduct()%></li>
                                    </a>
                                <%
                            	}
                                %>
                                </ul>
                            </div>
                        </li>
                        <%}
                        %>

                </ul>
            </div>
            <div class="show_product">
                <div class="wrap_show wrap_show-break">
                	<% 
                		for(Product product : arrProduct){
                            String codeTakeImg = product.getCategory().getCodeCategory()+"";
                        	ProductDAO searchFile = new ProductDAO();
                        	
                	%>
                    <a class="a_dentail-product" onclick="showProductDetails('<%=product.getNameProduct() %>','<%=product.getSizeProduct().getSizeM()%>','<%=product.getSizeProduct().getSizeL()%>','<%=urlTop%>/img/<%= product.getImgProduct()%>','<%=product.getCodeProduct()%>')" >
                        <div class="wrap_show-all length_cart">
                            <div class="wrap_show-list width-category">
                                <img src="<%=urlTop %>/img/<%= product.getImgProduct()%>" alt="<%=product.getNameProduct() %>">
                            </div>
                            <div class="wrap_show-information plus_coversition max_with-store">
                                <span href="#" class="wrap_show-name--product"><%=product.getNameProduct() %></span>
                                <div class="wrap_show-price flex_cart">
                                    <p class="sync-money"><%= searchFile.formatCurrency(product.getSizeProduct().getSizeM()) %></p>
                                    <div class="border_circle">
                                        <i class="fa-solid fa-plus"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                    <%
                		}
                    %>
                </div>

            </div>
            <div class="cart-show">
            </div>
        </div>
    </div>
	<jsp:include page="../footer.jsp" />
    <script src="<%=urlTop %>/js/category.js"></script>
		    <%
				String product_id ="";
				product_id = request.getParameter("product_id");
	    		if(product_id != null){
				ProductDAO proTakeURL = new ProductDAO();
				Product productURL = proTakeURL.selectById(new Product(product_id,"","",null,null));
				String imgLink = productURL.getCategory().getCodeCategory()+"";
				String referTmg = proTakeURL.takeFileImg(imgLink);
						
		    %>
		    <script>
				 var checkProduct = <%=product_id %>
		 		 if( checkProduct !== ""){
		 			document.getElementById("product-details").style.display = "flex";
		 		    showProductDetails(
		 		           '<%= productURL.getNameProduct() %>',
		 		           '<%= productURL.getSizeProduct().getSizeM() %>',
		 		           '<%= productURL.getSizeProduct().getSizeL() %>',
		 		           '<%= urlTop %>/img/<%= productURL.getImgProduct() %>',
		 		           '<%= productURL.getCodeProduct() %>'
		 		       );
		 		 }
		    	
		    </script>
		    
		    <%} %>
            <script>
        	const notifyAmong =  document.querySelector(".notify_among");
        	var textNotifyAmong = document.querySelector(".text-notify_among")
  		    var progressBar = document.querySelector(".control_notify");
        	var notifyAmongValue = "<%=notifyTotal%>";
        	
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


    
    
</body>
</html>