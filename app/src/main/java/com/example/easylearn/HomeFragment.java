package com.example.easylearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    Button assitantButton,libraryButton;
    BottomNavigationView bottomNavigationView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        libraryButton = view.findViewById(R.id.library_btn);
        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, new LibraryFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                bottomNavigationView.getMenu().findItem(R.id.library).setChecked(true);
            }
        });

        assitantButton = view.findViewById(R.id.assitant_btn);
        assitantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, new GptFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                bottomNavigationView.getMenu().findItem(R.id.ai_assistant).setChecked(true);
            }
        });

        return view;
    }
}