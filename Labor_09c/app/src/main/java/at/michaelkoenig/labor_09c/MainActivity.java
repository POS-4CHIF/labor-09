package at.michaelkoenig.labor_09c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapterTodo;
    private List<String> listData;
    private ListView lstvwTodo;
    private EditText txtTodo;
    private SharedPreferences prefs;
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "onCreate");

        prefs = getSharedPreferences("at.michaelkoenig.labor_09c", MODE_PRIVATE);

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
        txtTodo = this.findViewById(R.id.txt_input);

        adapterTodo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listData);
        lstvwTodo.setAdapter(adapterTodo);

        lstvwTodo.setOnItemClickListener((parent, view, position, id) -> adapterTodo.remove(adapterTodo.getItem(position)));
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        SharedPreferences.Editor ed = prefs.edit();
        Gson gson = new Gson();
        ed.putString("TODOS", gson.toJson(listData));
        ed.apply();
        Log.i(LOG_TAG, "saved JSON: " + listData);
    }

    public void onAddButtonClick(View view) {
        String item = txtTodo.getText().toString();
        if (!item.isEmpty()) {
            adapterTodo.add(item);
            txtTodo.setText("");
        }
    }

}
