package com.example.demomore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by benchengzhou on 2017/4/6.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态

    private static final String name = "test_db"; //数据库名称

    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {

        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类

        super(context, name, null, version);

    }

    /**
     * 这个方法仅仅调用在数据库第一次创建的时候
     *在onCreate()方法里可以生成数据库表结构及添加一些应用使用到的初始化数据
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)");

    }

    /**
     * 当数据库的版本号更改的时候调用
     * 当我们需要修改数据库表结构的时候升级数据库的版本号
     * 在实际项目开发中，当数据库表结构发生更新时，应该避免用户存放于数据库中的数据丢失。
     *
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)"); //往表中增加一列

    }


}
