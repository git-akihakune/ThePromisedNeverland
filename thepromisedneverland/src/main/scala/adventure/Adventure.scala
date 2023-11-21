package adventure

import scala.collection.immutable.Vector

class Adventure:

  val title = "The Promised Neverland"

  /* Area settings */
  private val southeastAger = Area(
    "SouthEast Ager - Grand House",
    "The Great Wall blocked your sight towards the south and east.\nThere is a small playground towards the west and a vast area at the north."
  )
  private val southwestAger = Area(
    "SouthWest Ager",
    "A playfield for the children. Contains many interactive objects."
  )
  private val northeastAger = Area(
    "NorthEast Ager",
    "There is a part of the high wall that is lower than the rest here. There is a small forest behind the town landscape."
  )
  private val northwestAger = Area(
    "NorthWest Ager",
    "Contain many houses. The scenery is almost the same as Northeast Ager, but the area seems to have some secrets. There is a mysterious large door towards the west that you are prohibited to go near."
  )
  
  private val exits = Vector(northeastAger)

  southeastAger.setNeighbors(
    Vector(
      "west" -> southwestAger,
      "north" -> northeastAger
    )
  )
  southwestAger.setNeighbors(
    Vector(
      "east" -> southeastAger,
      "north" -> northwestAger
    )
  )
  northeastAger.setNeighbors(
    Vector(
      "south" -> southeastAger,
      "west" -> northwestAger
    )
  )
  northwestAger.setNeighbors(
    Vector(
      "east" -> northeastAger,
      "south" -> southeastAger
    )
  )

  /* Object setting settings */
  private val oil = Item(
    "oil",
    "An old and dirty can filled with refined cooking oil. Seems like someone's attempt at something, but it was never successful."
  )
  private val bush = InteractiveObject(
    "bushes",
    "There are bushes behind the houses."
  )
  bush.addItem(oil)
  private val blanket = Item(
    "blanket",
    "There is a white blanket with stains on it. Typical children stuff at an orphanage."
  )
  private val tablecloth = Item(
    "tablecloth",
    "There is a white tablecloth which smells like pumpkin soup. You remember Nikel, the one got adopted right after staining the tablecloth."
  )
  private val lamp = Item(
    "lamp",
    "A kerosine lamp is casting a dim light around the room."
  )
  northwestAger.addObject(bush)
  southeastAger.addItem(lamp)
  southwestAger.addItem(tablecloth)
  southwestAger.addItem(blanket)

  /* Player settings */
  val player = Player(southeastAger)

  /* Game settings */
  var turnCount = 0
  val timeLimit = 63

  // TODO: Complete the following functions
  def isComplete = turnCount == timeLimit

  def isOver =
    this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  def welcomeMessage =
    "You are a child at an orphanage named Ager.\nFew days ago, you discovered the dark truth: This orphanage is a farm for brain-devouring demons, and all the children here, including you, are livestocks.\nYour single objective is to survive and escape."

  def goodbyeMessage =
    if this.isComplete then "Home at last... and phew, just in time! Well done!"
    else if this.exits.contains(this.player.location) && (!this.player.has(
        oil.name
      ) || !this.player.has(lamp.name))
    then
      "Home sweet home! Now the only thing you need is a working remote control."
    else if this.turnCount == this.timeLimit then
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else // game over due to player quitting
      "Quitter!"

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
