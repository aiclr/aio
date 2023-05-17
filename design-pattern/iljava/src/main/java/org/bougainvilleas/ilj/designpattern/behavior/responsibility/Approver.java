package org.bougainvilleas.ilj.designpattern.behavior.responsibility;

import java.util.logging.Logger;

/**
 * Chain of Responsibility Pattern
 * <p>
 * 责任链模式
 * <p>
 * 为请求创建一个接收者对象的链:{@link ApproverDepartment}->{@link ApproverCollege}->{@link ApproverViceSchoolMaster}->{@link ApproverSchoolMaster}->{@link ApproverDepartment}<br/>
 * 对请求的发送者{@link PurchaseRequest}和接收者{@link Approver}进行解耦
 * <p>
 * 责任链模式 通常每个接收者{@link Approver}都包含对另一个接收者{@link Approver}的引用<br/>
 * 如果一个接收者{@link Approver}不能处理该请求,那么它会把相同的请求传给下一个接收者,依次类推
 * <p>
 * 问题 : 当链比较长的时候,需要控制链中的节点数量<br/>
 * 一般在接收者里设置一个最大节点数量,在指定下一节点的时候,判断是否超过阈值<br/>
 * 避免出现超长链,影响系统性能
 */
public abstract class Approver {
  protected static Logger log = Logger.getLogger(Approver.class.getSimpleName());
  Approver approve;
  String name;

  public Approver(String name) {
    this.name = name;
  }

  /**
   * @param approve 下一个处理者
   */
  public void setApprover(Approver approve) {
    this.approve = approve;
  }

  public abstract void processRequest(PurchaseRequest request) throws InterruptedException;

}
