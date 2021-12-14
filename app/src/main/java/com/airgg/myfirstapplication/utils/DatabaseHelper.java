package com.airgg.myfirstapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.airgg.myfirstapplication.models.Todos;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DB_TODOS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Todos.CREATE_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Todos.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // ================= \\
    // -- PROCESS CRUD -- \\

    public long insertTodo(Todos todos) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Todos.COLUMN_JUDUL, todos.getJudul());
        values.put(Todos.COLUMN_KONTEN, todos.getKonten());
        values.put(Todos.COLUMN_TGLSTART, todos.getTglstart());
        values.put(Todos.COLUMN_TGLEND, todos.getTglend());

        // insert row
        long id = db.insert(Todos.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Todos getTodo(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Todos.TABLE_NAME,
                new String[]{Todos.COLUMN_ID, Todos.COLUMN_JUDUL, Todos.COLUMN_KONTEN, Todos.COLUMN_TGLSTART, Todos.COLUMN_TGLEND},
                Todos.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

//        if (cursor != null)
//            cursor.moveToFirst();
        if (cursor == null) return null;

        // prepare note object
        Todos todo = new Todos(
                cursor.getInt(cursor.getColumnIndex(Todos.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Todos.COLUMN_JUDUL)),
                cursor.getString(cursor.getColumnIndex(Todos.COLUMN_KONTEN)),
                cursor.getString(cursor.getColumnIndex(Todos.COLUMN_TGLSTART)),
                cursor.getString(cursor.getColumnIndex(Todos.COLUMN_TGLEND))
        );

        // close the db connection
        cursor.close();

        return todo;
    }

    public List<Todos> getAllTodo() {
        List<Todos> todos = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Todos.TABLE_NAME + " ORDER BY " + Todos.COLUMN_TGLSTART + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Todos todo = new Todos();
//                Todos.setId(cursor.getInt(cursor.getColumnIndex(Todos.COLUMN_ID)));Todos
//                Todos.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
//                Todos.setTimestamp(cursor.getString(cursor.getColumnIndex(NoTodoste.COLUMN_TIMESTAMP)));
                todo.setId(cursor.getInt(cursor.getColumnIndex(Todos.COLUMN_ID)));
                todo.setJudul(cursor.getString(cursor.getColumnIndex(Todos.COLUMN_JUDUL)));
                todo.setKonten(cursor.getString(cursor.getColumnIndex(Todos.COLUMN_KONTEN)));
                todo.setTglstart(cursor.getString(cursor.getColumnIndex(Todos.COLUMN_TGLSTART)));
                todo.setTglend(cursor.getString(cursor.getColumnIndex(Todos.COLUMN_TGLEND)));

                todos.add(todo);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return list
        return todos;
    }

    public int getTodosCount() {
        String countQuery = "SELECT  * FROM " + Todos.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateTodo(Todos todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Todos.COLUMN_JUDUL, todo.getJudul());
        values.put(Todos.COLUMN_KONTEN, todo.getKonten());
        values.put(Todos.COLUMN_TGLSTART, todo.getTglstart());
        values.put(Todos.COLUMN_TGLEND, todo.getTglend());

        // updating row
        return db.update(Todos.TABLE_NAME, values, Todos.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
    }

    public void deleteTodo(Todos todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Todos.TABLE_NAME, Todos.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
        db.close();
    }


}
