package barok.emese.flowerorderchallenge.presentation.util

object Convert {

    inline fun <reified T : Enum<T>> getNames() = enumValues<T>().map { it.name }
}