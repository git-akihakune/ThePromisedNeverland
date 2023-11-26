package adventure


class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  private val helpText = 
  """
  You are an orphan at Ager. You discovered the truth behind this demon's brain farm. You are now escaping, and you have these options:
    take <item name>        Pick up an item near by.
    drop <item name>        Throw an item away.
    search <object name>    Take a closer look into something.
    use <item name>         Use an item.
    go <direction>          Go into that direction.
    north/west/south/east   Shorthand for move commands.
    inventory               Check on what you're having.
    help                    Print this help message.
    rest                    Lazing around for a while.
    shout                   Vent out all the anger in your stomach.
  
  The game will try its best to understand you, so you may or may not need this help message much.
  """

  def execute(actor: Player): Option[String] = this.verb match
    // item commands
    case "get"       => Some(actor.get(this.modifiers))
    case "take"      => Some(actor.get(this.modifiers))
    case "grab"      => Some(actor.get(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "search"    => Some(actor.search(this.modifiers))
    case "use"       => Some(actor.use(this.modifiers))
    // move commands
    case "go"        => Some(actor.go(this.modifiers))
    case "north"     => Some(actor.go("north"))
    case "n"         => Some(actor.go("north"))
    case "south"     => Some(actor.go("south"))
    case "s"         => Some(actor.go("south"))
    case "west"      => Some(actor.go("west"))
    case "w"         => Some(actor.go("west"))
    case "east"      => Some(actor.go("east"))
    case "e"         => Some(actor.go("east"))
    // inventory commands
    case "inventory" => Some(actor.inventory)
    case "inv"       => Some(actor.inventory)
    case "i"         => Some(actor.inventory)
    // quit commands
    case "quit"      => Some(actor.quit())
    case "q"         => Some(actor.quit())
    // help command
    case "help"      => Some(this.helpText)
    case "h"         => Some(this.helpText)
    case "how"       => Some(this.helpText)
    // other commands
    case ""          => Some("I beg you pardon?")
    case "neverland" => Some("The land of dreams, turned into nightmares.")
    case "rest"      => Some(actor.rest())
    case "jump"      => Some("Are you proud of yourself?")
    case "shout"     => Some("Aaaarrrrgggghhhh!")
    case other       => None

  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = s"$verb (modifiers: $modifiers)"

end Action

