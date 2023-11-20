package adventure

import scala.collection.mutable.Map

class Area(var name: String, var description: String):

  private val neighbors = Map[String, Area]()
  private val items = Map[String, Item]()
  private val interactiveObjects = Map[String, InteractiveObject]()

  /** Returns the area that can be reached from this area by moving in the given
    * direction. The result is returned in an Option; None is returned if there
    * is no exit in the given direction.
    */
  def neighbor(direction: String) = this.neighbors.get(direction)

  def addItem(item: Item): Unit =
    this.items += item.name -> item
  def addObject(interactiveObject: InteractiveObject): Unit =
    this.interactiveObjects += interactiveObject.name -> interactiveObject
  def contains(itemName: String): Boolean =
    items.exists((key, value) => key == itemName)
  def removeItem(itemName: String): Option[Item] =
    if this.contains(itemName) then items.remove(itemName)
    else None

  /** Adds an exit from this area to the given area. The neighboring area is
    * reached by moving in the specified direction from this area.
    */
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  /** Adds exits from this area to the given areas. Calling this method is
    * equivalent to calling the setNeighbor method on each of the given
    * direction–area pairs.
    * @param exits
    *   contains pairs consisting of a direction and the neighboring area in
    *   that direction
    * @see
    *   [[setNeighbor]]
    */
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  /** Returns a multi-line description of the area as a player sees it. This
    * includes a basic description of the area as well as information about
    * exits and items. If there are no items present, the return value has the
    * form "DESCRIPTION\n\nExits available: DIRECTIONS SEPARATED BY SPACES". If
    * there are one or more items present, the return value has the form
    * "DESCRIPTION\nYou see here: ITEMS SEPARATED BY SPACES\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". The items and directions are listed in an
    * arbitrary order.
    */
  def fullDescription: String =
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    if items.size >= 1 then
      val itemsList = "\nYou see here: " + this.items.keys.mkString(" ")
      this.description + itemsList + exitList
    else this.description + exitList

  /** Returns a single-line description of the area for debugging purposes. */
  override def toString =
    this.name + ": " + this.description.replaceAll("\n", " ").take(150)

end Area
