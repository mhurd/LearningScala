// A higher-kinded type that simplifies
// the type-signature of a callback function
type Callback[T] = Function1[T, Unit]

// Here is a Callback that accepts an Int
val x : Callback[Int] = y => println(y + 2)
x(1)

// defines a function that requires a type
// parameter M that itself is parameterised by a single
// type parameter. The parameter type supplies the
// concrete type - in this case an Int
def foo[M[_]](f: M[Int]) = f

// example using the Callback
foo[Callback](x)(1)
// you can do the same thing with multiple types
def bar[M[_, _]](f: M[Int, Unit]) = f


// example using Function1
bar[Function1](x)(1)

// you can also do the same thing in-line with a type lambda
foo[({type X[Y] = Function1[Y, Unit]})#X]((x: Int) => println(x))(99)



