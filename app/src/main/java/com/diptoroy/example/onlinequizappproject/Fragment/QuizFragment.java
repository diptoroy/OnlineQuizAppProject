package com.diptoroy.example.onlinequizappproject.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diptoroy.example.onlinequizappproject.Activity.StartActivity;
import com.diptoroy.example.onlinequizappproject.ViewHolder.CategoryViewHolder;
import com.diptoroy.example.onlinequizappproject.Interface.ItemClickListener;
import com.diptoroy.example.onlinequizappproject.Model.Category;
import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    View qFragment;
    RecyclerView listCatarory;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

    public static QuizFragment newInstance(){
        QuizFragment quizFragment = new QuizFragment();
        return quizFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        qFragment = inflater.inflate(R.layout.fragment_quiz, container, false);

        listCatarory = qFragment.findViewById(R.id.list_Category);
        listCatarory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCatarory.setLayoutManager(layoutManager);

        loadCategories();
        
        return qFragment;
    }

    private void loadCategories() {

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, final Category category, int position) {

                categoryViewHolder.category_name.setText(category.getName());
                Picasso.get()
                        .load(category.getImage()).into(categoryViewHolder.category_image);
                categoryViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(),String.format("%s|%s",adapter.getRef(position).getKey(),category.getName()),Toast.LENGTH_LONG).show();

                        Intent play = new Intent(getActivity(), StartActivity.class);
                        CommonModel.categoryId = adapter.getRef(position).getKey();
                        startActivity(play);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listCatarory.setAdapter(adapter);
    }

}
