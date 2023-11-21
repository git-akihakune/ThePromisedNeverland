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
    "A playfield for the children."
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


  /* Gameplay settings */
  def firstRouteComplete: Boolean = 
    this.exits.contains(this.player.location) && this.player.has(tablecloth.name) && southeastAger.statusIs("burning")

  def isOver: Boolean =
    this.firstRouteComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  def welcomeMessage =
    "You are a child at an orphanage named Ager.\nFew days ago, you discovered the dark truth: This orphanage is a farm for brain-devouring demons, and all the children here, including you, are livestocks.\nYou have 7 days until you are shipped to the demons.\nYour single objective is to survive and escape."
    "[!!!] This game requires a large terminal window to display properly. You are recommended to open this window in full-screen. [!!!]"

  def goodbyeMessage: String =
    if this.firstRouteComplete then "You used the tablecloth as a rope and escaped!\nCongratulation, you are now free.\nThank you for playing The Promised Neverland."
    else if this.turnCount == this.timeLimit then
      "Your time ran out.\nYou have been shipped and your brain eaten by the demons.\nAll your attempts are now futile.\nBut maybe someone more fortunate will be able to escape in the future.\nUntil that day come, rest in piece, my dear child.\n\n[GAME OVER]"
    else // game over due to player quitting
      "You remember Mother's teaching: There is no `save` function in life. If you give up, then you have already lost."

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
