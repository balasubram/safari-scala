package liststuff

import scala.annotation.tailrec

object PrintStuffImplicit {
  implicit val printit: Int => Unit = x => println(s"the value is ${x}")
}

object UseLists {
  implicit val wrapRat: Int => Rational = x => new Rational(x, 1)

  class Rational(val num: Int, val demon: Int) {
    def + (n: Int): Rational = new Rational(num + n * demon, demon)
    def + (r: Rational) = ???
  }

  implicit class Oddity(val n : Int) {
    def +-+(s: String): String = s"oddity made this with $n and $s"
  }

  implicit class GreetingMessage(val msg: String)
  def greet(name:String)(implicit message: GreetingMessage): Unit =
    println(message.msg + name)

  //  @tailrec
  def toUpper(list: List[String]): List[String] = list match {
    case Nil => Nil
    case h :: tail => h.toUpperCase :: toUpper(tail)
  }

  @tailrec
  def forAllItems[T](list: List[T])(implicit op: T => Unit): Unit = list match {
    case Nil => ()
    case h :: t => op(h); forAllItems(t)
  }

  @tailrec
  def showAll[T](list: List[T]): Unit = list match {
    case Nil => ()
    case h :: t => println(s"> ${h}"); showAll(t)
  }

  //  def showAllNumbers(list: List[Int]) : Unit = list match {
  //    case Nil => ()
  //    case h :: t =>
  //      println(s">> ${h}")
  //      showAllNumbers(t)
  //  }
  def main(args: Array[String]): Unit = {
    //    val names = List[String]("Fred", "Jim", "Sheila")
    val names = List("Fred", "Jim", "Sheila", "Albert")
    println(s"first name is ${names(0)}")
    val changed = names.updated(1, "Jeremy")
    println(s"second name of names is ${names(1)}")
    println(s"second name changed is ${changed(1)}")
    println(s"head of names is " + names.head)

    //    val manyNames = Nil.::("Susan").::("Sally")
    val manyNames = "Sally" :: "Susan" :: names
    names match {
      case List(_, _, _) => println("Three elements")
      case x: List[_] if x.size > 2 => println("More than two elements")
      case _ => println("Other")
    }

    println("----------------------")
    manyNames match {
      case Nil => println("empty list")
      case h :: tail => println(s"List with head ${h} and tail ${tail}")
    }
    println("----------------------")
    showAll(manyNames)

    println("----------------------")
    showAll(List(1, 3, 5, 7, 9))

    println("----------------------")
    import PrintStuffImplicit._
//    forAllItems(List(1, 3, 5, 7, 9))(x => println(x))
    forAllItems(List(1, 3, 5, 7, 9))

    println("----------------------")
    forAllItems(manyNames)(x => println(x))

    println("----------------------");
    {
                                       // implicit class handling "auto conversion"
      implicit val msg: GreetingMessage = "Dear Mx. "
      greet("Albert") // implicit argument
    }

    implicit def albert: Int => String =
      x => s"automatically made string, value is $x"

    val txt: String = 99
    println(txt)
    println(99 +-+ "hello")

    val half = new Rational(1, 2)
    val r1 = half + 1
    val res = 1 + half // implicit conversion of Int to Rational, to allow use of + with rational
  }
}
