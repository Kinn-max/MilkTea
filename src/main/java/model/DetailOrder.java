package model;

import java.sql.Date;

public class DetailOrder {
	private String codeDetailOrder;
	private Order order;
	private String consignneeName;
	private String phoneNumber;
	private String deliveryAddress;
	private String note;
	private String payment;
	private Date dateOrder;
	public DetailOrder() {
		
	}
	public DetailOrder(String codeDetailOrder, Order order, String consignneeName, String phoneNumber,
			String deliveryAddress, String note, String payment, Date dateOrder) {
		super();
		this.codeDetailOrder = codeDetailOrder;
		this.order = order;
		this.consignneeName = consignneeName;
		this.phoneNumber = phoneNumber;
		this.deliveryAddress = deliveryAddress;
		this.note = note;
		this.payment = payment;
		this.dateOrder = dateOrder;
	}
	public String getCodeDetailOrder() {
		return codeDetailOrder;
	}
	public void setCodeDetailOrder(String codeDetailOrder) {
		this.codeDetailOrder = codeDetailOrder;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getConsignneeName() {
		return consignneeName;
	}
	public void setConsignneeName(String consignneeName) {
		this.consignneeName = consignneeName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Date getDateOrder() {
		return dateOrder;
	}
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	

}
