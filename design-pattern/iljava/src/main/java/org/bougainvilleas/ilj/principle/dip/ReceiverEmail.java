package org.bougainvilleas.ilj.principle.dip;

public class ReceiverEmail implements Receiver{
  @Override
  public String getInfo() {
    return "Email";
  }
}
