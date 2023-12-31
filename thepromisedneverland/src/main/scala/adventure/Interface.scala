package adventure

class TextInterface:
    def resetTextColor = 
        print(Console.RESET)

    def colorText(content: String, color: String): String =
        val ansiColor = color match {
            case "blue" => Console.BLUE
            case "red" => Console.RED
            case "green" => Console.GREEN
            case "cyan" => Console.CYAN
            case "yellow" => Console.YELLOW
            case "magenta" => Console.MAGENTA
            case "white" => Console.WHITE
            case "black" => Console.BLACK
        }
        ansiColor + content 

    def artisticPrint(content: String, color: String = "white", waitTime: Option[Int] = None, noNewline: Boolean = false): Unit =
        val sleepTime = waitTime.getOrElse(200)
        Thread.sleep(sleepTime)
        if noNewline then
            print(this.colorText(content, color))
        else
            println(this.colorText(content, color))
        this.resetTextColor

    def setBackgroundColor(color: String): Unit =
        val ansiColor = color match {
            case "black" => Console.BLACK_B
            case "red" => Console.RED_B
            case "green" => Console.GREEN_B
            case "yellow" => Console.YELLOW_B
            case "blue" => Console.BLUE_B
            case "magenta" => Console.MAGENTA_B
            case "cyan" => Console.CYAN_B
            case "white" => Console.WHITE_B
        }
        print(ansiColor)
    
    // untested, use with caution
    def invisibleText(content: String): String = Console.INVISIBLE + content
    def reversedText(content: String): String = Console.REVERSED + content
    def underlinedText(content: String): String = Console.UNDERLINED + content
    def boldText(content: String): String = Console.BOLD + content
    def blinkText(content: String): String = Console.BLINK + content


class Audio:
    import javax.sound.sampled._

    // Template audio file
    var audioInputStream = AudioSystem.getAudioInputStream(new java.io.File("data/audio/neverland-piano.wav"))
    val clip = AudioSystem.getClip

    def play(audioPath: String): Unit =
        try
            audioInputStream = AudioSystem.getAudioInputStream(new java.io.File(audioPath))
            clip.open(audioInputStream)
            clip.start
        catch
            case e: Exception => println(Console.RED + "[!]Your system does not support audio, so audio playback won't work.[!]")

    def playInLoop(audioPath: String, loop: Int): Unit =
        try
            audioInputStream = AudioSystem.getAudioInputStream(new java.io.File(audioPath))
            clip.open(audioInputStream)
            clip.start
            loop match {
              case 0 => clip.loop(Clip.LOOP_CONTINUOUSLY)
              case _ => clip.loop(loop)
            }
        catch
            case e: Exception => println(Console.RED + "[!]Your system does not support audio, so audio playback won't work.[!]")

    def stop: Unit =
        clip.close
        audioInputStream.close

            

