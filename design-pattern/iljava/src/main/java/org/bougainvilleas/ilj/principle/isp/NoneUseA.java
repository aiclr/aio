package org.bougainvilleas.ilj.principle.isp;

/**
 * {@link NoneUseA}通过{@link NoneISP}
 * 依赖仅使用{@link NoneUseA#dependAOperate1(NoneISP)}、{@link NoneUseA#dependAOperate2(NoneISP)}、{@link NoneUseA#dependAOperate3(NoneISP)}
 */
public class NoneUseA {
  public void dependAOperate1(NoneISP a) {
    a.operate1();
  }

  public void dependAOperate2(NoneISP a) {
    a.operate2();
  }

  public void dependAOperate3(NoneISP a) {
    a.operate3();
  }
}
