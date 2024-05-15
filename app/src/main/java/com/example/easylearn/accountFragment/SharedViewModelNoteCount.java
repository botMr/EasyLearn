package com.example.easylearn.accountFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelNoteCount extends ViewModel {
    private final MutableLiveData<Integer> notesCount = new MutableLiveData<>();
    private NotesRepository notesRepository;

    public void init(NotesRepository repository) {
        this.notesRepository = repository;
        notesCount.setValue(notesRepository.getNotesCount());
    }

    public void setNotesCount(Integer count){
        notesCount.setValue(count);
        notesRepository.saveNotesCount(count);
    }

    public LiveData<Integer> getNotesCount(){
        return notesCount;
    }
}
