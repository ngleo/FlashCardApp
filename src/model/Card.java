package model;

import java.io.Serializable;

public class Card implements Serializable {

    public String front;
    public String back;
    public Boolean isFlipped;

    // constructor
    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        this.isFlipped = false;
    }


    // getters
    public String getFront() { return this.front; }

    public String getBack() { return this.back; }

    // setters
    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
