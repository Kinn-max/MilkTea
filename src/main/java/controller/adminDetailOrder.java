package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DetailOrderDAO;
import model.DetailOrder;

/**
 * Servlet implementation class adminDetailOrder
 */
@WebServlet("/detail-order")
public class adminDetailOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDetailOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("o");
		if(option.equals("delete")) {
			deleteDetailOrder(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void deleteDetailOrder(HttpServletRequest request, HttpServletResponse response){
		try {
			String codeDetail = request.getParameter("id_orderDetail");
			DetailOrderDAO doDAO = new DetailOrderDAO();
			ArrayList<DetailOrder> arr =  doDAO.selectArrayid(new DetailOrder(codeDetail,null,"","","","","",null));
			int count  =  doDAO.deleteAll(arr);
			String notify = "";
			if(count !=0 ) {
				notify += "Đã xóa thành công một đơn hàng!";
			}else {
				notify += "Đã xảy ra lỗi!";
			}
			request.setAttribute("notify", notify);
			RequestDispatcher 	rq = request.getRequestDispatcher("admin/index.jsp?option=list-order");
			rq.forward(request, response);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
