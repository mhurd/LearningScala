package com.mhurd.learningscala.scalaindepth

case class Implicits(name: String) {
}

object Implicits {

  implicit object ImplicitFoo extends Implicits("foo") {
  }

}
