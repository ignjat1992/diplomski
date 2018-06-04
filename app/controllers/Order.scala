package controllers

import javax.inject._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}
import model.Order._
import model.Menu._

class Order @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

  def orders() = Action { implicit request =>
    Ok(views.html.orders(orderForm, menuSeq))
  }
}




