package at.michaelkoenig.labor_09c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapterTodo;
    private List<String> listData = new ArrayList<>();
    private ListView lstvwTodo;
    private EditText txtTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstvwTodo = this.findViewById(R.id.lstvw_todo);
        txtTodo = this.findViewById(R.id.txt_input);

        adapterTodo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listData);
        lstvwTodo.setAdapter(adapterTodo);

        lstvwTodo.setOnItemClickListener((parent, view, position, id) -> {
            adapterTodo.remove(adapterTodo.getItem(position));
        });
    }

    public void onAddButtonClick(View view) {
        String item = txtTodo.getText().toString();
        if (item.isEmpty()) return;
        adapterTodo.add(item);
        txtTodo.setText("");
    }

}
