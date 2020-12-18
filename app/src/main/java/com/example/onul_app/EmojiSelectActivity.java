package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EmojiSelectActivity extends AppCompatActivity {

    private Button rhj_expressionless;
    private Button rhj_happy;
    private Button rhj_yay;
    private Button rhj_smile;
    private Button rhj_upset;
    private Button rhj_tearsinmyeyes;
    private Button rhj_tragicomic;
    private Button rhj_surprise;
    private Button rhj_heart;
    private Button rhj_xx;
    private Button rhj_tt;
    private Button rhj_sleepy;
    private Button day_button;
    private String day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choose_emoji);

        Intent intent = getIntent();

        day = intent.getExtras().getString("day");

        rhj_expressionless = (Button) findViewById(R.id.button1);
        rhj_happy = (Button) findViewById(R.id.button2);
        rhj_yay = (Button) findViewById(R.id.button3);
        rhj_smile = (Button) findViewById(R.id.button4);
        rhj_upset = (Button) findViewById(R.id.button5);
        rhj_tearsinmyeyes = (Button) findViewById(R.id.button6);
        rhj_tragicomic = (Button) findViewById(R.id.button7);
        rhj_surprise = (Button) findViewById(R.id.button8);
        rhj_heart = (Button) findViewById(R.id.button9);
        rhj_xx = (Button) findViewById(R.id.button10);
        rhj_tt = (Button) findViewById(R.id.button11);
        rhj_sleepy = (Button) findViewById(R.id.button12);
        day_button=(Button)findViewById(R.id.day_button);

        rhj_expressionless.setOnClickListener(onClickListener);
        rhj_happy.setOnClickListener(onClickListener);
        rhj_yay.setOnClickListener(onClickListener);
        rhj_smile.setOnClickListener(onClickListener);
        rhj_upset.setOnClickListener(onClickListener);
        rhj_tearsinmyeyes.setOnClickListener(onClickListener);
        rhj_tragicomic.setOnClickListener(onClickListener);
        rhj_surprise.setOnClickListener(onClickListener);
        rhj_heart.setOnClickListener(onClickListener);
        rhj_xx.setOnClickListener(onClickListener);
        rhj_tt.setOnClickListener(onClickListener);
        rhj_sleepy.setOnClickListener(onClickListener);
        day_button.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                case R.id.button2:
                case R.id.button3:
                case R.id.button4:
                case R.id.button5:
                case R.id.button6:
                case R.id.button7:
                case R.id.button8:
                case R.id.button9:
                case R.id.button10:
                case R.id.button11:
                case R.id.button12:
                    WriteDiaryActivity(v.getId());
                    break;
                case R.id.day_button:
                    finish();
            }
        }
    };

    private void WriteDiaryActivity(int name){

        if(name==R.id.button1) {
            startToast(""+name);
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            intent.putExtra("emoji", name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button2){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button3){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button4){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button5){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button6){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button7){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button8){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button9){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button10){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button11){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }else if(name==R.id.button12){
            Intent intent = new Intent(this, WriteDiaryActivity.class);
            startToast(""+name);
            intent.putExtra("emoji",name);
            intent.putExtra("day",day);
            startActivity(intent);
        }

    }

    private void startToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_LONG).show(); }
}

