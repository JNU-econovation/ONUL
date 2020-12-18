package com.example.onul_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LastCheckActivity extends AppCompatActivity {
    private static final String TAG="LastCheckActivity";
    private TextView textView;
    private Button save_button;
    private Button day_button;
    private ImageView imageView;
    private int emoji;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String userId;
    private String day;
    private String written_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lastcheck);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        userId=mAuth.getCurrentUser().getUid();

        textView=(TextView)findViewById(R.id.written_text);
        save_button=(Button)findViewById(R.id.save_button);
        day_button=(Button)findViewById(R.id.day_button);
        imageView=(ImageView)findViewById(R.id.emoji);

        Intent intent = getIntent();

        written_text = intent.getExtras().getString("written_text");
        emoji = intent.getExtras().getInt("emoji");
        day=intent.getExtras().getString("day");

        textView.setText(written_text);
        setEmoji(emoji);


        save_button.setOnClickListener(onClickListener);
        day_button.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.save_button:
                    save();
                    thelastmainActivity();
                    break;
                case R.id.day_button:
                    gotoMainActivity();
                    break;

            }
        }
    };

    private void thelastmainActivity(){
        Intent intent=new Intent(this,thelastmainactivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void setEmoji(int emoji){
        if (emoji==2131296331) {
            imageView.setImageResource(R.drawable.soso);
        } else if (emoji==2131296336) {
            imageView.setImageResource(R.drawable.good);
        } else if (emoji==2131296337) {
            imageView.setImageResource(R.drawable.yay);
        } else if (emoji==2131296338) {
            imageView.setImageResource(R.drawable.smile);
        } else if (emoji==2131296339) {
            imageView.setImageResource(R.drawable.angry);
        } else if (emoji==2131296340) {
            imageView.setImageResource(R.drawable.upset);
        } else if (emoji==2131296341) {
            imageView.setImageResource(R.drawable.tearsofjoy);
        } else if (emoji==2131296342) {
            imageView.setImageResource(R.drawable.wow);
        } else if (emoji==2131296343) {
            imageView.setImageResource(R.drawable.heart);
        } else if (emoji==2131296332) {
            imageView.setImageResource(R.drawable.xx);
        } else if (emoji==2131296333) {
            imageView.setImageResource(R.drawable.sad);
        } else if (emoji==2131296334) {
            imageView.setImageResource(R.drawable.sleepy);
        }
    }

    private void save(){
        String written_text=textView.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("diary",written_text);
        userId=mAuth.getCurrentUser().getUid();
        DocumentReference documentReference=db.collection("users").document(userId).collection("diary").document(day);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

}
