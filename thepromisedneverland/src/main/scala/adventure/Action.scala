package adventure


class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def execute(actor: Player): Option[String] = this.verb match
    // item commands
    case "get"       => Some(actor.get(this.modifiers))
    case "take"      => Some(actor.get(this.modifiers))
    case "grab"      => Some(actor.get(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "search"    => Some(actor.search(this.modifiers))
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
    // TODO: Add help command
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

