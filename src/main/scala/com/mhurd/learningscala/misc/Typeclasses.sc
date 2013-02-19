import com.mhurd.learningscala.misc._

val movie = Movie("Pulp Fiction", "Quentin Tarantino", 1994)

val employee = Employee("Mike Hurd", 36)

// Use the Typeclasses to label the entities appropriately
println(LabelMaker.label(movie))
println(LabelMaker.label(employee))

// Define a new implicit method in scope that will add
// a new label function the labels using the appropriate
// LabelMaker.
implicit def pimpWithLabel[T](t: T)(implicit lm: LabelMaker[T]) = new {
  def label = lm.label(t)
}




println(movie.label)

println(employee.label)



