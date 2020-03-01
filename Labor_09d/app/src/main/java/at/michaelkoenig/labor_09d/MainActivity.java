package at.michaelkoenig.labor_09d;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.michaelkoenig.labor_09d.R;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapterTodo;
    private List<String> listData;
    private ListView lstvwTodo;
    private EditText txtTodo;
    private SharedPreferences prefs;
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "onCreate");

        prefs = getSharedPreferences("at.michaelkoenig.labor_09d", MODE_PRIVATE);

        String json = prefs.getString("TODOS", null);
        if (json != null) {
            Gson gson = new Gson();
            String[] data = gson.fromJson(json, String[].class);
            if (data != null) {
                listData = new ArrayList<>(Arrays.asList(data));
                Log.i(LOG_TAG, "restored JSON: " + listData);
            }
        } else {
            listData = new ArrayList<>();
        }

        lstvwTodo = this.findViewById(R.id.lstvw_todo);

        adapterTodo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listData);
        lstvwTodo.setAdapter(adapterTodo);

        lstvwTodo.setOnItemClickListener((parent, view, position, id) -> {
            adapterTodo.remove(adapterTodo.getItem(position));
            Utils.makeToast(this, R.string.removed_todo);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        SharedPreferences.Editor ed = prefs.edit();
        Gson gson = new Gson();
        ed.putString("TODOS", gson.toJson(listData));
        ed.apply();
        Log.i(LOG_TAG, "onSaveInstanceState: saved JSON: " + listData);
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, NewTodoActivity.class);
        this.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            String item = data.getStringExtra(NewTodoActivity.RETURN_DATA);
            adapterTodo.add(item);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
