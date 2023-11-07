package pl.edu.uwr.studenthardlife

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    private companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "listDBKotlin.db"
        private const val TABLE_KOTLIN = "KotlinTable"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_POINT = "point"
        private const val COLUMN_OPIS = "opis"
        private const val COLUMN_NUMBERLIST = "numberlist"
        private const val COLUMN_TYPE = "type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_STUDENTS_TABLE =
            "CREATE TABLE $TABLE_KOTLIN(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_POINT INTEGER," +
                    "$COLUMN_OPIS TEXT," +
                    "$COLUMN_NUMBERLIST INTEGER," +
                    "$COLUMN_TYPE INTEGER)"
        db?.execSQL(CREATE_STUDENTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_KOTLIN")
        onCreate(db)
    }

    fun addElement(element: ListTable){
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_POINT, element.point)
        contentValues.put(COLUMN_OPIS, element.opis)
        contentValues.put(COLUMN_NUMBERLIST, element.numberlist)
        contentValues.put(COLUMN_TYPE, element.type)

        db.insert(TABLE_KOTLIN, null, contentValues)

        db.close()
    }

        fun deleteElement(element: Int){
            val db = this.writableDatabase

            db.delete(
                TABLE_KOTLIN,
                "$COLUMN_ID=${element}",
                null)
            db.close()
        }

        fun updateElement (id: Int, opis: String){
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COLUMN_OPIS, opis)


            db.update(
                TABLE_KOTLIN,
                contentValues,
                "$COLUMN_ID=$id",
                null)

            db.close()
        }



    fun getElement(num: Int): List<ListTable> {
        val endlist: MutableList<ListTable> = ArrayList()

        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_NUMBERLIST=$num AND $COLUMN_TYPE=0", null)

        if (cursor.moveToFirst()) {
            do {
                endlist.add(ListTable(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3) ,
                    cursor.getInt(4)
                ))
            } while (cursor.moveToNext())
        }

        db.close()
        cursor.close()
        return endlist
    }

    fun getElementt(iD: Int): List<String>{
        val endlist: MutableList<String> = mutableListOf()

        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_ID=$iD", null)

        if (cursor.moveToFirst()) {
            do {
                endlist.add(cursor.getString(2))
            } while (cursor.moveToNext())
        }

        db.close()
        cursor.close()
        return endlist
    }

    fun getElementtt(num: Int): List<ListTable> {
        val endlist: MutableList<ListTable> = ArrayList()

        val db = this.readableDatabase
try{
        val cursor = db.rawQuery("SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_NUMBERLIST=$num AND $COLUMN_TYPE=1", null)

        if (cursor.moveToFirst()) {
            do {
                endlist.add(ListTable(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3) ,
                    cursor.getInt(4)
                ))
            } while (cursor.moveToNext())
        }
            db.close()
            cursor.close()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return emptyList()
        }
                if(endlist.isEmpty()){
                    var emptyL:List<ListTable> = listOf(ListTable(0,0,"none",0, 0))
                    return emptyL
                }else {
                    return endlist
                }
    }

    fun addToGallery(singleItem: ListTable): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_OPIS, singleItem.opis)
        contentValues.put(COLUMN_NUMBERLIST, singleItem.numberlist)
        contentValues.put(COLUMN_TYPE, singleItem.type)

        val result = db.insert(TABLE_KOTLIN, null, contentValues)
        db.close()
        return result
    }
}