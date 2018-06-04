package model

case class Menu(dishName: String, price: Double)

object Menu {

  val menuSeq = Seq(
    Menu("Margherita", 600.00),
    Menu("Capricciosa", 660.00),
    Menu("Prosciutto", 900.00),
    Menu("Funghi", 700.00),
    Menu("Quattro formaggi", 620.00),
    Menu("Napolitana", 630.00),
    Menu("Vegetariana", 680.00),
    Menu("Hawaii", 800.00),
    Menu("Marinara", 600.00),
    Menu("Prosciutto crudo", 650.00),
    Menu("Bianca", 630.00),
    Menu("Picante", 560.00),
    Menu("Al tonno", 700.00),
    Menu("Frutti di mare", 890.00),
    Menu("Calzone", 520.00),
  )
}