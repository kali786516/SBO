package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class MyUser(email:String,password:String)

object MyUser {

  /**
    * Parse a User from a ResultSet
    */
  val simple = {
    get[String]("MyUser.email") ~
      get[String]("MyUser.password") map {
      case email~password => MyUser(email,password)
    }
  }

  def all={
    DB.withConnection { implicit connection =>
      SQL("select * from user")().map { row =>
        MyUser(
          email = row[String]("email"),
          password = row[String]("password")
        )
      }.toList
    }
  }

  /**
    * Authenticate a User.
    */
  def authenticate(email: String, password: String): Option[MyUser] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
         select * from MyUser where
         email = {email} and password = {password}
        """
      ).on(
        'email -> email,
        'password -> password
      ).as(MyUser.simple.singleOpt)
    }
  }





}
