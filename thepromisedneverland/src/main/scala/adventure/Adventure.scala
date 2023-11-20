package adventure

class Adventure:

  val title = "The Promised Neverland"

  private val middle = Area(
    "Forest",
    "You are somewhere in the forest. There are a lot of trees here.\nBirds are singing."
  )
  private val northForest = Area(
    "Forest",
    "You are somewhere in the forest. A tangle of bushes blocks further passage north.\nBirds are singing."
  )
  private val southForest = Area("Forest", "The forest just goes on and on.")
  private val clearing = Area(
    "Forest Clearing",
    "You are at a small clearing in the middle of forest.\nNearly invisible, twisted paths lead in many directions."
  )
  private val tangle = Area(
    "Tangle of Bushes",
    "You are in a dense tangle of bushes. It's hard to see exactly where you're going."
  )
  private val home = Area(
    "Home",
    "Home sweet home! Now the only thing you need is a working remote control."
  )
  private val destination = home

  middle.setNeighbors(
    Vector(
      "north" -> northForest,
      "east" -> tangle,
      "south" -> southForest,
      "west" -> clearing
    )
  )
  northForest.setNeighbors(
    Vector("east" -> tangle, "south" -> middle, "west" -> clearing)
  )
  southForest.setNeighbors(
    Vector(
      "north" -> middle,
      "east" -> tangle,
      "south" -> southForest,
      "west" -> clearing
    )
  )
  clearing.setNeighbors(
    Vector(
      "north" -> northForest,
      "east" -> middle,
      "south" -> southForest,
      "west" -> northForest
    )
  )
  tangle.setNeighbors(
    Vector(
      "north" -> northForest,
      "east" -> home,
      "south" -> southForest,
      "west" -> northForest
    )
  )
  home.setNeighbors(Vector("west" -> tangle))

  // TODO: Uncomment the two lines below. Improve the code so that it places the items in clearing and southForest, respectively.
  private val battery = Item("battery", "It's a small battery cell. Looks new.")
  private val remote = Item(
    "remote",
    "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery."
  )
  clearing.addItem(battery)
  southForest.addItem(remote)

  /** The character that the player controls in the game. */
  val player = Player(middle)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0

  /** The maximum number of turns that this adventure game allows before time
    * runs out.
    */
  val timeLimit = 40

  /** Determines if the adventure is complete, that is, if the player has won.
    */
  def isComplete = this.player.location == this.destination && this.player.has(
    battery.name
  ) && this.player.has(remote.name)

  /** Determines whether the player has won, lost, or quit, thereby ending the
    * game.
    */
  def isOver =
    this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning
    * of the game.
    */
  def welcomeMessage =
    "You are lost in the woods. Find your way back home.\n\nBetter hurry, 'cause Scalatut elämät is on real soon now. And you can't miss Scalkkarit, right?"

  /** Returns a message that is to be displayed to the player at the end of the
    * game. The message will be different depending on whether or not the player
    * has completed their quest.
    */
  def goodbyeMessage =
    if this.isComplete then "Home at last... and phew, just in time! Well done!"
    else if this.player.location == this.destination && (!this.player.has(
        battery.name
      ) || !this.player.has(remote.name))
    then
      "Home sweet home! Now the only thing you need is a working remote control."
    else if this.turnCount == this.timeLimit then
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else // game over due to player quitting
      "Quitter!"

  /** Plays a turn by executing the given in-game command, such as “go west”.
    * Returns a textual report of what happened, or an error message if the
    * command was unknown. In the latter case, no turns elapse.
    */
  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
