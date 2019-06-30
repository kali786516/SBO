package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class User(
                 username: String,
                 password: String,
                 email: String,
                 profile: UserProfile
               )

case class UserProfile(
                        country: String,
                        address: Option[String],
                        age: Option[Int]
                      )

case class MyUserLogin(
                 username: String,
                 password: String
               )


