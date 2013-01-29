import com.mhurd.learningscala.scalaindepth.Implicits

def bar(implicit impl: Implicits): String = {
  impl.name + " bar"
}

// uses the implicit object defined in the Implicits
// companion object
println(bar) // should print "foo bar"

// bring a new implicit in scope to override that defined
// in the companion object
implicit object BarImplicit extends Implicits("bar"){}

// uses the locally scoped implicit object
println(bar) // should print "bar bar"
