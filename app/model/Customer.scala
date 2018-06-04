package model

case class Customer(name: Option[String], street: String, phoneNumber: Option[String])

object Customer {
  import play.api.libs.json._

  implicit object CustomerWrites extends OWrites[Customer] {
    def writes(customer: Customer):JsObject = Json.obj(
      "name" -> customer.name,
      "street" -> customer.street,
      "phone_number" -> customer.phoneNumber
    )
  }

  implicit object CustomerReads extends Reads[Customer] {
    def reads(json:JsValue):JsResult[Customer] = json match {
      case obj: JsObject => try {
        val name = (obj \ "name").asOpt[String]
        val street = (obj \ "street").as[String]
        val phoneNumber = (obj \ "phone_number").asOpt[String]

        JsSuccess(Customer(name, street, phoneNumber))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}