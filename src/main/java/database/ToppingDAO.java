package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Topping;

public class ToppingDAO implements InterfaceDAO<Topping> {

	@Override
	public ArrayList<Topping> selectAll() {
		ArrayList<Topping> result = new ArrayList<Topping>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from topping";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeTopping = rs.getString("codetopping");
				String nameTopping = rs.getString("nametopping");
				Double priceTopping = rs.getDouble("pricetopping");
				Topping Topping = new Topping(codeTopping, nameTopping, priceTopping);
				result.add(Topping);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Topping selectById(Topping t) {
		Topping result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from topping WHERE codetopping=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeTopping());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeTopping = rs.getString("codetopping");
				String nameTopping = rs.getString("nametopping");
				Double priceTopping = rs.getDouble("pricetopping");
				Topping Topping = new Topping(codeTopping, nameTopping, priceTopping);
				result = Topping;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insert(Topping t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO topping(codetopping,nametopping,pricetopping) VALUES(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeTopping());
			st.setString(2, t.getNameTopping());
			st.setDouble(3, t.getPriceTopping());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Topping> arr) {
		int result = 0;
		for(Topping topping : arr) {
			result += this.insert(topping);
		}
		
		return result;
	}

	@Override
	public int delete(Topping t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from topping WHERE codetopping=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeTopping());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Topping> arr) {
		int result = 0;
		for(Topping topping : arr) {
			result += this.delete(topping);
		}
		return result;
	}

	@Override
	public int update(Topping t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE topping SET nametopping=?, pricetopping=? WHERE codetopping=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getNameTopping());
			st.setDouble(2, t.getPriceTopping());
			st.setString(3, t.getCodeTopping());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
