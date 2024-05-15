package com.example.easylearn.learnFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.HideSystemUI;
import com.example.easylearn.LoadData;
import com.example.easylearn.R;
import com.example.easylearn.learnFragment.Quiz.QuizCardAdapter;
import com.example.easylearn.learnFragment.Quiz.QuizModel;
import com.example.easylearn.learnFragment.Theory.TheoryCardAdapter;
import com.example.easylearn.learnFragment.Theory.TheoryCardModel;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LearnMaterilsFragment extends Fragment {
    ChipNavigationBar chipNavigationBar;
    TextView usernameText;
    CircleImageView userImage;
    ArrayList<LearnCardModel> dictionaryCards;
    LearnCardAdapter learnAdapter;
    RecyclerView learnCards;
    ArrayList<TheoryCardModel> theoryCard;
    TheoryCardAdapter theoryAdapter;
    RecyclerView theoryCards;
    ArrayList<QuizModel> quizCard;
    QuizCardAdapter quizAdapter;
    RecyclerView quizCards;
    LoadData loadData;
    public LearnMaterilsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn_materils,container,false);
        HideSystemUI.hideSystemUI(getActivity());

        chipNavigationBar = getActivity().findViewById(R.id.bottom_nav);
        usernameText = view.findViewById(R.id.username_welcome);
        userImage = view.findViewById(R.id.image_username);
        setDictionaryCards(view);
        setTheoryCards(view);
        setQuizCards(view);

        String text = "Привет, ";
        loadData = new LoadData().loadImage(userImage,usernameText,text);

        return view;
    }

    private void setQuizCards(View view) {
        quizCards = view.findViewById(R.id.quiz_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setItemPrefetchEnabled(false);
        quizCards.setLayoutManager(layoutManager);
        quizCard = new ArrayList<>();
        quizCard.add(new QuizModel(getActivity().getResources().getColor(R.color.blue),"Задание №5",5));
        quizCard.add(new QuizModel(getActivity().getResources().getColor(R.color.light),"Задание №22",22));
        quizCard.add(new QuizModel(getActivity().getResources().getColor(R.color.blue),"Задание №23",23));
        quizCard.add(new QuizModel(getActivity().getResources().getColor(R.color.light),"Задание №24",24));
        quizCard.add(new QuizModel(getActivity().getResources().getColor(R.color.blue),"Задание №26",26));
        quizAdapter = new QuizCardAdapter(quizCard,getActivity());
        quizCards.setAdapter(quizAdapter);
    }
    private void setTheoryCards(View view) {
        theoryCards = view.findViewById(R.id.theory_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setItemPrefetchEnabled(false);
        theoryCards.setLayoutManager(layoutManager);
        theoryCard = new ArrayList<>();
        theoryCard.add(new TheoryCardModel(getActivity().getResources().getColor(R.color.blue),"Задание №5",getResources().getString(R.string.theory5Url)));
        theoryCard.add(new TheoryCardModel(getActivity().getResources().getColor(R.color.light),"Задание №22",getResources().getString(R.string.theory22Url)));
        theoryCard.add(new TheoryCardModel(getActivity().getResources().getColor(R.color.blue),"Задание №23",getResources().getString(R.string.theory23Url)));
        theoryCard.add(new TheoryCardModel(getActivity().getResources().getColor(R.color.light),"Задание №24",getResources().getString(R.string.theory24Url)));
        theoryCard.add(new TheoryCardModel(getActivity().getResources().getColor(R.color.blue),"Задание №26",getResources().getString(R.string.theory26Url)));
        theoryAdapter = new TheoryCardAdapter(theoryCard,getActivity());
        theoryCards.setAdapter(theoryAdapter);
    }
    private void setDictionaryCards(@NonNull View view) {
        learnCards = view.findViewById(R.id.learn_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setItemPrefetchEnabled(false);
        learnCards.setLayoutManager(layoutManager);
        dictionaryCards = new ArrayList<>();
        dictionaryCards.add(new LearnCardModel(getActivity().getResources().getColor(R.color.blue),"Ударения","233 всего","Словарь","DictionaryAdapter"));
        dictionaryCards.add(new LearnCardModel(getActivity().getResources().getColor(R.color.light),"Фразеологизмы","281 всего","Словарь","DictionaryPhraselogicalAdapter"));
        dictionaryCards.add(new LearnCardModel(getActivity().getResources().getColor(R.color.blue),"Паронимы","144 всего","Словарь","DictionaryParonymsAdapter"));
        learnAdapter = new LearnCardAdapter(dictionaryCards,getActivity());
        learnCards.setAdapter(learnAdapter);
    }
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            // Вернуться на свой фрагмент
        }
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(getActivity());
    }
}