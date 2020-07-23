package com.example.onul_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordFindActivity extends AppCompatActivity {
    private static final String TAG="PasswordFindActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_find);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.send_button).setOnClickListener(onClickListener);
        findViewById(R.id.login_button).setOnClickListener(onClickListener);
        findViewById(R.id.sign_up_button).setOnClickListener(onClickListener);
        findViewById(R.id.editText).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.send_button:
                    send();
                case R.id.login_button:
                    finish();
                    break;
                case R.id.sign_up_button:
                    gotoSignUpActivity();
                    break;

            }
        }
    };

    private void send() {
        String email =((EditText)findViewById(R.id.editText)).getText().toString();

        if (email.length() > 0) {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                                finish();
                            }
                        }
                    });

        } else {
            startToast("이메일을 입력해 주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    private void gotoSignUpActivity(){
        Intent intent= new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

}
