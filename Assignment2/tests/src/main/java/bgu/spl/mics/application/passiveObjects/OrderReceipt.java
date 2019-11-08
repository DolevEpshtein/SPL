package bgu.spl.mics.application.passiveObjects;

/**
 * Passive data-object representing a receipt that should 
 * be sent to a customer after the completion of a BookOrderEvent.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class OrderReceipt {

	private int orderID;
	private String sellingServiceName;
	private int customerID;
	private String bookName;
	private int price;
	private int issuedTick;
	private int processTick;
	private int orderTick;

	public OrderReceipt(int orderID, String sellingServiceName, int customerID, String bookName, int price, int issuedTick, int processTick, int orderTick) {
		this.orderID = orderID;
		this.sellingServiceName = sellingServiceName;
		this.customerID = customerID;
		this.bookName = bookName;
		this.price = price;
		this.issuedTick = issuedTick;
		this.processTick = processTick;
		this.orderTick = orderTick;
	}

	/**
	 * Retrieves the orderId of this receipt.
	 */
	public int getOrderId() {
		return orderID;
	}

	/**
	 * Retrieves the name of the selling service which handled the order.
	 */
	public String getSeller() {
		return sellingServiceName;
	}

	/**
	 * Retrieves the ID of the customer to which this receipt is issued to.
	 * <p>
	 * @return the ID of the customer
	 */
	public int getCustomerId() {
		return customerID;
	}

	/**
	 * Retrieves the name of the book which was bought.
	 */
	public String getBookTitle() {
		return bookName;
	}
	/**
	 * Retrieves the price the customer paid for the book.
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Retrieves the tick in which this receipt was issued.
	 */
	public int getIssuedTick() {
		return issuedTick;
	}

	/**
	 * Retrieves the tick in which the customer sent the purchase request.
	 */
	public int getOrderTick() {
		return orderTick;
	}

	/**
	 * Retrieves the tick in which the treating selling service started
	 * processing the order.
	 */
	public int getProcessTick() {
		return processTick;
	}
}