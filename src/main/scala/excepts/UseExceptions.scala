package excepts

import scala.util.{Failure, Success, Try}

object UseExceptions {
  // pass by "name" arguments -- evaluation does NOT happen as part
  // of function invocation. Code is (kinda) passed in unevaluated
  def loop(n: Int)(op: => Unit) : Unit = {
    println("In loop...")
    var count = n
    while (count > 0) {
      op
      print(".")
      count -= 1
    }
  }

  def main(args: Array[String]): Unit = {
    loop(4) {
      println("Hello")
      println("Goodbye")
    }

    Try {
      if (math.random > 0.5) throw new IllegalArgumentException
      "I succeeded"
    } match {
      case Success(value) => println(s"Got ${value}")
      case Failure(exception) => println(s"Failed with ${exception.getClass}")
    }
  }
}
