package com.mhurd.learningscala.finance

sealed abstract class Currency(val name: String, val description: String) {

  override def toString: String = name

  override def equals(other: Any): Boolean =
    other match {
      case that: Currency =>
        (that canEqual this) &&
          name == that.name
      case _ => false
    }

  def canEqual(other: Any): Boolean =
    other.isInstanceOf[Currency]

  override def hashCode: Int =
    41 * name.hashCode

}

object USD extends Currency("USD", "U.S. Dollar")
object KRW extends Currency("KRW", "Korean Won")
object EUR extends Currency("EUR", "Euro")


