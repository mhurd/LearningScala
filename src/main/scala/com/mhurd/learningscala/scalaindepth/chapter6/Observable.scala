package com.mhurd.learningscala.scalaindepth.chapter6

trait Observable {

  // Note that this is an abstract type, it has no definition
  type Handle

  // a map of Handles to callback functions that only accept as an arg
  // this specific instance type.
  var callbacks = scala.collection.mutable.HashMap[Handle, this.type => Unit]()

  def observe(callback: this.type => Unit): Handle = {
    val handle = createHandle(callback)
    callbacks += (handle -> callback)
    handle
  }

  def unObserve(handle: Handle) {
    callbacks -= handle
  }

  protected def createHandle(callback: this.type => Unit): Handle

  protected def notifyListeners() {
    for(callback <- callbacks.values) callback(this)
  }

}

trait DefaultHandles extends Observable {

  // Handle is now a concrete type with a definition specific to this implementation
  type Handle = (this.type => Unit)

  // Make the Handle be the callback itself by default
  protected def createHandle(callback: this.type => Unit): Handle = callback

}
