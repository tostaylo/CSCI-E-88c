package org.cscie88c.week4

object FunctionUtils {

  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int = {
    val list = List.fill(n)(f)
    list.foldLeft(x)((acc, next) => next(acc))
  }

  def myPositivePower(x: Int, n: Int): Int = {
    def square(base: Int): Int = base * x;
    applyNtimes(n - 1)(x)(square)
  }

  def deferredExecutor(name: String)(f: Int => Int): Int => Int = ???
}
