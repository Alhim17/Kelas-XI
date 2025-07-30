package com.komputerkit.intentactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textViewReceivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewReceivedData = findViewById(R.id.textViewReceivedData);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EXTRA_DATA")) {
            String receivedData = intent.getStringExtra("EXTRA_DATA");
            textViewReceivedData.setText(receivedData);
        }
    }
}
