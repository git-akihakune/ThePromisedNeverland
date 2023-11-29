package adventure

import scala.collection.immutable.Vector

class Adventure:

  val title = "The Promised Neverland"


  /* Area settings */
  private val southeastAger = Area(
    "SouthEast Ager - Grand House",
    "The Great Wall blocked your sight towards the south and east.\nThere is a small playground towards the west and a vast area at the north.",
    """
                                   /\
                              /\  //\\
                       /\    //\\///\\\        /\
                      //\\  ///\////\\\\  /\  //\\
         /\          /  ^ \/^ ^/^  ^  ^ \/^ \/  ^ \
        / ^\    /\  / ^   /  ^/ ^ ^ ^   ^\ ^/  ^^  \
       /^   \  / ^\/ ^ ^   ^ / ^  ^    ^  \/ ^   ^  \       *
      /  ^ ^ \/^  ^\ ^ ^ ^   ^  ^   ^   ____  ^   ^  \     /|\
     / ^ ^  ^ \ ^  _\___________________|  |_____^ ^  \   /||o\
    / ^^  ^ ^ ^\  /______________________________\ ^ ^ \ /|o|||\
   /  ^  ^^ ^ ^  /________________________________\  ^  /|||||o|\
  /^ ^  ^ ^^  ^    ||___|___||||||||||||___|__|||      /||o||||||\
 / ^   ^   ^    ^  ||___|___||||||||||||___|__|||          | |
/ ^ ^ ^  ^  ^  ^   ||||||||||||||||||||||||||||||oooooooooo| |ooooooo
ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
    """,
    "normal"
  )
  private val southwestAger = Area(
    "SouthWest Ager",
    "A playfield for the children.",
    """
             o\
   _________/__\__________
  |                  - (  |
 ,'-.                 . `-|
(____".       ,-.    '   ||
  |          /\,-\   ,-.  |
  |      ,-./     \ /'.-\ |
  |     /-.,\      /     \|
  |    /     \    ,-.     \
  |___/_______\__/___\_____\ 
    """,
    "normal"
  )
  private val northeastAger = Area(
    "NorthEast Ager",
    "There is a part of the high wall that is lower than the rest here.",
    """
        _    .  ,   .           .
    *  / \_ *  / \_      _  *        *   /\'__        *
      /    \  /    \,   ((        .    _/  /  \  *'.
 .   /\/\  /\/ :' __ \_  `          _^/  ^/    `--.
    /    \/  \  _/  \-'\      *    /.' ^_   \_   .'\  *
  /\  .-   `. \/     \ /==~=-=~=-=-;.  _/ \ -. `_/   \
 /  `-.__ ^   / .-'.--\ =-=~_=-=~=^/  _ `--./ .-'  `-
/jgs     `.  / /       `.~-^=-=~=^=.-'      '-._ `._
    """,
    "climbable"
  )
  private val northwestAger = Area(
    "NorthWest Ager",
    "Contain many houses. The scenery is almost the same as Northeast Ager, but the area seems to have some secrets.",
    """
    .                  .-.    .  _   *     _   .
           *          /   \     ((       _/ \       *    .
         _    .   .--'\/\_ \     `      /    \  *    ___
     *  / \_    _/ ^      \/\'__        /\/\  /\  __/   \ *
       /    \  /    .'   _/  /  \  *' /    \/  \/ .`'\_/\   .
  .   /\/\  /\/ :' __  ^/  ^/    `--./.'  ^  `-.\ _    _:\ _
     /    \/  \  _/  \-' __/.' ^ _   \_   .'\   _/ \ .  __/ \
   /\  .-   `. \/     \ / -.   _/ \ -. `_/   \ /    `._/  ^  \
  /  `-.__ ^   / .-'.--'    . /    `--./ .-'  `-.  `-. `.  -  `.
@/        `.  / /      `-.   /  .-'   / .   .'   \    \  \  .-  \%
@&8jgs@@%% @)&@&(88&@.-_=_-=_-=_-=_-=_.8@% &@&&8(8%@%8)(8@%8 8%@)%
@88:::&(&8&&8:::::%&`.~-_~~-~~_~-~_~-~~=.'@(&%::::%@8&8)::&#@8::::
`::::::8%@@%:::::@%&8:`.=~~-.~~-.~~=..~'8::::::::&@8:::::&8:::::'
 `::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::.'
    """,
    "normal"
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
  private val blanket = Item(
    "blanket",
    "There is a white blanket hanging with stains on it. Typical children stuff at an orphanage."
  )
  private val tablecloth = Item(
    "tablecloth",
    "There is a white tablecloth which smells like pumpkin soup. You remember Nikel, the one got adopted right after staining the tablecloth."
  )
  private val lamp = Item(
    "lamp",
    "A kerosine lamp is casting a dim light around the room."
  )
  private val diary = Item(
    "diary",
    "Someone's diary is wide-opening on the desk. You caught a glimpse of it written: \"Tried to climb the walls, failed. Need to distract the demons first. How?\""
  )
  private val scroll = Item(
    "scroll",
    "A leather scroll from someone is lying there, written only one word: \"Burn\"."
  )
  private val slide = InteractiveObject(
    "slide",
    "Some children are playing with a slide."
  )
  private val book = Item(
    "book",
    "There is a book on the grass, titled \"Conquer the walls: how to climb any surfaces using household items as ropes.\""
  )
  private val knife = Item(
    "knife",
    "A very sharp knife is lying on the dinner table."
  )
  private val forest = InteractiveObject(
    "forest",
    "There is a small forest behind the town landscape."
  )
  private val woodenCabin = InteractiveObject(
    "cabin",
    "A wooden cabin silently stands in the vastness of this area."
  )
  private val flowerField = InteractiveObject(
    "field",
    "A flower field basks among the grass."
  )
  private val flower = Item(
    "flower",
    "A beautiful wild flower."
  )
  private val toyBox = InteractiveObject(
    "box",
    "There is a large toy box with various things inside."
  )
  private val coat = Item(
    "coat",
    "A black adult coat is being hanged on the wall."
  )
  private val watergun = Item(
    "watergun",
    "A watergun which shoot out drops. Effective against children, not very effective against human-eating demons."
  )
  private val glasses = Item(
    "glasses",
    "A pair of glasses, which a child may have no need to use."
  )
  private val teaCup = Item(
    "cup",
    "There is a cup with some leftover tea on the dinning table."
  )
  private val greatDoor = InteractiveObject(
    "door",
    "There is a mysterious large door towards the west that you are prohibited to go near."
  )
  
  toyBox.addItem(glasses)
  toyBox.addItem(watergun)
  bush.addItem(oil)
  flowerField.addItem(flower)
  southeastAger.addItem(coat)
  southeastAger.addObject(toyBox)
  northwestAger.addObject(bush)
  southeastAger.addItem(lamp)
  southwestAger.addItem(tablecloth)
  southwestAger.addItem(blanket)
  southeastAger.addItem(diary)
  northwestAger.addItem(scroll)
  southwestAger.addObject(slide)
  northwestAger.addItem(book)
  southeastAger.addItem(knife)
  northeastAger.addObject(forest)
  northeastAger.addObject(woodenCabin)
  northeastAger.addObject(flowerField)
  southeastAger.addItem(teaCup)
  northwestAger.addObject(greatDoor)


  /* Player settings */
  val player = Player(southeastAger)


  /* Game settings */
  var turnCount = 0
  val timeLimit = 63

  def timeReport: String = 
    val dayPhase = (turnCount % 9) match {
      case 0 | 1 | 2 => "morning"
      case 3 | 4 | 5 => "afternoon"
      case 6 | 7 | 8 => "evening"
    }
    s"day ${turnCount / 9 + 1}, " + dayPhase

  /* Gameplay settings */
  def firstRouteComplete: Boolean = 
    this.exits.contains(this.player.location) && this.player.location.statusIs("climbed") && southeastAger.statusIs("burning")

  def firstRouteFailed: Boolean = 
    this.exits.contains(this.player.location) && this.player.location.statusIs("climbed") && !southeastAger.statusIs("burning")

  def isOver: Boolean =
    this.firstRouteComplete || this.firstRouteFailed || this.player.hasQuit || this.turnCount == this.timeLimit

  def welcomeMessage =
    "You are a child at an orphanage named Ager.\nFew days ago, you discovered the dark truth: This orphanage is a farm for brain-devouring demons, and all the children here, including you, are livestocks.\nYou have 7 days until you are shipped to the demons.\nYour single objective is to survive and escape."
  def warningMessage =
    "[!!!] This game requires a large terminal window to display properly. You are recommended to open this window in full-screen. [!!!]"

  def goodbyeMessage: String =
    if this.firstRouteComplete then "You used the tablecloth as a rope and escaped!\nCongratulation, you are now free.\nThank you for playing The Promised Neverland."
    else if this.firstRouteFailed then "The demons noticed your attempt to escape!\nYou have been captured by the demons.\nYou have been shipped and your brain eaten by the demons.\nAll your attempts are now futile.\nBut maybe someone more fortunate will be able to escape in the future.\nUntil that day come, rest in piece, my dear child.\n\n[GAME OVER]"
    else if this.turnCount == this.timeLimit then
      "Your time ran out.\nYou have been shipped and your brain eaten by the demons.\nAll your attempts are now futile.\nBut maybe someone more fortunate will be able to escape in the future.\nUntil that day come, rest in piece, my dear child.\n\n[GAME OVER]"
    else // game over due to player quitting
      "You remember Mother's teaching: There is no `save` function in life. If you give up, then you have already lost."

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then this.turnCount += 1
    outcomeReport.getOrElse(s"""What do you mean by "$command"?""")

end Adventure
