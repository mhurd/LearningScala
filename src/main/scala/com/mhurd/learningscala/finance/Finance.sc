import com.mhurd.learningscala.finance.Tenor

val tenor = Tenor("3D")

println("magnitude = " + tenor.magnitude)
println("unit = " + tenor.unit)

println("Using implicit: " + (tenor > "1D"))
println("Is 1Y < 3M? = " + (Tenor("1Y") < Tenor("3M")))
println("Is 1D < 2M? = " + (Tenor("1D") < Tenor("2M")))
println("Is 1Y < 2Y? = " + (Tenor("1Y") < Tenor("2Y")))
println("Is 1Y > 3M? = " + (Tenor("1Y") > Tenor("3M")))
println("Is 1D > 0D? = " + (Tenor("1D") > Tenor("0D")))
println("Is 3Y > 2Y? = " + (Tenor("3Y") > Tenor("2Y")))
println("Is 2W > 2W? = " + (Tenor("2W") > Tenor("2W")))

val tenors = List("1M", "1Y", "3W", "47D", "3Y", "1D")
println("Shortest Tenor = " + Tenor.shortestTenor(tenors))
println("Longest Tenor = " + Tenor.longestTenor(tenors))
