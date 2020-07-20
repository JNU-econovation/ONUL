package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="SignUpActivity";
    private EditText email_join;
    private EditText pwd_join;
    private Button login_btn;
    private Button password_find_btn;
    private Button sign_up_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email_join = (EditText)findViewById(R.id.editText);
        pwd_join = (EditText)findViewById(R.id.editText1);
        login_btn = (Button) findViewById(R.id.login_button);
        password_find_btn=(Button)findViewById(R.id.password_find_button);
        sign_up_btn = (Button) findViewById(R.id.sign_up_button);



        login_btn.setOnClickListener(onClickListener);
        password_find_btn.setOnClickListener(onClickListener);
        sign_up_btn.setOnClickListener(onClickListener);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.login_button:
                    login();
                    break;
                case R.id.password_find_button:
                    gotoPasswordFindActivity();
                    break;
                case R.id.sign_up_button:
                    gotoSignUpActivity();
                    break;

            }
        }
    };


    private void login() {
        String email = email_join.getText().toString();
        String password = pwd_join.getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인 성공");
                                gotoMyPageActivity();//gotoMainActivity();
                            } else {
                                if(task.getException()!=null) {
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    private void gotoMainActivity(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoSignUpActivity(){
        Intent intent= new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    private void gotoPasswordFindActivity(){
        Intent intent= new Intent(this,PasswordFindActivity.class);
        startActivity(intent);
    }

    private void gotoMyPageActivity(){
        Intent intent = new Intent(this,MyPageActivity.class);
        startActivity(intent);
    }


}
