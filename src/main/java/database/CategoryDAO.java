package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;

public class CategoryDAO implements InterfaceDAO<Category> {

	@Override
	public ArrayList<Category> selectAll() {
		ArrayList<Category> result = new ArrayList<Category>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from category";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCategory = rs.getString("codecategory");
				String nameCategory = rs.getString("namecategory");
				String imgCategory = rs.getString("imgcategory");
				Category category = new Category(codeCategory, imgCategory, nameCategory);
				result.add(category);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Category selectById(Category t) {
		Category result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from category WHERE codecategory=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeCategory());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCategory = rs.getString("codecategory");
				String nameCategory = rs.getString("namecategory");
				String imgCategory = rs.getString("imgcategory");
				Category category = new Category(codeCategory, imgCategory, nameCategory);
				result = category;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insert(Category t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO category(codecategory,namecategory,imgcategory) VALUES(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeCategory());
			st.setString(2, t.getNameCategory());
			st.setString(3, t.getImgCategory());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Category> arr) {
		int result = 0;
		for(Category category : arr) {
			result += this.insert(category);
		}
		
		return result;
	}

	@Override
	public int delete(Category t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from category WHERE codecategory=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeCategory());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Category> arr) {
		int result = 0;
		for(Category category : arr) {
			result += this.delete(category);
		}
		return result;
	}

	@Override
	public int update(Category t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE category SET namecategory=?, imgcategory=? WHERE codecategory=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getNameCategory());
			st.setString(2, t.getImgCategory());
			st.setString(3, t.getCodeCategory());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Category> selectCategoryLIMIT() {
		ArrayList<Category> result = new ArrayList<Category>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from category ORDER BY RAND() LIMIT 3";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeCategory = rs.getString("codecategory");
				String nameCategory = rs.getString("namecategory");
				String imgCategory = rs.getString("imgcategory");
				Category category = new Category(codeCategory, imgCategory, nameCategory);
				result.add(category);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
