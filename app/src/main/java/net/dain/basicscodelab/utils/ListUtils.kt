package net.dain.basicscodelab.utils

fun <A, B, C> List<A>.zip(other: Iterable<B>, other2: Iterable<C>): List<Triple<A, B, C>> {
    val list = ArrayList<Triple<A, B, C>>(size)
    val secondList = other.toList()
    val thirdList = other2.toList()

    for (i in indices) {
        list.add(Triple(get(i), secondList[i], thirdList[i]))
    }
    return list
}

// fun <A> List<A>.zip(vararg others: Iterable<*>): List<Any> {
//     val list = ArrayList<Any>(size)
//     for (i in indices) {
//         list.add(Pair(get(i), others[i]))
//     }
//     return list
// }

