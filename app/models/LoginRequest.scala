package models

case class LoginRequest(username: String, password: String)

object LoginRequest {

  /*

  val availableUsers= Seq(LoginRequest("root", "god"),
    LoginRequest("sysadmin", "password1"),
    LoginRequest("kali.tummala@gmail.com", "Sairam786516"))

  val validUsers = Map("sysadmin" -> "password1", "root" -> "god")

  def authenticate = validUsers.exists(_ == (availableUsers))*/


}
