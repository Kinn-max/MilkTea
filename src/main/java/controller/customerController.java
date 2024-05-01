package controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CustomerDAO;
import model.Customer;

/**
 * Servlet implementation class customerController
 */
@WebServlet("/customer")
public class customerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public customerController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestCustomer = request.getParameter("submit");
		if(requestCustomer.equals("register")) {
			registerCustomer(request,response);
		}
		if(requestCustomer.equals("signIn")) {
			signInCustomer(request,response);
			
		}
		if(requestCustomer.equals("change")) {
			changeCustomer(request,response);
			
		}
		if(requestCustomer.equals("update")) {
			updateCustomer(request,response);
			
		}
		if(requestCustomer.equals("log-out")) {	
			HttpSession session = request.getSession();
			session.invalidate();
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
			response.sendRedirect(url+"/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void registerCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String fullName = request.getParameter("fullname");
			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirm_password = request.getParameter("confirm_password");
			String url ="";

			String notifyUser = "";
			String notifyEmail = "";
			String notifyPassword ="";
			CustomerDAO cusDAO = new CustomerDAO();
			Customer customer = cusDAO.selectByUser( new Customer("",userName,"","","","",""));
			if (fullName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
			    String errorMessage = "Vui lòng nhập thông tin vào các trường!";
			    request.setAttribute("errorMessage", errorMessage);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/register.jsp");
			    dispatcher.forward(request, response);
			} else {
				if( customer != null) {
					notifyUser += "Tên đăng nhập đã tồn tại!";
				}
				CustomerDAO maiDAO = new CustomerDAO();
				Customer customerMai = maiDAO.selectByEmail( new Customer("","","","","","",email));
				if( customerMai != null) {
					notifyEmail += "Email đã tồn tại!";
				}
				if(!password.equals(confirm_password)) {
					notifyPassword += "Vui lòng nhập đúng mật khẩu!";
				}
				if(notifyEmail.length()> 0 || notifyPassword.length()>0 || notifyUser.length() >0 ) {
					url +="/customers/register.jsp";
				}else {
				    LocalTime myObj = LocalTime.now();
				    String codeDate = myObj.toString();
				    String codeAP = "QWERTYUIOPASDFGHJKLZXCVBNM";
				    
			        Random random = new Random();
			        int randomNumber = random.nextInt(25) + 1;
			        String codeTotalCustomer = codeAP.charAt(randomNumber)+ codeDate + userName;
					Customer cusomerCorrect = new Customer(codeTotalCustomer,userName,password,fullName,"","",email);
					CustomerDAO addCustomer = new CustomerDAO();
					addCustomer.insert(cusomerCorrect);
					url += "/customers/signIn.jsp";
				}
				request.setAttribute("notifyUser", notifyUser);
				request.setAttribute("notifyEmail", notifyEmail);
				request.setAttribute("notifyPassword", notifyPassword);
				request.setAttribute("fullName", fullName);
				request.setAttribute("userName", userName);
				request.setAttribute("email", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void signInCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			 String url = "";
			CustomerDAO cusDAO = new CustomerDAO();
			Customer customerCorrect = cusDAO.selectByUser(new Customer("",userName,"","","","",""));
			String notifyTotal= "";
			if(customerCorrect != null) {
				if((userName.equals(customerCorrect.getUserCustomer()) || userName.equals(customerCorrect.getEmail())) && passWord.equals(customerCorrect.getPassword()) ){
					HttpSession session = request.getSession();
					session.setAttribute("customer",customerCorrect );
					url+= "/index.jsp";
				}else {
					notifyTotal = "Tài khoản hoặc mật khẩu không đúng!";
					url+= "/customers/signIn.jsp";
				}
			}else {
					notifyTotal = "Tài khoản hoặc mật khẩu không đúng!";
					url+= "/customers/signIn.jsp";
			}
			request.setAttribute("notifyTotal", notifyTotal);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void changeCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String passWordPresent = request.getParameter("password-present");
			String passWordNew= request.getParameter("password-new");
			String passWordConfirm = request.getParameter("password-confirm");
	        HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			CustomerDAO cusDAO = new CustomerDAO();
			String url = "/customers/change.jsp";
			String notify ="";
			if(customer != null) {
				if(passWordPresent.equals(customer.getPassword()) && passWordNew.equals(passWordConfirm)){
					customer.setPassword(passWordConfirm);
					cusDAO.update(customer);
					notify += "Thay đổi mật khẩu thành công!";
				}else {
					notify+= "Vui lòng nhập đúng";
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
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
	
			String fullName = request.getParameter("fullname");
			String phoneNumber = request.getParameter("phone-number");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
	        HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			CustomerDAO cusDAO = new CustomerDAO();
			String url = "/customers/update.jsp";
			String notify ="";
			if(customer != null) {
				customer.setfullName(fullName);
				customer.setphoneNumber(phoneNumber);
				customer.setEmail(email);;
				customer.setAddress(address);
				cusDAO.update(customer);
				notify+= "Cập nhật thông tin thành công!";
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
