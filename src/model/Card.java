package model;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 8585555395808288264L;

    public String front;
    public String back;
    public Boolean isFlipped;

    // constructor
    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        this.isFlipped = false;
    }
}
