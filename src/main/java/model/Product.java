package model;

public class Product {
	private String codeProduct;
	private String nameProduct;
	private String imgProduct;
	private Category category;
	private SizeProduct sizeProduct;
	
	public Product() {
		
	}

	public Product(String codeProduct, String nameProduct, String imgProduct, Category category,
			SizeProduct sizeProduct) {
		super();
		this.codeProduct = codeProduct;
		this.nameProduct = nameProduct;
		this.imgProduct = imgProduct;
		this.category = category;
		this.sizeProduct = sizeProduct;
	}

	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getImgProduct() {
		return imgProduct;
	}

	public void setImgProduct(String imgProduct) {
		this.imgProduct = imgProduct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SizeProduct getSizeProduct() {
		return sizeProduct;
	}

	public void setSizeProduct(SizeProduct sizeProduct) {
		this.sizeProduct = sizeProduct;
	}

	
	
	

}
