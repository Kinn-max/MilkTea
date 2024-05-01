package model;


public class Customer {
	private String codeCustomer;
	private String userCustomer;
	private String password; 
	private String fullName;
	private String address; 
	private String phoneNumber;
	private String email;
	public Customer() {
		
	}
	public Customer(String codeCustomer, String userCustomer, String password, String fullName, String address,
			String phoneNumber, String email) {
		this.codeCustomer = codeCustomer;
		this.userCustomer = userCustomer;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getCodeCustomer() {
		return codeCustomer;
	}
	public void setCodeCustomer(String codeCustomer) {
		this.codeCustomer = codeCustomer;
	}
	public String getUserCustomer() {
		return userCustomer;
	}
	public void setUserCustomer(String userCustomer) {
		this.userCustomer = userCustomer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfullName() {
		return fullName;
	}
	public void setfullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getphoneNumber() {
		return phoneNumber;
	}
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
