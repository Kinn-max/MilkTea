package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SlideIndex;

public class SlideIndexDAO implements InterfaceDAO<SlideIndex> {

	@Override
	public ArrayList<SlideIndex> selectAll() {
		ArrayList<SlideIndex> result = new ArrayList<SlideIndex>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from slideindex";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeImg = rs.getString("codeimg");
				String takeImg = rs.getString("takeimg");
				SlideIndex SlideIndex = new SlideIndex(codeImg, takeImg);
				result.add(SlideIndex);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public SlideIndex selectById(SlideIndex t) {
		SlideIndex result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from slideindex WHERE codeimg=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeImg());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeImg = rs.getString("codeimg");
				String takeImg = rs.getString("takeimg");
				SlideIndex SlideIndex = new SlideIndex(codeImg, takeImg);
				result = SlideIndex;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insert(SlideIndex t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO slideindex(codeimg,takeimg) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeImg());
			st.setString(2, t.getTakeImg());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<SlideIndex> arr) {
		int result = 0;
		for(SlideIndex slideIndex : arr) {
			result += this.insert(slideIndex);
		}
		
		return result;
	}

	@Override
	public int delete(SlideIndex t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from slideindex WHERE codeimg=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeImg());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<SlideIndex> arr) {
		int result = 0;
		for(SlideIndex slideIndex : arr) {
			result += this.delete(slideIndex);
		}
		return result;
	}

	@Override
	public int update(SlideIndex t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE slideindex SET takeimg=? WHERE codeimg=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTakeImg());
			st.setString(2, t.getCodeImg());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
