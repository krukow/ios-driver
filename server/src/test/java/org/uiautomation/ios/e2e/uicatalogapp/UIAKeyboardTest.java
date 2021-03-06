package org.uiautomation.ios.e2e.uicatalogapp;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uiautomation.ios.UIAModels.UIAElement;
import org.uiautomation.ios.UIAModels.UIAElementArray;
import org.uiautomation.ios.UIAModels.UIAKey;
import org.uiautomation.ios.UIAModels.UIAKeyboard;
import org.uiautomation.ios.UIAModels.UIATableCell;
import org.uiautomation.ios.UIAModels.UIATextField;
import org.uiautomation.ios.UIAModels.predicate.AndCriteria;
import org.uiautomation.ios.UIAModels.predicate.Criteria;
import org.uiautomation.ios.UIAModels.predicate.NameCriteria;
import org.uiautomation.ios.UIAModels.predicate.TypeCriteria;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIAApplication;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIADriver;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIATarget;
import org.uiautomation.ios.client.uiamodels.impl.RemoteUIAWindow;
import org.uiautomation.ios.exceptions.NoSuchElementException;

public class UIAKeyboardTest extends UICatalogTestsBase {

  @Test(expectedExceptions = NoSuchElementException.class)
  public void throwsIfKeyboardNotPresent() {
    RemoteUIADriver driver = null;
    try {
      driver = getDriver();
      RemoteUIATarget target = driver.getLocalTarget();
      RemoteUIAApplication app = target.getFrontMostApp();
      app.getKeyboard();

    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  private UIATextField getTextField(RemoteUIAWindow win) {
    String name = "TextFields, Uses of UITextField";
    Criteria c1 = new TypeCriteria(UIATableCell.class);
    Criteria c2 = new NameCriteria(name);
    Criteria c = new AndCriteria(c1, c2);
    UIAElement element = win.findElement(c);
    element.tap();
    Criteria fieldC =
        new AndCriteria(new TypeCriteria(UIATextField.class), new NameCriteria("Normal"));
    UIATextField textfield = (UIATextField) win.findElement(fieldC);
    return textfield;
  }

  /**
   * 28 on IOS 5 31 on IOS 6
   */
  @Test(groups = "broken")
  public void canFindKeyboard() {
    RemoteUIADriver driver = null;
    try {

      driver = getDriver();
      RemoteUIATarget target = driver.getLocalTarget();
      RemoteUIAApplication app = target.getFrontMostApp();
      RemoteUIAWindow win = app.getMainWindow();

      UIATextField textfield = getTextField(win);
      textfield.tap();

      UIAKeyboard keyboard = app.getKeyboard();

      UIAElementArray<UIAKey> keys = keyboard.getKeys();
      Assert.assertTrue(keys.size() == 28 || keys.size() == 31);
      // GB, should be a qwerty
      Assert.assertEquals(keys.get(0).getName(), "Q");

    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  // some timing issues here.Need a change server side.
  @Test(groups = "broken")
  public void typeBasic() {
    RemoteUIADriver driver = null;
    try {

      driver = getDriver();
      RemoteUIATarget target = driver.getLocalTarget();
      RemoteUIAApplication app = target.getFrontMostApp();
      RemoteUIAWindow win = app.getMainWindow();

      UIATextField textfield = getTextField(win);
      textfield.tap();

      UIAKeyboard keyboard = app.getKeyboard();
      keyboard.typeString("F");



      Assert.assertEquals(textfield.getValue(), "Francois");

    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }
}
