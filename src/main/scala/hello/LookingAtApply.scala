package hello

object LookingAtApply {
  def apply(x:String):String = s"You applied ${x} to me!"

  def main(args: Array[String]): Unit = {
    val names = Array.apply("Fred", "Jim", "Sheila")
//    val firstName = names.apply(0)
    val firstName = names(0)
    // string interpolation...
    // also raw".." and f"..." triple quotes for text over multiple lines...
    println(s"first name is $firstName")
    println(s"first name is ${firstName.toUpperCase} " +
      s"and it has ${firstName.length} characters")
    names(0) = "Frederick"
    names.update(0, "Frederick")

    println(s"first name is now ${names(0)}")

//    println(s"LookingAtApply('Hello') gives ${LookingAtApply.apply("Hello")} ")
    println(s"LookingAtApply('Hello') gives ${LookingAtApply("Hello")} ")
  }
}
