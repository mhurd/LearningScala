package com.mhurd.learningscala.finance

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class TenorTest extends FlatSpec with ShouldMatchers {

  def basicFixture =
    new {
      val stringTenors = List("1M", "35D", "2Y", "3W", "11M", "12W", "47D")
      val tenors = List(Tenor("1M"), Tenor("35D"), Tenor("2Y"), Tenor("3W"), Tenor("11M"), Tenor("12W"), Tenor("47D"))
    }

  "Tenors" must "be able to be constructed from a String" in {
    expect("1D") {
      Tenor("1D").toString
    }
    expect("1W") {
      Tenor("1W").toString
    }
    expect("1M") {
      Tenor("1M").toString
    }
    expect("1Y") {
      Tenor("1Y").toString
    }
  }

  it must "throw an illegal argument exception for malformed tenors" in {
    evaluating { Tenor("1Z") } should produce [IllegalArgumentException]
    evaluating { Tenor("W1M") } should produce [IllegalArgumentException]
    evaluating { Tenor("2") } should produce [IllegalArgumentException]
    evaluating { Tenor("W") } should produce [IllegalArgumentException]
  }

  it must "be able to find the shortest Tenor in a list of String Tenors" in {
    val f = basicFixture
    expect("3W") {
      Tenor.shortestTenor(f.stringTenors).toString
    }
  }

  it must "be able to find the longest Tenor in a list of String Tenors" in {
    val f = basicFixture
    expect("2Y") {
      Tenor.longestTenor(f.stringTenors).toString
    }
  }

  it must "be able to find the shortest Tenor in a list of Tenors objects" in {
    val f = basicFixture
    expect("3W") {
      Tenor.shortestTenor(f.tenors).toString
    }
  }

  it must "be able to find the longest Tenor in a list of Tenors objects" in {
    val f = basicFixture
    expect("2Y") {
      Tenor.longestTenor(f.tenors).toString
    }
  }

}
