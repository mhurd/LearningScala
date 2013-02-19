import java.util.regex._

// catch the exception and change to Option None
def pattern(s: String): Option[Pattern] =
  try {
    Some(Pattern.compile(s))
  } catch {
    case e: PatternSyntaxException => None
  }

def mkMatcher(pat: String): Option[String => Boolean] =
  pattern(pat) map (p => (s: String) => p.matcher(s).matches)

// show that incorrect syntax results in a None for the matcher
mkMatcher("\'\\|***>")
def matcher = mkMatcher("[0-9]*")

// Use getOrElse to handle the matcher being None
matcher.getOrElse((a: String) => false)("01189277655")

// can also use for-comprehension to safely handle the
// None from the pattern function
def mkMatcher_1(pat: String): Option[String => Boolean] =
  for {
    p <- pattern(pat)
  } yield ((s: String) => p.matcher(s).matches)

// wrap in another for-comprehension that handles
// the None
def doesMatch(pat: String, s: String): Boolean = {
    val m = for {
      p <- mkMatcher_1(pat)
    } yield p(s)
    m.getOrElse(false)
  }
doesMatch("[0-9]*", "01189277655")
doesMatch("[0-9]*", "ABCDE")
doesMatch("\'\\|***>", "01189277655")

