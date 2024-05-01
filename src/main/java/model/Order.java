package model;


public class Order {
	private String codeOrder;
	private Customer customer;
	private Product product;
	private String sizeProduct;
	private int quantity;
	private Suger suger;
	private String fullNameTopping;
	private double totalPrice;
	private double pricetopping;
	private int checkOrder;
	
	public String getCodeOrder() {
		return codeOrder;
	}

	public void setCodeOrder(String codeOrder) {
		this.codeOrder = codeOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSizeProduct() {
		return sizeProduct;
	}

	public void setSizeProduct(String sizeProduct) {
		this.sizeProduct = sizeProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Suger getSuger() {
		return suger;
	}

	public void setSuger(Suger suger) {
		this.suger = suger;
	}

	public String getFullNameTopping() {
		return fullNameTopping;
	}

	public void setFullNameTopping(String fullNameTopping) {
		this.fullNameTopping = fullNameTopping;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getPricetopping() {
		return pricetopping;
	}

	public void setPricetopping(double pricetopping) {
		this.pricetopping = pricetopping;
	}

	public int getCheckOrder() {
		return checkOrder;
	}

	public void setCheckOrder(int checkOrder) {
		this.checkOrder = checkOrder;
	}

	public Order(String codeOrder, Customer customer, Product product, String sizeProduct, int quantity, Suger suger,
			String fullNameTopping, double totalPrice, double pricetopping, int checkOrder) {
		super();
		this.codeOrder = codeOrder;
		this.customer = customer;
		this.product = product;
		this.sizeProduct = sizeProduct;
		this.quantity = quantity;
		this.suger = suger;
		this.fullNameTopping = fullNameTopping;
		this.totalPrice = totalPrice;
		this.pricetopping = pricetopping;
		this.checkOrder = checkOrder;
	}

	public Order() {
		
	}

	


	


	

	
	
	

}
