package br.inatel.dm112.model;

import br.inatel.dm112.rest.support.DeliveryException;

public class DeliveryStatus {

	public enum DELIVERY_STATUS {
		OK, NULL_VALUES, ORDER_NOT_FOUND, WRONG_ORDER_STATUS, ORDER_ERROR, EMAIL_ERROR
	}

	private String cpf;
	private int orderNumber;
	private int status;

	public DeliveryStatus() {
	}

	public DeliveryStatus(int status, String cpf, int orderNumber) {
		super();
		this.status = status;
		this.cpf = cpf;
		this.orderNumber = orderNumber;
	}

	public static DeliveryException createErrorStatus(String msg, String cpf, int orderNumber, DELIVERY_STATUS errorStatus) {
		System.out.println(msg);
		return new DeliveryException(msg + " Details: error status " + errorStatus.name()
				+ " CPF: " + cpf + " Order: " + orderNumber);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "DeliveryStatus [cpf=" + cpf + ", orderNumber=" + orderNumber + ", status=" + status + "]";
	}

}
