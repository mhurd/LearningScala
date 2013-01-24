import com.mhurd.learningscala.scalaindepth.SyncUtil._

import java.io.File

val filePath1 = "src/main/resources/com/mhurd/learningscala/scalaindepth/tmp1"



val filePath2 = "src/main/resources/com/mhurd/learningscala/scalaindepth/tmp2"



synchronise(
  new File(filePath1),
  new File(filePath2)
)







