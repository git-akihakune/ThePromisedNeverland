import adventure.*
import scala.io.StdIn.*


object AdventureTextUI extends App:

  private val game = Adventure()
  private val ui = TextInterface()
  private val player = game.player
  this.run()

  private def run() =
    ui.artisticPrint(this.game.welcomeMessage, "cyan")
    ui.artisticPrint(this.game.warningMessage, "red")
    ui.resetTextColor
    while !this.game.isOver do
      this.printAreaInfo()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)

  private def printAreaInfo() =
    val area = this.player.location
    val time = this.game.timeReport
    val fullDescription = area.name + ", " + time
    
    ui.artisticPrint(area.areaArt, "white", Option(500))

    ui.artisticPrint("\n\n" + fullDescription, "green")
    ui.artisticPrint("-" * fullDescription.length, "yellow")
    ui.artisticPrint(area.fullDescription + "\n")

  private def playTurn() =
    ui.artisticPrint("\nWhat to do next?\n> ", "magenta", noNewline=true)
    val command = readLine()
    val turnReport = this.game.playTurn(command)
    if turnReport.nonEmpty then
      ui.artisticPrint(turnReport)

end AdventureTextUI

