package com.mhurd.learningscala.scalaindepth.chapter6

object Resources {

  // Structural type defines a type which is defined as any type with a close() method
  type Resource = {
    def close()
  }

  // Uses reflection to close a resource
  def closeResource(r: Resource) {
    r.close()
  }

}
