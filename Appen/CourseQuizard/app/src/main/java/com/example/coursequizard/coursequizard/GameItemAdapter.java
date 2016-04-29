package com.example.coursequizard.coursequizard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Simon on 2016-04-27.
 */
public class GameItemAdapter extends ArrayAdapter<Game> {
    //private ArrayList<Game> objects;
    private Context context1;

    public GameItemAdapter(Context context, int textViewResourceId, ArrayList<Game> objects) {
        super(context, textViewResourceId, objects);
        //this.objects = objects;
    }
    public GameItemAdapter(Context context,int resource, int textViewResourceId, ArrayList<Game> items) {
        super(context, resource, textViewResourceId, items);
        this.context1 = context;
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.fragment_gameitem, null);
            //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // v = inflater.inflate(R.layout.fragment_gameitem, null);
        }
        Game i = getItem(position);
        if (i != null) {
            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            /*
            private int opponentScore;
            private int userScore;
            private int gameID;
            private String courseName;
            private int state;
            private String stateMessage;
             */

            TextView gameScore = (TextView) v.findViewById(R.id.scoreTextView);
            TextView courseName = (TextView) v.findViewById(R.id.courseNameTextView);
            TextView stateMessage = (TextView) v.findViewById(R.id.opponentNameTextView);
            TextView opponentScoreText = (TextView) v.findViewById(R.id.opponentScoreTextView);
            TextView userScoreText = (TextView) v.findViewById(R.id.userScoreTextView);


            // check to see if each individual textview is null.
            // if not, assign some text!
            String opponentScore = "";
            String userScore = "";
            String user_1_turn ="Your";
            String user_2_turn ="Your";
            String userScoreName   ="Your score";
            String opponentScoreName = "´s score";
            if (gameScore != null) {
                // check if user is user1/sentby
                if (SaveSharedData.getUserName(getContext()).equals(i.getUser_1())) {
                    opponentScore = String.valueOf(i.getUser2_score());
                    userScore = String.valueOf(i.getUser1_score());
                    String score = userScore + " - " + opponentScore;
                    gameScore.setText(score);
                    user_2_turn =i.getUser_2() + "´s";
                    opponentScoreName = i.getUser_2() + opponentScoreName;
                    opponentScoreText.setText(opponentScoreName);
                    userScoreText.setText(userScoreName);

                } else {
                    userScore = String.valueOf(i.getUser2_score());
                    opponentScore = String.valueOf(i.getUser1_score());
                    String score = userScore + " - " + opponentScore;
                    gameScore.setText(score);
                    user_1_turn =i.getUser_1() +"´s";
                    opponentScoreName = i.getUser_1() + opponentScoreName;
                    opponentScoreText.setText(opponentScoreName);
                    userScoreText.setText(userScoreName);
                }
                if (courseName != null) {
                    courseName.setText(i.getcourse_name());
                }
                if (stateMessage != null) {
                    //0 pending
                    //1  user2
                    //2 user 1
                    // 3 finished
                    switch(i.getGame_status()){
                        case(1):
                            i.setGame_status_string(user_2_turn + " turn");
                            break;
                        case(2):
                            i.setGame_status_string(user_1_turn + " turn");
                            break;
                        case(3):
                            i.setGame_status_string("Game Finished");
                            break;
                        default:
                            i.setGame_status_string("Pending");
                            break;
                    }
                    stateMessage.setText(i.getGame_status_string());
                }
            }
        }
            return v;
        }
    }

