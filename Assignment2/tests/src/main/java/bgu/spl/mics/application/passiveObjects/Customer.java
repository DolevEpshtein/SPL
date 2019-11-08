package bgu.spl.mics.application.passiveObjects;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Passive data-object representing a customer of the store.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class Customer {
	private int ID;
	private String name;
	private String address;
	int distance;
	private List<OrderReceipt> Receipts;
	private int creditCardNumber;
	private AtomicInteger availableAmountInCreditCard=new AtomicInteger(0);

	public Customer(int id, String name, String address, int distance, List receipts,
					int cc, int availableamount) {
		ID=id;
		this.name=name;
		this.address=address;
		this.distance=distance;
		Receipts=receipts;
		creditCardNumber=cc;
		availableAmountInCreditCard.compareAndSet(0,availableamount);
	}

	/**
	 * Retrieves the name of the customer.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the ID of the customer  .
	 */
	public int getId() {
		return ID;
	}

	/**
	 * Retrieves the address of the customer.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Retrieves the distance of the customer from the store.
	 */
	public int getDistance() {
		return distance;
	}


	/**
	 * Retrieves a list of receipts for the purchases this customer has made.
	 * <p>
	 * @return A list of receipts.
	 */
	public List<OrderReceipt> getCustomerReceiptList() {
		return Receipts;
	}

	/**
	 * Retrieves the amount of money left on this customers credit card.
	 * <p>
	 * @return Amount of money left.
	 */
	public int getAvailableCreditAmount() {
		return availableAmountInCreditCard.get();
	}

	public void chargeCreditByAmount(int amount) {
		int value;
		do {
			value = availableAmountInCreditCard.get();
		}while (!availableAmountInCreditCard.compareAndSet(value, value - amount)) ;
	}

	/**
	 * Retrieves this customers credit card serial number.
	 */
	public int getCreditNumber() {
		return creditCardNumber;
	}

	synchronized public void addReceipe(OrderReceipt receipt){
		Receipts.add(receipt);
	}

	synchronized public int getSumOfOrders(){
		int sum=0;
		for(OrderReceipt receipt:Receipts){
			sum+=receipt.getPrice();
		}
		return sum;
	}
}