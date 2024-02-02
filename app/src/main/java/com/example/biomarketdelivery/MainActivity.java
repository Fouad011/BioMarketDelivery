package com.example.biomarketdelivery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biomarketdelivery.adapters.LinkAdapter;
import com.example.biomarketdelivery.firebase.DBProductResume;
import com.example.biomarketdelivery.models.LinkModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
//    FirebaseFirestore db;

    RecyclerView recyclerViewLink;
    List<LinkModel> linkModelList = new ArrayList<>();
    LinkAdapter linkAdapter;
    TextView topTitle;
    ImageView quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();



        actionBar("Commands");



//        db = FirebaseFirestore.getInstance();

        recyclerViewLink = findViewById(R.id.recycleViewLinks);

        linkAdapter = new LinkAdapter(getApplicationContext(), linkModelList);

        recyclerViewLink.setAdapter(linkAdapter);


        DBProductResume DBProductResume = new DBProductResume();
        DBProductResume.getCommandData(linkModelList, linkAdapter);


    }

    private void actionBar(String title){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setCustomView(R.layout.custom_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);


        View customView = actionBar.getCustomView();
        topTitle = customView.findViewById(R.id.titleOfActionBar); // Assuming this is the ID of your title TextView
        quit = customView.findViewById(R.id.quit); // Assuming this is the ID of your title TextView
        topTitle.setText(title);
        quit.setVisibility(View.GONE);
    }


}