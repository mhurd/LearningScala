import com.mhurd.learningscala.scalaindepth.chapter6._

class Changer extends DefaultHandles {
  var variable: Int = 1
  def setVariable(newValue: Int) {
    variable = newValue
    notifyListeners()
  }

}

val foo = new Changer()
val bar = new Changer()
val handle = foo.observe((changer) => println("Changed to " + changer.variable))
foo.setVariable(2)
foo.setVariable(3)
foo.setVariable(4)

handle(foo)

foo.unObserve(handle)

foo.setVariable(5)
class MyClosable {
  def close() {
    println("closed...")
  }

}

Resources.closeResource(new MyClosable)
