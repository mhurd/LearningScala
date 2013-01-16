package com.mhurd.learningscala.futures

import concurrent._
import scala.util.{Failure, Success, Random}
import scala.concurrent.duration._

object Sequences {

  import ExecutionContext.Implicits.global

  def createFuture(throwException: Boolean): Future[Int] = {
    future {
      Thread.sleep(5000)
      if (throwException) throw new RuntimeException
      val rand = Random.nextInt(1000)
      Console.println(Thread.currentThread().getName + " generated: " + rand)
      rand
    } recover {
      case e: RuntimeException => {
        Console.println(Thread.currentThread().getName + " caught exception, returning: " + 0)
        0
      }
    }
  }

  def go() {

    val start = System.currentTimeMillis()
    val futures = List(
      createFuture(throwException = false),
      createFuture(throwException = false),
      createFuture(throwException = false),
      createFuture(throwException = false),
      createFuture(throwException = true)
    )

    val futuresSequence: Future[List[Int]] = Future.sequence(futures)
    val sum = Await.result(futuresSequence, 10 seconds).sum
    println("Sum = " + sum)
    val stop = System.currentTimeMillis()
    println("Took " + (stop-start) + " ms")

    Thread.sleep(5000)
  }

}
