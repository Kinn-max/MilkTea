package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Suger;

public class SugerDAO implements InterfaceDAO<Suger> {

	@Override
	public ArrayList<Suger> selectAll() {
		ArrayList<Suger> result = new ArrayList<Suger>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from suger";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeSuger = rs.getString("codesuger");
				String nameSuger = rs.getString("namesuger");
				Suger Suger = new Suger(codeSuger, nameSuger);
				result.add(Suger);
			}
		con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Suger selectById(Suger t) {
		Suger result = null;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from suger WHERE codesuger=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSuger());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeSuger = rs.getString("codesuger");
				String nameSuger = rs.getString("namesuger");
				Suger Suger = new Suger(codeSuger, nameSuger);
				result = Suger;
				break;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int insert(Suger t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "INSERT INTO suger(codesuger,namesuger) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSuger());
			st.setString(2, t.getNameSuger());
			
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Suger> arr) {
		int result = 0;
		for(Suger suger : arr) {
			result += this.insert(suger);
		}
		
		return result;
	}

	@Override
	public int delete(Suger t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "DELETE from suger WHERE codesuger=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCodeSuger());	
			result = st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Suger> arr) {
		int result = 0;
		for(Suger suger : arr) {
			result += this.delete(suger);
		}
		return result;
	}

	@Override
	public int update(Suger t) {
		int result = 0;
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "UPDATE suger SET namesuger=? WHERE codesuger=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getNameSuger());
			st.setString(2, t.getCodeSuger());
			result = st.executeUpdate();
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
