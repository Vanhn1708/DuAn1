package com.example.duan1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.duan1.model.Music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicSqlite extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "Music";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private String Music ="Music";
    private String name ="name";
    private String path ="path";
    private String artist ="artist";


    public MusicSqlite(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
    }


    public void createDataBase() {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e("abc", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public long insertAccount( Music music) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name, music.getMusic());
        contentValues.put(path, music.getPath());
        contentValues.put(artist, music.getArtist());

        long id = sqLiteDatabase.insert(Music, null, contentValues);

        sqLiteDatabase.close();
        return id;
    }


    public List<Music> musicList() {

        List<Music> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT * FROM Music";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        cursor.moveToFirst();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Music music1 = new Music();
                    music1.music = cursor.getString(cursor.getColumnIndex(name));
                    music1.path = cursor.getString(cursor.getColumnIndex(path));
                    music1.artist = cursor.getString(cursor.getColumnIndex(artist));

                    list.add(music1);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
