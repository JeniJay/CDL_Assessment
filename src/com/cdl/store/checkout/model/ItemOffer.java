package com.cdl.store.checkout.model;

public class ItemOffer {
	    private int quantity;
	    private int price;

	    public ItemOffer(int quantity, int price) {
	        this.quantity = quantity;
	        this.price = price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public int getPrice() {
	        return price;
	    }
	}