package com.example.coursequizard.coursequizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyProfileActivity extends AppCompatActivity {
    LinkedList<String> universityNameList = new LinkedList<String>();
    LinkedList<University> universityLinkedList = new LinkedList<University>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        fromActivity();

    }
    public void editMyUniversity(View view){
        Spinner spinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        SaveSharedData.setMyUniversityName(MyProfileActivity.this,spinner.getSelectedItem().toString());
        Log.i("id","id");
        Log.i("id",String.valueOf(universityLinkedList.get(spinner.getSelectedItemPosition()).getU_ID()));
        SaveSharedData.setMyUniversityID(MyProfileActivity.this,String.valueOf(universityLinkedList.get(spinner.getSelectedItemPosition()).getU_ID()));
        Log.i("saveds", SaveSharedData.getMyUniversityID(MyProfileActivity.this));
    }
    public void toAllCoursesActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        String type = "get all courses";
        //TODO IMPLEMENT USER
        //String username = "Daniel";
        bgws.execute(type);
    }
    public void createSpinner(){
        for ( int j =0; j< universityLinkedList.size() ;j++){
            universityNameList.add(universityLinkedList.get(j).getName()) ;
        }
        Spinner universitySpinner = (Spinner)findViewById(R.id.universitySpinnerProfile);
        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, universityNameList);
        universitySpinner.setAdapter(universityAdapter);

    }
    public void fromActivity(){
        ArrayList<String> message = new ArrayList<String>();
        message =  getIntent().getExtras().getStringArrayList("prevActivity");
        if(message.get(0).equals("fromMainActivity")){
            CQParser parser = new CQParser();
            universityLinkedList= parser.toUList(message.get(1));
             createSpinner();
            message.get(1);
        }
    }

    public void toMyCoursesActivity(View view){
        //Intent i = new Intent(getApplicationContext(), MyCoursesActivity.class);
        // ArrayList<String> send = new ArrayList<String>();
        //send.add(opponent);
        //send.add(course);
        //i.putExtra("Opponent and Course", send);
        //startActivity(i);
        BackgroundWithServer bgws = new BackgroundWithServer(this);
        String type = "get my courses";
        //TODO IMPLEMENT USER
        String username = SaveSharedData.getUserName(MyProfileActivity.this);
        bgws.execute(type, username);
    }
    public void myFriends(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity. this);
        bgws.execute("friendlist" );
        Intent i = new Intent(MyProfileActivity.this, FriendsActivity. class);
        startActivity(i);
    }
    public void addFriend(View view){
        Intent i = new Intent(MyProfileActivity.this, FriendRequestActivity.class);
        startActivity(i);
    }
    public void toPendingActivity(View view){
        BackgroundWithServer bgws = new BackgroundWithServer(MyProfileActivity.this);
        bgws.execute("pending");
    }
}