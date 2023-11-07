package pl.edu.uwr.studenthardlife

data class ListTable(val point: Int, val opis: String, val numberlist: Int, val type: Int ) {
    var id: Int = 0
    constructor(id: Int, point: Int, opis: String, numberlist: Int, type: Int) : this(point, opis, numberlist, type) {
        this.id = id
    }
}