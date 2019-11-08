package bgu.spl.mics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements bgu.spl.mics.MessageBus {

	static private int servicesCounter;
	private ConcurrentHashMap<Integer, LinkedBlockingQueue<Message>> microServicesQueues;
	private ConcurrentHashMap<Class, LinkedList<Integer>> registersMap;
	private ConcurrentHashMap<Event,Future> futures;

	private static class MessageBusImplHolder {
		private static MessageBusImpl instance = new MessageBusImpl();
	}
	private MessageBusImpl() {
		microServicesQueues = new ConcurrentHashMap<>();
		registersMap = new ConcurrentHashMap<>();
	}

	public static MessageBusImpl getInstance() {
		return MessageBusImplHolder.instance;
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		subscribe(type,m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		subscribe(type,m);
	}

	public void subscribe(Class<? extends Message> type, MicroService m){
		if(registersMap.containsKey(type)){
			registersMap.get(type).add(m.getID());
		}
		else{
			registersMap.putIfAbsent(type,new LinkedList<Integer>());
			registersMap.get(type).add(m.getID());
		}
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		futures.get(e).resolve(result);
	}

	@Override
	public void sendBroadcast(Broadcast b) {
		LinkedList<Integer> broadcastRegistered = registersMap.get(b.getClass());
		for (Integer id : broadcastRegistered) {
			try {
				microServicesQueues.get(id).put(b);
			} catch (InterruptedException e) {
			}
		}
	}


	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		LinkedList<Integer> eventsReistered = registersMap.get(e.getClass());
		int id = eventsReistered.remove();
		eventsReistered.add(id);
		try{
			microServicesQueues.get(id).put(e);
		}
		catch (InterruptedException e1){}
		Future f = new Future();
		futures.putIfAbsent(e,f);
		return f;
	}


	@Override
	public void register(MicroService m) {
		m.setID(servicesCounter);
		servicesCounter++;
		microServicesQueues.put(m.getID(),new LinkedBlockingQueue<>());
	}

	@Override
	public void unregister(MicroService m) {
		microServicesQueues.remove(m.getID());
		Iterator it = registersMap.values().iterator();
		while (it.hasNext()) {
			LinkedList<Integer> tmpList = (LinkedList) it.next();
			for (Integer tmpId : tmpList) {
				if (tmpId == m.getID()) {
					tmpList.remove(m.getID());
				}
			}
		}
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		int microServiceID = m.getID();
		LinkedBlockingQueue<bgu.spl.mics.Message> microServiceQueue = microServicesQueues.get(microServiceID);
		return microServiceQueue.take();
	}

	public ConcurrentHashMap<Integer, LinkedBlockingQueue<bgu.spl.mics.Message>> getMicroServicesQueues() {
		return microServicesQueues;
	}


}