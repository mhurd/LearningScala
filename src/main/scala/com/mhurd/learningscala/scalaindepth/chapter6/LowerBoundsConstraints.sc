class A {
  // B must be a super-type of List[Int]
  type B >: List[Int]
  def foo(a: B) = a
}
// refine B upwards is ok
val x = new A { type B = Traversable[Int] }

x.foo(Set(1))


// works because as a runtime argument it can be a
// polymorphic subtype of B (Traversable[Int] in
// this case)

val y = new A { type B = Set[Int]}      // won't work, violates the type constraints




// Note the difference between compile type type
// constraints and run time type constraints

