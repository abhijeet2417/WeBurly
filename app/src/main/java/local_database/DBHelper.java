package local_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelclass.Products;


/**
 * Created by product on 9/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "product.db";
    private static final String TABLE_NAME = "product";
    private static final String KEY_PROCOLOR = "procolor";
    private static final String KEY_ID = "id";
    private static final String KEY_PROID = "proid";
    private static final String KEY_name = "proname";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QTY = "qty";
    private static final String KEY_TOTAL_AMT = "amt";
    private static final String KEY_IMG = "image";

    private static final String TABLE_BACKSTACK = "backstack";
    private static final String KEY_CATID = "cid";
    private static final String KEY_SUBCATID = "scid";


    public int insert(Products pro) {
        //Open connection to write data
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROID, pro.getProductId());
        values.put(KEY_name, pro.getProName());
        values.put(KEY_PRICE, pro.getProPrice());
        values.put(KEY_QTY, pro.getProQty());
        values.put(KEY_PROCOLOR, pro.getProcolor());
        values.put(KEY_TOTAL_AMT, pro.getProTotalAmt());
        values.put(KEY_IMG, pro.getProimage());
        // Inserting Row
        long student_Id = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public int insertBackstack(String cid, String scid) {
        //Open connection to write data
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATID, cid);
        values.put(KEY_SUBCATID, scid);
        // Inserting Row
        long student_Id = db.insert(TABLE_BACKSTACK, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public void deleteAllbackstack() {
        SQLiteDatabase db = getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.execSQL("delete from " + TABLE_BACKSTACK);
        db.close(); // Closing database connection
    }

    public void deleteBackstack() {
        SQLiteDatabase dbd = getWritableDatabase();
        dbd.execSQL("delete from " + TABLE_BACKSTACK + " WHERE id = (SELECT MAX(id) FROM " + TABLE_BACKSTACK + ")");
        dbd.close();
    }

    public int getCartCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if (cnt > 0)
            return cnt;
        else
            return 0;
    }

    public List<Products> getLatestBackstack() {
        //Open connection to read only
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT " + KEY_CATID + "," + KEY_SUBCATID + " FROM " + TABLE_BACKSTACK + " WHERE id = (SELECT MAX(id) FROM " + TABLE_BACKSTACK + ")";
        List<Products> prolist = new ArrayList<Products>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Products obj = new Products();
                obj.setCategoryid(cursor.getString(cursor.getColumnIndex(KEY_CATID)));
                obj.setSubcategoryid(cursor.getString(cursor.getColumnIndex(KEY_SUBCATID)));
                prolist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return prolist;
    }

    public List<Products> getProductList() {
        //Open connection to read only
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  " +
                KEY_PROID + "," +
                KEY_name + "," +
                KEY_PRICE + "," +
                KEY_QTY + "," +
                KEY_PROCOLOR + "," +
                KEY_IMG + "," +
                KEY_TOTAL_AMT +
                " FROM " + TABLE_NAME;


        //Student student = new Student();
        List<Products> studentList = new ArrayList<Products>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Products obj = new Products();
                obj.setProductId(cursor.getString(cursor.getColumnIndex(KEY_PROID)));
                obj.setProName(cursor.getString(cursor.getColumnIndex(KEY_name)));
                obj.setProPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                obj.setProQty(cursor.getString(cursor.getColumnIndex(KEY_QTY)));
                obj.setProcolor(cursor.getString(cursor.getColumnIndex(KEY_PROCOLOR)));
                obj.setProimage(cursor.getString(cursor.getColumnIndex(KEY_IMG)));
                obj.setProTotalAmt(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_AMT)));
                studentList.add(obj);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_PROID + " TEXT, "
                + KEY_name + " TEXT, "
                + KEY_PRICE + " TEXT, "
                + KEY_QTY + " TEXT, "
                + KEY_PROCOLOR + " TEXT, "
                + KEY_IMG + " TEXT, "
                + KEY_TOTAL_AMT + " TEXT )";

        String CREATE_TABLE_BACKSTACK = "CREATE TABLE " + TABLE_BACKSTACK + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_CATID + " TEXT, "
                + KEY_SUBCATID + " TEXT )";

        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_BACKSTACK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKSTACK);

        // Create tables again
        onCreate(db);

    }

}
