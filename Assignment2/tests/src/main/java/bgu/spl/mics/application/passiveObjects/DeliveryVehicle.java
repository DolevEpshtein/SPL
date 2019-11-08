package bgu.spl.mics.application.passiveObjects;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Passive data-object representing a delivery vehicle of the store.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class DeliveryVehicle {

	private int license;
	private int speed;
	private AtomicBoolean isFree;

	public AtomicBoolean getIsFree() {
		return isFree;
	}

	/**
	 * Constructor.
	 */
	public DeliveryVehicle(int license, int speed) {
		this.license=license;
		this.speed=speed;
		isFree=new AtomicBoolean(true);
	}

	/**
	 * Retrieves the license of this delivery vehicle.
	 */
	public int getLicense() {
		return license;
	}

	/**
	 * Retrieves the speed of this vehicle person.
	 * <p>
	 * @return Number of ticks needed for 1 Km.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Simulates a delivery by sleeping for the amount of time that
	 * it takes this vehicle to cover {@code distance} KMs.
	 * <p>
	 * @param address	The address of the customer.
	 * @param distance	The distance from the store to the customer.
	 */
	public void deliver(String address, int distance) {
		int time=distance/speed;
		try {
			Thread.currentThread().sleep(time);
		}
		catch(InterruptedException e){}
	}
}