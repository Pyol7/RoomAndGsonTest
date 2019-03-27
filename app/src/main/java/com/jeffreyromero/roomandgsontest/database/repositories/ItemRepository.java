package com.jeffreyromero.roomandgsontest.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.os.AsyncTask;

import com.jeffreyromero.roomandgsontest.database.AppDatabase;
import com.jeffreyromero.roomandgsontest.database.daos.ItemDao;
import com.jeffreyromero.roomandgsontest.models.Item;
import com.jeffreyromero.roomandgsontest.models.itemTypes.DroppedCeiling;
import com.jeffreyromero.roomandgsontest.models.itemTypes.DrywallCeiling;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static final String TAG = "ItemRepository";
    private ItemDao itemDao;

    public ItemRepository(Application application) {
        // Create the app database
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.itemDao = appDatabase.itemDao();
    }

    public void insert(Item item) {
        new InsertAsyncTask(itemDao).execute(item);
    }

    public void getAll(GetAllAsyncTask.OnPostExecuteListener listener) {
        new GetAllAsyncTask(itemDao, listener).execute();
    }

    public LiveData<Item> get(int itemID) {
        return itemDao.get(itemID);
    }

    public static class GetAllAsyncTask extends AsyncTask<Void, Void, List<Item>> {
        private WeakReference<ItemDao> itemDao_w;
        private WeakReference<OnPostExecuteListener> listener_w;

        public interface OnPostExecuteListener {
            void onPostExecute(List<Item> items);
        }

        GetAllAsyncTask(ItemDao itemDao, OnPostExecuteListener listener) {
            this.itemDao_w = new WeakReference<>(itemDao);
            this.listener_w = new WeakReference<>(listener);
        }

        @Override
        protected List<Item> doInBackground(Void... voids) {
            // Get strong reference for itemDeo
            ItemDao itemDao = itemDao_w.get();
            if (itemDao == null) {
                return null;
            }
            Cursor cursor = null;
            try {
                List<Item> items = new ArrayList<>();
                cursor = itemDao.getAllAsCursor();
                // looping through all rows and add to list
                if (cursor.moveToFirst()) {
                    do {
                        // use the type value here
                        String subType = cursor.getString(cursor.getColumnIndex("subType"));
                        Item item = null;
                        if (subType.equals("Dropped Ceiling")) {
                            item = new DroppedCeiling(cursor.getString(cursor.getColumnIndex("name")));
                        } else if (subType.equals("Drywall Ceiling")) {
                            item = new DrywallCeiling(cursor.getString(cursor.getColumnIndex("name")));
                        }
                        items.add(item);
                    } while (cursor.moveToNext());
                }
                return items;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            // Get strong reference for listener
            OnPostExecuteListener listener = listener_w.get();
            if (listener != null) {
                listener.onPostExecute(items);
            }
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Item, Void, Long> {
        private ItemDao dao;

        InsertAsyncTask(ItemDao dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(Item... items) {
            return dao.insert(items[0]);
        }

    }

}
