package com.mhurd.learningscala.finance

import org.joda.time.{DateTime, Duration, Period}

case class Tenor(tenor:  String) {

  if (!tenor.matches("^[0-9]*[dD]$|^[0-9]*[wW]$|^[0-9]*[mM]$|^[0-9]*[yY]$")) {
    throw new IllegalArgumentException("Malformed Tenor!")
  }

  val magnitude = tenor.filter(_.isDigit).toInt

  val unit = tenor.filterNot(_.isDigit)

  private def durationFromCurrentTime(period: Period): Duration =
    period.toDurationFrom(new DateTime)

  def >(otherTenor: Tenor): Boolean =
    durationFromCurrentTime(this).isLongerThan(durationFromCurrentTime(otherTenor))

  def <(otherTenor: Tenor): Boolean =
    durationFromCurrentTime(this).isShorterThan(durationFromCurrentTime(otherTenor))

  override def equals(other: Any): Boolean =
    other match {
      case that: Tenor =>
        (that canEqual this) &&
          magnitude == that.magnitude &&
          unit == that.unit
      case _ => false
    }

  def canEqual(other: Any): Boolean =
    other.isInstanceOf[Tenor]

  override def hashCode: Int =
    41 * (
      41 + magnitude
      ) + tenor.hashCode

  override def toString: String = tenor

}

object Tenor {

  implicit def tenor2Period(tenor: Tenor): Period = {
    tenor.unit match {
      case "D" => Period.days(tenor.magnitude)
      case "W" => Period.weeks(tenor.magnitude)
      case "M" => Period.months(tenor.magnitude)
      case "Y" => Period.years(tenor.magnitude)
    }
  }

  implicit def string2Tenor(tenor: String): Tenor =
    Tenor(tenor)

  implicit def tenors2StringTenors(tenors: List[Tenor]): List[String] =
     tenors map (_.toString)

  def shortestTenor(tenorStrings: List[String]): Tenor =
    Tenor(tenorStrings reduce((t1, t2) => if (Tenor(t1) < Tenor(t2)) t1 else t2))

  def longestTenor(tenorStrings: List[String]): Tenor =
    Tenor(tenorStrings reduce((t1, t2) => if (Tenor(t1) > Tenor(t2)) t1 else t2))

}
