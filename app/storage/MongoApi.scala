package storage

import javax.inject._
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Future

class MongoApi @Inject()(val reactiveMongoApi: ReactiveMongoApi) {
  import scala.concurrent.ExecutionContext.Implicits.global

  val myDb = reactiveMongoApi.database
  val orders:Future[JSONCollection] = myDb.map(_.collection("orders"))


}
