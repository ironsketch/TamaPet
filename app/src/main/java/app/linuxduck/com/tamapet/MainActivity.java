package app.linuxduck.com.tamapet;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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

    // Initializations
    // ---------------
    // Pet
    private Pet myPet;

    // Ints
    private int timeKeeper;
    private int gameAttempts;
    private int random;
    private int SHORTduration;
    private int LONGduration;
    private int creatureChoice;
    private int resourceID;
    private int width;
    private int height;

    // Floats
    private float objX;
    private float objY;

    //TextViews
    private TextView hungerIntText;
    private TextView waterIntText;
    private TextView ageIntText;
    private TextView happyIntText;
    private TextView healthIntText;
    private TextView deathIntText;
    private TextView nameYourNewPetText;
    private TextView deadPetText;
    private TextView guessANumberText;
    private TextView alreadyGuessedLettersText;
    private TextView hiddenWordText;
    private TextView guessALetterText;
    private TextView loadedPetNameText;

    // Buttons
    private Button gameOneButton;
    private Button gameTwoButton;
    private Button listOfPetsButton;
    private Button createNewPetButton;
    private Button openOldPetButton;
    private Button submitButton;

    // ImageButtons
    private ImageButton featherImageButton;
    private ImageButton ballImageButton;
    private ImageButton soundImageButton;
    private ImageButton savedCreaturesImageButton;
    private ImageButton mealImageButton;
    private ImageButton treatImageButton;
    private ImageButton waterImageButton;
    private ImageButton playImageButton;
    private ImageButton punishImageButton;
    private ImageButton shotImageButton;

    // EditTexts
    private EditText numberEditText;
    private EditText letterEditText;
    private EditText nameEditText;

    // Characters
    Character characterGuess;

    // CharSequences
    private CharSequence name;

    // Strings
    private String hiddenWordString;
    private String randomlyChoseWord;
    private String eventText;
    private String word;

    // Booleans
    private boolean saveButtonWasOpened;
    private boolean featherWasClicked;
    private boolean ballWasClicked;
    private boolean soundORnot;
    private boolean loaded;
    private boolean first;
    private boolean second;

    // ImageViews
    private ImageView creatureImage;
    private ImageView currentCreatureImg;
    private ImageView featherImg;
    private ImageView ballImg;
    private ImageView waterImg;
    private ImageView foodImg;
    private ImageView treatImg;
    private ImageView heartImage;

    // Views
    private View view;

    // Contexts
    private Context context;

    // MediaPlayers
    private MediaPlayer catpurrSound;
    private MediaPlayer drinkingSound;
    private MediaPlayer eatingSound;

    // LinearLayouts
    private LinearLayout linearLayout;

    // ArrayLists
    private ArrayList<Character> guessedLettersArr;
    private ArrayList<Character> lettersHidden;
    private ArrayList<String> words;
    private ArrayList<String> creatureList;

    // AnimationDrawables
    private AnimationDrawable waterAnimation;
    private AnimationDrawable foodAnimation;
    private AnimationDrawable treatAnimation;
    private AnimationDrawable heartAnimation;

    // Displays
    private Display display;

    // Points
    private Point size;

    // ObjectAnimators
    private ObjectAnimator creatureAnimator;

    // Toasts
    Toast eventToast;
    Toast nopeToast;
    Toast correctToast;

    @Override
    protected void onStart(){
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pet
        myPet = new Pet();

        // Displays
        size = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        // Ints
        width = size.x;
        height = size.y;
        SHORTduration = Toast.LENGTH_SHORT;
        LONGduration = Toast.LENGTH_LONG;

        // TextViews
        hungerIntText = findViewById(R.id.hungerinttext);
        waterIntText = findViewById(R.id.waterinttext);
        ageIntText = findViewById(R.id.ageinttext);
        happyIntText = findViewById(R.id.happyinttext);
        healthIntText = findViewById(R.id.healthinttext);
        deathIntText = findViewById(R.id.deathinttext);
        nameYourNewPetText = findViewById(R.id.nameyournewpettext);
        deadPetText = findViewById(R.id.deadpettext);
        guessANumberText = findViewById(R.id.guessanumbertext);
        alreadyGuessedLettersText = findViewById(R.id.alreadyguessedletters);
        hiddenWordText = findViewById(R.id.hiddenword);
        guessALetterText = findViewById(R.id.guessaletter);
        loadedPetNameText = findViewById(R.id.loadedpetname);

        // Buttons
        gameOneButton = findViewById(R.id.gameonebutton);
        gameTwoButton = findViewById(R.id.gametwobutton);
        listOfPetsButton = findViewById(R.id.listofpets);
        createNewPetButton = findViewById(R.id.createnewpetbutton);
        openOldPetButton = findViewById(R.id.openoldpetbutton);
        submitButton = findViewById(R.id.submit);

        // ImageButton
        featherImageButton = findViewById(R.id.featherimagebutton);
        ballImageButton = findViewById(R.id.ballimagebutton);
        soundImageButton = findViewById(R.id.soundimagebutton);
        savedCreaturesImageButton = findViewById(R.id.savedcreaturesimagebutton);
        mealImageButton = findViewById(R.id.mealbutton);
        treatImageButton = findViewById(R.id.treatbutton);
        waterImageButton = findViewById(R.id.waterbutton);
        playImageButton = findViewById(R.id.playbutton);
        punishImageButton = findViewById(R.id.punishbutton);
        shotImageButton = findViewById(R.id.shotbutton);

        // EditTexts
        nameEditText = findViewById(R.id.nameedittext);
        numberEditText = findViewById(R.id.numberedittext);
        letterEditText = findViewById(R.id.letteredittext);

        // Booleans
        saveButtonWasOpened = false;
        soundORnot = false;
        featherWasClicked = false;
        ballWasClicked = false;
        loaded = false;
        first = true;
        second = true;

        // ImageViews
        waterImg = findViewById(R.id.waterbowl);
        waterImg.setBackgroundResource(R.drawable.water_animation);
        foodImg = findViewById(R.id.foodbowl);
        foodImg.setBackgroundResource(R.drawable.food_animation);
        treatImg = findViewById(R.id.bonetreat);
        treatImg.setBackgroundResource(R.drawable.treat_animation);
        heartImage = findViewById(R.id.heartimageview);
        heartImage.setBackgroundResource(R.drawable.heart_animation);
        heartImage.setVisibility(View.VISIBLE);
        creatureImage = findViewById(R.id.creatureimageview);
        featherImg = findViewById(R.id.feather);
        ballImg = findViewById(R.id.ball);

        // Contexts
        context = getApplicationContext();

        // MediaPlayers
        catpurrSound = MediaPlayer.create(this, R.raw.purr);
        drinkingSound = MediaPlayer.create(this, R.raw.drinking);
        eatingSound = MediaPlayer.create(this, R.raw.eating);

        // LinearLayouts
        linearLayout = findViewById(R.id.linearlayoutlistofpets);

        // ArrayLists
        creatureList = new ArrayList<>();
        creatureList.add("tamaameba"); creatureList.add("tamabomb"); creatureList.add("tamachip"); creatureList.add("tamaeyeturtle");
        creatureList.add("tamafish"); creatureList.add("tamaghost"); creatureList.add("tamagross"); creatureList.add("tamahive");
        creatureList.add("tamahumanface"); creatureList.add("tamapalm"); creatureList.add("tamapills"); creatureList.add("tamarobot");
        creatureList.add("tamarobotheadandspine"); creatureList.add("tamascarycat"); creatureList.add("tamascorpion"); creatureList.add("tamaship");
        creatureList.add("tamasideface"); creatureList.add("tamasierpinskitriangle"); creatureList.add("tamasnake"); creatureList.add("tamaspekter");
        creatureList.add("tamaspidereyeball"); creatureList.add("tamasquiggle"); creatureList.add("tamawtf");

        // AnimationDrawables
        waterAnimation = (AnimationDrawable) waterImg.getBackground();
        foodAnimation = (AnimationDrawable) foodImg.getBackground();
        treatAnimation = (AnimationDrawable) treatImg.getBackground();
        heartAnimation = (AnimationDrawable) heartImage.getBackground();

        // ObjectAnimators

        // Toasts
        nopeToast = Toast.makeText(context, context.getString(R.string.nope), SHORTduration);
        correctToast = Toast.makeText(context, context.getString(R.string.correct), SHORTduration);

        // More settings to set
        display.getSize(size);

        settings();

        findViewById(R.id.mainall).setOnTouchListener(handleTouch);

        soundImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSound();
            }
        });

        featherImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffEverything();
                if(!featherWasClicked && loaded) {
                    if(!first) {
                        objX = featherImg.getX();
                        objY = featherImg.getY();
                    } else {
                        objX = width * 0.8f;
                        objY = height / 2f;
                    }
                    myPet.updateHappy(2);
                    featherImg.setVisibility(View.VISIBLE);
                    animateCreature(objX, objY);
                    featherWasClicked = true;
                    updateGame();
                    first = false;
                } else {
                    featherWasClicked = false;
                    featherImg.setVisibility(View.GONE);
                }
            }
        });

        ballImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffEverything();
                if(!ballWasClicked) {
                    if(!second) {
                        objX = ballImg.getX();
                        objY = ballImg.getY();
                    } else {
                        objX = width * .2f;
                        objY = height / 2f;
                    }
                    myPet.updateHappy(2);
                    ballImg.setVisibility(View.VISIBLE);
                    animateCreature(objX, objY);
                    ballWasClicked = true;
                    updateGame();
                    second = false;
                } else {
                    ballWasClicked = false;
                    ballImg.setVisibility(View.GONE);
                }
            }
        });

        creatureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartImage.setX(creatureImage.getX());
                heartImage.setY(creatureImage.getY());
                myPet.updateHappy(2);
                heartAnimation.stop();
                heartAnimation.start();
                catpurrSound.start();
                updateGame();
            }
        });

        savedCreaturesImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffEverything();
                linearLayout.removeAllViewsInLayout();
                if(!saveButtonWasOpened) {
                    createNewPetButton.setVisibility(View.VISIBLE);
                    openOldPetButton.setVisibility(View.VISIBLE);
                    loadedPetNameText.setVisibility(View.GONE);
                    deadPetText.setVisibility(View.GONE);
                    nameEditText.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);
                    saveButtonWasOpened = true;
                    turnOffEverything();
                } else {
                    createNewPetButton.setVisibility(View.GONE);
                    openOldPetButton.setVisibility(View.GONE);
                    loadedPetNameText.setVisibility(View.GONE);
                    deadPetText.setVisibility(View.GONE);
                    nameEditText.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    saveButtonWasOpened = false;
                    turnOffEverything();
                }
            }
        });

        createNewPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                turnOffEverything();
                linearLayout.removeAllViewsInLayout();
                deadPetText.setVisibility(View.GONE);
                nameEditText.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                nameYourNewPetText.setVisibility(View.VISIBLE);
                createNewPetButton.setVisibility(View.GONE);
                openOldPetButton.setVisibility(View.GONE);
                saveButtonWasOpened = false;

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view = getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        createNewPetButton.setVisibility(View.GONE);
                        openOldPetButton.setVisibility(View.GONE);
                        submitButton.setVisibility(View.GONE);
                        nameEditText.setVisibility(View.GONE);
                        nameEditText.getText().toString();

                        creatureChoice = abs((int) (Math.random() * creatureList.size()) - 1);
                        resourceID = getResources().getIdentifier(creatureList.get(creatureChoice), "drawable", getPackageName());

                        creatureImage.setBackgroundResource(resourceID);
                        creatureImage.setVisibility(View.VISIBLE);


                        myPet = new Pet(nameEditText.getText().toString().toLowerCase().replace(" ", "_"), 0, 50, 50, 100,
                                0, 50, (int)(currentTimeMillis() / 1000), (int)(currentTimeMillis() / 1000), 90, creatureList.get(creatureChoice));

                        String petInfo = nameEditText.getText().toString().toLowerCase().replace(" ", "_") + " 0 50 50 100 0 50 " + String.valueOf((int)(currentTimeMillis() / 1000)) + " " +
                                String.valueOf((int)(currentTimeMillis() / 1000) + " 85 " + creatureList.get(creatureChoice));
                        loaded = true;
                        FileOutputStream outputStream;
                        try{
                            outputStream = openFileOutput(nameEditText.getText().toString().toLowerCase().replace(" ", "_") + ".pet", Context.MODE_PRIVATE);
                            outputStream.write(petInfo.getBytes());
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        updateGame();

                        loadedPetNameText.setText(myPet.getName().replace("_", " "));
                        view = getCurrentFocus();
                        nameYourNewPetText.setVisibility(View.GONE);

                    }
                });
            }
        });

        openOldPetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveButtonWasOpened = false;
                deadPetText.setVisibility(View.GONE);
                createNewPetButton.setVisibility(View.GONE);
                openOldPetButton.setVisibility(View.GONE);
                loadedPetNameText.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                turnOffEverything();
                choosePet();
            }
        });
        mealImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded) {
                    myPet.updateHunger(15, false);
                    myPet.ageUp((int) (currentTimeMillis() / 1000));
                    foodAnimation.stop();
                    foodAnimation.start();
                    animateCreature(foodImg.getX(), foodImg.getY());
                    eatingSound.start();
                    turnOffEverything();
                    updateGame();
                }
            }
        });
        treatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded) {
                    treatAnimation.stop();
                    treatAnimation.start();
                    animateCreature(treatImg.getX(), treatImg.getY());
                    eatingSound.start();
                    myPet.updateHunger(30, true);
                    myPet.updateHealth(-5);
                    myPet.updateHappy(10);
                    turnOffEverything();
                    updateGame();
                }
            }
        });
        waterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded) {
                    myPet.updateThirst(15);
                    myPet.ageUp((int) (currentTimeMillis() / 1000));
                    waterAnimation.stop();
                    waterAnimation.start();
                    animateCreature(waterImg.getX(), waterImg.getY());
                    drinkingSound.start();
                    turnOffEverything();
                    updateGame();
                }
            }
        });
        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loaded) {

                    gameOneButton.setVisibility(View.VISIBLE);
                    gameTwoButton.setVisibility(View.VISIBLE);

                    gameOneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gameAttempts = 3;
                            guessANumberText.setVisibility(View.VISIBLE);
                            guessANumberText.setText(getString(R.string.guess_a_number));
                            numberEditText.setVisibility(View.VISIBLE);
                            submitButton.setVisibility(View.VISIBLE);

                            random = 0 + (int) (Math.random() * (0 - 4) + 1);
                            gameOneButton.setVisibility(View.GONE);
                            gameTwoButton.setVisibility(View.GONE);
                            submitButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int guessInt = Integer.parseInt(numberEditText.getText().toString());
                                    if (guessInt == random) {
                                        myPet.play();
                                        gameAttempts = 0;
                                        correctToast.show();
                                    } else {
                                        gameAttempts--;
                                        numberEditText.getText().clear();
                                        nopeToast.show();
                                    }
                                    if (gameAttempts == 0 || gameAttempts == 3) {
                                        view = getCurrentFocus();
                                        if (view != null) {
                                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                        }
                                        guessANumberText.setVisibility(View.GONE);
                                        numberEditText.setVisibility(View.GONE);
                                        submitButton.setVisibility(View.GONE);
                                        updateGame();
                                    }
                                }
                            });
                        }
                    });
                    gameTwoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alreadyGuessedLettersText.setText("");
                            gameOneButton.setVisibility(View.GONE);
                            gameTwoButton.setVisibility(View.GONE);
                            // Words for hangman
                            words = new ArrayList<>();
                            words.add("apple");
                            words.add("butter");
                            words.add("cat");
                            words.add("dog");
                            words.add("elephant");
                            words.add("future");
                            words.add("ghost");
                            words.add("history");
                            words.add("icing");
                            words.add("jump");
                            words.add("kill");
                            words.add("little");
                            words.add("moth");
                            words.add("naughty");
                            words.add("octopus");
                            words.add("peanut");
                            words.add("quit");
                            words.add("race");
                            words.add("simple");
                            words.add("terrible");
                            words.add("unbeatable");
                            words.add("very");
                            words.add("wild");
                            words.add("xenoblast");
                            words.add("yoda");
                            words.add("zap");

                            // Words that were obfuscated
                            lettersHidden = new ArrayList<>();

                            // Letters Guessed
                            guessedLettersArr = new ArrayList<>();

                            // Picking a random word from words
                            random = abs((int) (Math.random() * words.size()) - 1);
                            word = words.get(random);

                            // Finding letters to obfuscate
                            for (int i = 0; i < 3; i++) {
                                int rando = abs((int) (Math.random() * word.length()) - 1);
                                Character randomLetter = word.charAt(rando);
                                if (!lettersHidden.contains(randomLetter)) {
                                    lettersHidden.add(randomLetter);
                                }
                            }

                            submitButton.setVisibility(View.VISIBLE);
                            letterEditText.setVisibility(View.VISIBLE);
                            guessALetterText.setVisibility(View.VISIBLE);
                            guessALetterText.setText(getString(R.string.guess_a_letter));

                            // Creating an obfuscated word... obfuscate.
                            hiddenWordString = wordChange(word, lettersHidden);

                            alreadyGuessedLettersText.setVisibility(View.VISIBLE);
                            hiddenWordText.setVisibility(View.VISIBLE);
                            hiddenWordText.setText(hiddenWordString);
                            gameAttempts = 6;

                            submitButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (letterEditText.getText().length() > 0)
                                        characterGuess = letterEditText.getText().charAt(0);
                                    else
                                        characterGuess = ' ';

                                    letterEditText.getText().clear();
                                    if (lettersHidden.contains(characterGuess)) {
                                        lettersHidden.remove(characterGuess);
                                        hiddenWordString = wordChange(word, lettersHidden);
                                        hiddenWordText.setText(hiddenWordString);
                                        correctToast.show();
                                    } else {
                                        guessedLettersArr.add(characterGuess);
                                        gameAttempts--;
                                        nopeToast.show();
                                    }

                                    alreadyGuessedLettersText.setText(wrongGuesses(guessedLettersArr));

                                    if (lettersHidden.isEmpty() || lettersHidden.size() == 0) {
                                        view = getCurrentFocus();
                                        if (view != null) {
                                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                        }
                                        alreadyGuessedLettersText.setVisibility(View.GONE);
                                        lettersHidden.removeAll(lettersHidden);
                                        guessedLettersArr.removeAll(guessedLettersArr);
                                        myPet.play();
                                        correctToast.show();
                                        guessALetterText.setVisibility(View.GONE);
                                        letterEditText.setVisibility(View.GONE);
                                        submitButton.setVisibility(View.GONE);
                                        hiddenWordText.setVisibility(View.GONE);
                                        updateGame();
                                        guessedLettersArr.clear();
                                    } else if (gameAttempts == 0) {
                                        view = getCurrentFocus();
                                        if (view != null) {
                                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                        }
                                        alreadyGuessedLettersText.setVisibility(View.GONE);
                                        lettersHidden.removeAll(lettersHidden);
                                        Log.e("hmm", "ok");
                                        guessedLettersArr.removeAll(guessedLettersArr);
                                        nopeToast.show();
                                        guessALetterText.setVisibility(View.GONE);
                                        letterEditText.setVisibility(View.GONE);
                                        submitButton.setVisibility(View.GONE);
                                        hiddenWordText.setVisibility(View.GONE);
                                        updateGame();
                                        guessedLettersArr.clear();
                                    }
                                }
                            });
                        }
                    });

                    updateGame();
                    animateCreature();
                }
            }
        });
        punishImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded) {
                    myPet.updateHealth(-10);
                    myPet.updateHappy(-10);
                    myPet.updateRandomEvent();
                    turnOffEverything();
                    updateGame();
                    animateCreature();
                }
            }
        });
        shotImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded){
                    myPet.giveShot();
                    turnOffEverything();
                    updateGame();
                    animateCreature();
                }
            }
        });
    }
    @Override
     public void onPause() {
        super.onPause();
        save();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        timeKeeper = (int)(currentTimeMillis() / 1000);
        myPet.updateAwayTime(timeKeeper);
        save();
        updateText();
    }

    // Updating Text
    private boolean updateText(){
        ageIntText.setText(String.valueOf(myPet.getAge()));
        hungerIntText.setText(String.valueOf(myPet.getHunger()));
        waterIntText.setText(String.valueOf(myPet.getThirst()));
        happyIntText.setText(String.valueOf(myPet.getHappy()));
        healthIntText.setText(String.valueOf(myPet.gethealth()));
        deathIntText.setText(String.valueOf(myPet.getDeath()));
        int percentage = myPet.getRandomEvent();
        int randomEv = abs((int) (Math.random() * 100) + 1);
        if(randomEv <= percentage){
            eventText = myPet.randomEvent();
            return true;
        }
        return false;
    }

    // Update Game
    private void updateGame(){
        myPet.ageUp((int)(currentTimeMillis() / 1000));
        if(updateText()){
            eventToast = Toast.makeText(context, eventText, LONGduration);
            eventToast.show();
        }
        handlingDeath();
        save();
    }

    // Save pet
    private void save(){
        String petInfo = myPet.getName() + " " + String.valueOf(myPet.getAge()) + " " + String.valueOf(myPet.getHunger()) + " " +
                String.valueOf(myPet.getThirst()) + " " + String.valueOf(myPet.gethealth()) + " " + String.valueOf(myPet.getDeath()) + " " +
                String.valueOf(myPet.gethealth()) + " " + String.valueOf(myPet.getAgeTime()) + " " +
                String.valueOf((int)(currentTimeMillis() / 1000) + " " + myPet.getRandomEvent() + " " + myPet.getCreature());

        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(myPet.getName() + ".pet", Context.MODE_PRIVATE);
            outputStream.write(petInfo.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handling Death
    private void handlingDeath(){
        if(myPet.isDead()){
            deadPetText.setVisibility(View.VISIBLE);
            openOldPetButton.setVisibility(View.VISIBLE);
            createNewPetButton.setVisibility(View.VISIBLE);
        }
    }
    private String wordChange(String s, ArrayList<Character> a){
        String hidden = s;
        for(int i = 0; i < a.size(); i++){
            hidden = hidden.replace(a.get(i), '_');
        }
        return hidden;
    }

    private String wrongGuesses(ArrayList<Character> a){
        String guesses = "";
        for(int i = 0; i < a.size(); i++){
            guesses += a.get(i);
        }
        return guesses;
    }

    private void animateCreature(){
        int randomX = ThreadLocalRandom.current().nextInt(300, width - 300);
        int randomY = ThreadLocalRandom.current().nextInt(300, height - 300);
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", randomX);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", randomY);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }

    private void animateCreature(float newX, float newY){
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationX", newX - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
        creatureAnimator = ObjectAnimator.ofFloat(creatureImage, "translationY", newY - 50);
        creatureAnimator.setDuration(2000);
        creatureAnimator.start();
    }

    private void choosePet(){
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
                            myPet = new Pet(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                                    Integer.parseInt(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]),
                                    Integer.parseInt(words[7]), Integer.parseInt(words[8]), Integer.parseInt(words[9]), words[10]);
                            myPet.updateAwayTime((int)(currentTimeMillis() / 1000));
                            resourceID = getResources().getIdentifier(words[10], "drawable", getPackageName());

                            creatureImage.setBackgroundResource(resourceID);
                            creatureImage.setVisibility(View.VISIBLE);

                            updateGame();
                            save();
                            loaded = true;

                            openOldPetButton.setVisibility(View.GONE);
                            createNewPetButton.setVisibility(View.GONE);
                            linearLayout.removeAllViewsInLayout();
                            loadedPetNameText.setText(myPet.getName().replace("_", " "));
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

    // On Touch listener
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

    // Settings page
    private void settings(){
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
                        soundORnot = Boolean .parseBoolean(line);
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
                    soundORnot = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        changeSound();
    }

    // Sound handler
    private void changeSound(){
        if(soundORnot) {
            catpurrSound.setVolume(1.0f,1.0f);
            drinkingSound.setVolume(0.4f,0.4f);
            eatingSound.setVolume(0.4f,0.4f);
            soundImageButton.setBackgroundResource(R.drawable.musicbutton);
            FileOutputStream outputStream;
            try{
                outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                outputStream.write("true".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            soundORnot = false;
        } else {
            catpurrSound.setVolume(0.0f,0.0f);
            drinkingSound.setVolume(0.0f,0.0f);
            eatingSound.setVolume(0.0f,0.0f);
            soundImageButton.setBackgroundResource(R.drawable.nomusicbutton);
            FileOutputStream outputStream;
            try{
                outputStream = openFileOutput("settings", Context.MODE_PRIVATE);
                outputStream.write("false".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            soundORnot = true;
        }
    }

    private void turnOffEverything(){
        gameOneButton.setVisibility(View.GONE);
        gameTwoButton.setVisibility(View.GONE);
        nameYourNewPetText.setVisibility(View.GONE);
        nameEditText.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        nameYourNewPetText.setVisibility(View.GONE);
    }
}
