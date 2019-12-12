package com.sherwinvaz4827.user.testremoteconfig1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

private DatabaseReference mReff;
private FirebaseDatabase database;
private String version="1.1";
String urlApp="Link will show";
private Uri dataUri= Uri.parse("https://github.com/Grimnoir");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        mReff=database.getReference("Version");

        checkUpdates();

    }

    public void startViewData() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(dataUri); // Uri of the data to be displayed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void checkUpdates() {

        mReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String databaseVersion = dataSnapshot.getValue().toString();

                if (version.equals(databaseVersion)) {
                    Toast.makeText(MainActivity.this, "App upto date", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("New Version Available")
                            .setMessage("Please update to new version to continue use").setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, "" + urlApp, Toast.LENGTH_SHORT).show();
                                    startViewData();


                                }
                            }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    System.exit(0);
                                }
                            }).create();

                    alertDialog.show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
