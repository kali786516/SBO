package controllers
import models._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{mapping, text, tuple}
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json._
import play.api.libs.json.JsPath
import play.api.libs.json.Json.toJson
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.libs.json.Reads.StringReads
import play.api.libs.json.Reads.functorReads
//import models.LoginRequest
import models.Login

object PlayTutorialController extends Controller {

  val signupForm = Form(
    mapping(
      "username" -> text(minLength = 4),
      "password" -> text(minLength = 4)
    )(MyUser.apply)(MyUser.unapply)
  )


  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => MyUser.authenticate(email, password).isDefined
      //case _ => println(result) true
    })
  )

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm)).withNewSession
    //Redirect(routes.PlayTutorialController.home)
  }


  def getdbvalues = Action {
    val users=MyUser.all
    Ok(views.html.dbvalues(users))
  }


  // Handles the username-password sent as JSON:
  def login = Action(parse.json) { request =>

    // Creates a reader for the JSON - turns it into a LoginRequest
    implicit val loginRequest: Reads[LoginRequest] = Json.reads[LoginRequest]

    /*
     * Call validate and if ok we return valid=true and put username in session
     */
    request.body.validate[LoginRequest] match {
      case s: JsSuccess[LoginRequest] if (s.get.authenticate) => {
        Ok(toJson(Map("valid" -> true))).withSession("user" -> s.get.username)
      }
      // Not valid
      case _ => Ok(toJson(Map("valid" -> false)))
    }
  }



  def home = Action { implicit request =>
    request.session.get("user").map {
      user =>
      {
        Ok(views.html.home())
      }
    }.getOrElse(Redirect(routes.PlayTutorialController.index()))
  }


  case class LoginRequest(username: String, password: String) {

    // Simple username-password map in place of a database:


    var validUsers2 = scala.collection.mutable.Map[String, String]()

    //validUsers2.put()


    val validUsers = Map("sysadmin" -> "password1", "root" -> "god")



    def authenticate = validUsers.exists(_ == (username, password))

  }

  /*relates to task button*/

  def tasks = Action { implicit request =>
    //Ok(views.html.index(Task.all))
    Ok(views.html.index(loginForm))
  }

  def createnewtasks=Action {
    Ok(views.html.createnewtask(Task.all))
  }

  def newTask = Action(parse.urlFormEncoded) {
    implicit request =>
      Task.add(request.body.get("taskName").get.head)
      //Redirect(routes.BookControllerDB.index())
      //Ok(views.html.newtask(Task.all))
      Redirect(routes.PlayTutorialController.createnewtasks())
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }

  /*relates to search artist button*/

  def listArtist=Action {
    Ok(views.html.searchartist(Artist.fetch))
  }

  /*def signup=Action {
    Ok(views.html.signup())
  }*/

  def searchArtistByName()=Action {
    Ok(views.html.searchartistbyname(Artist.fetch))
  }

  def searchArtistByCountry()=Action {
    Ok(views.html.searchartistbycountry(Artist.fetch))
  }


  def fetchArtistByCountry(name:String) = Action(parse.urlFormEncoded) {
    implicit request =>
      //Artist.fetchByName(request.body.get("artistName").get.head)
      Ok(views.html.searchartist(Artist.fetchByCountry(request.body.get("countryName").get.head)))
  }


  def fetchArtistByName(name:String) =  Action(parse.urlFormEncoded) {
    implicit request =>
      //Artist.fetchByName(request.body.get("artistName").get.head)
      Ok(views.html.searchartist(Artist.fetchByName(request.body.get("artistName").get.head)))
  }

  def search(name: Option[String], country: String) = Action {
    val result = name match{
      case Some(n) => Artist.fetchByNameAndCountry(n,country)
      case None => Artist.fetchByCountry(country)
    }
    if(result.isEmpty){
      NoContent
    } else {
      Ok(views.html.searchartist(result))
    }
  }



}
