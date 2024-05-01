package model;

public class Topping {
	private String codeTopping;
	private String nameTopping;
	private double priceTopping;
	public Topping() {
		
	}
	public Topping(String codeTopping, String nameTopping, double priceTopping) {
		super();
		this.codeTopping = codeTopping;
		this.nameTopping = nameTopping;
		this.priceTopping = priceTopping;
	}
	public String getCodeTopping() {
		return codeTopping;
	}
	public void setCodeTopping(String codeTopping) {
		this.codeTopping = codeTopping;
	}
	public String getNameTopping() {
		return nameTopping;
	}
	public void setNameTopping(String nameTopping) {
		this.nameTopping = nameTopping;
	}
	public double getPriceTopping() {
		return priceTopping;
	}
	public void setPriceTopping(double priceTopping) {
		this.priceTopping = priceTopping;
	}

	
	

}
