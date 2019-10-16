package com.aqr.book;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ModifyOrderBasedBook implements OrderBasedBook , Serializable {
	
	private static final long serialversionUID =  129337938L;
	   //ORDER_ID
		private final String orderId;
		//ORDER_TYPE
		private final OrderType orderType = OrderType.MODIFY_ORDER;		

		//QUANTITY
		private final Integer newQuantity;

		public ModifyOrderBasedBook(String orderId, Integer newQuantity) {
			super();
			this.orderId = orderId;
			this.newQuantity = newQuantity;
		}

		public String getOrderId() {
			return orderId;
		}

		public OrderType getOrderType() {
			return orderType;
		}

		public Integer getNewQuantity() {
			return newQuantity;
		}

		@Override
		public String toString() {
			return "ModifyOrderBasedBook [orderId=" + orderId + ", orderType=" + orderType + ", newQuantity="
					+ newQuantity + "]";
		}
		
		
		
}
