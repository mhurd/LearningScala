
// Partial application is calling a function with some of its
// arguments resulting in a new function that accepts the rest
val times2 = (2 * _) // note the type inference
// alternatively if the unknown arg is on the left the declaration
// requires its type to be declared:
// val times2: Int => Int = (_ * 2)