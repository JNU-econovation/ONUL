package com.example.onul_app;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends Activity {

    private static final String TAG="SignUpActivity";
    private EditText name_join;
    private EditText email_join;
    private EditText pwd_join;
    private EditText pwd_join_check;
    private EditText nickname_join;
    private Button sign_up_btn;
    private Button out;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        name_join = (EditText)findViewById(R.id.TextView);
        email_join = (EditText)findViewById(R.id.editText1);
        pwd_join = (EditText)findViewById(R.id.editText2);
        pwd_join_check = (EditText)findViewById(R.id.editText3);
        nickname_join = (EditText)findViewById(R.id.editText4);
        sign_up_btn = (Button) findViewById(R.id.button);
        out = (Button) findViewById(R.id.button1);


        sign_up_btn.setOnClickListener(onClickListener);
        out.setOnClickListener(onClickListener);

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
                case R.id.button:
                    signUp();
                    break;
                case R.id.button1:
                    finish();
                    break;
            }
        }
    };
    private void signUp() {
        String email=email_join.getText().toString();
        String password=pwd_join.getText().toString();
        String password_check=pwd_join_check.getText().toString();
        String name=name_join.getText().toString();
        String nickname=nickname_join.getText().toString();

        if(email.length()>0&&password.length()>0&&password_check.length()>0&&name.length()>0&&nickname.length()>0){
            if(password.equals(password_check))
            {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입 성공");
                                    finish();
                                } else {
                                    if(task.getException()!=null) {
                                        startToast(task.getException().toString());
                                    }
                                }

                                // ...
                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다.");
            }

        }else
        {
            startToast("입력하지 않은 란이 있습니다. 입력하세요.");
        }


    }

    private void startToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_LONG).show(); }

}
