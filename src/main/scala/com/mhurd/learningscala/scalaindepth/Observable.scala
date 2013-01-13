package com.mhurd.learningscala.scalaindepth

trait Observable {

  type Handle

  var callbacks = scala.collection.mutable.HashMap[Handle, this.type => Unit]()

  def observe(callback: this.type => Unit): Handle = {

    val handle = createHandle(callback)
    callbacks += (handle -> callback)
    handle

  }

  def unObserve(handle: Handle) : Unit = {

    callbacks -= handle

  }

  protected def createHandle(callback: this.type => Unit): Handle

  protected def notifyListeners() : Unit = for(callback <- callbacks.values) callback(this)

}

trait DefaultHandles extends Observable {

  type Handle = (this.type => Unit)

  protected def createHandle(callback: this.type => Unit): Handle = callback

}
