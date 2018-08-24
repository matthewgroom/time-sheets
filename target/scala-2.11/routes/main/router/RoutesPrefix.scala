
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/temp/development/time-sheets/conf/routes
// @DATE:Fri Aug 24 15:31:56 BST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
