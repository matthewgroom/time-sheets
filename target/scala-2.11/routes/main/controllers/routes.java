
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/temp/development/time-sheets/conf/routes
// @DATE:Fri Aug 24 15:31:56 BST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
