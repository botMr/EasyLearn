package com.example.easylearn.practiceFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.HideSystemUI;
import com.example.easylearn.LoadData;
import com.example.easylearn.R;
import com.example.easylearn.accountFragment.NotesRepository;
import com.example.easylearn.accountFragment.SharedViewModelNoteCount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class PracticeFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_note;
    TextView usernameText;
    CircleImageView userImage;
    ChipNavigationBar chipNavigationBar;
    NoteAdapter adapter;
    LoadData loadData;
    public PracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        HideSystemUI.hideSystemUI(getActivity());

        SharedViewModelNoteCount sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModelNoteCount.class);
        NotesRepository notesRepository = new NotesRepository(getContext());
        sharedViewModel.init(notesRepository);

        chipNavigationBar = getActivity().findViewById(R.id.bottom_nav);
        recyclerView = view.findViewById(R.id.notes_cards);
        add_note = view.findViewById(R.id.add_note);
        usernameText = view.findViewById(R.id.username_practice);
        userImage = view.findViewById(R.id.image_username_practice);

        String text = "Твои сочинения, ";
        loadData = new LoadData().loadImage(userImage,usernameText,text);

        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoteActivity.class);
                startActivity(intent);
            }
        });

        Realm.init(getContext().getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime",Sort.DESCENDING).findAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoteAdapter(getContext(),notesList);
        recyclerView.setAdapter(adapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                adapter.notifyDataSetChanged();
                sharedViewModel.setNotesCount(notes.size());
            }
        });

        return view;
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(getActivity());
    }
}