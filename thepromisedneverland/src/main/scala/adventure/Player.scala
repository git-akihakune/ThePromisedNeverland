package adventure

import scala.collection.mutable.Map

class Player(startingArea: Area):

  private var currentLocation = startingArea
  private var quitCommandGiven = false              // one-way flag
  private var inv = Map[String, Item]()

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation

  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then "You go " + direction + "." else "You can't go " + direction + "."

  def rest() =
    "You rest for a while. Better get a move on, though."

  def quit() = // Quitting signal from player
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


