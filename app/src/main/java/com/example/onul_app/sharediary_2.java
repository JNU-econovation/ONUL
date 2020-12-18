package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class sharediary_2 extends AppCompatActivity {
    Button button;
    String diary_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharediary_2);

        button=(Button)findViewById(R.id.diary_button);

        Intent intent = getIntent();

        diary_name= intent.getExtras().getString("name");

        button.setText(diary_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),sharediary_friend.class);
                startActivity(intent);
            }
        });

    }
}
