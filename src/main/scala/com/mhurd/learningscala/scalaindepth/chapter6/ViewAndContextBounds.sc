case class AClass()
case class BClass()

def AtoB(a: AClass): BClass = BClass()
// declare implicit
implicit val atb = AtoB(_)

// indicates that there must be an A => BClass
// implicit conversion in scope
def foo[A <% BClass](a: A): BClass = a
foo(AClass())

def foo2[A <% BClass : List](a: A): BClass = a
