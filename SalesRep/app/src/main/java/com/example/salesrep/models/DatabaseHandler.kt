package com.example.salesrep.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{

    companion object
    {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "StockDatabase"
        private const val TABLE_NAME = "StockTable"

        private const val KEY_ITEM_ID = "itemID"
        private const val KEY_ITEM = "itemName"
        private const val KEY_QUANTITY = "quantity"
        private const val KEY_AMOUNT = "amount"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        //creating table with fields
        val CREATE_STOCK_TABLE = ("CREATE TABLE " + TABLE_NAME + "(" + KEY_ITEM_ID + " INTEGER PRIMARY KEY"  + KEY_ITEM +
                " TEXT,"+ KEY_QUANTITY + " INTEGER " + KEY_AMOUNT + " INTEGER " + ")")

        db?.execSQL(CREATE_STOCK_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }


    fun addStock(stock : StockModelClass) : Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ITEM_ID, stock.itemId)
        contentValues.put(KEY_ITEM, stock.itemName)
        contentValues.put(KEY_QUANTITY, stock.quantity)
        contentValues.put(KEY_AMOUNT, stock.amount)


        //Inserting row
        val success = db.insert(TABLE_NAME, null, contentValues)

        db.close()
        return success
    }

    //method to read data
    @SuppressLint("Range")
    fun viewStock(): ArrayList<StockModelClass>
    {
        val stockList: ArrayList<StockModelClass> = ArrayList<StockModelClass>()

        val selectQuery = "SELECT * FROM $TABLE_NAME"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
                cursor = db.rawQuery(selectQuery, null)
        }
        catch (e: SQLiteException)
        {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var item_id : Int
        var item_name : String
        var quantity : String
        var amount : String

        if(cursor.moveToFirst())
        {
            do {
                item_id = cursor.getInt(cursor.getColumnIndex(KEY_ITEM_ID))
                item_name = cursor.getString(cursor.getColumnIndex(KEY_ITEM))
                quantity= cursor.getString(cursor.getColumnIndex(KEY_QUANTITY))
                amount = cursor.getString(cursor.getColumnIndex(KEY_AMOUNT))

                val stock = StockModelClass(itemId = item_id, itemName = item_name, quantity = quantity, amount = amount)
            }while (cursor.moveToNext())
        }
        return stockList
    }

    //function to update stock
    fun updateStock(stock: StockModelClass) : Int
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ITEM_ID, stock.itemId)
        contentValues.put(KEY_ITEM, stock.itemName)
        contentValues.put(KEY_QUANTITY, stock.quantity)
        contentValues.put(KEY_AMOUNT, stock.amount)

        //updating row
        val success = db.update(TABLE_NAME, contentValues, KEY_ITEM_ID + " = "+ stock.itemId, null)

        db.close()
        return success
    }

    //function to delete stock
    fun deleteStock(stock: StockModelClass): Int
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_ITEM_ID, stock.itemId)

        val success = db.delete(TABLE_NAME, KEY_ITEM_ID + " = "+ stock.itemId, null)

        db.close()
        return success
    }


}