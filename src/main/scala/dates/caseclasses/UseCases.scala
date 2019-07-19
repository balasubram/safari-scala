package dates.caseclasses

case class Automobile(seats: Int) {
  def showMe: String = s"I'm an automobile with $seats seats"
}

case class Truck(payload: Int)

object UseCases {
  def main(args: Array[String]): Unit = {
    val car = Automobile(4)
    println(s"The car is ${car}")
    val otherCar = Automobile(4)
    val yetAnother = Automobile(5)
    println(s"car == otherCar? ${car == otherCar}")
    println(s"car == yetAnother? ${car == yetAnother}")
    println(s"use the method: ${car.showMe}")
    val truck = Truck(5000)

    val transporter = if (math.random > 0.5) car else truck
    val capacity = transporter match {
      case Automobile(seats) => {
        println(s"It's a car with ${seats} seats")
        200 * seats
      }
      case Truck(payload) => {
        println(s"it's a truck with ${payload} payload")
        payload
      }
    }
  }
}
