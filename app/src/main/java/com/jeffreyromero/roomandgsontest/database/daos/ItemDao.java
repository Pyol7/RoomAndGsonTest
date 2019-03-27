package com.jeffreyromero.roomandgsontest.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.jeffreyromero.roomandgsontest.models.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    long insert(Item item);

    @Query("SELECT * FROM items WHERE items.ID = :itemID")
    LiveData<Item> get(int itemID);

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAll();

    @Query("SELECT * FROM items")
    Cursor getAllAsCursor();

}