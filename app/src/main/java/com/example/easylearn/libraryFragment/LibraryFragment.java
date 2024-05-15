package com.example.easylearn.libraryFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.easylearn.HideSystemUI;
import com.example.easylearn.LoadData;
import com.example.easylearn.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LibraryFragment extends Fragment {
    ArrayList<BookModel> bookList;
    BookAdapter adapter;
    RecyclerView bookSlider;
    SearchView searchBook;
    CircleImageView image_username;
    TextView text_welcome_user;
    LoadData loadData;
    public LibraryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library,container,false);
        HideSystemUI.hideSystemUI(getActivity());

        text_welcome_user = view.findViewById(R.id.text_welcome_user);
        image_username = view.findViewById(R.id.image_username);

        String text = "Что будем читать \nсегодня, ";
        loadData = new LoadData().loadImage(image_username,text_welcome_user,text);

        bookSlider = view.findViewById(R.id.bookSlider);
        bookSlider.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        bookList = new ArrayList<>();

        Card();

        adapter = new BookAdapter(bookList,getContext());
        bookSlider.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        searchBook = view.findViewById(R.id.searchBook);
        searchBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }

    private void Card() {
        bookList.add(new BookModel("Капитанская дочка","Александр Пушкин",getResources().getString(R.string.audioBook1URL),getResources().getString(R.string.textBook1URL),getResources().getString(R.string.bookCover1URL),getResources().getString(R.string.book1_info)));
        bookList.add(new BookModel("451 градус по Фаренгейту","Рэй Брэдбери",getResources().getString(R.string.audioBook2URL),getResources().getString(R.string.textBook2URL),getResources().getString(R.string.bookCover2URL),getResources().getString(R.string.book2_info)));
        bookList.add(new BookModel("1984","Джордж Оруэлл",getResources().getString(R.string.audioBook3URL),getResources().getString(R.string.textBook3URL),getResources().getString(R.string.bookCover3URL),getResources().getString(R.string.book3_info)));
        bookList.add(new BookModel("А зори здесь тихие…","Борис Васильев",getResources().getString(R.string.audioBook4URL),getResources().getString(R.string.textBook4URL),getResources().getString(R.string.bookCover4URL),getResources().getString(R.string.book4_info)));
        bookList.add(new BookModel("Анна Каренина","Лев Толстой",getResources().getString(R.string.audioBook5URL),getResources().getString(R.string.textBook5URL),getResources().getString(R.string.bookCover5URL),getResources().getString(R.string.book5_info)));
        bookList.add(new BookModel("Антоновские яблоки","Иван Бунин",getResources().getString(R.string.audioBook6URL),getResources().getString(R.string.textBook6URL),getResources().getString(R.string.bookCover6URL),getResources().getString(R.string.book6_info)));
        bookList.add(new BookModel("Без языка","Владимир Короленко",getResources().getString(R.string.audioBook7URL),getResources().getString(R.string.textBook7URL),getResources().getString(R.string.bookCover7URL),getResources().getString(R.string.book7_info)));
        bookList.add(new BookModel("Белая Гвардия","Михаил Булгаков",getResources().getString(R.string.audioBook8URL),getResources().getString(R.string.textBook8URL),getResources().getString(R.string.bookCover8URL),getResources().getString(R.string.book8_info)));
        bookList.add(new BookModel("Братья Карамазовы","Фёдор Достоевский",getResources().getString(R.string.audioBook9URL),getResources().getString(R.string.textBook9URL),getResources().getString(R.string.bookCover9URL),getResources().getString(R.string.book9_info)));
        bookList.add(new BookModel("Война и мир","Лев Толстой",getResources().getString(R.string.audioBook10URL),getResources().getString(R.string.textBook10URL),getResources().getString(R.string.bookCover10URL),getResources().getString(R.string.book10_info)));
        bookList.add(new BookModel("Герой нашего времени","Михаил Лермонтов",getResources().getString(R.string.audioBook11URL),getResources().getString(R.string.textBook11URL),getResources().getString(R.string.bookCover11URL),getResources().getString(R.string.book11_info)));
        bookList.add(new BookModel("Голова профессора Доуэля","Александр Беляев",getResources().getString(R.string.audioBook12URL),getResources().getString(R.string.textBook12URL),getResources().getString(R.string.bookCover12URL),getResources().getString(R.string.book12_info)));
        bookList.add(new BookModel("Горе от ума","Александр Грибоедов",getResources().getString(R.string.audioBook13URL),getResources().getString(R.string.textBook13URL),getResources().getString(R.string.bookCover13URL),getResources().getString(R.string.book13_info)));
        bookList.add(new BookModel("Господин из Сан-Франциско","Иван Бунин",getResources().getString(R.string.audioBook14URL),getResources().getString(R.string.textBook14URL),getResources().getString(R.string.bookCover14URL),getResources().getString(R.string.book14_info)));
        bookList.add(new BookModel("Гранатовый браслет","Александр Куприн",getResources().getString(R.string.audioBook15URL),getResources().getString(R.string.textBook15URL),getResources().getString(R.string.bookCover15URL),getResources().getString(R.string.book15_info)));
        bookList.add(new BookModel("Гроза","Александр Островский",getResources().getString(R.string.audioBook16URL),getResources().getString(R.string.textBook16URL),getResources().getString(R.string.bookCover16URL),getResources().getString(R.string.book16_info)));
        bookList.add(new BookModel("Доктор Живаго","Борис Пастернак",getResources().getString(R.string.audioBook17URL),getResources().getString(R.string.textBook17URL),getResources().getString(R.string.bookCover17URL),getResources().getString(R.string.book17_info)));
        bookList.add(new BookModel("Евгений Онегин","Александр Пушкин",getResources().getString(R.string.audioBook18URL),getResources().getString(R.string.textBook18URL),getResources().getString(R.string.bookCover18URL),getResources().getString(R.string.book18_info)));
        bookList.add(new BookModel("Ионыч","Антон Чехов",getResources().getString(R.string.audioBook19URL),getResources().getString(R.string.textBook19URL),getResources().getString(R.string.bookCover19URL),getResources().getString(R.string.book19_info)));
        bookList.add(new BookModel("Мартин Иден","Джек Лондон",getResources().getString(R.string.audioBook20URL),getResources().getString(R.string.textBook20URL),getResources().getString(R.string.bookCover20URL),getResources().getString(R.string.book20_info)));
        bookList.add(new BookModel("Мастер и Маргарита","Михаил Булгаков",getResources().getString(R.string.audioBook21URL),getResources().getString(R.string.textBook21URL),getResources().getString(R.string.bookCover21URL),getResources().getString(R.string.book21_info)));
        bookList.add(new BookModel("Мёртвые души","Николай Гоголь",getResources().getString(R.string.audioBook22URL),getResources().getString(R.string.textBook22URL),getResources().getString(R.string.bookCover22URL),getResources().getString(R.string.book22_info)));
        bookList.add(new BookModel("Мцыри","Михаил Лермонтов",getResources().getString(R.string.audioBook23URL),getResources().getString(R.string.textBook23URL),getResources().getString(R.string.bookCover23URL),getResources().getString(R.string.book23_info)));
        bookList.add(new BookModel("Мы","Евгений Замятин",getResources().getString(R.string.audioBook24URL),getResources().getString(R.string.textBook24URL),getResources().getString(R.string.bookCover24URL),getResources().getString(R.string.book24_info)));
        bookList.add(new BookModel("На дне","Максим Горький",getResources().getString(R.string.audioBook25URL),getResources().getString(R.string.textBook25URL),getResources().getString(R.string.bookCover25URL),getResources().getString(R.string.book25_info)));
        bookList.add(new BookModel("Не стреляйте в белых лебедей","Борис Васильев",getResources().getString(R.string.audioBook26URL),getResources().getString(R.string.textBook26URL),getResources().getString(R.string.bookCover26URL),getResources().getString(R.string.book26_info)));
        bookList.add(new BookModel("Невеста","Антон Чехов",getResources().getString(R.string.audioBook27URL),getResources().getString(R.string.textBook27URL),getResources().getString(R.string.bookCover27URL),getResources().getString(R.string.book27_info)));
        bookList.add(new BookModel("Обломов","Иван Гончаров",getResources().getString(R.string.audioBook28URL),getResources().getString(R.string.textBook28URL),getResources().getString(R.string.bookCover28URL),getResources().getString(R.string.book28_info)));
        bookList.add(new BookModel("Олеся","Александр Куприн",getResources().getString(R.string.audioBook29URL),getResources().getString(R.string.textBook29URL),getResources().getString(R.string.bookCover29URL),getResources().getString(R.string.book29_info)));
        bookList.add(new BookModel("Отцы и дети","Иван Тургенев",getResources().getString(R.string.audioBook30URL),getResources().getString(R.string.textBook30URL),getResources().getString(R.string.bookCover30URL),getResources().getString(R.string.book30_info)));
        bookList.add(new BookModel("Песня про купца Калашникова","Михали Лермонтов",getResources().getString(R.string.audioBook31URL),getResources().getString(R.string.textBook31URL),getResources().getString(R.string.bookCover31URL),getResources().getString(R.string.book31_info)));
        bookList.add(new BookModel("Портрет","Николай Гоголь",getResources().getString(R.string.audioBook32URL),getResources().getString(R.string.textBook32URL),getResources().getString(R.string.bookCover32URL),getResources().getString(R.string.book32_info)));
        bookList.add(new BookModel("Портрет Дориана Грея","Оскар Уайльд",getResources().getString(R.string.audioBook33URL),getResources().getString(R.string.textBook33URL),getResources().getString(R.string.bookCover33URL),getResources().getString(R.string.book33_info)));
        bookList.add(new BookModel("После бала","Лев Толстой",getResources().getString(R.string.audioBook34URL),getResources().getString(R.string.textBook34URL),getResources().getString(R.string.bookCover34URL),getResources().getString(R.string.book34_info)));
        bookList.add(new BookModel("Преступление и наказание","Фёдор Достоевский",getResources().getString(R.string.audioBook35URL),getResources().getString(R.string.textBook35URL),getResources().getString(R.string.bookCover35URL),getResources().getString(R.string.book35_info)));
        bookList.add(new BookModel("Собачье сердце","Михаил Булгаков",getResources().getString(R.string.audioBook36URL),getResources().getString(R.string.textBook36URL),getResources().getString(R.string.bookCover36URL),getResources().getString(R.string.book36_info)));
        bookList.add(new BookModel("Тарас Бульба","Николай Гоголь",getResources().getString(R.string.audioBook37URL),getResources().getString(R.string.textBook37URL),getResources().getString(R.string.bookCover37URL),getResources().getString(R.string.book37_info)));
        bookList.add(new BookModel("Телеграмма","Константин Паустовский",getResources().getString(R.string.audioBook38URL),getResources().getString(R.string.textBook38URL),getResources().getString(R.string.bookCover38URL),getResources().getString(R.string.book38_info)));
        bookList.add(new BookModel("Тихий Дон","Михаил Шолохов",getResources().getString(R.string.audioBook39URL),getResources().getString(R.string.textBook39URL),getResources().getString(R.string.bookCover39URL),getResources().getString(R.string.book39_info)));
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