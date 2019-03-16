package app.linuxduck.com.tamapet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {
    public Pet meow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView feedText = this.findViewById(R.id.feedInfo);
        final TextView waterText = this.findViewById(R.id.waterInfo);
        final TextView ageText = this.findViewById(R.id.ageInfo);
        final TextView happyText = this.findViewById(R.id.happyInfo);
        final TextView healthText = this.findViewById(R.id.healthInfo);
        final TextView deathText = this.findViewById(R.id.deathInfo);
        final TextView newPetText = this.findViewById(R.id.NewPet);
        final TextView oldPetText = this.findViewById(R.id.OldPet);
        final TextView deadPetText = this.findViewById(R.id.deadPet);
        final TextView game1Text = this.findViewById(R.id.game1);
        final TextView game2Text = this.findViewById(R.id.game2);

        newPetText.setText("Create New Pet");
        oldPetText.setText("Load Old Pet");

        // My attempt
        // updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText);

        Button meal = findViewById(R.id.mealBTN);
        Button treat = findViewById(R.id.treatBTN);
        Button water = findViewById(R.id.waterBTN);
        Button play = findViewById(R.id.playBTN);
        Button punish = findViewById(R.id.PunishBTN);
        Button shot = findViewById(R.id.shotBTN);

        newPetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                meow = new Pet("Spekter.pet", 0, 50, 50, 100,
                        0, 50, (int)(currentTimeMillis() / 1000), (int)(currentTimeMillis() / 1000));
                newPetText.setText("");
                oldPetText.setText("");

                String petInfo = "Spekter 0 50 50 100 0 50 " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                deadPetText.setText("");
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));
            }
        });
        oldPetText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newPetText.setText("");
                oldPetText.setText("");
                deadPetText.setText("");

                try {
                    String[] words = new String[9];
                    FileInputStream petFile = openFileInput("Spekter.pet");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(petFile)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        words = line.split(" ");
                    }
                    br.close();
                    meow = new Pet(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                            Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
                            Integer.parseInt(words[7]), Integer.parseInt(words[8]));
                    meow.updateAwayTime((int)(currentTimeMillis() / 1000));
                    ageText.setText(String.valueOf(meow.getAge()));
                    feedText.setText(String.valueOf(meow.getHunger()));
                    waterText.setText(String.valueOf(meow.getThirst()));
                    happyText.setText(String.valueOf(meow.getHappy()));
                    healthText.setText(String.valueOf(meow.gethealth()));
                    deathText.setText(String.valueOf(meow.getDeath()));


                    if(meow.isDead()){
                        deadPetText.setText("DEAD PET");
                        newPetText.setText("Create New Pet");
                        oldPetText.setText("Load Old Pet");
                    }

                    // Saving
                    String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                            String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                            String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                            String.valueOf((int)(currentTimeMillis() / 1000));

                    FileOutputStream outputStream;

                    try{
                        outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                        outputStream.write(petInfo.getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHunger(15, false);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        treat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHunger(30, true);
                meow.updateHealth(-5);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateThirst(15);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.play();
                game1Text.setText("");
                game2Text.setText("");
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        punish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHealth(-10);
                meow.updateHappy(-10);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.giveShot();
                meow.updateHappy(-60);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                if(meow.isDead()){
                    deadPetText.setText("DEAD PET");
                    newPetText.setText("Create New Pet");
                    oldPetText.setText("Load Old Pet");
                }
                ageText.setText(String.valueOf(meow.getAge()));
                feedText.setText(String.valueOf(meow.getHunger()));
                waterText.setText(String.valueOf(meow.getThirst()));
                happyText.setText(String.valueOf(meow.getHappy()));
                healthText.setText(String.valueOf(meow.gethealth()));
                deathText.setText(String.valueOf(meow.getDeath()));

                // Saving
                String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                        String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                        String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                        String.valueOf((int)(currentTimeMillis() / 1000));

                FileOutputStream outputStream;

                try{
                    outputStream = openFileOutput("Spekter.pet", Context.MODE_PRIVATE);
                    outputStream.write(petInfo.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // My attempt
    // public void updateText(Pet meow, TextView deadPet, TextView age, TextView feed, TextView water, TextView happy, TextView health, TextView death){
    //    deadPet.setText("");
    //    age.setText(String.valueOf(meow.getAge()));
    //    feed.setText(String.valueOf(meow.getHunger()));
    //    water.setText(String.valueOf(meow.getThirst()));
    //    happy.setText(String.valueOf(meow.getHappy()));
    //    health.setText(String.valueOf(meow.gethealth()));
    //    death.setText(String.valueOf(meow.getDeath()));
    //}
}
