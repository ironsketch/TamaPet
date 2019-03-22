package app.linuxduck.com.tamapet;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {
    int timeKeeper = 0;
    private Pet meow = new Pet();
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
    private TextView creatureName;
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
    private EditText petNameEdit;
    private Character guess;
    private ArrayList<Character> guessedLettersArr;
    private ArrayList<Character> lettersHidden;
    private ArrayList<String> words;
    private String hidden;
    private String word;
    private CharSequence eventText;
    private CharSequence name;
    private int attempts;
    private int guessAttempts;
    private int random;
    private int eventDuration;
    private int duration;
    private int creatureChoice;
    private int resourceID;
    private View view;
    private Context context;
    private LinearLayout linearLayout;
    private ImageView savedCreatures;
    private boolean saveWasOpened;
    private boolean featherClicked;
    private boolean ballClicked;
    private boolean soundornot;
    private ImageView featherImg;
    private ImageView ballImg;
    private ImageView featherButton;
    private ImageView ballButton;
    private ImageView musicOnImg;
    private ImageView musicOffImg;
    private MediaPlayer catpurr;
    private MediaPlayer drinking;
    private MediaPlayer eating;

    // Animation Initializers
    private ArrayList<String> creatureList;
    private ImageView waterImg;
    private ImageView foodImg;
    private ImageView treatImg;
    private ImageView heartImage;
    private AnimationDrawable waterAnimation;
    private AnimationDrawable foodAnimation;
    private AnimationDrawable treatAnimation;
    private AnimationDrawable heartAnimation;

    private ImageView creatureImage;

    private ObjectAnimator creatureAnimator;
    //------------------------

    private Display display;
    private Point size;
    private int width;
    private int height;

    @Override
    protected void onStart(){
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings();

        saveWasOpened = false;
        featherClicked = false;
        ballClicked = false;

        // The window limits
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        savedCreatures = (ImageView)findViewById(R.id.savecreature);

        // Animation Stuff
        waterImg = (ImageView)findViewById(R.id.waterBowl);
        waterImg.setBackgroundResource(R.drawable.water_animation);
        waterAnimation = (AnimationDrawable) waterImg.getBackground();

        foodImg = (ImageView)findViewById(R.id.foodBowl);
        foodImg.setBackgroundResource(R.drawable.food_animation);
        foodAnimation = (AnimationDrawable) foodImg.getBackground();

        treatImg = (ImageView)findViewById(R.id.bone);
        treatImg.setBackgroundResource(R.drawable.treat_animation);
        treatAnimation = (AnimationDrawable) treatImg.getBackground();

        heartImage = (ImageView)findViewById(R.id.heart);
        heartImage.setBackgroundResource(R.drawable.heart_animation);
        heartAnimation = (AnimationDrawable) heartImage.getBackground();

        creatureList = new ArrayList<>();
        creatureList.add("tamaameba"); creatureList.add("tamabomb"); creatureList.add("tamachip"); creatureList.add("tamaeyeturtle");
        creatureList.add("tamafish"); creatureList.add("tamaghost"); creatureList.add("tamagross"); creatureList.add("tamahive");
        creatureList.add("tamahumanface"); creatureList.add("tamapalm"); creatureList.add("tamapills"); creatureList.add("tamarobot");
        creatureList.add("tamarobotheadandspine"); creatureList.add("tamascarycat"); creatureList.add("tamascorpion"); creatureList.add("tamaship");
        creatureList.add("tamasideface"); creatureList.add("tamasierpinskitriangle"); creatureList.add("tamasnake"); creatureList.add("tamaspekter");
        creatureList.add("tamaspidereyeball"); creatureList.add("tamasquiggle"); creatureList.add("tamawtf");

        creatureImage = (ImageView)findViewById(R.id.creatureImageView);
        // ---------------

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
        creatureName = this.findViewById(R.id.creatureName);

        petNameEdit = findViewById(R.id.nameTextBox);
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

        featherButton = findViewById(R.id.featherBUTT);
        ballButton = findViewById(R.id.ballBUTT);
        featherImg = findViewById(R.id.feather);
        ballImg = findViewById(R.id.ball);

        context = getApplicationContext();
        CharSequence nope = "Nope!";
        CharSequence correct = "Correct!";
        duration = Toast.LENGTH_SHORT;
        eventDuration = Toast.LENGTH_LONG;

        nopeToast = Toast.makeText(context, nope, duration);
        correctToast = Toast.makeText(context, correct, duration);

        linearLayout = findViewById(R.id.petList);

        findViewById(R.id.alleverything).setOnTouchListener(handleTouch);

        musicOffImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!soundornot) {
                    catpurr.setVolume(1.0f,1.0f);
                    drinking.setVolume(0.4f,0.4f);
                    eating.setVolume(0.4f,0.4f);
                    musicOnImg.setVisibility(View.VISIBLE);
                    musicOffImg.setVisibility(View.GONE);
                    FileOutputStream outputStream;
                    try{
                        outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                        outputStream.write("true".getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    soundornot = true;
                }
            }
        });

        musicOnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundornot) {
                    catpurr.setVolume(0.0f,0.0f);
                    drinking.setVolume(0.0f,0.0f);
                    eating.setVolume(0.0f,0.0f);
                    musicOnImg.setVisibility(View.GONE);
                    musicOffImg.setVisibility(View.VISIBLE);
                    FileOutputStream outputStream;
                    try{
                        outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                        outputStream.write("false".getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    soundornot = false;
                }
            }
        });

        featherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!featherClicked) {
                    meow.updateHappy(2);
                    featherImg.setVisibility(View.VISIBLE);
                    animateCreature(featherImg.getX(), featherImg.getY());
                    featherClicked = true;
                    if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                        eventToast = Toast.makeText(context, eventText, eventDuration);
                        eventToast.show();
                    }
                    handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                    save(meow);
                } else {
                    featherClicked = false;
                    featherImg.setVisibility(View.GONE);
                }
            }
        });

        ballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ballClicked) {
                    meow.updateHappy(2);
                    ballImg.setVisibility(View.VISIBLE);
                    animateCreature(ballImg.getX(), ballImg.getY());
                    ballClicked = true;
                    if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                        eventToast = Toast.makeText(context, eventText, eventDuration);
                        eventToast.show();
                    }
                    handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                    save(meow);
                } else {
                    ballClicked = false;
                    ballImg.setVisibility(View.GONE);
                }
            }
        });

        creatureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catpurr.start();
                heartImage.setX(creatureImage.getX());
                heartImage.setY(creatureImage.getY());
                meow.updateHappy(2);
                heartAnimation.stop();
                heartAnimation.start();
                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
            }
        });

        savedCreatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViewsInLayout();
                if(!saveWasOpened) {
                    newPetText.setVisibility(View.VISIBLE);
                    oldPetText.setVisibility(View.VISIBLE);
                    petNameText.setVisibility(View.GONE);
                    deadPetText.setVisibility(View.GONE);
                    petNameEdit.setVisibility(View.GONE);
                    nameButton.setVisibility(View.GONE);
                    saveWasOpened = true;
                } else {
                    newPetText.setVisibility(View.GONE);
                    petNameText.setVisibility(View.GONE);
                    oldPetText.setVisibility(View.GONE);
                    deadPetText.setVisibility(View.GONE);
                    petNameEdit.setVisibility(View.GONE);
                    nameButton.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    saveWasOpened = false;
                }
            }
        });

        newPetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                linearLayout.removeAllViewsInLayout();
                deadPetText.setVisibility(View.GONE);
                petNameEdit.setVisibility(View.VISIBLE);
                nameButton.setVisibility(View.VISIBLE);
                petNameText.setVisibility(View.VISIBLE);
                newPetText.setVisibility(View.GONE);
                oldPetText.setVisibility(View.GONE);
                saveWasOpened = false;

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

                        creatureChoice = abs((int) (Math.random() * creatureList.size()) - 1);
                        resourceID = getResources().getIdentifier(creatureList.get(creatureChoice), "drawable", getPackageName());

                        creatureImage.setBackgroundResource(resourceID);
                        creatureImage.setVisibility(View.VISIBLE);


                        meow = new Pet(petNameEdit.getText().toString().toLowerCase().replace(" ", "_"), 0, 50, 50, 100,
                                0, 50, (int)(currentTimeMillis() / 1000), (int)(currentTimeMillis() / 1000), 90, creatureList.get(creatureChoice));

                        String petInfo = petNameEdit.getText().toString().toLowerCase().replace(" ", "_") + " 0 50 50 100 0 50 " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                                String.valueOf((int)(currentTimeMillis() / 1000) + " 85 " + creatureList.get(creatureChoice));

                        FileOutputStream outputStream;
                        try{
                            outputStream = openFileOutput(petNameEdit.getText().toString().toLowerCase().replace(" ", "_") + ".pet", Context.MODE_PRIVATE);
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
                        creatureName.setText(meow.getName().replace("_", " "));
                        view = getCurrentFocus();
                    }
                });
            }
        });
        oldPetText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deadPetText.setVisibility(View.GONE);
                newPetText.setVisibility(View.GONE);
                oldPetText.setVisibility(View.GONE);
                petNameText.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                saveWasOpened = false;
                choosePet();
            }
        });
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game1.setVisibility(View.GONE);
                game2.setVisibility(View.GONE);
                meow.updateHunger(15, false);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                foodAnimation.stop();
                foodAnimation.start();
                animateCreature(foodImg.getX(), foodImg.getY());
                eating.start();

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
                game1.setVisibility(View.GONE);
                game2.setVisibility(View.GONE);
                treatAnimation.stop();
                treatAnimation.start();
                animateCreature(treatImg.getX(), treatImg.getY());
                eating.start();
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
                game1.setVisibility(View.GONE);
                game2.setVisibility(View.GONE);
                meow.updateThirst(15);
                meow.ageUp((int)(currentTimeMillis() / 1000));
                waterAnimation.stop();
                waterAnimation.start();
                animateCreature(waterImg.getX(), waterImg.getY());
                drinking.start();

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
                                    guessedLetters.setVisibility(View.GONE);
                                    lettersHidden.removeAll(lettersHidden);
                                    guessedLettersArr.removeAll(guessedLettersArr);
                                    guessedLetters.setVisibility(View.GONE);
                                    nopeToast.show();
                                    guessText.setVisibility(View.GONE);
                                    charGuessEdit.setVisibility(View.GONE);
                                    guessButton2.setVisibility(View.GONE);
                                    hiddenText.setVisibility(View.GONE);
                                    guessedLetters.setVisibility(View.GONE);
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
                animateCreature();
            }
        });
        punish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game1.setVisibility(View.GONE);
                game2.setVisibility(View.GONE);
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
                animateCreature();
            }
        });
        shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game1.setVisibility(View.GONE);
                game2.setVisibility(View.GONE);
                meow.giveShot();
                meow.ageUp((int)(currentTimeMillis() / 1000));

                if(updateText(meow, deadPetText, ageText, feedText, waterText, happyText, healthText, deathText)){
                    eventToast = Toast.makeText(context, eventText, eventDuration);
                    eventToast.show();
                }
                handlingDeath(meow, deadPetText, newPetText, oldPetText, nameButton, petNameEdit);
                save(meow);
                animateCreature();
            }
        });
    }
    @Override
     public void onPause() {
        super.onPause();
        save(meow);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        timeKeeper = (int)(currentTimeMillis() / 1000);
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
                String.valueOf(meow.gethealth()) + " " + String.valueOf(meow.getAgeTime()) + " " +
                String.valueOf((int)(currentTimeMillis() / 1000) + " " + meow.getRandomEvent() + " " + meow.getCreature());

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

    public void resetAnimations(){

    }

    public void animateCreature(){
        int randomX = ThreadLocalRandom.current().nextInt(300, width - 300);
        int randomY = ThreadLocalRandom.current().nextInt(300, height - 300);
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", randomX);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", randomY);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }
    public void animateCreature(float newX, float newY){
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", newX - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", newY - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }

    public void choosePet(){
        File path = getFilesDir();
        File[] files = path.listFiles();
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < files.length; i++)
        {
            int lengthOfSplit = files[i].getName().split("\\.").length;
            if(lengthOfSplit > 1 && files[i].getName().toString().split("\\.")[1].equals("pet") && !files[i].getName().toString().split("\\.")[0].equals("null")){
                name = files[i].getName().toString().split("\\.")[0];
                final Button newButt = new Button(this);
                newButt.setBackgroundResource(R.drawable.petlistimage);
                newButt.setText(name);
                newButt.setVisibility(linearLayout.getVisibility());
                newButt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                newButt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.setVisibility(View.GONE);
                        try {
                            view = getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            String[] words = new String[11];
                            FileInputStream petFile = openFileInput(newButt.getText().toString() + ".pet");
                            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(petFile)));
                            String line;
                            while ((line = br.readLine()) != null) {
                                words = line.split(" ");
                            }
                            br.close();
                            meow = new Pet(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                                    Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
                                    Integer.parseInt(words[7]), Integer.parseInt(words[8]), Integer.parseInt(words[9]), words[10]);
                            meow.updateAwayTime((int)(currentTimeMillis() / 1000));
                            resourceID = getResources().getIdentifier(words[10], "drawable", getPackageName());

                            creatureImage.setBackgroundResource(resourceID);
                            creatureImage.setVisibility(View.VISIBLE);

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
                            linearLayout.removeAllViewsInLayout();
                            creatureName.setText(meow.getName().replace("_", " "));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                if (linearLayout != null) {
                    linearLayout.addView(newButt);
                }
            }
        }
    }
    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            float xC = event.getX();
            float yC = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    animateCreature(xC, yC);
                    break;
            }
            return true;
        }
    };

    public void settings(){
        // Settings file
        File path = getFilesDir();
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals("settings")) {
                // Load Settings
                try {
                    FileInputStream settingsFile = openFileInput("settings");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(settingsFile)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        soundornot = Boolean .parseBoolean(line);
                    }
                    br.close();
                } catch (Exception e){
                }

                //check settings
            } else {
                // Create new settings
                FileOutputStream outputStream;
                try{
                    outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                    outputStream.write("true".getBytes());
                    outputStream.close();
                    catpurr.setVolume(1.0f,1.0f);
                    drinking.setVolume(0.4f,0.4f);
                    eating.setVolume(0.4f,0.4f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Sounds //
        catpurr = MediaPlayer.create(this, R.raw.purr);
        drinking = MediaPlayer.create(this, R.raw.drinking);
        eating = MediaPlayer.create(this, R.raw.eating);
        musicOffImg = (ImageView)findViewById(R.id.nomusicBUTT);
        musicOnImg = (ImageView)findViewById(R.id.musicBUTT);

        if(!soundornot){
            catpurr.setVolume(0.0f,0.0f);
            drinking.setVolume(0.0f,0.0f);
            eating.setVolume(0.0f,0.0f);
            musicOnImg.setVisibility(View.GONE);
            musicOffImg.setVisibility(View.VISIBLE);
        } else {
            catpurr.setVolume(1.0f,1.0f);
            drinking.setVolume(0.4f,0.4f);
            eating.setVolume(0.4f,0.4f);
        }
    }
}
