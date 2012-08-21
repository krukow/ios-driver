/*
 * Copyright 2012 ios-driver committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uiautomation.ios.server.command;

import org.json.JSONObject;
import org.uiautomation.ios.communication.FailedWebDriverLikeResponse;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.communication.WebDriverLikeResponse;
import org.uiautomation.ios.server.UIAScriptRequest;
import org.uiautomation.ios.server.UIAScriptResponse;
import org.uiautomation.ios.server.instruments.IOSDriver;
import org.uiautomation.ios.server.instruments.SessionsManager;

/**
 * execute a snipet of javascript in the instruments script context and returns the result.
 * 
 * @author freynaud
 * 
 */
public abstract class UIAScriptHandler extends BaseCommandHandler {
  private String js;

  public UIAScriptHandler(IOSDriver driver, WebDriverLikeRequest request) {
    super(driver, request);
  }

  protected void setJS(String js) {
    this.js = js;
  }



  public WebDriverLikeResponse handle() throws Exception {
    if (js == null) {
      throw new RuntimeException("need to specify the JS to run");
    }
    UIAScriptRequest r = new UIAScriptRequest(js);
    communication().sendNextCommand(r);
    UIAScriptResponse resp = communication().waitForResponse();
    WebDriverLikeResponse webDriverLikeResponse;
    try {
      JSONObject json = resp.getResponse();
      webDriverLikeResponse = new WebDriverLikeResponse(json);
    } catch (Exception e) {
      return new FailedWebDriverLikeResponse(getRequest().getSession(), e);
    }
    return webDriverLikeResponse;
  }
}
