package org.bougainvilleas.ilj.designpattern.behavior.responsibility;

/**
 * 院长审批 <=10000
 */
public class ApproverCollege extends Approver {

  public ApproverCollege(String name) {
    super(name);
  }

  @Override
  public void processRequest(PurchaseRequest request) throws InterruptedException {
    if (request.getPrice() <= 10000) {
      log.info(name + "处理了" + request.getId());
    } else {
      log.info(name + "--->" + approve.name);
      approve.processRequest(request);
    }
  }
}
