package bgu.spl.mics.application.passiveObjects;

public class BookOrderInfo {

    private String bookTitle;
    private int tick;

    public BookOrderInfo(String name, int time) {
        bookTitle = name;
        tick = time;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getTick() {
        return tick;
    }
}