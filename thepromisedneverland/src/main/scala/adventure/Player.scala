package adventure

import scala.collection.mutable.Map
import scala.collection.immutable.Vector

class Player(startingArea: Area):

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private var inv = Map[String, Item]()

  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation

  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then "You went " + direction + "." else "You can't go " + direction + "."

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

  def use(objectString: String): String =
    val usingItems: Vector[String] = objectString.split(" ").toVector

    def affectArea(currentStatus: String, updatedStatus: String, returnedText: String) =
      if this.currentLocation.statusIs(currentStatus) then
        this.currentLocation.updateStatus(updatedStatus)
        usingItems.foreach(this.inv.remove(_))
        returnedText
      else "For what?"

    if usingItems.forall(item => inv.contains(item)) then
      val action: String = objectString match {
        case "tablecloth" => affectArea("climbable", "climbed", "You can now climb the wall!")
        case "oil lamp" => affectArea("normal", "burning", "The area has been drowned in fierce flame!")
        case "lamp oil" => affectArea("normal", "burning", "The area has been drowned in fierce flame!")
        case "blanket" => affectArea("burning", "normal", "You successsfully put out the fire...")
        case otheritem => s"However, there is time and space for everything, and it's not time to use the $otheritem yet."
      }
      s"You have used your ${usingItems.mkString(", ")} well. $action"
    else "A rule of life: You cannot use something you don't have."

  override def toString = "Now at: " + this.location.name

end Player


