package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Category;
import model.Product;
import model.SizeProduct;

public class ProductDAO implements InterfaceDAO<Product> {
	

	@Override
	public ArrayList<Product> selectAll() {
		ArrayList<Product> result = new ArrayList<Product>();
		
		try {
			Connection con = JDBC_connect.getConnection();
			String sql = "SELECT * from product";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codeProduct = rs.getString("codeproduct");
				String nameProduct = rs.getString("nameproduct");
				String imgProduct = rs.getString("imgproduct");
				String category = rs.getString("category");
				String sizeProduct = rs.getString("sizeproduct");
				
				CategoryDAO ctDAO = new CategoryDAO();
				Category ct = ctDAO.selectById(new Category(category,"",""));
				
				SizeProductDAO stDAO = new SizeProductDAO();
				SizeProduct sip = stDAO.selectById(new SizeProduct(sizeProduct,0,0));
				
				Product product = new Product(codeProduct, nameProduct, imgProduct, ct, sip);
				result.add(product);
				
				
				
			}
			con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


@Override
public Product selectById(Product t) {
	Product result = null;
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "SELECT * from product WHERE codeproduct=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,t.getCodeProduct());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			String codeProduct = rs.getString("codeproduct");
			String nameProduct = rs.getString("nameproduct");
			String imgProduct = rs.getString("imgproduct");
			String category = rs.getString("category");
			String sizeProduct = rs.getString("sizeproduct");
			
			CategoryDAO ctDAO = new CategoryDAO();
			Category ct = ctDAO.selectById(new Category(category,"",""));
			
			SizeProductDAO stDAO = new SizeProductDAO();
			SizeProduct sip = stDAO.selectById(new SizeProduct(sizeProduct,0,0));
	
			
			Product product = new Product(codeProduct, nameProduct, imgProduct, ct, sip);
			result = product;
			break;
		}
		con.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}

@Override
public int insert(Product t) {
	int result = 0;
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "INSERT INTO `product` (codeproduct, nameproduct, imgproduct, category, sizeproduct) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getCodeProduct());
		st.setString(2, t.getNameProduct());
		st.setString(3, t.getImgProduct());
		st.setString(4, t.getCategory().getCodeCategory());
		st.setString(5, t.getSizeProduct().getCodeSize());
		
		result = st.executeUpdate();
		con.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public int insertAll(ArrayList<Product> arr) {
	int result = 0;
	for(Product product : arr) {
		result += this.insert(product);
	}
	
	return result;
}

@Override
public int delete(Product t) {
	int result = 0;
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "DELETE from product WHERE codeproduct=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getCodeProduct());	
		result = st.executeUpdate();
		con.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return result;
}

@Override
public int deleteAll(ArrayList<Product> arr) {
	int result = 0;
	for(Product product : arr) {
		result += this.delete(product);
	}
	return result;
}

@Override
public int update(Product t) {
	int result = 0;
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "UPDATE product SET nameproduct=?, imgProduct=?, category=?, sizeProduct=? WHERE codeproduct=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,t.getNameProduct());
		st.setString(2, t.getImgProduct());
		st.setString(3,t.getCategory().getCodeCategory());
		st.setString(4,t.getSizeProduct().getCodeSize());
		st.setString(5,t.getCodeProduct());
		result = st.executeUpdate();
		
		con.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}
public ArrayList<Product> selectIndexFour() {
	ArrayList<Product> result = new ArrayList<Product>();
	
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "SELECT * from product ORDER BY RAND() LIMIT 4";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			String codeProduct = rs.getString("codeproduct");
			String nameProduct = rs.getString("nameproduct");
			String imgProduct = rs.getString("imgproduct");
			String category = rs.getString("category");
			String sizeProduct = rs.getString("sizeproduct");
			
			CategoryDAO ctDAO = new CategoryDAO();
			Category ct = ctDAO.selectById(new Category(category,"",""));
			
			SizeProductDAO stDAO = new SizeProductDAO();
			SizeProduct sip = stDAO.selectById(new SizeProduct(sizeProduct,0,0));
			
			Product product = new Product(codeProduct, nameProduct, imgProduct, ct, sip);
			result.add(product);
			
			
			
		}
		con.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}
public ArrayList<Product> selectProductCategory(String codeCategory) {
	ArrayList<Product> result = new ArrayList<Product>();
	
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "SELECT * from product WHERE category=?  LIMIT 5";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, codeCategory);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			String codeProduct = rs.getString("codeproduct");
			String nameProduct = rs.getString("nameproduct");
			String imgProduct = rs.getString("imgproduct");
			String category = rs.getString("category");
			String sizeProduct = rs.getString("sizeproduct");
			
			CategoryDAO ctDAO = new CategoryDAO();
			Category ct = ctDAO.selectById(new Category(category,"",""));
			
			SizeProductDAO stDAO = new SizeProductDAO();
			SizeProduct sip = stDAO.selectById(new SizeProduct(sizeProduct,0,0));
			
			Product product = new Product(codeProduct, nameProduct, imgProduct, ct, sip);
			result.add(product);
			
			
			
		}
		con.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}
public String takeFileImg(String inString) {
	String url="";
    if(inString.equals("01")){
    	url+= "trasua";
    }
    if(inString.equals("02")){
    	url+= "coffe";
    }
    if(inString.equals("03")){
    	url+= "sinhto";
    }
    if(inString.equals("04")){
    	url+= "kem";
    }
	
	return url;
}
public ArrayList<Product> selectBySearch(String nameSearch) {
	ArrayList<Product> result = new ArrayList<Product>();
	try {
		Connection con = JDBC_connect.getConnection();
		String sql = "SELECT * from product WHERE nameproduct LIKE ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,"%"+nameSearch + "%");
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			String codeProduct = rs.getString("codeproduct");
			String nameProduct = rs.getString("nameproduct");
			String imgProduct = rs.getString("imgproduct");
			String category = rs.getString("category");
			String sizeProduct = rs.getString("sizeproduct");
			
			CategoryDAO ctDAO = new CategoryDAO();
			Category ct = ctDAO.selectById(new Category(category,"",""));
			
			SizeProductDAO stDAO = new SizeProductDAO();
			SizeProduct sip = stDAO.selectById(new SizeProduct(sizeProduct,0,0));
	
			
			Product product = new Product(codeProduct, nameProduct, imgProduct, ct, sip);
			result.add(product);
		}
		con.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return result;
}
public String formatCurrency(double amount) {
    DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
    return formatter.format(amount);
}


}
