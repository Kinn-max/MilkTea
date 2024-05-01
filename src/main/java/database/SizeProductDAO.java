package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SizeProduct;

public class SizeProductDAO implements InterfaceDAO<SizeProduct> {
	@Override
	public ArrayList<SizeProduct> selectAll() {
		ArrayList<SizeProduct> result = new ArrayList<SizeProduct>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from sizeproduct";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeSize = rs.getString("codesize");
				Double sizeM = rs.getDouble("sizem");
				Double sizeL = rs.getDouble("sizel");
				SizeProduct SizeProduct = new SizeProduct(codeSize, sizeM, sizeL);
				result.add(SizeProduct);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public SizeProduct selectById(SizeProduct t) {
		SizeProduct result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from sizeproduct WHERE codesize=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSize());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeSize = rs.getString("codesize");
				Double sizeM = rs.getDouble("sizem");
				Double sizeL = rs.getDouble("sizel");
				SizeProduct SizeProduct = new SizeProduct(codeSize, sizeM, sizeL);
				result = SizeProduct;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insert(SizeProduct t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO sizeproduct(codesize,sizem,sizel) VALUES(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSize());
			st.setDouble(2, t.getSizeM());
			st.setDouble(3, t.getSizeL());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<SizeProduct> arr) {
		int result = 0;
		for(SizeProduct SizeProduct : arr) {
			result += this.insert(SizeProduct);
		}
		
		return result;
	}

	@Override
	public int delete(SizeProduct t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from sizeproduct WHERE codesize=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSize());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<SizeProduct> arr) {
		int result = 0;
		for(SizeProduct SizeProduct : arr) {
			result += this.delete(SizeProduct);
		}
		return result;
	}

	@Override
	public int update(SizeProduct t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE sizeproduct SET sizem=?, sizel=? WHERE codesize=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setDouble(1, t.getSizeM());
			st.setDouble(2, t.getSizeL());
			st.setString(3, t.getCodeSize());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
