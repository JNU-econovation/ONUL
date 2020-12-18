package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class sharediary_lastcheck extends AppCompatActivity {
    Button save_button;
    TextView textView;
    String written_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharediary_lastcheck);

        save_button=(Button)findViewById(R.id.save_button);
        textView=(TextView) findViewById(R.id.written_text);
/*
        Intent intent = getIntent();

        written_text = intent.getExtras().getString("written_text");
        Toast.makeText(this, written_text,Toast.LENGTH_LONG).show();

        textView.setText(written_text);

 */

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),sharediary_list_next.class);
                startActivity(intent);
            }
        });


    }
}
