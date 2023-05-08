package com.cdl.store.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cdl.store.checkout.model.ItemOffer;
import com.cdl.store.checkout.model.Items;
/**
 * @author jenifer
 *
 */
public class CheckOutSystem {
	Map<String,Integer> itemsToCalculate=new HashMap<String,Integer>();
	
	/*add the scanned items in the cart*/
	public void addItem(String item) throws Exception{
		itemsToCalculate.put(item, itemsToCalculate.getOrDefault(item, 0) + 1);
    }

	/*method to process the customer checkout */
	public void processCustomerCheckout(Items item) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter item name (or 'exit' to finish):");
		if(scanner.hasNextLine())
		{
			boolean flag=true;
			int total=0;
			while(flag)
			{
				String itemName=scanner.nextLine();
				if(itemName.equals("exit"))
				{
					if(total!=0)
						System.out.println("Please pay "+total+" pence to proceed..");
					break;
				}
				if(item.getItems().containsKey(itemName))
				{
					try {
						addItem(itemName);
					} catch (Exception e) {
						System.out.println("Invalid item name "+e.getMessage());
					}
					total=calculateTotal(item);
					flag=true;
				}else
				{
					System.out.println("Please enter a valid item");
					  flag=true;
				}
			}
			
		}
		
	}

	/*calculate the total pence on each item is added */
	private int calculateTotal(Items item) {
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
		return total;
	}

}
