package org.bougainvilleas.base.designpattern.pattern.structural.adapter.objectadapter;

import org.bougainvilleas.base.designpattern.pattern.structural.adapter.classadapter.Volatage220V;

public class Client {

    public static void main(String[] args) {
        Phone p=new Phone();
        p.charging(new VolatageAdapter(new Volatage220V()));
    }
}
