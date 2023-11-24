import adventure.*
import scala.io.StdIn.*


object AdventureTextUI extends App:

  private val game = Adventure()
  private val ui = Interface()
  private val player = game.player
  this.run()

  private def run() =
    ui.slowPrint(this.game.welcomeMessage)
    ui.slowPrint(this.game.warningMessage)
    while !this.game.isOver do
      this.printAreaInfo()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)

  private def printAreaInfo() =
    val area = this.player.location
    val time = this.game.timeReport
    val fullDescription = area.name + ", " + time
    
    ui.slowPrint(area.areaArt, Option(500))

    ui.slowPrint("\n\n" + fullDescription)
    ui.slowPrint("-" * fullDescription.length)
    ui.slowPrint(area.fullDescription + "\n")

  private def playTurn() =
    println()
    val command = readLine("What to do next?\n> ")
    val turnReport = this.game.playTurn(command)
    if turnReport.nonEmpty then
      ui.slowPrint(turnReport)

end AdventureTextUI

