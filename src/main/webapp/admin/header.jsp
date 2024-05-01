<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <% String urlTop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
 %>
    <header>
        <nav class="title">
            <div class="title_center">
                <div class="title_center-all">
                    <a href="<%=urlTop %>/admin/index.jsp">
                        <div class="img_shop">
                            <img src="<%=urlTop %>/img/logo.png" alt="" class="img_shop-picture">
                        </div>
                    </a>
                    <ul class="title_list">
                        <li class="title_list-item">
                            <a href="#" class="title_list-new">CHÀO MỪNG TỚI QUẢN LÝ WEBSITE KINN</a>
                        </li>
                    </ul>
                    <div class="both_right">
                        <div class="title_search">
                            <input type="text" class="title_search-ip" placeholder="Tìm kiếm!" >
                            <div class="title_search-glass">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </div>
                        </div>
                        <div class="title_signCart">
                            <div class="title_signCart-user">
                                <i class="fa-solid fa-user"></i>
                                <ul class="title_user-list">
                                    <li class="title_user-list-li">
                                        <a href="">Đăng ký</a>
                                    </li>
                                    <li class="title_user-list-li">
                                        <a href="">Đăng nhập</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="title_signCart-bar">
                                <i class="fa-solid fa-bars"></i>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
    
        </nav>

    </header>