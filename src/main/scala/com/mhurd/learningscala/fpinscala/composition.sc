// we have a function of type A => B, and a function of type
// B => C, we can compose them so that together they form a
// new function of type A => C
def compose[A,B,C](f: B => C, g: A => B): A => C =
  a => f(g(a))

def f1: String => String = s => s + "1"
def f2: String => String = s => s + "2"

def f3 = compose(f1, f2)

f3("f")

// Scala Function1 has this built
// works <- back through the composes
f1.compose(f2)("f")
// or -> forwards through the definition
f1.andThen(f2)("f")

// Multiple arguments
def lift[A,B,C,D](f: (B, C) => D)(g: A => B, h: A => C): A => D =
  a => f(g(a), h(a))

