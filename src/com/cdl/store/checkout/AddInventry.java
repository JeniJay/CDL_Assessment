/**
 * 
 */
package com.cdl.store.checkout;

import java.util.Map.Entry;

import com.cdl.store.checkout.model.ItemOffer;
import com.cdl.store.checkout.model.Items;

import java.util.Scanner;

/**
 * @author jenifer
 *
 */
public class AddInventry {

	public void addInventry(Items item) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Username :");
		String username = scanner.nextLine();
		System.out.print("Enter Password :");
		String password = scanner.nextLine();
		if (username.equals("admin") && password.equals("Admin$123")) {
			System.out.println("Available items :");
			System.out.println("Item     Unit Price(pence)     Special Price(pence)");
			for (Entry<String, Integer> entry : item.getItems().entrySet()) {
				String key = entry.getKey();
				if (item.getOffers() != null && item.getOffers().get(key) != null) {
					ItemOffer itemOffer = item.getOffers().get(key);
					System.out.println(entry.getKey() + "         " + entry.getValue() + "                     "
							+ itemOffer.getQuantity() + " for " + itemOffer.getPrice());
				} else
					System.out.println(entry.getKey() + "         " + entry.getValue());
			}
			boolean flag = true;
			while (flag) {

				System.out.print("Do you want to add/change item (y/n) :");
				String itemFlag = scanner.nextLine();
				if (itemFlag.equals("y")) {
					System.out.print("Enter item name (or 'exit' to finish): ");
					String itemName = scanner.nextLine();

					if (itemName.equals("done")) {
						flag = false;
					} else {
						try {
							System.out.print("Enter item price : ");
							String itemPrice = scanner.nextLine();
							item.getItems().put(itemName, Integer.parseInt(itemPrice));
							System.out.print("Does this item have any offer (y/n) :");
							String offerFlag = scanner.nextLine();
							if (offerFlag.equals("y")) {
								System.out.print("Enter offer quantity : ");
								String offerQuantity = scanner.nextLine();
								System.out.print("Enter offer price : ");
								String offerPrice = scanner.nextLine();
								item.getOffers().put(itemName,
										new ItemOffer(Integer.parseInt(offerQuantity), Integer.parseInt(offerPrice)));
							} else if (offerFlag.equals("n"))
								item.getOffers().remove(itemName);
							flag = true;
						} catch (NumberFormatException e) {
							System.out.println("Enter valid input (price or quantity in number)");
							flag = true;
						}
					}

				} else
					flag = false;

			}

		} else {
			System.out.println("Invalid credentials");

		}
	}

}
