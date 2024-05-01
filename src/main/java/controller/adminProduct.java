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
import database.ProductDAO;
import database.SizeProductDAO;
import model.Category;
import model.Product;
import model.SizeProduct;

/**
 * Servlet implementation class adminProduct
 */
@MultipartConfig
@WebServlet("/admin-product")
public class adminProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminProduct() {
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
			deleteProduct(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("o");
		if(option.equals("add-product")) {
			addProduct(request,response);
		}
		if(option.equals("update-product")) {
			updateProduct(request,response);
		}
	}
	private void addProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeProduct = request.getParameter("codeproduct");
			String nameProduct = request.getParameter("nameproduct");
			String codeCategory = request.getParameter("codecategory");
			String codesize = request.getParameter("codesize");
			Part part = request.getPart("file-product");
			String notify = "";
			if(codeProduct.equals(null) || nameProduct.equals(null) || codeCategory.equals(null) || codesize.equals(null) || part.equals(null)) {
				notify+= "Vui lòng nhập đủ các trường!";
			}else {
				String folder = getServletContext().getRealPath("img");
				String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if(!Files.exists(Path.of(folder))) {
					Files.createDirectories(Path.of(folder));
				}
				part.write(folder+"/"+fileName);
			
				ProductDAO proDAO = new ProductDAO();
				CategoryDAO cateDAO = new CategoryDAO();
				SizeProductDAO sizeDAO = new SizeProductDAO();
				
				Category category =  cateDAO.selectById(new Category(codeCategory,"",""));
				SizeProduct sizeProduct = sizeDAO.selectById(new SizeProduct(codesize,0,0));
				Product product = new Product(codeProduct, nameProduct, fileName, category, sizeProduct);
				int count = proDAO.insert(product);
				if(count != 0) {
					notify +="Thêm sản phẩm: " + nameProduct +" thành công!";
				}else {
					notify +="Thêm sản phẩm lỗi!";
				}
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq  = request.getRequestDispatcher("admin/index.jsp?option=add-product");
			rq.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeProduct = request.getParameter("codeproduct");
			String nameProduct = request.getParameter("nameproduct");
			String codeCategory = request.getParameter("codecategory");
			String codesize = request.getParameter("codesize");
			Part part = request.getPart("file-product");
			String notify = "";
			if(codeProduct.equals(null) || nameProduct.equals(null) || codeCategory.equals(null) || codesize.equals(null) || part.equals(null)) {
				notify+= "Vui lòng nhập đủ các trường!";
			}else {
				String folder = getServletContext().getRealPath("img");
				String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if(!Files.exists(Path.of(folder))) {
					Files.createDirectories(Path.of(folder));
				}
				part.write(folder+"/"+fileName);
			
				ProductDAO proDAO = new ProductDAO();
				CategoryDAO cateDAO = new CategoryDAO();
				SizeProductDAO sizeDAO = new SizeProductDAO();
				
				Category category =  cateDAO.selectById(new Category(codeCategory,"",""));
				SizeProduct sizeProduct = sizeDAO.selectById(new SizeProduct(codesize,0,0));
				Product product = new Product(codeProduct, nameProduct, fileName, category, sizeProduct);
				int count = proDAO.update(product);
				if(count != 0) {
					notify +="Cập nhật sản phẩm: " + nameProduct +" thành công!";
				}else {
					notify +="Cập nhật sản phẩm lỗi!";
				}
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq  = request.getRequestDispatcher("admin/index.jsp?option=list-product");
			rq.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeProduct = request.getParameter("id_product");
			String notify = "";
			if(!codeProduct.equals(null)) {
				ProductDAO proDAO = new ProductDAO();
				Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
				
				notify+= "Đã xóa sản phẩm: " + product.getNameProduct() +" thành công !";
				proDAO.delete(product);
				
			}
			request.setAttribute("notify", notify);
			RequestDispatcher sq = request.getRequestDispatcher("/admin/index.jsp?option=list-product");
			sq.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
