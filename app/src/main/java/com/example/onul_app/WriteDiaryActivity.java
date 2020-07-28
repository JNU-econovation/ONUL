package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class WriteDiaryActivity extends AppCompatActivity {
    private Button save_button;
    private Button day_button;
    private EditText writeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_write);

        save_button=(Button)findViewById(R.id.save_button);
        day_button=(Button)findViewById(R.id.day_button);
        writeText=(EditText)findViewById(R.id.write_here);

        save_button.setOnClickListener(onClickListener);
        day_button.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.save_button:
                    save();
                    break;
                case R.id.day_button:
                    gotoMainActivity();
                    break;

            }
        }
    };

    private void save(){
        String written_text=writeText.getText().toString();
        Intent intent = new Intent(this,LastCheckActivity.class);
        intent.putExtra("written_text",written_text);
        startActivity(intent);
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_LONG).show(); }
}
