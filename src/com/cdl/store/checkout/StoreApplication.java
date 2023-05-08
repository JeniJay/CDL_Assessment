/**
 * 
 */
package com.cdl.store.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.cdl.store.checkout.model.ItemOffer;
import com.cdl.store.checkout.model.Items;

/**
 * @author jenifer
 *
 */
public class StoreApplication {

	/**
	 * @param args
	 */
	Map<String, Integer> items;
	Map<String, ItemOffer> offers ;
	Items item;
	
	/*Add some sample items and offers since we don't use database*/
	private StoreApplication()
	{
		items = new HashMap<String, Integer>();
		items.put("A", 50);
		items.put("B", 30);
		items.put("C", 20);
		items.put("D", 15);
		
		
		offers = new HashMap<String, ItemOffer>();
		offers.put("A", new ItemOffer(3, 130));
		offers.put("B", new ItemOffer(2, 45));
		
		item = new Items();
		item.setItems(items);
		item.setOffers(offers);
	}
	/*Main method of store application*/
	public static void main(String[] args) {
		StoreApplication storeApplication=new StoreApplication();
		CheckOutSystem checkout = new CheckOutSystem();
		InventrySystem inventry = new InventrySystem();
		try (Scanner scanner = new Scanner(System.in)) {
			boolean flag = true;
			while (flag) {
				System.out.print("Enter 'c' if you are a customer or enter 's' if you are a store manager(or 'done' to exit ) :");
				if (scanner.hasNextLine()) {
					String input = scanner.nextLine();
					if (input.equals("done")) {
						flag = false;
					}
					else if (input.equalsIgnoreCase("c")) {
						checkout.processCustomerCheckout(storeApplication.item);
						flag = false;
					} else if (input.equalsIgnoreCase("s")) {
						inventry.addInventry(storeApplication.item);
						flag = true;
					} else {
						System.out.println("Enter valid input");
						flag = true;
					}
				} else {
					System.out.println("No input found.");
					flag = false;
				}
			}
			System.out.println("Exit..");
			
		}catch(NoSuchElementException e)
		{
			System.out.println("Error Reading Input "+e.getMessage());
		}
		
	}

}
