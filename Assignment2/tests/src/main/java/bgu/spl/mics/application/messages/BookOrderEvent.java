package bgu.spl.mics.application.messages;

import bgu.spl.mics.Callback;
import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.BookOrderInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;

public class BookOrderEvent implements Event<OrderReceipt>, Callback<BookOrderEvent> {

    private String bookTitle;
    private Customer customer;

    public BookOrderEvent(BookOrderInfo orderInfo, Customer orderer){
        bookTitle=orderInfo.getBookTitle();
        customer=orderer;

    }
    public void call(BookOrderEvent event){
    }
}