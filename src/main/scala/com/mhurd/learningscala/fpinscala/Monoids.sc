/**
 * The exact same rules govern integer addition. It's associative and it
 * has an identity element, 0, which "does nothing" when added to another
 * integer. Ditto for multiplication, whose identity element is 1.
 * The Boolean operators, && and || are likewise associative, and they
 * have identity elements true and false, respectively.
 * These are just a few simple examples, but algebras like this are
 * virtually everywhere. The term for this kind of algebra is "monoid".
 * The laws of associativity and identity are collectively called the
 * monoid laws. A monoid consists of:
 * - Some type A
 * - A binary associative operation that takes two values of type A
 * and combines them into one.
 */

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

val stringMonoid = new Monoid[String] {
  def op(a1: String, a2: String) = a1 + a2
  def zero = ""
}

val strings = List("One", "Two", "Three")
strings.foldLeft(stringMonoid.zero)(stringMonoid.op(_, _))

val intAddition = new Monoid[Int] {
  def op(a1: Int, a2: Int): Int = a1 + a2
  def zero: Int = 0
}

val ints = List(1, 2, 3, 4, 5)

val intMultiplication = new Monoid[Int] {
  def op(a1: Int, a2: Int): Int = a1 * a2
  def zero: Int = 1
}

ints.foldLeft(intMultiplication.zero)(intMultiplication.op(_, _))

val bools1 = List(true, true, true) // or-ed/and-ed = true
val bools2 = List(true, false, true) // or-ed = true, and-ed = false
val bools3 = List(true) // or-ed = true, and-ed = true
val bools4 = List(false) // or-ed = false, and-ed = false

val booleanOr = new Monoid[Boolean] {
  def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
  def zero: Boolean = false
}
bools1.foldLeft(booleanOr.zero)(booleanOr.op(_, _))
bools2.foldLeft(booleanOr.zero)(booleanOr.op(_, _))
bools3.foldLeft(booleanOr.zero)(booleanOr.op(_, _))
bools4.foldLeft(booleanOr.zero)(booleanOr.op(_, _))

val booleanAnd = new Monoid[Boolean] {
  def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
  def zero: Boolean = true
}

bools1.foldLeft(booleanAnd.zero)(booleanAnd.op(_, _))
bools2.foldLeft(booleanAnd.zero)(booleanAnd.op(_, _))
bools3.foldLeft(booleanAnd.zero)(booleanAnd.op(_, _))
bools4.foldLeft(booleanAnd.zero)(booleanAnd.op(_, _))

// Notice that we have a choice in how we implement `op`.
// We can compose the options in either order. Both of those implementations
// satisfy the monoid laws, but they are not equivalent.
// This is true in general--that is, every monoid has a _dual_ where the
// `op` combines things in the opposite order. Monoids like `booleanOr` and
// `intAddition` are equivalent to their duals because their `op` is commutative
// as well as associative.
def optionMonoid[A] = new Monoid[Option[A]]{
  def op(x: Option[A], y: Option[A]) = x orElse y
  val zero = None
}

// We can get the dual of any monoid just by flipping the `op`.
def dual[A](m: Monoid[A]): Monoid[A] = new Monoid[A] {
  def op(x: A, y: A): A = m.op(y, x)
  val zero = m.zero
}

// Now we can have both monoids on hand
def firstOptionMonoid[A]: Monoid[Option[A]] = optionMonoid[A]
def lastOptionMonoid[A]: Monoid[Option[A]] = dual(firstOptionMonoid)

// There is a choice of implementation here as well.
// Do we implement it as `f compose g` or `f andThen g`? We have to pick one.
def endoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
  def op(f: A => A, g: A => A) = f compose g
  val zero = (a: A) => a
}

// Just what is a monoid, then? It is simply an implementation of an
// interface governed by some laws. Stated tersely, a monoid is a type
// together with an associative binary operation (op) which has an
// identity element (zero).