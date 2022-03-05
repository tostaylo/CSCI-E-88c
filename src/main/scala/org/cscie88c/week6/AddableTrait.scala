package org.cscie88c.week6

trait Addable[A] {
  def plus(other: A): A
}

case class MyInt(value: Int) extends Addable[MyInt] {
  def plus(myInt: MyInt): MyInt = MyInt(myInt.value + this.value)
}

case class MyBool(value: Boolean) extends Addable[MyBool] {
  def plus(myBool: MyBool): MyBool = MyBool(myBool.value || this.value)
}
