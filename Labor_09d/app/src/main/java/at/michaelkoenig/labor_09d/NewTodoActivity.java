package at.michaelkoenig.labor_09d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewTodoActivity extends AppCompatActivity {
    private EditText txtTodo;
    public static final String RETURN_DATA = "TODO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        txtTodo = findViewById(R.id.txtTodo);
    }

    public void onAddButtonClick(View view) {
        String item = txtTodo.getText().toString();
        if (!item.isEmpty()) {
            Utils.makeToast(this, R.string.added_todo);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(RETURN_DATA, item);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Utils.makeToast(this, R.string.invalid_todo);
        }
    }



}
