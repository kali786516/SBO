package models

case class Login(username:String,password:String)

object Login {
  /*private var loginList: List[Login] = List()*/

  /*
  def add(username: String,password:String,name:String) = {
    val lastId: Int = if (loginList.nonEmpty) loginList.last.id else 0
    loginList = loginList ++ List(Login(lastId + 1, username,password,name))
  }*/

  def getloginmap()= {
    val availableArtist = Seq(Login("kali.tummala@gmail.com", "Sairam786516"),
      Login("krishnast.tummala@gmail.com", "Sairam786516"),
      Login("sysadmin", "password1"),
      Login("root", "god")
    )


    //val validUsers = Map("sysadmin" -> "password1", "root" -> "god")

    //availableArtist.toMap
    //println(availableArtist.toMap)
  }


}
