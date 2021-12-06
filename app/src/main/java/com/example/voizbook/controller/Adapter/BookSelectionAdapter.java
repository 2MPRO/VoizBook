package com.example.voizbook.controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voizbook.R;
import com.example.voizbook.model.BookSelection;

import java.util.ArrayList;

public class BookSelectionAdapter extends RecyclerView.Adapter<BookSelectionAdapter.ViewHoler> {
ArrayList<BookSelection> bookArr;
Context context;

    public BookSelectionAdapter(ArrayList<BookSelection> bookArr, Context context) {
        this.bookArr = bookArr;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.book_selection_item,parent,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.txtCategory.setText(bookArr.get(position).getCategory());
        holder.btnCategory.setImageResource(bookArr.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return bookArr.size();

    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView txtCategory ;
        ImageButton btnCategory;
       public ViewHoler(@NonNull View itemView) {
           super(itemView);

           txtCategory  = (TextView) itemView.findViewById(R.id.txtCategory);
           btnCategory = (ImageButton) itemView.findViewById(R.id.btnCategory);


       }
   }
}
