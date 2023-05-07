package com.cdl.store.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cdl.store.checkout.model.ItemOffer;
import com.cdl.store.checkout.model.Items;

public class CheckOutSystem {
	Map<String,Integer> itemsToCalculate=new HashMap<String,Integer>();
	
	public void addItem(String item) throws Exception{
		itemsToCalculate.put(item, itemsToCalculate.getOrDefault(item, 0) + 1);
    }

	public void calculateCustomerCheckout(Items item) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter item name (or 'exit' to finish):");
		if(scanner.hasNextLine())
		{
			boolean flag=true;
			while(flag)
			{
				String itemName=scanner.nextLine();
				if(itemName.equals("done"))
				{
					//System.out.println("Total :"+total);
					break;
				}
				if(item.getItems().containsKey(itemName))
				{
					try {
						addItem(itemName);
					} catch (Exception e) {
						System.out.println("Invalid item name "+e.getMessage());
					}
					calculateTotal(item);
					flag=true;
				}else
				{
					System.out.println("Please enter a valid item");
					  flag=true;
				}
			}
			
		}
		
	}

	private void calculateTotal(Items item) {
		// TODO Auto-generated method stub
		Map<String,Integer> availableItems=item.getItems();
		Map<String,ItemOffer> availableOffers=item.getOffers();
		int total=0;
		
		for(Map.Entry<String, Integer> entry:itemsToCalculate.entrySet())
		{
			String itemName=entry.getKey();
			int quantity=entry.getValue();
			int itemPrice=quantity*availableItems.get(itemName);
			if(availableOffers.containsKey(itemName))
			{
				ItemOffer itemOffer=availableOffers.get(itemName);
				int offerQuantity=itemOffer.getQuantity();
				int offerPrice=itemOffer.getPrice();
				int numOfOfferItem=quantity/offerQuantity;
				int numOfUnOfferItem=quantity%offerQuantity;
				itemPrice=numOfOfferItem*offerPrice+numOfUnOfferItem*availableItems.get(itemName);
			}
			total+=itemPrice;
		}
		System.out.println("       Total :"+total);
		
	}

}
