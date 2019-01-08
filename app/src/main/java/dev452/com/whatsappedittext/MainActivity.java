package dev452.com.whatsappedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dev452.com.whatsappedittext_lib.WhatsAppEditText;
import dev452.com.whatsappedittext_lib.WhatsAppTextView;

public class MainActivity extends AppCompatActivity {
    private WhatsAppEditText whatsAppEditText;
    private WhatsAppTextView whatsAppTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whatsAppEditText = (WhatsAppEditText) findViewById(R.id.edittext);
        whatsAppTextView = (WhatsAppTextView) findViewById(R.id.textview);
    }

    public void demoBtn(View view) {
        String edit = whatsAppEditText.getText().toString().trim();
        whatsAppTextView.setText(edit);
    }
}
