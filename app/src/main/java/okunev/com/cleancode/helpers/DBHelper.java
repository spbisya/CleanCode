package okunev.com.cleancode.helpers;

/**
 * Project YobaClicker. Created by gwa on 7/23/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import okunev.com.cleancode.models.CustomModel;

public class DBHelper extends SQLiteOpenHelper {
    //Achievements
    public static final String DATABASE_NAME = "DB_NAME";
    public static final String MODEL_TABLE_NAME = "TABLE_NAME";
    public static final String MODEL_COLUMN_ID = "id";
    public static final String MODEL_COLUMN_NAME = "name";
    public static final String MODEL_COLUMN_DESCRIPTION = "description";
    public static final String MODEL_COLUMN_LINK = "link";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MODEL_TABLE_NAME + " " +
                "(id integer primary key, name text, description text, link text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MODEL_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertModel(CustomModel customModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MODEL_COLUMN_NAME, customModel.getName());
        contentValues.put(MODEL_COLUMN_DESCRIPTION, customModel.getDescription());
        contentValues.put(MODEL_COLUMN_LINK, customModel.getLink());
        db.insert(MODEL_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateModel(Integer id, CustomModel customModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MODEL_COLUMN_NAME, customModel.getName());
        contentValues.put(MODEL_COLUMN_DESCRIPTION, customModel.getDescription());
        contentValues.put(MODEL_COLUMN_LINK, customModel.getLink());
        db.update(MODEL_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public ArrayList<CustomModel> getAllModels() {
        ArrayList<CustomModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + MODEL_TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            CustomModel customModel = new CustomModel();
            customModel.setName(res.getString(res.getColumnIndex(MODEL_COLUMN_NAME)));
            customModel.setDescription(res.getString(res.getColumnIndex(MODEL_COLUMN_DESCRIPTION)));
            customModel.setLink(res.getString(res.getColumnIndex(MODEL_COLUMN_LINK)));
            array_list.add(customModel);
            res.moveToNext();
        }
        return array_list;
    }

    public Integer deleteModel(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MODEL_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MODEL_TABLE_NAME);
        return numRows;
    }

}