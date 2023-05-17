package org.bougainvilleas.ilj.designpattern.behavior.responsibility;

/**
 * 校长 >30000
 */
public class ApproverSchoolMaster extends Approver {

  public ApproverSchoolMaster(String name) {
    super(name);
  }

  @Override
  public void processRequest(PurchaseRequest request) throws InterruptedException {
    if (request.getPrice() > 30000) {
      log.info(name + "处理了" + request.getId());
    } else {
      log.info(name + "--->" + approve.name);
      approve.processRequest(request);
    }
  }
}
