import com.mhurd.learningscala.scalaindepth.chapter6.DefaultHandles

class Changer extends DefaultHandles {

  var variable: Int = 1

  def setVariable(newValue: Int) = {
    variable = newValue
    notifyListeners()
  }

}

val foo = new Changer()

foo.observe((changer) => println("Changed to " + changer.variable))
foo.setVariable(2)
foo.setVariable(3)
foo.setVariable(4)