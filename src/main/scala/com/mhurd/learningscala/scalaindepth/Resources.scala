package com.mhurd.learningscala.scalaindepth

import scala.language.reflectiveCalls

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
