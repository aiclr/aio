package org.bougainvilleas.ilj.designpattern.behavior.responsibility;

/**
 * 教学主任 审批<=5000
 */
public class ApproverDepartment extends Approver {

  public ApproverDepartment(String name) {
    super(name);
  }

  @Override
  public void processRequest(PurchaseRequest request) throws InterruptedException {
    if (request.getPrice() <= 5000) {
      log.info(name + "处理了" + request.getId());
    } else {
      log.info(name + "--->" + approve.name);
      approve.processRequest(request);
    }
  }
}
