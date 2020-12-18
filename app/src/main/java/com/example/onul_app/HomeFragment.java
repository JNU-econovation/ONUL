package com.example.onul_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    MainActivity mainActivity;

    private Context context;

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
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_home, container,false);

        context=container.getContext();

        MaterialCalendarView materialCalendarView=(MaterialCalendarView)rootView.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(1990, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                startToast(""+date);
                gotoEmojiSelectActivity(date);

            }
        });
        return rootView;
    }

    private void startToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    private void gotoEmojiSelectActivity(CalendarDay date){
        Intent intent= new Intent(mainActivity.getApplicationContext(),EmojiSelectActivity.class);
        String day=date.toString();
        intent.putExtra("day",day);
        startActivity(intent);
    }

}
