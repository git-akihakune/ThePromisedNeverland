package adventure

import scala.collection.mutable.Map

class Player(startingArea: Area):

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private var inv = Map[String, Item]()

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

  def search(objectName: String): String =
    val returningItem = this.currentLocation.getItem(objectName)
    returningItem match {
      case Some(item) => {
        this.inv += (item.name -> item)
        s"You found ${item.name} hidden in $objectName"
      }
      case None => "Literally nothing was found. This place is emptier than your life."
    }

  override def toString = "Now at: " + this.location.name

end Player


