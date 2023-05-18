package org.bougainvilleas.ilj.principle.dip;

public class ReceiverSMS implements Receiver{
  @Override
  public String getInfo() {
    return "SMS";
  }
}
