package org.bougainvilleas.ilj.principle.dip;

/**
 * {@link NoneUser}接收消息{@link NoneEmail}
 * 1. 实现简单<br/>
 * 2. 如果{@link NoneUser}获取的对象是微信，短信等，则应新增类，同时{@link NoneUser}也要增加相应的接收方法<br/>
 * 3. 解决思路，为{@link NoneEmail}设计抽象接口，{@link NoneUser}类与抽象接口发生依赖。微信，短信各自实现的接口，就符合依赖倒转原则
 */
public class NoneUser {
  public String receiveEmail(NoneEmail email) {
    return email.getInfo();
  }
  public String receiveSMS(NoneSMS sms) {
    return sms.getInfo();
  }
}
