package com.example.posts;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class Converters {
    @TypeConverter
    public String fromUserToString(User user){
        return new Gson().toJson(user);
    }
    @TypeConverter
    public User fromStringToUser(String strUser){
        return new Gson().fromJson(strUser,User.class);
    }
}
