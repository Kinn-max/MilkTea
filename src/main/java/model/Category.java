package model;

public class Category {
	private String codeCategory;
	private String imgCategory;
	private String nameCategory;
	
	public Category() {
		
	}

	public Category(String codeCategory, String imgCategory, String nameCategory) {
		super();
		this.codeCategory = codeCategory;
		this.imgCategory = imgCategory;
		this.nameCategory = nameCategory;
	}

	public String getCodeCategory() {
		return codeCategory;
	}

	public void setCodeCategory(String codeCategory) {
		this.codeCategory = codeCategory;
	}

	public String getImgCategory() {
		return imgCategory;
	}

	public void setImgCategory(String imgCategory) {
		this.imgCategory = imgCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	

}
