import play.api.{Application, GlobalSettings}
import play.api.db.DB
import anorm._

object Global extends GlobalSettings {

  override def onStart(app: Application)= {

    import play.api.Play.current

    DB.withConnection { implicit connection =>

      SQL("drop table if exists user;").execute()
      SQL("create table if not exists user (  email varchar(255) ,password varchar(255) not null);").execute()
      SQL("INSERT INTO user (email,password) VALUES('kali.tummala@gmail.com','Sairam786516')").execute()
      //SQL("INSERT INTO books(title,price,author) VALUES('Apache Kafka CookBook','27$','Shapria')").execute()
      //SQL("INSERT INTO books(title,price,author) VALUES('Apache Scala CookBook','10$','Alvin Alexander')").execute()
    }


  }

}
