package com.example.applicationnews.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.applicationnews.enity.CollectE;
import java.util.ArrayList;
import java.util.List;

public class CollectDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "collects.db";   //数据库名
    private static final int VERSION = 1;    //版本号
    private static CollectDbHelper sHelper;

    //必须实现其中一个构方法
    public CollectDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static CollectDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new CollectDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建collect_table表
        db.execSQL("create table collects_table(collect_id integer primary key autoincrement, " +
                "uniquekey text," +       //新闻ID
                "username text," +      //用户名
                "news_json text" +       //新闻数据
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //添加收藏列表
    public int addCollect(String username, String uniquekey, String news_json) {
        if (!isCollect(uniquekey)) {

            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("username", username);
            values.put("uniquekey", uniquekey);
            values.put("news_json", news_json);
            String nullColumnHack = "values(?,?,?)";
            //执行
            int insert = (int) db.insert("collects_table", nullColumnHack, values);
            // db.close();
            return insert;
        }
        return 0;
    }

    //判断是否收藏过
    @SuppressLint("Range")
    public boolean isCollect(String uniquekey) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select collect_id,username,uniquekey,news_json  from collects_table where uniquekey=?";
        String[] selectionArgs = {uniquekey};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
//        cursor.close();
//        db.close();
        return  cursor.moveToNext();
    }


    //获取所有收藏记录
    @SuppressLint("Range")
    public List<CollectE> queryCollectListData(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<CollectE> list = new ArrayList<>();
        String sql;
        Cursor cursor;
        if (username == null) {
            sql = "select collect_id,username,uniquekey,news_json  from collects_table";
            cursor = db.rawQuery(sql, null);
        } else {
            sql = "select collect_id,username,uniquekey,news_json  from collects_table where username=?";
            cursor = db.rawQuery(sql, new String[]{username});
        }
        while (cursor.moveToNext()) {
            int collect_id = cursor.getInt(cursor.getColumnIndex("collect_id"));
            String uniquekey = cursor.getString(cursor.getColumnIndex("uniquekey"));
            String userName = cursor.getString(cursor.getColumnIndex("username"));
            String news_json = cursor.getString(cursor.getColumnIndex("news_json"));
            list.add(new CollectE(collect_id, userName, uniquekey, news_json));
        }
        cursor.close();
        db.close();
        return list;
    }

}