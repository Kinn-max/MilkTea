package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DetailOrder;
import model.Order;


public class DetailOrderDAO implements InterfaceDAO<DetailOrder> {

	@Override
	public ArrayList<DetailOrder> selectAll() {
		ArrayList<DetailOrder> result = new ArrayList<DetailOrder>();
		try {
			Connection con = JDBC_connect.getConnection();
			String  sql = "SELECT * from `detailorder`";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeDetailOrder = rs.getString("codedetailorder");
				String order = rs.getString("order");
				String consignneeName = rs.getString("consigneename");
				String phoneNumber = rs.getString("phonenumber");
				String deliveryAddress = rs.getString("deliveryaddress");
				String note = rs.getString("note");
				String payment = rs.getString("payment");
				Date dateOrder = rs.getDate("dateorder");
				OrderDAO orDAO = new OrderDAO();
				Order orderCorrect = orDAO.selectById(new Order(order,null,null,"",0,null,"",0,0,0));
				
				DetailOrder detailOrder = new DetailOrder(codeDetailOrder, orderCorrect, consignneeName, phoneNumber, deliveryAddress, note, payment, dateOrder);
				result.add(detailOrder);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public DetailOrder selectById(DetailOrder t) {
		DetailOrder result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String  sql = "SELECT * from `detailorder` WHERE codedetailorder=? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,t.getCodeDetailOrder());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeDetailOrder = rs.getString("codedetailorder");
				String order = rs.getString("order");
				String consignneeName = rs.getString("consignnename");
				String phoneNumber = rs.getString("phonenumber");
				String deliveryAddress = rs.getString("deliveryaddress");
				String note = rs.getString("note");
				String payment = rs.getString("payment");
				Date dateOrder = rs.getDate("dateorder");
				OrderDAO orDAO = new OrderDAO();
				Order orderCorrect = orDAO.selectById(new Order(order,null,null,"",0,null,"",0,0,0));
				
				DetailOrder detailOrder = new DetailOrder(codeDetailOrder, orderCorrect, consignneeName, phoneNumber, deliveryAddress, note, payment, dateOrder);
				result = detailOrder;
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
	public int insert(DetailOrder t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO detailorder(codedetailorder,`order`,consigneename,phonenumber,deliveryaddress,note,payment,dateorder) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeDetailOrder());
			st.setString(2, t.getOrder().getCodeOrder());
			st.setString(3, t.getConsignneeName());
			st.setString(4, t.getPhoneNumber());
			st.setString(5, t.getDeliveryAddress());
			st.setString(6, t.getNote());
			st.setString(7, t.getPayment());
			st.setDate(8, t.getDateOrder());
			result = st.executeUpdate();
			con.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insertAll(ArrayList<DetailOrder> arr) {
		int result = 0;
		for(DetailOrder detailOrder : arr) {
			result += this.insert(detailOrder);
		}
		
		return result;
	}

	@Override
	public int delete(DetailOrder t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from `detailorder` WHERE codedetailorder=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeDetailOrder());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<DetailOrder> arr) {
		int result = 0;
		for(DetailOrder detailOrder : arr) {
			result += this.delete(detailOrder);
		}
		return result;
	}

	@Override
	public int update(DetailOrder t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE `detailorder` SET order=?,consigneename=?,phonenumber=?,deliveryaddress=?,note=?,payment=?,dateorder=? WHERE codedetailorder=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, t.getOrder().getCodeOrder());
			st.setString(2, t.getConsignneeName());
			st.setString(3, t.getPhoneNumber());
			st.setString(4, t.getDeliveryAddress());
			st.setString(5, t.getNote());
			st.setString(6, t.getPayment());
			st.setDate(7, t.getDateOrder());
			st.setString(8, t.getCodeDetailOrder());
			result = st.executeUpdate();
			con.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;

	}
	public ArrayList<DetailOrder> selectArrayid(DetailOrder t ) {
		ArrayList<DetailOrder> result = new ArrayList<DetailOrder>();
		try {
			Connection con = JDBC_connect.getConnection();
			String  sql = "SELECT * from `detailorder` WHERE codedetailorder =?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeDetailOrder());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeDetailOrder = rs.getString("codedetailorder");
				String order = rs.getString("order");
				String consignneeName = rs.getString("consigneename");
				String phoneNumber = rs.getString("phonenumber");
				String deliveryAddress = rs.getString("deliveryaddress");
				String note = rs.getString("note");
				String payment = rs.getString("payment");
				Date dateOrder = rs.getDate("dateorder");
				OrderDAO orDAO = new OrderDAO();
				Order orderCorrect = orDAO.selectById(new Order(order,null,null,"",0,null,"",0,0,0));
				
				DetailOrder detailOrder = new DetailOrder(codeDetailOrder, orderCorrect, consignneeName, phoneNumber, deliveryAddress, note, payment, dateOrder);
				result.add(detailOrder);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}

}
