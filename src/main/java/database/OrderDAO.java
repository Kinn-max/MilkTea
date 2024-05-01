package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Order;
import model.Product;
import model.Suger;

public class OrderDAO implements InterfaceDAO<Order>{

	@Override
	public ArrayList<Order> selectAll() {
			ArrayList<Order> result = new ArrayList<Order>();
			try {
				Connection con = JDBC_connect.getConnection();
				String sql ="SELECT * from `order`";
				PreparedStatement  st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String codeOrder = rs.getString("codeorder");
					String codeCustomer = rs.getString("codecustomer");
					String codeProduct = rs.getString("codeproduct");
					String codeSize = rs.getString("codesize");
					int quantity = rs.getInt("quantity");
					String suger = rs.getString("suger");
					String fullNameTopping = rs.getString("fullnametopping");
					double totalPrice = rs.getDouble("totalprice");
					double priceTopping = rs.getDouble("pricetopping");
					int checkOrder = rs.getInt("checkorder");
					CustomerDAO cusDAO = new CustomerDAO();
					Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
					ProductDAO proDAO = new ProductDAO();
					Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
					SugerDAO suDAO = new SugerDAO();
					Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
					Order order = new Order(codeOrder, customer, product, codeSize, quantity, sugerCorrect, fullNameTopping,totalPrice,priceTopping,checkOrder );
					result.add(order);
				}
				con.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return result;
	}

	@Override
	public Order selectById(Order t) {
		Order result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql ="SELECT * from `order` WHERE codeorder=?";
			PreparedStatement  st = con.prepareStatement(sql);
			st.setString(1, t.getCodeOrder());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeOrder = rs.getString("codeorder");
				String codeCustomer = rs.getString("codecustomer");
				String codeProduct = rs.getString("codeproduct");
				String codeSize = rs.getString("codesize");
				int quantity = rs.getInt("quantity");
				String suger = rs.getString("suger");
				String fullNameTopping = rs.getString("fullnametopping");
				double totalPrice = rs.getDouble("totalprice");
				double priceTopping = rs.getDouble("pricetopping");
				int checkOrder = rs.getInt("checkorder");
				CustomerDAO cusDAO = new CustomerDAO();
				Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
				ProductDAO proDAO = new ProductDAO();
				Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
				SugerDAO suDAO = new SugerDAO();
				Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
				Order order = new Order(codeOrder, customer, product, codeSize, quantity, sugerCorrect, fullNameTopping,totalPrice,priceTopping,checkOrder );
				result = order;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
		
	}

	@Override
	public int insert(Order t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO `order`(codeorder,codecustomer,codeproduct,codesize,quantity,suger,fullnametopping,totalprice,priceTopping,checkorder) VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeOrder());
			st.setString(2, t.getCustomer().getCodeCustomer());
			st.setString(3, t.getProduct().getCodeProduct());
			st.setString(4, t.getSizeProduct());
			st.setInt(5, t.getQuantity());
			st.setString(6, t.getSuger().getCodeSuger());
			st.setString(7, t.getFullNameTopping());
			st.setDouble(8, t.getTotalPrice());
			st.setDouble(9, t.getPricetopping());
			st.setInt(10, t.getCheckOrder());
			result =st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insertAll(ArrayList<Order> arr) {
		int result = 0;
		for(Order order : arr) {
			result += this.insert(order);
		}
		
		return result;
	}

