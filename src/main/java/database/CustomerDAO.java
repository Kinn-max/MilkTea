package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;

public class CustomerDAO implements InterfaceDAO<Customer> {

		@Override
		public ArrayList<Customer> selectAll() {
			ArrayList<Customer> result = new ArrayList<Customer>();
			
			try {
				Connection con = JDBC_connect.getConnection();
				String sql = "SELECT * from customer";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					String codeCustomer = rs.getString("codecustomer");
					String userCustomer = rs.getString("usercustomer");
					String password = rs.getString("password");
					String fullName = rs.getString("fullname");
					String address = rs.getString("address");
					String phoneNumber = rs.getString("phonenumber");
					String email = rs.getString("email");
					Customer Customer = new Customer(codeCustomer, userCustomer, password, fullName, address, phoneNumber, email);
					result.add(Customer);
				}
				con.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}


	@Override
	public Customer selectById(Customer t) {
		Customer result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from customer WHERE codecustomer=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,t.getCodeCustomer());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCustomer = rs.getString("codecustomer");
				String userCustomer = rs.getString("usercustomer");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phonenumber");
				String email = rs.getString("email");
				Customer Customer = new Customer(codeCustomer, userCustomer, password, fullName, address, phoneNumber, email);
				result = Customer;
				break;
			}
			con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insert(Customer t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO customer(codecustomer,usercustomer,password,fullname,address,phonenumber,email) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeCustomer());
			st.setString(2, t.getUserCustomer());
			st.setString(3, t.getPassword());
			st.setString(4, t.getfullName());
			st.setString(5, t.getAddress());
			st.setString(6, t.getphoneNumber());
			st.setString(7, t.getEmail());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Customer> arr) {
		int result = 0;
		for(Customer customer : arr) {
			result += this.insert(customer);
		}
		
		return result;
	}

	@Override
	public int delete(Customer t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from customer WHERE codecustomer=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeCustomer());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Customer> arr) {
		int result = 0;
		for(Customer customer : arr) {
			result += this.delete(customer);
		}
		return result;
	}

	@Override
	public int update(Customer t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE customer SET usercustomer=?, password=?, fullname=?, address=?, phonenumber=?, email=? WHERE codecustomer=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,t.getUserCustomer());
			st.setString(2, t.getPassword());
			st.setString(3,t.getfullName());
			st.setString(4,t.getAddress());
			st.setString(5,t.getphoneNumber());
			st.setString(6,t.getEmail());
			st.setString(7,t.getCodeCustomer());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Customer selectByUser(Customer t) {
		Customer result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from `customer` WHERE usercustomer=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,t.getUserCustomer());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCustomer = rs.getString("codecustomer");
				String userCustomer = rs.getString("usercustomer");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phonenumber");
				String email = rs.getString("email");
				Customer Customer = new Customer(codeCustomer, userCustomer, password, fullName, address, phoneNumber, email);
				result = Customer;
				break;
			}
			con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public Customer selectByEmail(Customer t) {
		Customer result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from customer WHERE email=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,t.getEmail());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCustomer = rs.getString("codecustomer");
				String userCustomer = rs.getString("usercustomer");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phonenumber");
				String email = rs.getString("email");
				Customer Customer = new Customer(codeCustomer, userCustomer, password, fullName, address, phoneNumber, email);
				result = Customer;
				break;
			}
			con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	

}
