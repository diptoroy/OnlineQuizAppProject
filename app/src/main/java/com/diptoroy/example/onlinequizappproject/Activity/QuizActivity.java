package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diptoroy.example.onlinequizappproject.Interface.ItemClickListener;
import com.diptoroy.example.onlinequizappproject.Model.Category;
import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.diptoroy.example.onlinequizappproject.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class QuizActivity extends AppCompatActivity {

    RecyclerView listCatarory;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;


    FirebaseDatabase database;
    DatabaseReference categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");



        listCatarory = findViewById(R.id.list_Category);
        listCatarory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listCatarory.setLayoutManager(layoutManager);

        loadCategories();
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
                        Toast.makeText(getApplicationContext(),String.format("%s|%s",adapter.getRef(position).getKey(),category.getName()),Toast.LENGTH_LONG).show();

                        activity();
                        CommonModel.categoryId = adapter.getRef(position).getKey();
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listCatarory.setAdapter(adapter);
    }

    private void activity() {
        Intent play = new Intent(QuizActivity.this, StartActivity.class);
        startActivity(play);
    }
}
