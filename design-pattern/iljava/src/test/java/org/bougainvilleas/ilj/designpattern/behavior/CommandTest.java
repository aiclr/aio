package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.command.Command;
import org.bougainvilleas.ilj.designpattern.behavior.command.CommandLightOff;
import org.bougainvilleas.ilj.designpattern.behavior.command.CommandLightOn;
import org.bougainvilleas.ilj.designpattern.behavior.command.Invoker;
import org.bougainvilleas.ilj.designpattern.behavior.command.ReceiverLight;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.Action;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.ActionClose;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.ActionOpen;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.ActionSave;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.Editor;
import org.bougainvilleas.ilj.designpattern.behavior.command.editor.Macro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("命令模式")
class CommandTest {

  @DisplayName("命令模式-普通")
  @Test
  void commandTest() {
    ReceiverLight light = new ReceiverLight();
    CommandLightOn lightOn = new CommandLightOn(light);
    CommandLightOff lightOff = new CommandLightOff(light);

    Invoker invoker = new Invoker();
    invoker.setCommand(0,lightOn,lightOff);

    invoker.onButtonWasPushed(0);
    invoker.offButtonWasPushed(0);
    invoker.undoButtonWasPushed();
    //todo 不支持反复撤销
    invoker.undoButtonWasPushed();
    invoker.undoButtonWasPushed();
  }
  @DisplayName("命令模式-匿名内部类")
  @Test
  void anonymousTest() {
    ReceiverLight light = new ReceiverLight();

    Invoker invoker = new Invoker();

    invoker.setCommand(0, new Command() {
      @Override
      public void execute() {
        light.on();
      }

      @Override
      public void undo() {
        light.off();
      }
    }, new Command() {
      @Override
      public void execute() {
        light.off();
      }

      @Override
      public void undo() {
        light.on();
      }
    });

    invoker.onButtonWasPushed(0);
    invoker.offButtonWasPushed(0);
    invoker.undoButtonWasPushed();
    //todo 不支持反复撤销
    invoker.undoButtonWasPushed();
    invoker.undoButtonWasPushed();
  }

  @DisplayName("命令模式-编辑器")
  @Test
  void editorTest(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(new ActionOpen(editor));
    macro.record(new ActionSave(editor));
    macro.record(new ActionClose(editor));
    macro.run();
  }
  @DisplayName("命令模式-编辑器-匿名内部类")
  @Test
  void editorAnonymousTest(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(new Action(){
      @Override
      public void perform() {
        editor.open();
      }
    });
    macro.record(new Action(){
      @Override
      public void perform() {
        editor.save();
      }
    });
    macro.record(new Action(){
      @Override
      public void perform() {
        editor.close();
      }
    });
    macro.run();
  }

  @DisplayName("命令模式-编辑器-lambda")
  @Test
  void editorLambdaTest(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(editor::open);
    macro.record(editor::save);
    macro.record(editor::close);
    macro.run();
  }

}
