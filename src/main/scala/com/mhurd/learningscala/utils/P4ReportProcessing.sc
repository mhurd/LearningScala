/**
 * Process the P4 report results of:
 *
 * select file, revision from files where file like '//UKDevelopment/GMO/trunk/%' order by revision desc;
 *
 */

import java.io.PrintWriter

val inputFile = "c:\\results.txt"
val outputFile = "c:\\results-processed.txt"

val filterRegex = """^.*(\.java).*$"""

val source = io.Source.fromFile(inputFile)

val matchRevision = """([0-9]*)$""".r
def getFileName(in: String): String = {
  val group = matchRevision.split(in)
  group match {
    case Array(file) => file.trim
    case _ => "?"
  }
}

def getRevision(in: String): Int = {
  val group = matchRevision.findFirstIn(in)
  group match {
    case Some(rev) => rev.toInt
    case _ => 0
  }
}

def splitFile(in: String): (String, Int) = {
  (getFileName(in), getRevision(in))
}

val pairs = source.getLines().toList map splitFile

val prunedPairs = for {
  aMap <- pairs.groupBy(_._1)
  if filterRegex.r.findFirstIn(aMap._1).isDefined
} yield {
  aMap._2.sortBy(_._2).reverse.head
}

val p = new PrintWriter(outputFile)

for {
  pair <- prunedPairs.toList.sortBy(_._2).reverse
} yield {
  p.write(pair._1 + " " + pair._2 + "\n")
}





















p.close()

source.close()































