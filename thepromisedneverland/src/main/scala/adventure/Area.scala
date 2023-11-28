package adventure

import scala.collection.mutable.Map

class Area(var name: String, var description: String, val areaArt: String, var status: String):

  private val neighbors = Map[String, Area]()
  private val items = Map[String, Item]()
  private val interactiveObjects = Map[String, InteractiveObject]()

  /** Returns the area that can be reached from this area by moving in the given
    * direction. The result is returned in an Option; None is returned if there
    * is no exit in the given direction.
    */
  def neighbor(direction: String) = this.neighbors.get(direction)

  def updateStatus(newStatus: String): Unit =
    this.status = newStatus
  def statusIs(comparingStatus: String): Boolean = this.status == comparingStatus

  def addItem(item: Item): Unit =
    this.items += item.name -> item
  def addObject(interactiveObject: InteractiveObject): Unit =
    this.interactiveObjects += interactiveObject.name -> interactiveObject
  def contains(itemName: String): Boolean =
    items.exists((key, value) => key == itemName)
  def removeItem(itemName: String): Option[Item] =
    if this.items.contains(itemName) then items.remove(itemName)
    else None
  def getItem(objectName: String): Option[Item] =
    if this.interactiveObjects.contains(objectName) then 
      this.interactiveObjects(objectName).getItem
    else None

  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  def fullDescription: String =
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    val objectList = "\n" + this.items.values.map(_.description).mkString("\n") + "\n" + this.interactiveObjects.values.map(_.description).mkString("\n")
    this.description + objectList + exitList

  override def toString =
    this.name + ": " + this.description.replaceAll("\n", " ").take(150)

end Area
