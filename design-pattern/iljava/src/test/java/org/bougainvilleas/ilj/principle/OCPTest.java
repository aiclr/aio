package org.bougainvilleas.ilj.principle;

import org.bougainvilleas.ilj.principle.ocp.NoneGraphicEditor;
import org.bougainvilleas.ilj.principle.ocp.NoneShapeCircle;
import org.bougainvilleas.ilj.principle.ocp.NoneShapeRectangle;
import org.bougainvilleas.ilj.principle.ocp.OCPGraphicEditor;
import org.bougainvilleas.ilj.principle.ocp.OCPShapeCircle;
import org.bougainvilleas.ilj.principle.ocp.OCPShapeRectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("开闭原则")
class OCPTest {

  @DisplayName("开闭原则-符合")
  @Test
  void ocpTest() {
    OCPGraphicEditor editor = new OCPGraphicEditor();
    editor.drawShape(new OCPShapeCircle());
    editor.drawShape(new OCPShapeRectangle());
  }

  @DisplayName("开闭原则-不符合")
  @Test
  void noneOCPTest() {
    NoneGraphicEditor editor = new NoneGraphicEditor();
    editor.drawShape(new NoneShapeCircle());
    editor.drawShape(new NoneShapeRectangle());
  }
}
