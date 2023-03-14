package com.learning.mydictionary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


// Our DAO(Database Access Object class)
@Database(entities = {User.class}, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase instance;
    private static final String DB_NAME = "Dictionary_tb";

    public static AppDatabase getDB(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DB_NAME)
                   // .fallbackToDestructiveMigration()
                   // .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public static void destroyInstance(){
        instance = null;
    }
    }
