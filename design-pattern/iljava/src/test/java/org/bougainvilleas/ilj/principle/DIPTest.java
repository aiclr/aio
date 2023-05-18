package org.bougainvilleas.ilj.principle;

import org.bougainvilleas.ilj.principle.dip.NoneEmail;
import org.bougainvilleas.ilj.principle.dip.NoneSMS;
import org.bougainvilleas.ilj.principle.dip.NoneUser;
import org.bougainvilleas.ilj.principle.dip.Receiver;
import org.bougainvilleas.ilj.principle.dip.ReceiverEmail;
import org.bougainvilleas.ilj.principle.dip.ReceiverSMS;
import org.bougainvilleas.ilj.principle.dip.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("依赖倒置原则")
class DIPTest {

  @DisplayName("不符合")
  @Test
  void noneTest() {
    NoneUser user = new NoneUser();
    NoneEmail email = new NoneEmail();
    NoneSMS sms = new NoneSMS();
    assertEquals("Email", user.receiveEmail(email));
    assertEquals("SMS", user.receiveSMS(sms));
  }

  @DisplayName("符合-接口传递依赖关系")
  @Test
  void dipInterfaceTest() {
    User user = new User();
    Receiver email = new ReceiverEmail();
    Receiver sms = new ReceiverSMS();
    assertEquals("Email", user.receive(email));
    assertEquals("SMS", user.receive(sms));
  }
  @DisplayName("符合-构造器传递依赖关系")
  @Test
  void dipConstructorTest() {
    Receiver email = new ReceiverEmail();
    Receiver sms = new ReceiverSMS();
    User user = new User(email);
    assertEquals("Email", user.receive());
    user = new User(sms);
    assertEquals("SMS", user.receive());
  }
  @DisplayName("符合-setter方法传递依赖关系")
  @Test
  void dipSetterTest() {
    User user = new User();
    Receiver email = new ReceiverEmail();
    Receiver sms = new ReceiverSMS();
    user.setReceiver(email);
    assertEquals("Email", user.receive());
    user.setReceiver(sms);
    assertEquals("SMS", user.receive());
  }

}
