package adventure

import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var inv = Map[String, Item]()

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player’s current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then "You go " + direction + "." else "You can't go " + direction + "."


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() =
    "You rest for a while. Better get a move on, though."


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() =
    this.quitCommandGiven = true
    ""

  def drop(itemName: String): String =
    if !this.inv.contains(itemName) then
      "You don't have that!"
    else
      this.currentLocation.addItem(this.inv.apply(itemName))
      this.inv.remove(itemName)
      s"You drop the ${itemName}."

  def examine(itemName: String): String =
    if !this.inv.contains(itemName) then
      "If you want to examine something, you need to pick it up first."
    else
      s"You look closely at the ${itemName}.\n${this.inv.apply(itemName).description}"

  def get(itemName: String): String =
    if this.currentLocation.contains(itemName) then
      // this.inv += (itemName -> this.currentLocation.item(itemName))
      this.inv += (itemName -> this.currentLocation.removeItem(itemName).get)
      s"You pick up the $itemName."
    else
      s"There is no $itemName here to pick up."

  def has(itemName: String): Boolean = this.inv.contains(itemName)

  def inventory: String =
    if this.inv.isEmpty then
      "You are empty-handed."
    else
      s"You are carrying:\n${this.inv.keys.mkString("\n")}"

  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player


