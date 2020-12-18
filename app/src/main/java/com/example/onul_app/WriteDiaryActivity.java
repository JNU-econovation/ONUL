package com.example.onul_app;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WriteDiaryActivity extends AppCompatActivity {
    private static final String TAG="WriteDiaryActivity";
    private Button save_button;
    private Button day_button;
    private EditText writeText;
    private  ImageView imageView;
    private int emoji;
    private Button stt_button;
    final int PERMISSION = 1;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String userId;
    private String day;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef=storage.getReference();
    StorageReference spaceRef;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_write);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        userId=mAuth.getCurrentUser().getUid();

        if ( Build.VERSION.SDK_INT >= 23 ){
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO},PERMISSION);
        } //안드로이드 6.0버전부터는 퍼미션을 따로 체크해줘야 한다.

        stt_button=(Button)findViewById(R.id.stt_button);
        save_button = (Button) findViewById(R.id.save_button);
        day_button = (Button) findViewById(R.id.day_button);
        writeText = (EditText) findViewById(R.id.write_here);
        // 액티비티 내에 보여주고 싶은 이미지뷰 바인딩
        imageView = (ImageView) findViewById(R.id.emoji);

        save_button.setOnClickListener(onClickListener);
        day_button.setOnClickListener(onClickListener);

        Intent intent = getIntent();

        emoji = intent.getExtras().getInt("emoji");
        day = intent.getExtras().getString("day");
        setEmoji(emoji);

        stt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceTask voiceTask=new VoiceTask();
                voiceTask.execute();
            }
        });

    }

    public class VoiceTask extends AsyncTask<String,Integer,String>{
        String str=null;

        @Override
        protected String doInBackground(String... params){
            //TODO Auto-generated method stub
            try {
                getVoice();
            } catch (Exception e){
                //TODO: handle exception
            }
            return str;
        }

        @Override
        protected  void onPostExecute(String result){
            try {

            } catch (Exception e){
                Log.d("onActivityResult","getImageURL exception");
            }
        }
    }

    private void getVoice(){
        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        String language="ko-KR";

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,language);
        startActivityForResult(intent,2);

    }

    protected  void onActivityResult(int requestCode, int resultCode,Intent data){
        //TODO Auto-generated method stub
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==RESULT_OK){

            ArrayList<String> results=data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String str=results.get(0);
            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

            EditText writeText = (EditText) findViewById(R.id.write_here);
            writeText.setText(str);


        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_button:
                    save();
                    break;
                case R.id.day_button:
                    gotoMainActivity();
                    break;

            }
        }
    };

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
        String written_text=writeText.getText().toString();

        Intent intent = new Intent(this,LastCheckActivity.class);
        intent.putExtra("written_text",written_text);
        intent.putExtra("emoji",emoji);
        intent.putExtra("day",day);
        startActivity(intent);
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_LONG).show(); }
}
