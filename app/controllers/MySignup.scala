package controllers

import play.api.mvc.Controller
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

import models._

object MySignup  extends Controller {

  val signupForm = Form(
    mapping(
      "email" -> text(minLength = 4),
      "password" -> text(minLength = 4)
      )(MyUser.apply)(MyUser.unapply)
  )

  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => MyUser.authenticate(email, password).isDefined
    })
  )




  /**
    * Display an empty form.
    */
  //def form = Action {
    //Ok(html.mylogin(signupForm));
  //}

  /**
    * Handle login form submission.
    */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.mylogin(formWithErrors)),
      user => Redirect(routes.PlayTutorialController.index).withSession("password" -> user._1)
    )
  }



  /**
    * Handle form submission.
    */
  def submit = Action { implicit request =>
    signupForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      //errors => BadRequest(html.mylogin(errors)),
      errors => Ok,
      // We got a valid User value, display the summary
      //user => Ok(html.summary(user))
      user => {
        println(user.email)
        println(user.password)

        //BookDB.create(books)
        Ok
        //Ok(html.summary(MyUserLogin))
      }
    )
  }







}
