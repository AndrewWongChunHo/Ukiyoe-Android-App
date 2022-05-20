package com.example.ea_android_wongchunho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ea_android_wongchunho.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {

    //Define variables;
    TextView timer, result;
    ImageView quizImage;
    HashMap<String, Integer> map = new HashMap<>(); //Store text and image resources
    ArrayList<String> quizList = new ArrayList<>(); //Store text
    int index; //For incrementing points
    Button btn1, btn2, btn3, btn4; //4 options
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;
    long timeLimit; //Set the time limit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        //get id from the quiz xml
        timer = findViewById(R.id.timer);
        result = findViewById(R.id.result);
        quizImage = findViewById(R.id.quiz_image);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.points);
        index = 0;

        // Populate the list with all the painting names
        quizList.add("Under the Wave off Kanagawa");
        quizList.add("Storm below Mount Fuji");
        quizList.add("Ghost of Omitsu");
        quizList.add("Mochizuki");
        quizList.add("Morning Cherries at Yoshiwara");
        quizList.add("Poem by Ariwara no Narihira");
        quizList.add("Monk Nichiren Calming the Stormy Sea");
        quizList.add("Takiyasha the Witch and the Skeleton Specter");

        // Get the images for the list and assign the index to the images
        map.put(quizList.get(0), R.drawable.under_the_wave_off);
        map.put(quizList.get(1), R.drawable.storm_below_mountfuji);
        map.put(quizList.get(2), R.drawable.ghost_of_omitsu);
        map.put(quizList.get(3), R.drawable.mochizuki);
        map.put(quizList.get(4), R.drawable.morning_cherries);
        map.put(quizList.get(5), R.drawable.poem);
        map.put(quizList.get(6), R.drawable.stormysea);
        map.put(quizList.get(7), R.drawable.skeleton);
        Collections.shuffle(quizList);
        timeLimit = 10000;
        points = 0;
        startGame();
    }

    private void startGame() {
        timeLimit = 10000;
        //set the textView as timer
        timer.setText("" + (timeLimit / 1000) + "s");
        //set points
        tvPoints.setText(points + " / " + quizList.size());
        generateQuestions(index);

        //Instantiate the countDownTimer object and override onTick and onFinish
        countDownTimer = new CountDownTimer(timeLimit, 1000){

            @Override
            public void onTick(long timeLimit) {
                timer.setText("" + (timeLimit / 1000) + "s");
            }

            @Override
            public void onFinish() {
                //automatically increment index by 1 when the user cannot answer the question within the time frame
                index++;
                // To check whether questions are answered or not
                if (index > quizList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    quizImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    // direct user to the game finish page when the game is finished
                    Intent intent = new Intent(StartGame.this, GameFinish.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    finish();
                } else {
                    countDownTimer = null;
                    startGame();
                }
            }
        }.start();

    }

    //Generate questions randomly
    private void generateQuestions(int index) {
        // Clone QuizList to a new ArrayList called quizListTemp.
        ArrayList<String> quizListTemp = (ArrayList<String>) quizList.clone();
        // Using index to identify the correct answer
        String correctAnswer = quizList.get(index);
        quizListTemp.remove(correctAnswer);
        Collections.shuffle(quizListTemp);
        // Create a temporary ArrayList for storing three non-repeated random answers
        ArrayList<String> newList = new ArrayList<>();
        // Get first three elements from the quiz list and add them into the newly created list
        newList.add(quizListTemp.get(0));
        newList.add(quizListTemp.get(1));
        newList.add(quizListTemp.get(2));
        //Add correct answer
        newList.add(correctAnswer);
        Collections.shuffle(newList);
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Get Image from the Map
        quizImage.setImageResource(map.get(quizList.get(index)));

    }

    public void nextQuestion(View view){
        // set the background of "nextQuestions"
        btn1.setBackground(getResources().getDrawable(R.drawable.round_button_white, null));
        btn2.setBackground(getResources().getDrawable(R.drawable.round_button_white, null));
        btn3.setBackground(getResources().getDrawable(R.drawable.round_button_white, null));
        btn4.setBackground(getResources().getDrawable(R.drawable.round_button_white, null));
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        // Check if all questions have been asked.
        if (index > quizList.size() - 1){
            // Hide the ImageView and Buttons if it matches the criteria
            quizImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Direct user to the GameFinish page when the game finishes
            Intent intent = new Intent(StartGame.this, GameFinish.class);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            countDownTimer = null;
            startGame();
        }
        
    }

    public void answerSelected(View view) {
        // Change the clicked Button's background color
        view.setBackgroundColor(Color.parseColor("#2ec772"));
        // Disable all four Buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        // Cancel the countDownTimer when answer is selected
        countDownTimer.cancel();
        String answer = ((Button) view).getText().toString().trim();
       //Using index to identify the correct answer
        String correctAnswer = quizList.get(index);
        //Compare the answer selected by users and the correct answer for the question
        if(answer.equals(correctAnswer)){
            // increment by 1 point if correct answer is selected
            points++;
            // Update the points and result
            tvPoints.setText(points + " / " + quizList.size());
            result.setText("Correct");
            result.setTextColor(getResources().getColor(R.color.green, null));

        } else {
            //Display "Wrong" when an incorrect answer is selected
            result.setText("Wrong");
            result.setTextColor(getResources().getColor(R.color.red, null));
        }
    }
}