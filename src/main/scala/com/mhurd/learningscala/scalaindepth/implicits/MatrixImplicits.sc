import com.mhurd.learningscala.scalaindepth.implicits.{SameThreadStrategy, MatrixUtils, Matrix}


val m1 = new Matrix(Array(Array(1.0, 2.0, 3.0), Array(4.0, 5.0, 6.0)))



val m2 = new Matrix(Array(Array(1.0), Array(1.0), Array(1.0)))




 implicit val s = SameThreadStrategy

 MatrixUtils.multiply(m1, m2)




