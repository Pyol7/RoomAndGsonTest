package com.jeffreyromero.roomandgsontest.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jeffreyromero.roomandgsontest.database.repositories.ItemRepository;
import com.jeffreyromero.roomandgsontest.models.Item;

public class ItemViewModel extends AndroidViewModel {
    private static final String TAG = "ItemViewModel";
    private ItemRepository itemRepository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        this.itemRepository = new ItemRepository(application);
    }

    public void insert(Item item){
        itemRepository.insert(item);
    }

    public LiveData<Item> get(int itemID){
        return itemRepository.get(itemID);
    }

    public void getAll(ItemRepository.GetAllAsyncTask.OnPostExecuteListener listener){
        itemRepository.getAll(listener);
    }

}
