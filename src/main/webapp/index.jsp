<%@page import="database.SlideIndexDAO"%>
<%@page import="model.SlideIndex"%>
<%@page import="model.Customer"%>
<%@page import="model.Category"%>
<%@page import="database.CategoryDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.ProductDAO"%>
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
    <link rel="icon page" type="jpg" href="img/the_end.jpg">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" href="Fonsomeone/fontawesome-free-6.2.0/css/all.min.css">
    <title>Cửa hàng Kinn</title>
</head>
<body>
    <div class="animte_change">
        <div class="wrapper">
            <div class="circle"></div>
            <div class="circle"></div>      
            <div class="circle"></div>
            <div class="shadow"></div>
            <div class="shadow"></div>
            <div class="shadow"></div>
        </div>
    </div>
    
		<!-- header -->
		<jsp:include page="header.jsp" />
       <%
   		ProductDAO proDAO = new ProductDAO();
       	ArrayList<Product> arrProductFour = proDAO.selectIndexFour();
       	CategoryDAO caDAO = new CategoryDAO();
       	ArrayList<Category> arrCategoryLimit = caDAO.selectCategoryLIMIT();
       	SlideIndexDAO sliDAO = new SlideIndexDAO();
   		
   
 		 %>
    <div class="wrap">
        <div class="title_center text_center">
            <div class="wrap_information">
                <div class="slideshow-container">
               	 <% ArrayList<SlideIndex>  slideArr = sliDAO.selectAll();
                       	for(SlideIndex slide : slideArr){
                       %>
                    <div class="mySlides fade">
                      <img src="<%=urlTop %>/img/<%=slide.getTakeImg() %>" >
                    </div>
                    <%} %>
                  </div>
                  <div class="center_text">
                        <h3>TEA & COFFE</h3>
                        <a href="products/store.jsp">
                            <span class="order_cetegory">VÀO ĐẶT HÀNG NGAY</span>
                        </a>
                        <span class="name_band">Kinn</span>
                    </div>
                </div>
                <div class="bottom_dot" >
                  <span class="dot" onclick="currentSlide(0)"></span> 
                  <span class="dot" onclick="currentSlide(1)"></span> 
                  <span class="dot" onclick="currentSlide(2)"></span> 
                </div>  

            </div>
            <div class="center_text-title">
                <h3 class="notify">SẢN PHẨM NỔI BẬT</h3>
                <p class="font_small">Sản phẩm được bình chọn yêu thích nhất</p>
            </div>
            <div class="wrap_show">
            <%
            	for(Product product : arrProductFour){
            		
    
            
            %>
                <a class="a_dentail-product"   href="<%=urlTop %>/products/store.jsp?product_id=<%=product.getCodeProduct() %>">
                    <div class="wrap_show-all">
                        <div class="wrap_show-list">
                        <%
                            String codeTakeImg = product.getCategory().getCodeCategory()+"";
                        	ProductDAO searchFile = new ProductDAO();

                            %>
                            <img loading="lazy" src="./img/<%=product.getImgProduct() %>" alt="<%=product.getNameProduct() %>">
                        </div>
                        <div class="wrap_show-information">
                            <span href="#" class="wrap_show-name--product"><%=product.getNameProduct() %></span>
                            <div class="wrap_show-price">
                                <p><%= searchFile.formatCurrency(product.getSizeProduct().getSizeM()) %></p>
                            </div>
                            <div>
                                <span class="order_add">Đặt hàng</span>
                            </div>
                        </div>
                        <div class="wrap_show-name">
                            <img loading="lazy" src="img/logo.png" alt="">
                        </div>
    
                    </div>
                </a>
                <%
            	}
                %>
 
                
              
            </div>
            <div class="center_text-title">
                <div class="more_infor">
                        <a href="products/store.jsp"class="more_infor-a" >XEM THÊM</a>
                </div >
                <h3 class="notify">NHÓM SẢN PHẨM ĐƯỢC QUAN TÂM</h3>
            </div>
            <div class="wrap_show">
            <%
            	for(Category category: arrCategoryLimit){
            		
            %>
            <a class="a_dentail-product"   href="products/store.jsp?category_id=<%=category.getCodeCategory() %>">
                <div class="wrap_show-all">
                    <div class="wrap_show-list">
                        <img loading="lazy" src="img/<%=category.getImgCategory() %>" alt="<%=category.getNameCategory() %>">
                    </div>
                    <div class="wrap_show-information">
                        <a href="#" class="wrap_show-name--product"><%=category.getNameCategory() %></a>
                    </div>
                </div>
             </a>
            <%
        		}
            %>
            </div>
            <div class="center_text-title">
                <h3 class="notify text_dark">VỀ CHÚNG tôi</h3>
                <div class="wrap_facebook-me">
                    <h3>COFFE AND TEA</h3>
                    <span>@trankien</span>
                </div>
            </div>
            <div class="about_me title_center">
                <div class="img_about-me">
                    <img src="./img/about_us_new.png" alt="">
                </div>
                <div class="text_about-me-all">
                    <h3>Về Chúng tôi</h3>
                    <p>Bên cạnh niềm tự hào về những ly trà sữa ngon sạch tươi, chúng tôi luôn tự tin mang đến khách hàng những trải nghiệm tốt nhất về dịch vụ và không gian.</p>
                    <a href="#">
                        <span class="order_add">Xem thêm</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="chat_bot">
            <div class="chat_bot-width">
                <img src="./img/the_end.jpg" alt="">
            </div>
        </div>
    </div>
    <!-- footer -->
	<%@include file="footer.jsp" %>


    <script src="js/style.js"></script>
    
</body>
</html>