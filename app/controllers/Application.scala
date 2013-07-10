package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.{Iteratee, Enumerator}

object Application extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def websocket = WebSocket.using[String] { request =>
    val out = Enumerator.imperative[String]()

    val in = Iteratee.foreach[String] { message =>
      out.push("Hello " + message + "!")
    }

    (in, out)
  }
}