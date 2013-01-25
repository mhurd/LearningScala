package com.mhurd.learningscala.scalaindepth

sealed trait HList {}

final case class HCons[H, T <: HList](head: H, tail: T) extends HList {
  def ::[T](v: T) = HCons(v, this)
  override def toString = head + " :: " + tail
}

final class HNil extends HList {
  def ::[T](v: T) = HCons(v, this)
  override def toString = "Nil"
}

object HList {
  type ::[H, T <: HList] = HCons[H, T]
  val :: = HCons
  val HNil = new HNil
}


