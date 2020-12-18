package com.example.onul_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class MypageFragment extends Fragment {
    MainActivity mainActivity;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String userId;

    private Context context;
    private Button modify_button;
    private Button withdrawal_button;
    private Button sign_out_button;
    private TextView name;
    private TextView email;
    private EditText password;
    private EditText password_check;
    private EditText nickname;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mainActivity=(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mainActivity=null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup pageView=(ViewGroup)inflater.inflate(R.layout.fragment_mypage,container,false);

        context=container.getContext();

        name=(TextView)pageView.findViewById(R.id.name);
        email=(TextView)pageView.findViewById(R.id.email);
        password=(EditText)pageView.findViewById(R.id.password);
        password_check=(EditText)pageView.findViewById(R.id.password_check);
        nickname=(EditText)pageView.findViewById(R.id.nickname);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        userId=mAuth.getCurrentUser().getUid();

        DocumentReference documentReference=db.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
                nickname.setText(documentSnapshot.getString("nickname"));
            }
        });



        modify_button = pageView.findViewById(R.id.modify_button); //수정하기 버튼
        withdrawal_button = pageView.findViewById(R.id.withdrawal_button); //탈퇴하기 버튼
        sign_out_button = pageView.findViewById(R.id.sign_out_button); //로그아웃 버튼

        modify_button.setOnClickListener(onClickListener);
        withdrawal_button.setOnClickListener(onClickListener);
        sign_out_button.setOnClickListener(onClickListener);

        return pageView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.modify_button:
                    modify();
                    break;
                case R.id.withdrawal_button:
                    withdrawal();
                    break;
                case R.id.sign_out_button:
                    signOut();
                    gotoLoginActivity();
                    break;

            }
        }
    };

    private void modify(){

    }

    private void withdrawal(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startToast("회원탈퇴 완료");
                            //회원 탈퇴했으니 처음 페이지로 이동이 필요함. 스택 고려하여 설계할 것
                            gotoLoginActivity();
                        }
                    }
                });
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        startToast("로그아웃 완료");
        mainActivity.finish();
    }

    private void gotoLoginActivity(){
        Intent intent= new Intent(mainActivity, LoginActivity.class);
        startActivity(intent);
        mainActivity.finish();
    }

    private void startToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
