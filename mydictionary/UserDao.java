package com.learning.mydictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
// Our Data Access Object Class
@Dao
public interface UserDao {

   @Query("SELECT * FROM Dictionary")
    LiveData<User> getAll();
    //List<User> getAll();

    @Insert
    void insertAll(User users);

   @Delete
    void delete(User user);

   @Update
    void update(User user);

}