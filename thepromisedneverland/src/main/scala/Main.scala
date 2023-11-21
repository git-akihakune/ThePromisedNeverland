import adventure.*
import scala.io.StdIn.*


object AdventureTextUI extends App:

  private val game = Adventure()
  private val player = game.player
  this.run()

  private def run() =
    println(this.game.welcomeMessage)
    println(this.game.warningMessage)
    while !this.game.isOver do
      this.printAreaInfo()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)

  private def printAreaInfo() =
    val area = this.player.location
    println(area.areaArt)
    println("\n\n" + area.name)
    println("-" * area.name.length)
    println(area.fullDescription + "\n")

  private def playTurn() =
    println()
    val command = readLine("What do you want to do next?\n> ")
    val turnReport = this.game.playTurn(command)
    if turnReport.nonEmpty then
      println(turnReport)

end AdventureTextUI

