package org.bougainvilleas.ilj.principle.isp;

/**
 * {@link NoneUseB}通过{@link NoneISP}
 * 依赖仅使用{@link NoneUseB#dependBOperate1(NoneISP)}、{@link NoneUseB#dependBOperate4(NoneISP)}、{@link NoneUseB#dependBOperate5(NoneISP)}
 */
public class NoneUseB {
  public void dependBOperate1(NoneISP b) {
    b.operate1();
  }

  public void dependBOperate4(NoneISP b) {
    b.operate4();
  }

  public void dependBOperate5(NoneISP b) {
    b.operate5();
  }
}
