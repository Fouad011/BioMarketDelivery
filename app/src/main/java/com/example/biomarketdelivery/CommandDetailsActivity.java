package com.example.biomarketdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biomarketdelivery.adapters.OrderAdapter;
import com.example.biomarketdelivery.firebase.DBProductResume;
import com.example.biomarketdelivery.firebase.DBUsersInformation;
import com.example.biomarketdelivery.models.LinkModel;
import com.example.biomarketdelivery.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CommandDetailsActivity extends AppCompatActivity {

    TextView priceSubTotal, priceTotal, livPrice, tVClientName, tVClientPhone, tVClientLocation;

    RecyclerView recyclerViewOrder;
    List<ProductModel> productModelList =  new ArrayList<>();
    OrderAdapter orderAdapter;
    Button buttonDeliveryOrder;

    TextView topTitle;
    ImageView quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_details);


        priceSubTotal = findViewById(R.id.priceSubTotal);
        priceTotal = findViewById(R.id.priceTotal);
        livPrice = findViewById(R.id.livraisonPrice);

        tVClientName = findViewById(R.id.clientName);
        tVClientPhone = findViewById(R.id.clientPhone);
        tVClientLocation= findViewById(R.id.clientLocation);

        actionBar("Command Details");


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        LinkModel linkModel = (LinkModel) extras.getSerializable("linkModel");

        buttonDeliveryOrder = findViewById(R.id.buttonDeliveryOrder);
        buttonDeliveryOrder.setOnClickListener(v -> commandDelivered(linkModel));



        recyclerViewOrder = findViewById(R.id.recycleViewProductsOrder);

        productModelList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getApplicationContext(), productModelList, priceSubTotal, priceTotal, 50.0);

        recyclerViewOrder.setAdapter(orderAdapter);


//        ActionBar actionBar = getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setCustomView(R.layout.custom_actionbar);
//        actionBar.setDisplayShowCustomEnabled(true);
//
//
//        View customView = actionBar.getCustomView();
//        topTitle = customView.findViewById(R.id.titleOfActionBar); // Assuming this is the ID of your title TextView
//        backSpace = customView.findViewById(R.id.backSpace); // Assuming this is the ID of your title TextView
//        topTitle.setText("Commands Details");
//        backSpace.setVisibility(View.GONE);




//        show client information
        DBUsersInformation clientInformation = new DBUsersInformation();
        clientInformation.showClientInformation(getApplicationContext(), linkModel.getUserId(), tVClientName, tVClientPhone, tVClientLocation);


//        show products of command
        DBProductResume dbProductResume = new DBProductResume();
        dbProductResume.showCommandProducts(getApplicationContext(), linkModel, productModelList, orderAdapter);

        for (ProductModel productModel : productModelList){
            System.out.println(productModel.getID() +" "+ productModel.getQuantity());
        }


    }

    private void commandDelivered(LinkModel linkModel) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference refCommand = db.getReference("Commands");


        refCommand.child("Deliverers").child(linkModel.getDeliveryId()).child(linkModel.getUserId()).child(linkModel.getId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.isComplete()){
                        Object object = task.getResult().getValue(Object.class);
//                        assert productResume != null;
//                        productResume.setId(task.getResult().getKey());
//                        System.out.println("--> " + productResume.toString());
                        setArchiveCommand(object, linkModel);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setArchiveCommand(Object object, LinkModel linkModel){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference refCommand = db.getReference("Commands");
        DatabaseReference refArchive = db.getReference("Archives");

        refArchive.child("Commands").child(linkModel.getDeliveryId()).child(linkModel.getUserId()).child(linkModel.getId()).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
//                    Toast.makeText(context1, "Succ", Toast.LENGTH_SHORT).show();
//                    refClient.child(userId).child("Pannier").removeValue();
                    Toast.makeText(getApplicationContext(), "Sec to submit products", Toast.LENGTH_SHORT).show();
                    refCommand.child("Deliverers").child(linkModel.getDeliveryId()).child(linkModel.getUserId()).child(linkModel.getId()).removeValue();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Field to submit products", Toast.LENGTH_SHORT).show();
                }
//                context1.startActivity(new Intent(context1, CommandsActivity.class));

            }
        });
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