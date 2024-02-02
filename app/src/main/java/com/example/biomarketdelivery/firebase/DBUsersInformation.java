package com.example.biomarketdelivery.firebase;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.biomarketdelivery.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBUsersInformation {
    Context context;

    public void showClientInformation(Context applicationContext, String userId, TextView tVClientName, TextView tVClientPhone, TextView tVClientLocation) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Clients");

        if(userId != null){
            ref.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        UserModel userModel = task.getResult().getValue(UserModel.class);
//                        deliveryModel.setId(task.getResult().getKey());
//                        tVDeliveryState.setText(deliveryModel.isState()?"In the process of delivery":"Yet to be confirmed");
//                        if(deliveryModel.isState()){
                        assert userModel != null;
                        tVClientName.setText(userModel.getFullName());
                        tVClientPhone.setText(userModel.getMobile());
                        tVClientLocation.setText(userModel.getLocation());
//                        }
                    }else {
                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
