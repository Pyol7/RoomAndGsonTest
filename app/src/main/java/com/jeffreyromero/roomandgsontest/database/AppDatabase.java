package com.jeffreyromero.roomandgsontest.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.jeffreyromero.roomandgsontest.database.daos.ItemDao;
import com.jeffreyromero.roomandgsontest.database.seeders.ItemTypesSeeder;
import com.jeffreyromero.roomandgsontest.models.Item;
import com.jeffreyromero.roomandgsontest.utilities.Converters;
import com.jeffreyromero.roomandgsontest.utilities.GsonTypeConverter;

@Database(entities = {
        Item.class,
}, version = 1, exportSchema = false)
@TypeConverters({Converters.class, GsonTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";
    private static final String DATABASE_NAME = "APP_DATABASE";
    private static AppDatabase mInstance;

    // Room generates the code of these methods when the database builder is executed.
    public abstract ItemDao itemDao();

    // Get a database mInstance
    public static synchronized AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = create(context);
        }
        return mInstance;
    }

    // Create the database
    private static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack);
        return builder.build();
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        /**
         * Called when the database is created for the first time. This is called after all the
         * tables are created.
         *
         * @param db The database.
         */
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        ItemDao itemDao;

        private PopulateDBAsyncTask(AppDatabase AppDatabase) {
            this.itemDao = AppDatabase.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Populate db

            new ItemTypesSeeder(itemDao).run();

            return null;
        }
    }
}
