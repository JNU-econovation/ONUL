package com.example.onul_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectLoginOrSignUp  extends AppCompatActivity {

    private Button login_btn;
    private Button sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectloginorsignup);

        login_btn = (Button) findViewById(R.id.login_button);
        sign_up_btn = (Button) findViewById(R.id.sign_up_button);

        login_btn.setOnClickListener(onClickListener);
        sign_up_btn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.login_button:
                    gotoLoginActivity();
                    break;
                case R.id.sign_up_button:
                    gotoSignUpActivity();
                    break;

            }
        }
    };

    private void gotoLoginActivity(){
        Intent intent= new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoSignUpActivity(){
        Intent intent= new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

}
