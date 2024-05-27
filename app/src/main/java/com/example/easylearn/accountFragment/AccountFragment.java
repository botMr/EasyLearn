package com.example.easylearn.accountFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.BugReportSheet;
import com.example.easylearn.HideSystemUI;
import com.example.easylearn.LoadData;
import com.example.easylearn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    TextView username_account,note_count;
    CircleImageView image_username;
    RecyclerView achieve_list;
    ArrayList<Achievement> achievement;
    AchievementAdapter adapter;
    FloatingActionButton text_settings,about_us_alert,bug_report;
    LoadData loadData;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        HideSystemUI.hideSystemUI(getActivity());

        username_account = view.findViewById(R.id.username_account);
        image_username = view.findViewById(R.id.image_username);
        note_count = view.findViewById(R.id.note_count);
        achieve_list = view.findViewById(R.id.achieve_list);
        text_settings = view.findViewById(R.id.text_settings);
        about_us_alert = view.findViewById(R.id.about_us_alert);
        bug_report = view.findViewById(R.id.bug_report);

        String text = "Привет, ";
        loadData = new LoadData().loadImage(image_username,username_account,text);

        NotesRepository repository = new NotesRepository(getContext());
        SharedViewModelNoteCount sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModelNoteCount.class);
        sharedViewModel.init(repository);

        int savedNotesCount = repository.getNotesCount();
        note_count.setText(Integer.toString(savedNotesCount));
        sharedViewModel.getNotesCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                Log.d("AccountFragment", "Notes count changed: " + count);
                note_count.setText(Integer.toString(count));
            }
        });


        Achievements(repository);

        text_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingsAccountActivity.class));
            }
        });

        about_us_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsSheet aboutUsSheet = new AboutUsSheet();
                aboutUsSheet.show(getChildFragmentManager(),"aboutSheet");
            }
        });

        bug_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BugReportSheet bugReportSheet = new BugReportSheet();
                bugReportSheet.show(getFragmentManager(),"bugSheet");
            }
        });

        return view;
    }

    private void Achievements(NotesRepository repository) {
        achievement = new ArrayList<>();
        achievement.add(new Achievement("1","5 есть - 1000 на подходе",R.drawable.achivemetn_uncolor,false));
        achievement.add(new Achievement("2","Уже 15?",R.drawable.achivemetn_uncolor,false));
        achievement.add(new Achievement("3","25 в кармане",R.drawable.achivemetn_uncolor,false));
        achievement.add(new Achievement("4","Уже 50",R.drawable.achivemetn_uncolor,false));
        adapter = new AchievementAdapter(getContext(),achievement);
        achieve_list.setLayoutManager(new LinearLayoutManager(getContext()));
        achieve_list.setAdapter(adapter);


        if (repository.getNotesCount() == 5) {
            adapter.checkAndUpdateAchievement("1"); // ID достижения, которое нужно обновить
        } else if (repository.getNotesCount() == 15){
            adapter.checkAndUpdateAchievement("1"); // ID достижения, которое нужно обновить
        } else if (repository.getNotesCount() == 25){
            adapter.checkAndUpdateAchievement("1"); // ID достижения, которое нужно обновить
        } else if (repository.getNotesCount() == 50){
            adapter.checkAndUpdateAchievement("1"); // ID достижения, которое нужно обновить
        }
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(getActivity());
    }
}