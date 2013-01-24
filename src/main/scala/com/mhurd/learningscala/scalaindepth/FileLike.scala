package com.mhurd.learningscala.scalaindepth

import java.io._
import scala.language.postfixOps
import io.Source

// Define a trait that wraps the type as a
// FileLike
trait FileLike[T] {

  def name(file: T): String

  def isDirectory(file: T): Boolean

  def children(directory: T): Seq[T]

  def child(parent: T, name: String): T

  def mkdirs(file: T)

  def content(file: T): InputStream

  def writeContent(file: T, otherContent: InputStream)

}

// Declares the implicit implementation of the FileLike wrapper for
// java.io.File
object FileLike {

  implicit val ioFileLike = new FileLike[File] {
    def name(file: File): String = file.getName

    def isDirectory(file: File): Boolean = file.isDirectory

    def children(directory: File): Seq[File] = directory.listFiles()

    def child(parent: File, name: String): File = new File(parent, name)

    def mkdirs(file: File) {
      file.mkdir()
    }

    def content(file: File): InputStream = new FileInputStream(file)

    def writeContent(file: File, otherContent: InputStream) {
      val contentString = Source.fromInputStream(otherContent).mkString("")
      println("Wrote content: '" + contentString + "' into " + file.getName)
    }

  }

}

object SyncUtil {

  // declares that the types F & T need an implicit wrapper FileLike[T] available
  def synchronise[F : FileLike, T : FileLike](from: F, to: T) {

    val fromHelper = implicitly[FileLike[F]]
    val toHelper = implicitly[FileLike[T]]

    def synchroniseFile(file1: F, file2: T) {
      toHelper.writeContent(file2, fromHelper.content(file1))
    }

    def synchroniseDirectory(dir1: F, dir2: T) {

      def findFile(file: F, directory: T): Option[T] = {
        (for (
          file2 <- toHelper.children(directory)
          if fromHelper.name(file) == toHelper.name(file2)
        ) yield file2).headOption
      }

      for (file1 <- fromHelper.children(dir1)) {
        val file2 = findFile(file1, dir2).getOrElse(toHelper.child(dir2, fromHelper.name(file1)))
        if (fromHelper.isDirectory(file1)) toHelper.mkdirs(file2)
        synchronise[F, T](file1, file2)
      }

    }

    if (fromHelper.isDirectory(from)) synchroniseDirectory(from, to)
    else synchroniseFile(from, to)

  }

}


