package com.example.easylearn.libraryFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements Filterable {
    ArrayList<BookModel> list;
    ArrayList<BookModel> filterdeList = new ArrayList<>();
    Context context;

    public BookAdapter(ArrayList<BookModel> list, Context context){
        this.list = list;
        this.filterdeList = new ArrayList<>(list);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BookModel model = filterdeList.get(position);
        model.loadCover(holder.bookCover);
        holder.cardBookName.setText(model.getCardBookName());
        holder.cardBookAuthor.setText(model.getCardBookAuthor());

        holder.cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookInfoActivity.class);
                intent.putExtra("bookCover",model.getBookCover());
                intent.putExtra("bookName",model.getCardBookName());
                intent.putExtra("bookAuthor",model.getCardBookAuthor());
                intent.putExtra("audioBookUrl",model.getAudioBookUrl());
                intent.putExtra("bookTextUrl",model.getTextBookURL());
                intent.putExtra("bookInfo",model.getBookInfo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterdeList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filterdeList = new ArrayList<>(list);
                } else {
                    ArrayList<BookModel> filteredList = new ArrayList<>();
                    for (BookModel book : list) {
                        // Если название книги содержит поисковый запрос, добавляем её в отфильтрованный список
                        if (book.getCardBookName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(book);
                        }
                    }
                    filterdeList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdeList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterdeList = (ArrayList<BookModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardBook;
        ImageView bookCover;
        TextView cardBookName,cardBookAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardBook = itemView.findViewById(R.id.bookCard);
            bookCover = itemView.findViewById(R.id.bookCover);
            cardBookName = itemView.findViewById(R.id.cardBookName);
            cardBookAuthor = itemView.findViewById(R.id.cardBookAuthor);
        }
    }

}
