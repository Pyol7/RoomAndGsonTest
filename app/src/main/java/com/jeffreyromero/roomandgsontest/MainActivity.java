package com.jeffreyromero.roomandgsontest;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jeffreyromero.roomandgsontest.database.repositories.ItemRepository;
import com.jeffreyromero.roomandgsontest.models.Item;
import com.jeffreyromero.roomandgsontest.viewModels.ItemViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemViewModel ivm = ViewModelProviders.of(this).get(ItemViewModel.class);
        ivm.getAll(new ItemRepository.GetAllAsyncTask.OnPostExecuteListener() {
            @Override
            public void onPostExecute(List<Item> items) {
                Log.i(TAG, "onPostExecute: " + items);
                TextView messageTV = findViewById(R.id.message_tv);
                messageTV.setText(items.get(1).getSubType());
            }
        });
    }
}
