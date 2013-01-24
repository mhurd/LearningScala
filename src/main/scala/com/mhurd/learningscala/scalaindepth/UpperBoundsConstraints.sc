class A {
  // B must be a sub-type of Traversable[Int]
  type B <: Traversable[Int]
  def count(b: B) = b.foldLeft(0)(_+_)
}
// refine B downwards is ok
val x = new A { type B = List[Int] }

// Works ok
x.count(List(1, 2))


// Compile error Set is not <: List
// x.count(Set(1, 2))

// refine B downwards is ok
val y = new A { type B = Set[Int] }

y.count(Set(1, 2))



