package com.example.easylearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    ArrayList<SetsModel> list;
    bookAdapter adapter;
    RecyclerView bookSlider;

    public LibraryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        bookSlider = view.findViewById(R.id.bookSlider);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setItemPrefetchEnabled(false);
        bookSlider.setLayoutManager(layoutManager);
        list = new ArrayList<>();

        Cards();

        adapter = new bookAdapter(list,getActivity());
        bookSlider.setAdapter(adapter);
        return view;
    }

    private void Cards() {
        list.add(new SetsModel(R.string.bookName1,R.string.bookAuthor1,getResources().getString(R.string.audioBook1URL),getResources().getString(R.string.bookName1),getResources().getString(R.string.textBook1URL),R.string.book1_info,getActivity().getResources().getColor(R.color.book1)));
        list.add(new SetsModel(R.string.bookName2,R.string.bookAuthor2,getResources().getString(R.string.audioBook2URL),getResources().getString(R.string.bookName2),getResources().getString(R.string.textBook2URL),R.string.book2_info,getActivity().getResources().getColor(R.color.book2)));
        list.add(new SetsModel(R.string.bookName3,R.string.bookAuthor3,getResources().getString(R.string.audioBook3URL),getResources().getString(R.string.bookName3),getResources().getString(R.string.textBook3URL),R.string.book3_info,getActivity().getResources().getColor(R.color.book3)));
        list.add(new SetsModel(R.string.bookName4,R.string.bookAuthor4,getResources().getString(R.string.audioBook4URL),getResources().getString(R.string.bookName4),getResources().getString(R.string.textBook4URL),R.string.book4_info,getActivity().getResources().getColor(R.color.book4)));
        list.add(new SetsModel(R.string.bookName5,R.string.bookAuthor5,getResources().getString(R.string.audioBook5URL),getResources().getString(R.string.bookName5),getResources().getString(R.string.textBook5URL),R.string.book5_info,getActivity().getResources().getColor(R.color.book5)));
        list.add(new SetsModel(R.string.bookName6,R.string.bookAuthor6,getResources().getString(R.string.audioBook6URL),getResources().getString(R.string.bookName6),getResources().getString(R.string.textBook6URL),R.string.book6_info,getActivity().getResources().getColor(R.color.book6)));
        list.add(new SetsModel(R.string.bookName7,R.string.bookAuthor7,getResources().getString(R.string.audioBook7URL),getResources().getString(R.string.bookName7),getResources().getString(R.string.textBook7URL),R.string.book7_info,getActivity().getResources().getColor(R.color.book7)));
        list.add(new SetsModel(R.string.bookName8,R.string.bookAuthor8,getResources().getString(R.string.audioBook8URL),getResources().getString(R.string.bookName8),getResources().getString(R.string.textBook8URL),R.string.book8_info,getActivity().getResources().getColor(R.color.book8)));
        list.add(new SetsModel(R.string.bookName9,R.string.bookAuthor9,getResources().getString(R.string.audioBook9URL),getResources().getString(R.string.bookName9),getResources().getString(R.string.textBook9URL),R.string.book9_info,getActivity().getResources().getColor(R.color.book9)));
        list.add(new SetsModel(R.string.bookName10,R.string.bookAuthor10,getResources().getString(R.string.audioBook10URL),getResources().getString(R.string.bookName10),getResources().getString(R.string.textBook10URL),R.string.book10_info,getActivity().getResources().getColor(R.color.book10)));
        list.add(new SetsModel(R.string.bookName11,R.string.bookAuthor11,getResources().getString(R.string.audioBook11URL),getResources().getString(R.string.bookName11),getResources().getString(R.string.textBook11URL),R.string.book11_info,getActivity().getResources().getColor(R.color.book11)));
        list.add(new SetsModel(R.string.bookName12,R.string.bookAuthor12,getResources().getString(R.string.audioBook12URL),getResources().getString(R.string.bookName12),getResources().getString(R.string.textBook12URL),R.string.book12_info,getActivity().getResources().getColor(R.color.book12)));
        list.add(new SetsModel(R.string.bookName13,R.string.bookAuthor13,getResources().getString(R.string.audioBook13URL),getResources().getString(R.string.bookName13),getResources().getString(R.string.textBook13URL),R.string.book13_info,getActivity().getResources().getColor(R.color.book13)));
        list.add(new SetsModel(R.string.bookName14,R.string.bookAuthor14,getResources().getString(R.string.audioBook14URL),getResources().getString(R.string.bookName14),getResources().getString(R.string.textBook14URL),R.string.book14_info,getActivity().getResources().getColor(R.color.book14)));
        list.add(new SetsModel(R.string.bookName15,R.string.bookAuthor15,getResources().getString(R.string.audioBook15URL),getResources().getString(R.string.bookName15),getResources().getString(R.string.textBook15URL),R.string.book15_info,getActivity().getResources().getColor(R.color.book15)));
        list.add(new SetsModel(R.string.bookName16,R.string.bookAuthor16,getResources().getString(R.string.audioBook16URL),getResources().getString(R.string.bookName16),getResources().getString(R.string.textBook16URL),R.string.book16_info,getActivity().getResources().getColor(R.color.book16)));
        list.add(new SetsModel(R.string.bookName17,R.string.bookAuthor17,getResources().getString(R.string.audioBook17URL),getResources().getString(R.string.bookName17),getResources().getString(R.string.textBook17URL),R.string.book17_info,getActivity().getResources().getColor(R.color.book17)));
        list.add(new SetsModel(R.string.bookName18,R.string.bookAuthor18,getResources().getString(R.string.audioBook18URL),getResources().getString(R.string.bookName18),getResources().getString(R.string.textBook18URL),R.string.book18_info,getActivity().getResources().getColor(R.color.book18)));
        list.add(new SetsModel(R.string.bookName19,R.string.bookAuthor19,getResources().getString(R.string.audioBook19URL),getResources().getString(R.string.bookName19),getResources().getString(R.string.textBook19URL),R.string.book19_info,getActivity().getResources().getColor(R.color.book19)));
        list.add(new SetsModel(R.string.bookName20,R.string.bookAuthor20,getResources().getString(R.string.audioBook20URL),getResources().getString(R.string.bookName20),getResources().getString(R.string.textBook20URL),R.string.book20_info,getActivity().getResources().getColor(R.color.book20)));
        list.add(new SetsModel(R.string.bookName21,R.string.bookAuthor21,getResources().getString(R.string.audioBook21URL),getResources().getString(R.string.bookName21),getResources().getString(R.string.textBook21URL),R.string.book21_info,getActivity().getResources().getColor(R.color.book21)));
        list.add(new SetsModel(R.string.bookName22,R.string.bookAuthor22,getResources().getString(R.string.audioBook22URL),getResources().getString(R.string.bookName22),getResources().getString(R.string.textBook22URL),R.string.book22_info,getActivity().getResources().getColor(R.color.book22)));
        list.add(new SetsModel(R.string.bookName23,R.string.bookAuthor23,getResources().getString(R.string.audioBook23URL),getResources().getString(R.string.bookName23),getResources().getString(R.string.textBook23URL),R.string.book23_info,getActivity().getResources().getColor(R.color.book23)));
        list.add(new SetsModel(R.string.bookName24,R.string.bookAuthor24,getResources().getString(R.string.audioBook24URL),getResources().getString(R.string.bookName24),getResources().getString(R.string.textBook24URL),R.string.book24_info,getActivity().getResources().getColor(R.color.book24)));
        list.add(new SetsModel(R.string.bookName25,R.string.bookAuthor25,getResources().getString(R.string.audioBook25URL),getResources().getString(R.string.bookName25),getResources().getString(R.string.textBook25URL),R.string.book25_info,getActivity().getResources().getColor(R.color.book25)));
        list.add(new SetsModel(R.string.bookName26,R.string.bookAuthor26,getResources().getString(R.string.audioBook26URL),getResources().getString(R.string.bookName26),getResources().getString(R.string.textBook26URL),R.string.book26_info,getActivity().getResources().getColor(R.color.book26)));
        list.add(new SetsModel(R.string.bookName27,R.string.bookAuthor27,getResources().getString(R.string.audioBook27URL),getResources().getString(R.string.bookName27),getResources().getString(R.string.textBook27URL),R.string.book27_info,getActivity().getResources().getColor(R.color.book27)));
        list.add(new SetsModel(R.string.bookName28,R.string.bookAuthor28,getResources().getString(R.string.audioBook28URL),getResources().getString(R.string.bookName28),getResources().getString(R.string.textBook28URL),R.string.book28_info,getActivity().getResources().getColor(R.color.book28)));
        list.add(new SetsModel(R.string.bookName29,R.string.bookAuthor29,getResources().getString(R.string.audioBook29URL),getResources().getString(R.string.bookName29),getResources().getString(R.string.textBook29URL),R.string.book29_info,getActivity().getResources().getColor(R.color.book29)));
        list.add(new SetsModel(R.string.bookName30,R.string.bookAuthor30,getResources().getString(R.string.audioBook30URL),getResources().getString(R.string.bookName30),getResources().getString(R.string.textBook30URL),R.string.book30_info,getActivity().getResources().getColor(R.color.book30)));
        list.add(new SetsModel(R.string.bookName31,R.string.bookAuthor31,getResources().getString(R.string.audioBook31URL),getResources().getString(R.string.bookName31),getResources().getString(R.string.textBook31URL),R.string.book31_info,getActivity().getResources().getColor(R.color.book31)));
        list.add(new SetsModel(R.string.bookName32,R.string.bookAuthor32,getResources().getString(R.string.audioBook32URL),getResources().getString(R.string.bookName32),getResources().getString(R.string.textBook32URL),R.string.book32_info,getActivity().getResources().getColor(R.color.book32)));
        list.add(new SetsModel(R.string.bookName33,R.string.bookAuthor33,getResources().getString(R.string.audioBook33URL),getResources().getString(R.string.bookName33),getResources().getString(R.string.textBook33URL),R.string.book33_info,getActivity().getResources().getColor(R.color.book33)));
        list.add(new SetsModel(R.string.bookName34,R.string.bookAuthor34,getResources().getString(R.string.audioBook34URL),getResources().getString(R.string.bookName34),getResources().getString(R.string.textBook34URL),R.string.book34_info,getActivity().getResources().getColor(R.color.book34)));
        list.add(new SetsModel(R.string.bookName35,R.string.bookAuthor35,getResources().getString(R.string.audioBook35URL),getResources().getString(R.string.bookName35),getResources().getString(R.string.textBook35URL),R.string.book35_info,getActivity().getResources().getColor(R.color.book35)));
        list.add(new SetsModel(R.string.bookName36,R.string.bookAuthor36,getResources().getString(R.string.audioBook36URL),getResources().getString(R.string.bookName36),getResources().getString(R.string.textBook36URL),R.string.book36_info,getActivity().getResources().getColor(R.color.book36)));
        list.add(new SetsModel(R.string.bookName37,R.string.bookAuthor37,getResources().getString(R.string.audioBook37URL),getResources().getString(R.string.bookName37),getResources().getString(R.string.textBook37URL),R.string.book37_info,getActivity().getResources().getColor(R.color.book37)));
        list.add(new SetsModel(R.string.bookName38,R.string.bookAuthor38,getResources().getString(R.string.audioBook38URL),getResources().getString(R.string.bookName38),getResources().getString(R.string.textBook38URL),R.string.book38_info,getActivity().getResources().getColor(R.color.book38)));
        list.add(new SetsModel(R.string.bookName39,R.string.bookAuthor39,getResources().getString(R.string.audioBook39URL),getResources().getString(R.string.bookName39),getResources().getString(R.string.textBook39URL),R.string.book39_info,getActivity().getResources().getColor(R.color.book39)));
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

