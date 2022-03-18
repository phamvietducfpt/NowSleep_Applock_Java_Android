package com.PVD.NowSleep.activities.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.PVD.NowSleep.R;

public class HomeFragment extends Fragment {
    private ConstraintLayout settimepage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settimepage = (ConstraintLayout) inflater.inflate(R.layout.fragment_home, container, false);
        settimepage.findViewById(R.id.settime_page);
        settimepage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettimeActivity.class);
                startActivity(intent);
            }
        });
        return settimepage;

    }
}