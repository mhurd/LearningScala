def even(n: Int): Boolean = n % 2 == 0

def negative(n: Int): Boolean = n < 0

// Higher-order function that takes a function
// and returns a new function that negates the
// result of the original function
def not(f: Int => Boolean): Int => Boolean =
  n => !(f(n))

def odd = not(even)
// same as:
// def odd = not(n => even(n))
def positive = not(negative)
odd(1)
odd(2)
positive(1)
positive(-3)

def isEmpty(s: String): Boolean = s == ""

// Improved version of 'not' that takes a type
// parameter which allows it to work on more than
// just functions of Int => Boolean
def not2[A](f: A => Boolean): A => Boolean =
  n => !(f(n))

def notEmpty = not2(isEmpty)
notEmpty("foobar")
notEmpty("")

// Define a new type to better capture the meaning
// of A => Boolean
type Predicate[A] = A => Boolean

def not3[A](f: Predicate[A]): Predicate[A] =
  n => !(f(n))
def notEmpty2 = not3[String](isEmpty)
notEmpty2("foobar")
notEmpty2("")

def divisibleBy(k: Int): Predicate[Int] =
  n => n % k == 0

def divisibleBy2 = divisibleBy(2)
divisibleBy2(10)
divisibleBy2(9)

// Curried version, first call creates the function
// and the second passes the argument to it
divisibleBy(3)(9)
def even2(n: Int): Boolean =
  divisibleBy(2)(n)
even2(1)
even2(4)

def divisibleBy3And5(k: Int): Boolean =
  divisibleBy(3)(k) && divisibleBy(5)(k)
divisibleBy3And5(15)
divisibleBy3And5(3)

def divisibleBy3Or5(k: Int): Boolean =
  divisibleBy(3)(k) || divisibleBy(5)(k)

divisibleBy3And5(15)
divisibleBy3And5(3)

// Combines two predicates with a boolean operator
// to create a new higher-order function
def lift[A](
             f: (Boolean, Boolean) => Boolean,
             l: Predicate[A],
             r: Predicate[A]): Predicate[A] =
  n => f(l(n), r(n))


lift[Int](_&&_, divisibleBy(2), divisibleBy(6))(12)
lift[Int](_&&_, divisibleBy(2), divisibleBy(6))(8)
