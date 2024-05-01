<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String urlTop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>
        <div class="category category-ad">
            <div class="both_first">
                <h3>Danh mục</h3>
                <div class="click_option">
                    <i class="fa-solid fa-bars"></i>
                </div>
            </div>
            <ul class="category_list">
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <div class="both_icon">
                                <i class="fa-solid fa-bell"></i>
                                <p>Đơn hàng hôm nay</p>
                            </div>
                            <div class="round-round">
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>
                        </div>
                        <div class="list-dentail" >
                            <ul>
                                <a href="<%=urlTop %>/admin/index.jsp?option=list-order">
                                    <li class="list-dentail--item">Đã đặt hàng</li>
                                </a>
                                <li class="list-dentail--item">Đã bán</li>
                                <li class="list-dentail--item">Đã hủy</li>
                            </ul>
                        </div>
                    </li>
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <div class="both_icon">
                                <i class="fa-solid fa-calendar-week"></i>
                                <p>Tùy chọn danh mục</p>
                            </div>
                            <div class="round-round">
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>
                        </div>
                        <div class="list-dentail" >
                            <ul>
                                <a href="<%=urlTop %>/admin/index.jsp?option=add-category">
                                    <li class="list-dentail--item">Thêm danh mục</li>
                                </a>
                                 <a href="<%=urlTop %>/admin/index.jsp?option=list-category">
	                                <li class="list-dentail--item">Xóa hoặc sửa danh mục</li>
                                </a>
                            </ul>
                        </div>
                    </li>
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <div class="both_icon">
                                <i class="fa-solid fa-layer-group"></i>
                                <p>Tùy chọn sản phẩm</p>
                            </div>
                            <div class="round-round">
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>
                        </div>
                        <div class="list-dentail" >
                            <ul>
                            	<a href="<%=urlTop %>/admin/index.jsp?option=add-product">
	                                <li class="list-dentail--item">Thêm sản phẩm</li>
                            	</a>
                            	<a href="<%=urlTop %>/admin/index.jsp?option=list-product">
	                                <li class="list-dentail--item">Xóa hoặc sửa sản phẩm</li>
                            	</a>
                            </ul>
                        </div>
                    </li>
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <a href="<%=urlTop %>/admin/index.jsp?option=list-size">
                                 <div class="both_icon">
                                    <i class="fa-solid fa-square"></i>
                                    <p>Tùy chọn giá thuộc size</p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <a href="<%=urlTop %>/admin/index.jsp?option=list-topping">
                                <div class="both_icon">
                                    <i class="fa-solid fa-ranking-star"></i>
                                    <p>Tùy chọn topping</p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <a href="<%=urlTop %>/admin/index.jsp?option=list-slide">
                                <div class="both_icon">
                                    <i class="fa-solid fa-sliders"></i>
                                    <p>Sửa slide</p>
                                </div>
                            </a>
                        </div>
                    </li>
                     <li class="category_list-item menu_ts">
                        <div class="show_category-both">
                            <div class="both_icon">
                                <div class="both_icon">
                                    <i class="fa-solid fa-users"></i>
                                    <p>Quản lý khách hàng</p>
                                </div>
                            </div>
                            <div class="round-round">
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>
                        </div>
                        <div class="list-dentail" >
                            <ul>
                                <a href="<%=urlTop %>/admin/index.jsp?option=add-customer">
                                    <li class="list-dentail--item">Thêm khách hàng</li>
                                </a>
                                 <a href="<%=urlTop %>/admin/index.jsp?option=list-customer">
	                                <li class="list-dentail--item">Xóa hoặc chỉnh sửa khách hàng</li>
                                </a>
                            </ul>
                        </div>
                    </li>
            </ul>
        </div>