package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ToppingDAO;
import model.Topping;

/**
 * Servlet implementation class adminTopping
 */
@WebServlet("/admin-topping")
public class adminTopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminTopping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		if(option.equals("more")) {
			addTopping(request,response);
		}
	}
	private void addTopping(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codeTopping = request.getParameter("code-topping");
			String nameTopping = request.getParameter("name-topping");
			String priceTopping = request.getParameter("price-topping");
			double price = Double.parseDouble(priceTopping);
			String notify = "";
			if(codeTopping.equals(null) || nameTopping.equals(null) || priceTopping.equals(null)) {
				notify += "Vui lòng kiểm tra lại các trường";
			}else {
				Topping topping = new Topping(codeTopping, nameTopping, price);
				ToppingDAO topDAO = new ToppingDAO();
				topDAO.insert(topping);
				notify += "Thêm topping " +nameTopping +" thành công!";
			}
			request.setAttribute("notify", notify);
			RequestDispatcher rq = request.getRequestDispatcher("/admin/index.jsp?option=list-topping");
			rq.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
