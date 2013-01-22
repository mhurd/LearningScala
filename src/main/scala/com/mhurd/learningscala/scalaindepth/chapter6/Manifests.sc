import reflect.ClassTag

// declaring the implicit ClassTag captures the type
// information and makes it available when the new
// Array is constructed.
def first[A : ClassTag](x: Array[A]) = Array(x(0))

first(Array(1, 2, 4))



