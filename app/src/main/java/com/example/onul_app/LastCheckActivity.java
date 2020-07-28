package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LastCheckActivity extends AppCompatActivity {
    private TextView textView;
    private Button save_button;
    private Button day_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lastcheck);

        textView=(TextView)findViewById(R.id.written_text);
        save_button=(Button)findViewById(R.id.save_button);
        day_button=(Button)findViewById(R.id.day_button);

        textView.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        String written_text = intent.getExtras().getString("written_text");
        textView.setText(written_text);

        save_button.setOnClickListener(onClickListener);
        day_button.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.save_button:

                    break;
                case R.id.day_button:
                    gotoMainActivity();
                    break;

            }
        }
    };

    private void gotoMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
