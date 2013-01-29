package com.mhurd.learningscala.finance

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CurrencyTest extends FlatSpec with ShouldMatchers {

  "Currencies" must "have correct display names" in {
    expect("USD") {
      USD.toString
    }
    expect("KRW") {
      KRW.toString
    }
    expect("EUR") {
      EUR.toString
    }
  }

  it must "have correct equals behaviour" in {
    expect(true) {
      USD == USD
    }
    expect(true) {
      KRW == KRW
    }
    expect(true) {
      EUR == EUR
    }
    expect(false) {
      USD == EUR
    }
    expect(false) {
      USD == KRW
    }
    expect(false) {
      KRW == EUR
    }
  }

}
