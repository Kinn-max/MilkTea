package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.CategoryDAO;
import model.Category;

/**
 * Servlet implementation class test
 */
@MultipartConfig
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq  = request.getRequestDispatcher("/test.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
				String codeDanhMuc = request.getParameter("code-category");
				String nameDanhMuc = request.getParameter("name-category");

				System.out.println(codeDanhMuc);
				System.out.println(nameDanhMuc);
				request.setAttribute("codeDanhMuc", codeDanhMuc);
				request.setAttribute("nameDanhMuc", nameDanhMuc);
				RequestDispatcher rq  = request.getRequestDispatcher("/test.jsp");
				rq.forward(request, response);
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        

	}

}
