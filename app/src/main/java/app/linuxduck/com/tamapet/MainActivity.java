package app.linuxduck.com.tamapet;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {
    int timeKeeper = 0;
    Pet meow = new Pet();
    private TextView feedText;
    private TextView waterText;
    private TextView ageText;
    private TextView happyText;
    private TextView healthText;
    private TextView deathText;
    private TextView newPetText;
    private TextView oldPetText;
    private TextView deadPetText;
    private TextView petNameText;
    private TextView guessText;
    private TextView hiddenText;
    private TextView guessedLetters;
    private Button nameButton;
    private Button guessButton;
    private Button guessButton2;
    private Button game1;
    private Button game2;
    private Toast nopeToast;
    private Toast correctToast;
    private Toast eventToast;
    private EditText numberGuessEdit;
    private EditText charGuessEdit;
    private Character guess;
    private ArrayList<Character> guessedLettersArr;
    private ArrayList<Character> lettersHidden;
    private ArrayList<String> words;
    private String hidden;
    private String word;
    private CharSequence eventText;
    private int attempts;
    private int guessAttempts;
    private int random;
    private int eventDuration;
    private boolean accomplished;
    private View view;
    private Context context;

    @Override
    protected void onStart(){
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedText = this.findViewById(R.id.feedInfo);
        waterText = this.findViewById(R.id.waterInfo);
        ageText = this.findViewById(R.id.ageInfo);
        happyText = this.findViewById(R.id.happyInfo);
        healthText = this.findViewById(R.id.healthInfo);
        deathText = this.findViewById(R.id.deathInfo);
        newPetText = this.findViewById(R.id.NewPet);
        oldPetText = this.findViewById(R.id.OldPet);
        deadPetText = this.findViewById(R.id.deadPet);
        petNameText = this.findViewById(R.id.PetName);
        guessText = this.findViewById(R.id.GuessNumber);
        hiddenText = this.findViewById(R.id.Hidden);
        guessedLetters = this.findViewById(R.id.Guessed);

        final EditText petNameEdit = findViewById(R.id.nameTextBox);
        numberGuessEdit = findViewById(R.id.numberTextBox);
        charGuessEdit = findViewById(R.id.charTextBox);

        newPetText.setVisibility(View.VISIBLE);
        oldPetText.setVisibility(View.VISIBLE);

        Button meal = findViewById(R.id.mealBTN);
        Button treat = findViewById(R.id.treatBTN);
        Button water = findViewById(R.id.waterBTN);
        Button play = findViewById(R.id.playBTN);
        Button punish = findViewById(R.id.PunishBTN);
        Button shot = findViewById(R.id.shotBTN);
        guessButton = findViewById(R.id.numberButton);
        guessButton2 = findViewById(R.id.numberButton2);
        nameButton = findViewById(R.id.nameButton);
        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);

        petNameEdit.setVisibility(View.GONE);
        numberGuessEdit.setVisibility(View.GONE);
        charGuessEdit.setVisibility(View.GONE);
        nameButton.setVisibility(View.GONE);
        guessButton.setVisibility(View.GONE);
        guessButton2.setVisibility(View.GONE);
        hiddenText.setVisibility(View.GONE);
        guessedLetters.setVisibility(View.GONE);
        guessText.setVisibility(View.GONE);
        game1.setVisibility(View.GONE);
        game2.setVisibility(View.GONE);
        deadPetText.setVisibility(View.GONE);
        petNameText.setVisibility(View.GONE);

        context = getApplicationContext();
        CharSequence nope = "Nope!";
        CharSequence correct = "Correct!";
        int duration = Toast.LENGTH_SHORT;
        eventDuration = Toast.LENGTH_LONG;

        nopeToast = Toast.makeText(context, nope, duration);
        correctToast = Toast.makeText(context, correct, duration);

        newPetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                deadPetText.setVisibility(View.GONE);
                petNameEdit.setVisibility(View.VISIBLE);
                nameButton.setVisibility(View.VISIBLE);
                petNameText.setVisibility(View.VISIBLE);
                newPetText.setVisibility(View.GONE);
                oldPetText.setVisibility(View.GONE);

                nameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view = getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        petNameText.setVisibility(View.GONE);
                        petNameEdit.getText().toString();

                        meow = new Pet(petNameEdit.getText().toString().toLowerCase(), 0, 50, 50, 100,
                                0, 50, (int)(currentTimeMillis() / 1000), (int)(currentTimeMillis() / 1000), 90);

                        String petInfo = petNameEdit.getText().toString().toLowerCase() + " 0 50 50 100 0 50 " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                                String.valueOf((int)(currentTimeMillis() / 1000) + " 85");

                        FileOutputStream outputStream;
                        try{
                            outputStream = openFileOutput(petNameEdit.getText().toString().toLowerCase() + ".pet", Context.MODE_PRIVATE);
                            outputStream.write(petInfo.getBytes());
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                            eventToast = Toast.makeText(context, eventText, eventDuration);
                            eventToast.show();
                        }
                        petNameEdit.setVisibility(View.GONE);
                        nameButton.setVisibility(View.GONE);
                        newPetText.setVisibility(View.GONE);
                        oldPetText.setVisibility(View.GONE);
                        view = getCurrentFocus();
                    }
                });
            }
        });
        oldPetText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deadPetText.setVisibility(View.GONE);
                petNameEdit.setVisibility(View.VISIBLE);
                nameButton.setVisibility(View.VISIBLE);
                newPetText.setVisibility(View.GONE);
                oldPetText.setVisibility(View.GONE);

                petNameText.setVisibility(View.VISIBLE);

                nameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        petNameText.setVisibility(View.GONE);
                        petNameEdit.getText().toString();

                        try {
                            view = getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            String[] words = new String[9];
                            FileInputStream petFile = openFileInput(petNameEdit.getText().toString().toLowerCase() + ".pet");
                            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(petFile)));
                            String line;
                            while ((line = br.readLine()) != null) {
                                words = line.split(" ");
                            }
                            br.close();
                            meow = new Pet(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                                    Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
                                    Integer.parseInt(words[7]), Integer.parseInt(words[8]), Integer.parseInt(words[9]));
                            meow.updateAwayTime((int)(currentTimeMillis() / 1000));

                            if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                                eventToast = Toast.makeText(context, eventText, eventDuration);
                                eventToast.show();
                            }
                            handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                            save(meow);
                            petNameEdit.setVisibility(View.GONE);
                            nameButton.setVisibility(View.GONE);
                            newPetText.setVisibility(View.GONE);
                            oldPetText.setVisibility(View.GONE);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHunger(15, false);
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });
        treat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHunger(30, true);
                meow.updateHealth(-5);
                meow.updateHappy(10);
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateThirst(15);
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                game1.setVisibility(View.VISIBLE);
                game2.setVisibility(View.VISIBLE);

                game1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guessAttempts = 3;
                        guessText.setVisibility(View.VISIBLE);
                        guessText.setText("Guess a number (0-9)");
                        numberGuessEdit.setVisibility(View.VISIBLE);
                        guessButton.setVisibility(View.VISIBLE);

                        random = 0 + (int)(Math.random() * (0 - 9) + 1);
                        game1.setVisibility(View.GONE);
                        game2.setVisibility(View.GONE);
                        guessButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                int guessInt = Integer.parseInt(numberGuessEdit.getText().toString());
                                if(guessInt == random){
                                    meow.play();
                                    guessAttempts = 0;
                                    correctToast.show();
                                } else {
                                    guessAttempts--;
                                    numberGuessEdit.getText().clear();
                                    nopeToast.show();
                                }
                                if(guessAttempts == 0 || guessAttempts == 3){
                                    view = getCurrentFocus();
                                    if (view != null) {
                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                    }
                                    guessText.setVisibility(View.GONE);
                                    numberGuessEdit.setVisibility(View.GONE);
                                    guessButton.setVisibility(View.GONE);
                                    if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                                        eventToast = Toast.makeText(context, eventText, eventDuration);
                                        eventToast.show();
                                    }
                                    handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                                    save(meow);
                                }
                            }
                        });
                    }
                });
                game2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game1.setVisibility(View.GONE);
                        game2.setVisibility(View.GONE);
                        // Words for hangman
                        words = new ArrayList<>();
                        words.add("apple"); words.add("butter"); words.add("cat"); words.add("dog"); words.add("elephant"); words.add("future");
                        words.add("ghost"); words.add("history"); words.add("icing"); words.add("jump"); words.add("kill"); words.add("little");
                        words.add("moth"); words.add("naughty"); words.add("octopus"); words.add("peanut"); words.add("quit"); words.add("race");
                        words.add("simple"); words.add("terrible"); words.add("unbeatable"); words.add("very"); words.add("wild");
                        words.add("xenoblast"); words.add("yoda"); words.add("zap");

                        // Words that were obfuscated
                        lettersHidden = new ArrayList<>();

                        // Letters Guessed
                        guessedLettersArr = new ArrayList<>();

                        // Picking a random word from words
                        random = abs((int) (Math.random() * words.size()) - 1);
                        word = words.get(random);

                        // Finding letters to obfuscate
                        for(int i = 0; i < 3; i++){
                            int rando = abs((int) (Math.random() * word.length()) - 1);
                            Character randomLetter = word.charAt(rando);
                            if(!lettersHidden.contains(randomLetter)){
                                lettersHidden.add(randomLetter);
                            }
                        }

                        guessButton2.setVisibility(View.VISIBLE);
                        charGuessEdit.setVisibility(View.VISIBLE);
                        guessText.setVisibility(View.VISIBLE);
                        guessText.setText("Guess a letter!");

                        // Creating an obfuscated word... obfuscate.
                        hidden = wordChange(word, lettersHidden);

                        guessedLetters.setVisibility(View.VISIBLE);
                        hiddenText.setVisibility(View.VISIBLE);
                        hiddenText.setText(hidden);
                        attempts = 6;
                        accomplished = false;

                        guessButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                if(charGuessEdit.getText().length() > 0)
                                    guess = charGuessEdit.getText().charAt(0);
                                else
                                    guess = ' ';

                                charGuessEdit.getText().clear();
                                if(lettersHidden.contains(guess)) {
                                    lettersHidden.remove(guess);
                                    hidden = wordChange(word, lettersHidden);
                                    hiddenText.setText(hidden);
                                    correctToast.show();
                                } else {
                                    guessedLettersArr.add(guess);
                                    attempts--;
                                    nopeToast.show();
                                }

                                guessedLetters.setText(wrongGuesses(guessedLettersArr));

                                if(lettersHidden.isEmpty() || lettersHidden.size() == 0){
                                    view = getCurrentFocus();
                                    if (view != null) {
                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                    }
                                    guessedLetters.setText("");
                                    guessedLetters.setVisibility(View.GONE);
                                    lettersHidden.removeAll(lettersHidden);
                                    guessedLettersArr.removeAll(guessedLettersArr);
                                    meow.play();
                                    correctToast.show();
                                    guessText.setVisibility(View.GONE);
                                    charGuessEdit.setVisibility(View.GONE);
                                    guessButton2.setVisibility(View.GONE);
                                    hiddenText.setVisibility(View.GONE);
                                    if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                                        eventToast = Toast.makeText(context, eventText, eventDuration);
                                        eventToast.show();
                                    }
                                    handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                                    save(meow);
                                    guessedLettersArr.clear();
                                } else if(attempts == 0){
                                    view = getCurrentFocus();
                                    if (view != null) {
                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                    }
                                    guessedLetters.setText("");
                                    lettersHidden.removeAll(lettersHidden);
                                    guessedLettersArr.removeAll(guessedLettersArr);
                                    guessedLetters.setVisibility(View.GONE);
                                    nopeToast.show();
                                    guessText.setVisibility(View.GONE);
                                    charGuessEdit.setVisibility(View.GONE);
                                    guessButton2.setVisibility(View.GONE);
                                    hiddenText.setVisibility(View.GONE);
                                    if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                                        eventToast = Toast.makeText(context, eventText, eventDuration);
                                        eventToast.show();
                                    }
                                    handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                                    save(meow);
                                }
                            }
                        });
                    }
                });

                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });
        punish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.updateHealth(-10);
                meow.updateHappy(-10);
                meow.updateRandomEvent();
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });
        shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meow.giveShot();
                meow.updateHappy(-60);
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });

    }
    @Override
     public void onPause() {
        super.onPause();
        timeKeeper = (int)(currentTimeMillis() / 1000);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        meow.updateAwayTime(timeKeeper);
        save(meow);
        ageText.setText(String.valueOf(meow.getAge()));
        feedText.setText(String.valueOf(meow.getHunger()));
        waterText.setText(String.valueOf(meow.getThirst()));
        happyText.setText(String.valueOf(meow.getHappy()));
        healthText.setText(String.valueOf(meow.gethealth()));
        deathText.setText(String.valueOf(meow.getDeath()));
    }
    // Updating Text
    public boolean updateText(Pet meow, TextView deadPet, TextView age, TextView feed,
                           TextView water, TextView happy, TextView health, TextView death){
        int percentage = meow.getRandomEvent();
        int randomEv = abs((int) (Math.random() * 100) + 1);
        if(randomEv <= percentage){
            eventText = meow.randomEvent();
            deadPet.setVisibility(View.GONE);
            age.setText(String.valueOf(meow.getAge()));
            feed.setText(String.valueOf(meow.getHunger()));
            water.setText(String.valueOf(meow.getThirst()));
            happy.setText(String.valueOf(meow.getHappy()));
            health.setText(String.valueOf(meow.gethealth()));
            death.setText(String.valueOf(meow.getDeath()));
            return true;
        }
        deadPet.setVisibility(View.GONE);
        age.setText(String.valueOf(meow.getAge()));
        feed.setText(String.valueOf(meow.getHunger()));
        water.setText(String.valueOf(meow.getThirst()));
        happy.setText(String.valueOf(meow.getHappy()));
        health.setText(String.valueOf(meow.gethealth()));
        death.setText(String.valueOf(meow.getDeath()));
        return false;
    }

    // Save pet
    public void save(Pet meow){
        String petInfo = meow.getName() + " " + String.valueOf(meow.getAge()) + " " + String.valueOf(meow.getHunger()) + " " +
                String.valueOf(meow.getThirst()) + " " + String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getDeath()) + " " +
                String.valueOf(meow.gethealth()) + " " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                String.valueOf((int)(currentTimeMillis() / 1000) + " " + meow.getRandomEvent());

        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(meow.getName() + ".pet", Context.MODE_PRIVATE);
            outputStream.write(petInfo.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Handling Death
    public void handlingDeath(Pet meow, TextView deadPet, TextView newPet, TextView oldPet, Button nameButt, EditText mEdit){
        if(meow.isDead()){
            deadPet.setVisibility(View.VISIBLE);
            newPet.setVisibility(View.VISIBLE);
            oldPet.setVisibility(View.VISIBLE);
            deadPet.setVisibility(View.VISIBLE);
            newPet.setVisibility(View.VISIBLE);
            oldPet.setVisibility(View.VISIBLE);
            mEdit.setVisibility(View.VISIBLE);
            nameButt.setVisibility(View.VISIBLE);
            newPetText.setVisibility(View.VISIBLE);
            oldPetText.setVisibility(View.VISIBLE);
        }
    }
    public String wordChange(String s, ArrayList<Character> a){
        String hidden = s;
        for(int i = 0; i < a.size(); i++){
            hidden = hidden.replace(a.get(i), '_');
        }
        return hidden;
    }

    public String wrongGuesses(ArrayList<Character> a){
        String guesses = "";
        for(int i = 0; i < a.size(); i++){
            guesses += a.get(i);
        }
        return guesses;
    }
}
