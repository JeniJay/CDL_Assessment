/**
 * 
 */
package com.cdl.store.checkout.model;

import java.util.Map;

/**
 * @author jenifer
 *
 */
public class Items {

	private Map<String, Integer> items;
    private Map<String, ItemOffer> offers;
	public Map<String, Integer> getItems() {
		return items;
	}
	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
	public Map<String, ItemOffer> getOffers() {
		return offers;
	}
	public void setOffers(Map<String, ItemOffer> offers) {
		this.offers = offers;
	}
    
}
