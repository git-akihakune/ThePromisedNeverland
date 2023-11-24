package adventure

class Interface:
    def slowPrint(line: String, waitTime: Option[Int] = None) =
        val sleepTime = waitTime.getOrElse(200)
        Thread.sleep(sleepTime)
        println(line)
