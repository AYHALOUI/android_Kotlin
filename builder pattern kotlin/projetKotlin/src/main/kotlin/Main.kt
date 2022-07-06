fun main(){

    val orderLis = mutableListOf<Order>()

    val order1 = Order(1)
    val noodles = Noodles()
    order1.addItem(noodles)
    orderLis.add(order1)

    println("")

    val order2 = Order(2)
    val vegetables1 = Vegetables()
    order2.addItem(vegetables1)
    orderLis.add(order2)


    println("")

    val order3 = Order(3)
    val vegetables2 = Vegetables()
    val veg = Vegetables("aymene", "haloui")
    order3.addAll(listOf(vegetables2,veg))
    orderLis.add(order3)

    val order4 = Order(4)
        .addItem(Noodles())
        .addItem(Vegetables("a","b","c"))
    orderLis.add(order4)

    orderLis.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Vegetables())
    )

    for (order in orderLis){
        order.print()
        println()
    }

}

open class Item(val name : String, val price : Int)

class Noodles : Item("Noodles", 10){
    override fun toString(): String {
        return name
    }
}
class Vegetables(private vararg val toppings : String) : Item("Vegetables", 5){
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's choice"
        } else {
            return name + " " +toppings.joinToString()
        }
    }
}

class Order(private val OrderNumber : Int){
    private val itemList =  mutableListOf<Item>()

    fun addItem( newItem : Item ): Order {
        itemList.add(newItem)
        return this
    }

    fun addAll( newItems : List<Item> ): Order {
        itemList.addAll(newItems)
        return this
    }
    fun print(){
        println("Order #${OrderNumber}")
        var totalPrice = 0
        for (item in itemList){
            println("${item.name} : ${item.price}")
            totalPrice += item.price
        }
        print("Total : ${totalPrice}")
    }
}



