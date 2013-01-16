import scala.concurrent._

// futures will spawn threads in the global context thread pool
import ExecutionContext.Implicits.global
import scala.util.Random

/**
 * Examples taken from:
 * http://www.srirangan.net/2013-01-controlling-flow-with-scala-futures
 */
object futures {

  def goRecover() {

    val tryDivideByZeroAgain = future {
      Thread.sleep(1000)
      1 / 0
    } recover {
      case e: ArithmeticException => "Infinity"
    }
    tryDivideByZeroAgain onSuccess {
      case e => Console.println(e)
    }
    tryDivideByZeroAgain onFailure {
      case e => Console.println(e)
    }

    Console.println("Try dividing by zero, recover from exception..")

    Thread.sleep(2000)

  }

  def goFallbackTo() {

    val f1 = future {
      Thread.sleep(500)
      1 / 0
    }

    val f2 = future {
      Thread.sleep(500)
      "Infinity"
    }

    f1 fallbackTo f2 onSuccess {
      case v => Console.println(v)
    }

    Console.println("Try dividing by zero, fallback to another future..")

    Thread.sleep(2000)

  }

  def goAndThen() {
    // the for {} construct lets us execute multiple futures in parallel
    // to serially execute futures in specific orders, we use `andThen`
    // andThen ensures execution orders in what would otherwise be random

    val whamBamThankYouMaam = future {
      Thread.sleep(500)
      Console.println("Wham!")
    } andThen {
      case _ => {
        Thread.sleep(500)
        Console.println("Bam!")
      }
    } andThen {
      case _ => {
        Thread.sleep(500)
        Console.println("Thank you ma'am!")
      }
    }

    Console.println("Will you score?")

    Thread.sleep(2000)
  }

  def goPromise() {
    // `promises` can be used to compose type safe futures

    val willYouMarryMe = promise[Boolean]()
    willYouMarryMe.future onSuccess {
      case yes => Console.println("Yes! :D")
    }
    willYouMarryMe.future onFailure {
      case no => Console.println("No :(")
    }

    future {
      Thread.sleep(1000)
      if (new Random().nextBoolean())
        willYouMarryMe success true // try passing non boolean value here
      else
        willYouMarryMe failure new Exception
    }

    Console.println("Will you marry me?")

    Thread.sleep(2000)
  }

}

futures.goRecover()




futures.goFallbackTo()




futures.goAndThen()



futures.goPromise()






