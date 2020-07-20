package com.example.onul_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class MyPageActivity extends AppCompatActivity {
    private static final String TAG="MyPageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            gotoSignUpActivity();
        }else{
            FirebaseFirestore db=FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                            } else {
                                Log.d(TAG, "No such document");
                                gotoMemberActivity();
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
        findViewById(R.id.button).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener=new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.button:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate() {
        String name = ((EditText)findViewById(R.id.editText)).getText().toString();
        String nickname = ((EditText)findViewById(R.id.editText1)).getText().toString();
        String email = ((EditText)findViewById(R.id.editText2)).getText().toString();

        if (name.length()>0&&nickname.length()>0&&email.length()>0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();


            Memberinfo memberinfo = new Memberinfo(name, nickname, email);
           if(user!=null){
               db.collection("users").document(user.getUid()).set(memberinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                      startToast("회원정보 등록 성공");
                   }
               })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               startToast("회원정보 등록 실패");
                           }
                       });
           }

        } else {
            startToast("회원정보를 입력해 주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    private void gotoSignUpActivity(){
        Intent intent= new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    private void gotoMemberActivity(){
        Intent intent= new Intent(this,MemberActivity.class);
        startActivity(intent);
    }
}
