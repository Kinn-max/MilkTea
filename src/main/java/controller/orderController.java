package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contact.Email;
import database.DetailOrderDAO;
import database.OrderDAO;
import model.Customer;
import model.DetailOrder;
import model.Order;
import model.Product;

/**
 * Servlet implementation class orderController
 */
@WebServlet("/orders")
public class orderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String optionOrder = request.getParameter("order-option");
		if(optionOrder.equals("order-now")) {
			orderNow(request,response);
		}
		if(optionOrder.equals("delete")) {
			deleteOrder(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void orderNow(HttpServletRequest request, HttpServletResponse response) {
		try {
	        HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			OrderDAO orDAO = new OrderDAO();
			ArrayList<Order> arrOrder = orDAO.selectOrderCustomer(new Order("",customer,null,"",0,null,"",0,0,0));
			String notify = "";
			String url ="";
			if(arrOrder.isEmpty()) {
				notify += "Bạn chưa có sản phẩm nào";
				url+="/products/cart.jsp";
				
			}else {
		
				String consigneeName = request.getParameter("consigneename");
				String phoneNumber = request.getParameter("phonenumber");
				String deliveryAddress = request.getParameter("deliveryaddress");
				String note = request.getParameter("note");
				String payment = request.getParameter("payment");
			    String codeAP = "QWERTYUIOPASDFGHJKLZXCVBNM";
		        Random random = new Random();
		        int randomNumber = random.nextInt(25) + 1;
		        LocalTime myTime = LocalTime.now();
		        String codeTotalOrder = "detail_"+codeAP.charAt(randomNumber) + myTime.toString();
		        LocalDate myObj = LocalDate.now(); 
		        Date date = java.sql.Date.valueOf(myObj);
		        DetailOrderDAO detaiDAO = new DetailOrderDAO();
		        if(consigneeName.equals("") || phoneNumber.equals("") || deliveryAddress.equals("") || payment == null) {
		        	url+="/products/cart.jsp";
		        	notify += "Kiểm tra lại thông tin để chúng tôi có thể giao hàng đúng tới bạn!";
		        }else {
		        	for(Order order : arrOrder) {
		        		orDAO.updateCheckOrder(order);
		        		DetailOrder detailOrder = new DetailOrder(codeTotalOrder,order,consigneeName,phoneNumber,deliveryAddress,note,payment,date);
		        		detaiDAO.insert(detailOrder);
		        	}
	        		String emailTo = customer.getEmail();
	        		Email sendEmail = new Email();
	        		sendEmail.sendMessage(emailTo);
		        	 
		        	url += "/products/success.jsp";
		        }
				
				
			}
			request.setAttribute("notify", notify);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idOrder = request.getParameter("id_order");
			String notify = "";
	        HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			OrderDAO orDAO = new OrderDAO();
			String url = "";
			if(idOrder.equals("all")) {
				ArrayList<Order> arrOrder = orDAO.selectOrderCustomer(new Order("",customer,null,"",0,null,"",0,0,0));
				orDAO.deleteAll(arrOrder);
				url +="/products/cart.jsp";
				
			}
			else {
				Order order = new Order(idOrder,null,null,"",0,null,"",0,0,0);
				order =  orDAO.selectById(order);
				notify += "Đã xóa "+ order.getProduct().getNameProduct() +" trong giỏ hàng";
				orDAO.delete(order);
				url +="/products/cart.jsp";
			}
			request.setAttribute("notify", notify);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}
