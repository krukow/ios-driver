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
package org.uiautomation.ios.UIAModels;



public interface UIATarget {

  // TODO freynaud UIAPoint here.
  public void tap(int x, int y);

  public String getModel();

  public UIARect getRect();

  public String getName();

  public String getSystemName();

  public String getSystemVersion();

  public void takeScreenshot(String path);

  public UIAApplication getFrontMostApp();
  
  public String getLanguage();
  
  public String getLocale();

  public void setTimeout(int timeoutInSecnds);

  public int getTimeout(); 
}
