import adventure.*
import scala.io.StdIn.*


object AdventureTextUI extends App:

  private val game = Adventure()
  private val player = game.player
  this.run()

  private def slowPrint(line: String) =
    Thread.sleep(200)
    println(line)

  private def run() =
    this.slowPrint(this.game.welcomeMessage)
    this.slowPrint(this.game.warningMessage)
    while !this.game.isOver do
      this.printAreaInfo()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)

  private def printAreaInfo() =
    val area = this.player.location
    
    Thread.sleep(300) // Increase delay time for ascii art
    this.slowPrint(area.areaArt)

    this.slowPrint("\n\n" + area.name)
    this.slowPrint("-" * area.name.length)
    this.slowPrint(area.fullDescription + "\n")

  private def playTurn() =
    println()
    val command = readLine("What do you want to do next?\n> ")
    val turnReport = this.game.playTurn(command)
    if turnReport.nonEmpty then
      this.slowPrint(turnReport)

end AdventureTextUI

