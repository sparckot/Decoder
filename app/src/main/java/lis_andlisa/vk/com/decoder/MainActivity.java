package lis_andlisa.vk.com.decoder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MultiAutoCompleteTextView input;
    EditText key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //по ID возвращает View
        input = findViewById(R.id.input);
        key = findViewById(R.id.key);
    }

    public void onClickShifr(View view) {
        char[] inputChar = input.getText().toString().toCharArray();
        char[] keyChar = key.getText().toString().toCharArray();
        String out = shifr(inputChar, keyChar);
        Intent intent = new Intent(this, ScrollingActivity.class);
        //передаем интент с данными в другую активити для отображения
        intent.putExtra("input", out);
        startActivity(intent);
    }

    static String shifr(char[] text1, char[] key1) {
        char[] result = new char[text1.length];
        int j = 0;
        for (int i = 0; i < text1.length; i++) {
            result[i] = (char) (text1[i] ^ key1[j]);
            if (j == key1.length - 1)
                j = 0;
        }
        return new String(result);
    }
}
