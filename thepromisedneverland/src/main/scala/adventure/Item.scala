package adventure

import scala.collection.mutable.Buffer


class Item(val name: String, val description: String):

  override def toString = this.name

end Item


class InteractiveObject(val name: String, val description: String):
  private val items: Buffer[Item] = Buffer()

  def addItem(item: Item) =
    this.items += item

  // TODO: Write a "search" function to return hidden items

  override def toString = this.name

end InteractiveObject