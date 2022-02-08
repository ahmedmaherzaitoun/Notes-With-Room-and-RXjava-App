package com.example.posts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.Calendar;

@Database(entities = Post.class, version = 1)
@TypeConverters(Converters.class)
public abstract class PostsDB extends RoomDatabase {
    private static PostsDB instance ;
    public abstract PostsDao postsDao() ;

    public static synchronized PostsDB getInstance(Context context){

        if ( instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),PostsDB.class , "posts_db").
                    fallbackToDestructiveMigration().
                    build();
        }
        return instance ;

    }

}
