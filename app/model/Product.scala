package model

case class Product(name: String, price: Double, quantity: Int, final_price: Double)

object Product {
  import play.api.libs.json._

  implicit object ProductWrites extends OWrites[Product] {
    def writes(product: Product): JsObject = Json.obj(
      "name" -> product.name,
      "price" -> product.price,
      "quantity" -> product.quantity,
      "final_price" -> product.final_price
    )
  }

  implicit object ProductReads extends Reads[Product] {
    def reads(json: JsValue): JsResult[Product] = json match {
      case obj: JsObject => try {
        val name = (obj \ "name").as[String]
        val price = (obj \ "price").as[Double]
        val quantity = (obj \ "quantity").as[Int]
        val final_price = (obj \ "final_price").as[Double]

        JsSuccess(Product(name, price, quantity, final_price))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}