package at.michaelkoenig.labor_09b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekTipps;
    private Button btnGenerate;
    private TextView txtTipps;
    private TextView txtOutput;
    private RadioButton radio45;
    private int tippCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekTipps = findViewById(R.id.seekTipps);
        btnGenerate = findViewById(R.id.btnGenerate);
        txtTipps = findViewById(R.id.txtTipps);
        txtOutput = findViewById(R.id.txtOutput);
        radio45 = findViewById(R.id.radio45);

        tippCount = seekTipps.getProgress();
        updateTipp(tippCount);

        seekTipps.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tippCount = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                updateTipp(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateTipp(tippCount);
            }
        });

    }

    private void updateTipp(int tippCount) {
        String text = tippCount == 0 ? tippCount + 1 + " Tipp" : tippCount + 1 + " Tipps";
        btnGenerate.setText(text + " generieren");
        txtTipps.setText(text);
    }

    public void onBtnGenerateClick(View view) {
        StringBuilder sb = new StringBuilder();
        Log.i("lab9", generateTipp().toString());
        for (int i = 0; i < tippCount; i++) {
            Iterator<Integer> it = generateTipp().iterator();
            while(it.hasNext()) {
                sb.append(it.next());
                if(it.hasNext()) {
                    sb.append(", ");
                }
            }

            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        txtOutput.setText(sb.toString());
    }

    private Set<Integer> generateTipp() {
        Set<Integer> numbers = new TreeSet<>();
        int max = radio45.isSelected() ? 45 : 49;
        Random rd = new Random();

        while (numbers.size() <= 6) {
            numbers.add(rd.nextInt(max) + 1);
        }

        return numbers;
    }

}
