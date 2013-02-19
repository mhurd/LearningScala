package com.mhurd.learningscala.misc

case class Employee(name: String, age: Int)
case class Movie(title: String, director: String, year: Int)

trait LabelMaker[T] {
  def label(t: T): String
}

object LabelMaker {
  implicit object MovieLabelMaker extends LabelMaker[Movie] {
    def label(movie: Movie) = "Title: " + movie.title + " Director: " + movie.director
  }
  implicit object EmployeeLabelMaker extends LabelMaker[Employee] {
    def label(employee:Employee) = "Name: " + employee.name + " Age: " + employee.age
  }
  def label[T](someObject: T)(implicit lm: LabelMaker[T]) = lm.label(someObject)
}
