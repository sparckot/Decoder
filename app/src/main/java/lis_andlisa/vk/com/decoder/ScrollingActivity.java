package lis_andlisa.vk.com.decoder;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends Activity {

    TextView output;
    String outText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        //получаем элемент окна вывода
        output = findViewById(R.id.output);
        Intent intent = getIntent();
        //записываем в окно вывода текст, поллученный из предыдущей активности
        output.setText(intent.getStringExtra("input"));
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScrollingActivity.this);
        builder.setTitle("Выберите действие:")
//                .setMessage("Покормите кота!")
//                .setIcon(R.drawable.ic_android_cat)
                .setCancelable(true)
                .setNeutralButton("Отправить по...",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String textToSend="some text";
                        intent.putExtra(Intent.EXTRA_TEXT, textToSend);
                        try
                        {
                            startActivity(Intent.createChooser(intent, "Описание действия"));
                        }
                        catch (android.content.ActivityNotFoundException ex)
                        {
                            Toast.makeText(getApplicationContext(), "Some error", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Скопировать всё",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("", output.getText().toString());
                                if (clipboard != null) {
                                    clipboard.setPrimaryClip(clip);
                                }
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
