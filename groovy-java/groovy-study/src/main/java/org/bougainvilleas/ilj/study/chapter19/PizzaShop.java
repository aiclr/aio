package org.bougainvilleas.ilj.study.chapter19;

import java.util.HashMap;
import java.util.Map;

public class PizzaShop {
    String size;
    String crust;
    String[] topping;
    String address;
    Map<String,String> card;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String[] getTopping() {
        return topping;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public int setCard(String cardType, String cardNum) {
        this.card =new HashMap<>();
        card.put(cardType,cardNum);
        return 23;
    }
}
