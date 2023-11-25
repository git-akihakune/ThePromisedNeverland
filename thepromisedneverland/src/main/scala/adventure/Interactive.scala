package adventure

import scala.collection.mutable.Buffer


class Item(val name: String, val description: String):

  def use(usageLogic: String) = usageLogic

  override def toString = this.name

end Item


class InteractiveObject(val name: String, val description: String):
  private val items: Buffer[Item] = Buffer()

  def addItem(item: Item) =
    this.items += item

  def getItem: Option[Item] =
    val returningItem = this.items.lastOption
    this.items.trimEnd(1)
    returningItem

  override def toString = this.name

end InteractiveObject