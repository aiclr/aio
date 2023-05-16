package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.bridge.Brand;
import org.bougainvilleas.ilj.designpattern.structural.bridge.BrandIPhone;
import org.bougainvilleas.ilj.designpattern.structural.bridge.BrandXiaomi;
import org.bougainvilleas.ilj.designpattern.structural.bridge.Phone;
import org.bougainvilleas.ilj.designpattern.structural.bridge.PhoneFolded;
import org.bougainvilleas.ilj.designpattern.structural.bridge.PhoneUpRight;
import org.junit.jupiter.api.Test;

class BridgeTest {

  @Test
  void bridgeTest() {
    Brand xiaomi = new BrandXiaomi();
    Brand iphone = new BrandIPhone();

    Phone phone = new PhoneUpRight(xiaomi);
    phone.open();
    phone.call();
    phone.close();

    phone = new PhoneUpRight(iphone);
    phone.open();
    phone.call();
    phone.close();

    phone = new PhoneFolded(xiaomi);
    phone.open();
    phone.call();
    phone.close();

    phone = new PhoneFolded(iphone);
    phone.open();
    phone.call();
    phone.close();
  }
}