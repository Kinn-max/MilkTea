package model;

public class SizeProduct {
	private String codeSize;
	private double sizeM;
	private double sizeL;
	
	public SizeProduct() {
		
	}

	public SizeProduct(String codeSize, double sizeM, double sizeL) {
		this.codeSize = codeSize;
		this.sizeM = sizeM;
		this.sizeL = sizeL;
	}

	public String getCodeSize() {
		return codeSize;
	}

	public void setCodeSize(String codeSize) {
		this.codeSize = codeSize;
	}

	public double getSizeM() {
		return sizeM;
	}

	public void setSizeM(double sizeM) {
		this.sizeM = sizeM;
	}

	public double getSizeL() {
		return sizeL;
	}

	public void setSizeL(double sizeL) {
		this.sizeL = sizeL;
	}
	
	

}
