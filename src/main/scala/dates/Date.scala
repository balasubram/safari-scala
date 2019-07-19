package dates

import java.sql.SQLException

//class Date(d: Int, m: Int, y: Int) {
//  private val day: Int = d
//  val month: Int = m
//  val year: Int = y

// val (or var) in constructor args creates/initializes fields directly
class Date(val day: Int, val month: Int, val year: Int) {
//  import dates.Date._
  if (month < 1 || month > 12 || day < 1 || day > Date.daysInMonth(month, year))
    throw new IllegalArgumentException("Bad date values")

  def this(day: Int, month: Int) = this(day, month, 2019)
  def this() = {
    this(1, 1)
    println("Created total default object")
  }

  println(s"Making a date with ${day}, ${month}, ${year}")
  def dayOfWeek: Int = {
    val (m1, y1) = if (month < 3) (month + 12, year - 1) else (month, year)
    /*return */(day + (13 * (m1 + 1) / 5) + y1 + y1 / 4 - y1 / 100 + y1 / 400) % 7
  }
  override def toString:String = s"Date of ${day}, ${month} ${year}"
}

object Date { // object is the place for "static" features
  def isLeap(year: Int): Boolean =
    (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))

  def daysInMonth(month: Int, year: Int): Int =  month match {
    case 9 | 4 | 6 | 11 => 30
    case 2 => if (isLeap(year)) 29 else 28
//    case 1 | 3 |....
    case _ => 31 // not strictly good, since bad months produce 31!!!
  }
}

class Holiday(day: Int, month:Int, year: Int, val name:String)
  extends Date(day, month, year) {
  override def toString: String = super.toString + " a holiday called " + name
}

object TryDates {
  def main(args: Array[String]): Unit = {
    try {
      if (math.random() > 0.5) throw new SQLException("Bad DB")
      val d = new Date(-1, 1, 2000)
      println(s"Day of week of ${d} is ${d.dayOfWeek} and the day is ${d.day}")
    } catch {
      case x: IllegalArgumentException => // capture, instanceof and cast...
        println(s"It broke because bad args: ${x.getMessage}")
      case _ => println("Some other problem")
    }
    println(s"default date is ${new Date}")
    val h: Date = new Holiday(1, 1, 2000, "new year's day")

  }
}