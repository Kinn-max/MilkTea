package model;

public class SlideIndex {
	private String codeImg;
	private String takeImg;
	
	public SlideIndex() {
		
	}

	public SlideIndex(String codeImg, String takeImg) {
		super();
		this.codeImg = codeImg;
		this.takeImg = takeImg;
	}

	public String getCodeImg() {
		return codeImg;
	}

	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}

	public String getTakeImg() {
		return takeImg;
	}

	public void setTakeImg(String takeImg) {
		this.takeImg = takeImg;
	}
	

	

}
