package com.example.onul_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShareDiaryFragment extends Fragment {
    MainActivity mainActivity;
    Button button;
    EditText editText;
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
        ViewGroup shareView=(ViewGroup)inflater.inflate(R.layout.sharediary_1,container,false);

        editText=(EditText)shareView.findViewById(R.id.sharediary_blank);
        button=(Button)shareView.findViewById(R.id.diary_example);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mainActivity.getApplicationContext(),sharediary_2.class);
                intent.putExtra("name",editText.getText().toString());
                startActivity(intent);
            }
        });



        return shareView;
    }
}
