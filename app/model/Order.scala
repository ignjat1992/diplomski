package model

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints.pattern

case class Order(id: Option[String],
                 customer: Customer,
                 products: Seq[Product],
                 orders_price: Double,
                 delivery_price: Double,
                 total_price: Double)

object Order {
  import play.api.libs.json._

  val orderForm: Form[Order] = Form {
    mapping(
      "id" -> optional(text verifying pattern(
        """[a-fA-F0-9]{24}""".r, error = "error.objectId")),
      "customer" -> mapping(
        "name" -> optional(text),
        "street" -> text,
        "phone_number" -> optional(text)
      )(Customer.apply)(Customer.unapply),
      "products" -> seq(mapping(
        "name" -> text,
        "price" -> of[Double],
        "quantity" -> number,
        "final_price" -> of[Double]
      )(Product.apply)(Product.unapply)),
      "orders_price" -> of[Double],
      "delivery_price" -> of[Double],
      "total_price" -> of[Double]
    )(Order.apply)(Order.unapply)
  }

  implicit object OrderWrites extends OWrites[Order] {
    def writes(order: Order): JsObject = Json.obj(
      "id" -> order.id,
      "customer" -> order.customer,
      "products" -> order.products,
      "orders_price" -> order.orders_price,
      "delivery_price" -> order.delivery_price,
      "total_price" -> order.total_price,
    )
  }

  implicit object OrderReads extends Reads[Order] {
    def reads(json: JsValue): JsResult[Order] = json match {
      case obj: JsObject => try {
        val id = (obj \ "_id").asOpt[String]
        val customer = (obj \ "customer").as[Customer]
        val products = (obj \ "products").as[Seq[Product]]
        val orders_price = (obj \ "orders_price").as[Double]
        val delivery_price = (obj \ "delivery_price").as[Double]
        val total_price = (obj \ "total_price").as[Double]

        JsSuccess(Order(id, customer, products, orders_price, delivery_price, total_price))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}