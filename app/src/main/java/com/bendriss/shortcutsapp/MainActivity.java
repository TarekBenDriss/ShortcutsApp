package com.bendriss.shortcutsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout callLayout,messagesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callLayout = findViewById(R.id.callLayout);
        messagesLayout = findViewById(R.id.messagesLayout);

        callLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CallActivity.class);
                startActivity(intent);
            }
        });

        messagesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MessagesActivity.class);
                startActivity(intent);
            }
        });
    }
}
