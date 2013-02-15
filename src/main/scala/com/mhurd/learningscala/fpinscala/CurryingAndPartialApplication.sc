// Partial application is calling a function with some of its
// arguments resulting in a new function that accepts the rest
val times2: Int => Int = (_ * 2)

// Currying - Instead of a function taking a single tuple
// of n arguments, a curried function is a chain of n
// functions that take one argument each.
// The arrow (=>) in function types actually associates to the
// right so A => B => C is equivalent to A => (B => C)
def curry[A,B,C](f: (A, B) => C): A => B => C =
  n => f(n,_)

def foo: (Int, Int) => Int = (a, b) => a*b

def curriedFoo = curry[Int, Int ,Int](foo)

curriedFoo(3)(3)

def uncurry[A,B,C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)

def uncurriedFoo = uncurry(curriedFoo)

uncurriedFoo(3, 3)