	@Override
	public int delete(Order t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from `order` WHERE codeorder=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeOrder());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Order> arr) {
		int result = 0;
		for(Order order : arr) {
			result += this.delete(order);
		}
		return result;
	}

	@Override
	public int update(Order t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE `order` SET codecustomer=?,codeproduct=?,codesize=?,quantity=?,suger=?,fullnametopping=?,totalprice=?,priceTopping=?,checkorder=?) WHERE codeorder=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomer().getCodeCustomer());
			st.setString(2, t.getProduct().getCodeProduct());
			st.setString(3, t.getSizeProduct());
			st.setInt(4, t.getQuantity());
			st.setString(5, t.getSuger().getCodeSuger());
			st.setString(6, t.getFullNameTopping());
			st.setDouble(7, t.getTotalPrice());
			st.setDouble(8, t.getPricetopping());
			st.setDouble(9, t.getCheckOrder());
			st.setString(10, t.getCodeOrder());
			result =st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	public ArrayList<Order> selectAllById(Order t) {
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			Connection con = JDBC_connect.getConnection();
			String sql ="SELECT * from `order` WHERE codecustomer=? LIMIT 3";
			
			PreparedStatement  st = con.prepareStatement(sql);
			st.setString(1,t.getCustomer().getCodeCustomer());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeOrder = rs.getString("codeorder");
				String codeCustomer = rs.getString("codecustomer");
				String codeProduct = rs.getString("codeproduct");
				String codeSize = rs.getString("codesize");
				int quantity = rs.getInt("quantity");
				String suger = rs.getString("suger");
				String fullNameTopping = rs.getString("fullnametopping");
				double totalPrice = rs.getDouble("totalprice");
				double priceTopping = rs.getDouble("pricetopping");
				int checkOrder = rs.getInt("checkorder");
				CustomerDAO cusDAO = new CustomerDAO();
				Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
				ProductDAO proDAO = new ProductDAO();
				Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
				SugerDAO suDAO = new SugerDAO();
				Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
				Order order = new Order(codeOrder, customer, product, codeSize, quantity, sugerCorrect, fullNameTopping,totalPrice,priceTopping,checkOrder );
				result.add(order);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
}
	public ArrayList<Order> selectOrderCustomer(Order t) {
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			Connection con = JDBC_connect.getConnection();
			String sql ="SELECT * from `order` WHERE codecustomer=? AND checkorder= 0";
			PreparedStatement  st = con.prepareStatement(sql);
			st.setString(1,t.getCustomer().getCodeCustomer());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeOrder = rs.getString("codeorder");
				String codeCustomer = rs.getString("codecustomer");
				String codeProduct = rs.getString("codeproduct");
				String codeSize = rs.getString("codesize");
				int quantity = rs.getInt("quantity");
				String suger = rs.getString("suger");
				String fullNameTopping = rs.getString("fullnametopping");
				double totalPrice = rs.getDouble("totalprice");
				double priceTopping = rs.getDouble("pricetopping");
				int checkOrder = rs.getInt("checkorder");
				CustomerDAO cusDAO = new CustomerDAO();
				Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
				ProductDAO proDAO = new ProductDAO();
				Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
				SugerDAO suDAO = new SugerDAO();
				Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
				Order order = new Order(codeOrder, customer, product, codeSize, quantity, sugerCorrect, fullNameTopping,totalPrice,priceTopping,checkOrder );
				result.add(order);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
}
	public ArrayList<Order> selectOrderCustomerCheck(Order t) {
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * FROM `order` WHERE codecustomer=? AND checkorder=1";
			PreparedStatement  st = con.prepareStatement(sql);
			st.setString(1,t.getCustomer().getCodeCustomer());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeOrder = rs.getString("codeorder");
				String codeCustomer = rs.getString("codecustomer");
				String codeProduct = rs.getString("codeproduct");
				String codeSize = rs.getString("codesize");
				int quantity = rs.getInt("quantity");
				String suger = rs.getString("suger");
				String fullNameTopping = rs.getString("fullnametopping");
				double totalPrice = rs.getDouble("totalprice");
				double priceTopping = rs.getDouble("pricetopping");
				int checkOrder = rs.getInt("checkorder");
				CustomerDAO cusDAO = new CustomerDAO();
				Customer customer = cusDAO.selectById(new Customer(codeCustomer,"","","","","",""));
				ProductDAO proDAO = new ProductDAO();
				Product product = proDAO.selectById(new Product(codeProduct,"","",null,null));
				SugerDAO suDAO = new SugerDAO();
				Suger sugerCorrect = suDAO.selectById(new Suger(suger,""));
				Order order = new Order(codeOrder, customer, product, codeSize, quantity, sugerCorrect, fullNameTopping,totalPrice,priceTopping,checkOrder );
				result.add(order);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
}
		public void updateCheckOrder(Order t) {
			try {
				Connection con = JDBC_connect.getConnection();
				String sql = "UPDATE `order` SET checkorder = 1 WHERE codeorder = ?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1,t.getCodeOrder());
				st.executeUpdate();
				con.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

}
