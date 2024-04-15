package com.example.easylearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GptFragment extends Fragment {

   Button checkTextUniqless;
   Button mistakesButton;
   Button chatAssitant;

    public GptFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gpt, container, false);

        checkTextUniqless = view.findViewById(R.id.text_uniq_btn);
        checkTextUniqless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TextCheck.class));
            }
        });

        mistakesButton = view.findViewById(R.id.mistakes_btn);
        mistakesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TextMistakesChecker.class));
            }
        });

        chatAssitant = view.findViewById(R.id.chat_assistant);
        chatAssitant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GptActivity.class));
            }
        });

        return view;
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            // Вернуться на свой фрагмент
        }
    }

}