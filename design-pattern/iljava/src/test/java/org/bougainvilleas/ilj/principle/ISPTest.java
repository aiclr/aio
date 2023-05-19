package org.bougainvilleas.ilj.principle;

import org.bougainvilleas.ilj.principle.isp.Aimpl;
import org.bougainvilleas.ilj.principle.isp.Bimpl;
import org.bougainvilleas.ilj.principle.isp.NoneAImpl;
import org.bougainvilleas.ilj.principle.isp.NoneBImpl;
import org.bougainvilleas.ilj.principle.isp.NoneUseA;
import org.bougainvilleas.ilj.principle.isp.NoneUseB;
import org.bougainvilleas.ilj.principle.isp.UseA;
import org.bougainvilleas.ilj.principle.isp.UseB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("接口隔离原则")
class ISPTest {

  @DisplayName("符合")
  @Test
  void ocpTest() {
    UseA useA = new UseA();
    Aimpl a = new Aimpl();
    useA.dependAOperate1(a);
    useA.dependAOperate2(a);
    useA.dependAOperate3(a);

    UseB useB = new UseB();
    Bimpl b = new Bimpl();
    useB.dependBOperate1(b);
    useB.dependBOperate4(b);
    useB.dependBOperate5(b);
  }

  @DisplayName("不符合")
  @Test
  void noneISPTest() {
    NoneUseA useA = new NoneUseA();
    NoneAImpl a = new NoneAImpl();
    useA.dependAOperate1(a);
    useA.dependAOperate2(a);
    useA.dependAOperate3(a);

    NoneUseB useB = new NoneUseB();
    NoneBImpl b = new NoneBImpl();
    useB.dependBOperate1(b);
    useB.dependBOperate4(b);
    useB.dependBOperate5(b);
  }
}
