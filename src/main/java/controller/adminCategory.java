package controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.CategoryDAO;
import model.Category;

/**
 * Servlet implementation class adminCategory
 */
@MultipartConfig
@WebServlet("/admin-category")
public class adminCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("o");
		if(option.equals("delete")) {
			deleteCategory(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("o");
		if(option.equals("add-category")) {
			addCategory(request,response);
		}
		if(option.equals("update-category")){
			updateCategory(request,response);
		}
		

	}
	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeCategory = request.getParameter("code-category");
			String nameCategory = request.getParameter("name-category");
			Part part = request.getPart("file-category");
			
			String folder = getServletContext().getRealPath("img");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			String notify = "";
			if(codeCategory.equals(null) || nameCategory.equals(null) || fileName.equals(null)) {
				notify+= "Vui lòng nhập đủ các trường!";
			}else {
				if(!Files.exists(Path.of(folder))) {
					Files.createDirectories(Path.of(folder));
				}
				part.write(folder+"/"+fileName);
				notify +="Thêm danh mục: " + nameCategory +" thành công!";
				Category category = new Category(codeCategory,fileName,nameCategory);
				CategoryDAO cateDAO = new CategoryDAO();
				cateDAO.insert(category);
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq  = request.getRequestDispatcher("admin/index.jsp?option=add-category");
			rq.forward(request, response);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeCategory = request.getParameter("code-category");
			String nameCategory = request.getParameter("name-category");
			Part part = request.getPart("file-category");
			
			String folder = getServletContext().getRealPath("img");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			String notify = "";
			if(codeCategory.equals(null) || nameCategory.equals(null) || fileName.equals(null)) {
				notify+= "Vui lòng nhập đủ các trường!";
			}else {
				if(!Files.exists(Path.of(folder))) {
					Files.createDirectories(Path.of(folder));
				}
				part.write(folder+"/"+fileName);
				notify +="Cập nhật danh mục: " + nameCategory +" thành công!";
				Category category = new Category(codeCategory,fileName,nameCategory);
				CategoryDAO cateDAO = new CategoryDAO();
				cateDAO.update(category);
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq  = request.getRequestDispatcher("admin/index.jsp?option=list-category");
			rq.forward(request, response);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeCategory = request.getParameter("id_category");
			String notify = "";
			if(!codeCategory.equals(null)) {
				Category category = new Category(codeCategory,"","");
				CategoryDAO caDAO =  new CategoryDAO();
				category = caDAO.selectById(category);
				
				notify+= "Đã xóa danh mục " + category.getNameCategory() +" thành công !";
				caDAO.delete(category);
				
			}
			request.setAttribute("notify", notify);
			RequestDispatcher sq = request.getRequestDispatcher("/admin/index.jsp?option=list-category");
			sq.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	}


