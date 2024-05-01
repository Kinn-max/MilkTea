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
import database.OrderDAO;
import database.ProductDAO;
import database.SugerDAO;
import model.Customer;
import model.Order;
import model.Product;
import model.Suger;

/**
 * Servlet implementation class productController
 */
@WebServlet("/products")
public class productController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String productOption = request.getParameter("product-option");
		if(productOption.equals("add-cart") ) {
			addCart(request,response);
		}
		if(productOption.equals("buy-now")) {
			buyNow(request,response);
		}
		if(productOption.equals("search")) {
			searchGlass(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void buyNow(HttpServletRequest request, HttpServletResponse response) {
		try {
		    LocalTime myObj = LocalTime.now();
		    String codeDate = myObj.toString();
		    String codeAP = "QWERTYUIOPASDFGHJKLZXCVBNM";
		    
	        Random random = new Random();
	        int randomNumber = random.nextInt(25) + 1;
	        String codeTotalOrder = "order_"+codeAP.charAt(randomNumber)+ codeDate;
	        String url = "";
	        String notify = "";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            Customer customer = (Customer) session.getAttribute("customer");
	            if (customer != null) {
	            	
	          		String codeCustomer = customer.getCodeCustomer();
	        		String codeProduct = request.getParameter("codeProductTake");
	        		String priceSizeProduct = request.getParameter("codeSizeTake");
	        		String quantity = request.getParameter("codeQuantityTake");
	        		String suger = request.getParameter("type-suger");
	        		String selectedToppings = request.getParameter("allTopping");
	        		String priceTotal = request.getParameter("codePriceTake");
	        		String priceTopping = request.getParameter("codePriceAfterTake");
	        		int quantityCorrect = Integer.parseInt(quantity);
	        		double priceTotalCorrect = Double.parseDouble(priceTotal);
	        		double priceToppingCorrect = Double.parseDouble(priceTopping);
	        		// in product contain sizeProduct
	        		CustomerDAO cusDAO = new CustomerDAO();
	        		Customer customerCorrect = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
	        		ProductDAO proDAO = new ProductDAO();
	        		Product productCorrect = proDAO.selectById(new Product(codeProduct,"","",null,null));
	        		SugerDAO suDAO = new SugerDAO();
	        		Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
	        		
	        		OrderDAO orDAO = new OrderDAO();
	        		Order order = new Order(codeTotalOrder,customerCorrect,productCorrect,priceSizeProduct,quantityCorrect,sugerCorrect,selectedToppings,priceTotalCorrect,priceToppingCorrect,0);
	        		orDAO.insert(order);

	        		url+="/products/cart.jsp"; 
	            }else {
	              	url+="/customers/signIn.jsp"; 
	            }
	        } else {
	        	url+="/customers/signIn.jsp"; 
	        }
	    	request.setAttribute("notify", notify);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void addCart(HttpServletRequest request, HttpServletResponse response) {
		try {
		    LocalTime myObj = LocalTime.now();
		    String codeDate = myObj.toString();
		    String codeAP = "QWERTYUIOPASDFGHJKLZXCVBNM";
		    
	        Random random = new Random();
	        int randomNumber = random.nextInt(25) + 1;
	        String codeTotalOrder = "order_"+codeAP.charAt(randomNumber)+ codeDate;
	        String url = "";
	        String notify = "";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            Customer customer = (Customer) session.getAttribute("customer");
	            if (customer != null) {
	    
	          		String codeCustomer = customer.getCodeCustomer();
	        		String codeProduct = request.getParameter("codeProductTake");
	        		String priceSizeProduct = request.getParameter("codeSizeTake");
	        		String quantity = request.getParameter("codeQuantityTake");
	        		String suger = request.getParameter("type-suger");
	        		String selectedToppings = request.getParameter("allTopping");
	        		String priceTotal = request.getParameter("codePriceTake");
	        		String priceTopping = request.getParameter("codePriceAfterTake");
	        		int quantityCorrect = Integer.parseInt(quantity);
	        		double priceTotalCorrect = Double.parseDouble(priceTotal);
	        		double priceToppingCorrect = Double.parseDouble(priceTopping);
	        		// in product contain sizeProduct
	        		CustomerDAO cusDAO = new CustomerDAO();
	        		Customer customerCorrect = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
	        		ProductDAO proDAO = new ProductDAO();
	        		Product productCorrect = proDAO.selectById(new Product(codeProduct,"","",null,null));
	        		SugerDAO suDAO = new SugerDAO();
	        		Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
	        		
	        		OrderDAO orDAO = new OrderDAO();
	        		Order order = new Order(codeTotalOrder,customerCorrect,productCorrect,priceSizeProduct,quantityCorrect,sugerCorrect,selectedToppings,priceTotalCorrect,priceToppingCorrect,0);
	        		orDAO.insert(order);

	        		notify +="Thêm vào giỏ hàng thành công!";
	        		url+="/products/store.jsp"; 
	            }else {
	              	url+="/customers/signIn.jsp"; 
	            }
	        } else {
	        	url+="/customers/signIn.jsp"; 
	        }
	    	request.setAttribute("notify", notify);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void searchGlass(HttpServletRequest request, HttpServletResponse response) {
		try {
			String searchValue = request.getParameter("s");
			request.setAttribute("searchValue", searchValue);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/products/search.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
