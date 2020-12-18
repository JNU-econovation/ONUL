package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class sharediary_write extends AppCompatActivity {
    Button save_button;
    EditText editText;
    String diary;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharediary_write);

        save_button=(Button)findViewById(R.id.save_button);
        editText=(EditText)findViewById(R.id.write_here);
        diary=editText.getText().toString();
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),sharediary_lastcheck.class);
                intent.putExtra("written_text",diary);
                startActivity(intent);
            }
        });

    }
}
