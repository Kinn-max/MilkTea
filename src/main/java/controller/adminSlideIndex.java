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
import database.SlideIndexDAO;
import model.Category;
import model.SlideIndex;

/**
 * Servlet implementation class adminSlideIndex
 */
@MultipartConfig
@WebServlet("/admin-slide")
public class adminSlideIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminSlideIndex() {
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
			deleteSlide(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("o");
		if(option.equals("add-slide")) {
			addSlide(request,response);
		}

	}
	private void addSlide(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeSlide = request.getParameter("codeslide");
			Part part = request.getPart("file-slide");
			
			String folder = getServletContext().getRealPath("img");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			String notify = "";
			if(codeSlide.equals(null) || part.equals(null) || fileName.equals(null)) {
				notify+= "Vui lòng nhập đủ các trường!";
			}else {
				if(!Files.exists(Path.of(folder))) {
					Files.createDirectories(Path.of(folder));
				}
				part.write(folder+"/"+fileName);
				notify +="Thêm ảnh slide thành công!";
				SlideIndexDAO sliDAO = new SlideIndexDAO();
				SlideIndex slide = new SlideIndex(codeSlide, fileName);
				sliDAO.insert(slide);
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq  = request.getRequestDispatcher("admin/index.jsp?option=list-slide");
			rq.forward(request, response);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	private void deleteSlide(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeSlide = request.getParameter("id_slide");
			String notify = "";
			if(!codeSlide.equals(null)) {
				SlideIndexDAO sliDAO = new SlideIndexDAO();
				SlideIndex slide = new SlideIndex();
				slide.setCodeImg(codeSlide);
				
				notify+= "Đã xóa một ảnh slide thành công !";
				sliDAO.delete(slide);
				
			}
			request.setAttribute("notify", notify);
			RequestDispatcher sq = request.getRequestDispatcher("/admin/index.jsp?option=list-slide");
			sq.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
