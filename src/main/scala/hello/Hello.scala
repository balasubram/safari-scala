package hello

// no public, no static keywords
object Hello {
  def +++(x: String): String = "hey, you sent me " + x

  // "void" usually means "nothing" Unit is the no-value value
  // stylistically don't omit the = for a function definition...
  def main(args: Array[String]): Unit = {
    println("Hello Scala World")
    var count = 0;
    while (count < 2) {
      count += 1
      val name/*: String*/ = "Fred" + count
      println(count)
    }
//    var name = "Fred"
//    name = "Frederick"
//    name = 9
    val two:Int = 2

    /*Console.*/println(two.+(3))
    // illustrations of "infix" style
    Console.println(two + 99)
    Console.println(two + (99))
    // can use curlies, rather than parens for a single method argument
    Console println {two + 99}
    Console println two + 99

//    val newText = Hello.+++("Strange")
    val newText = Hello +++ "Strange"
    println(newText)

    val h = "h"
    val ello = "ello"
    // use == to test equality, it delegates to equals.
    // if you want identity comparison use .eq()
    println("is hello equal to hello? " + ("hello" == h + ello))
    println("is hello same string in memory as hello? " + ("hello".eq(h + ello)))
  }
}
