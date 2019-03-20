package app.linuxduck.com.tamapet;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Pet {
    private static final int DEATH_COST = 2;
    private static final int SHOT_COST = 100;
    private String name;
    private int age;
    private int hunger;
    private int thirst;
    private int health;
    private int death;
    private int happy;
    private int ageTime;
    private int awayTime;
    private String creature;
    private ArrayList<String> events;

    private int randomEvent;

    public String getName(){return name;}
    public int getAge(){return age;}
    public int getAgeTime(){return ageTime;}
    public int getHunger(){return hunger;}
    public int getThirst(){return thirst;}
    public int gethealth(){return health;}
    public int getDeath(){return death;}
    public int getHappy(){return happy;}
    public int getRandomEvent(){return randomEvent;}
    public String getCreature(){return creature;}

    public Pet(){}

    public Pet(String newName, int newAge, int newHunger, int newThirst, int newHealth,
               int newDeath, int newHappy, int newAgeTime, int newAwayTime, int newRandomEvent, String newCreature){
        name = newName;
        age = newAge;
        hunger = newHunger;
        thirst = newThirst;
        health = newHealth;
        death = newDeath;
        happy = newHappy;
        ageTime = newAgeTime;
        awayTime = newAwayTime;
        randomEvent = newRandomEvent;
        events = new ArrayList<>();
        creature = newCreature;

        // Fell down stairs 0
        events.add(name + " fell down some stairs! They hurt them selves and feel " +
                "dumb ... happiness (-10) and health (-15).");

        // Received roses 1
        events.add(name + " received mysterious roses! <3 <3 <3 happiness (+15)");

        // Got picked on 2
        events.add(name + " was picked on today and feels bad... happiness (-10)");

        // Got in a fight 3
        events.add(name + " got in a fight with another pet! happiness (-10) and health (-15)");

        // Long weird story 4
        events.add(name + " almost got whacked in the head with a ball. While trying to " +
                "avoid the ball they fell over into grass. This was on top of a steep hill and " + name +
                " tumbled over and over down the hill. happiness(-10)");

        // Cheetoh 5
        events.add(name + " found a cheetoh on the ground and ate it. hunger (+2) health (-2)");

        // New friend 6
        events.add(name + " made a new friend!! <3 <3 happiness (+20)");

        // Destroyed your home 7
        events.add(name + " absolutely and completely destroyed your home. happiness (-15)");

        // Ate bugs 8
        events.add(name + " was found outside chomping on all the bugs they could find. " +
                "Is that a squirrel tail? health (-15)");

        // Stole 9
        events.add(name + " stole candy from the corner store! happiness (-15) and health (-10)");

        // Said mean things 10
        events.add(name + " said really mean things that were totally uncalled for! happiness (-10)");

        // Shinigami 11
        events.add(name + " was visited by a Shinigami. Coolness (+100) but that's not a stat so...");

        // Stubbed toe 12
        events.add(name + " stubbed their toe. Ouch! health (-2)");

    }
    public void updateRandomEvent(){
        randomEvent--;
    }
    public void updateHunger(int calories, boolean treat){
        if(hunger + calories > 100) {
            hunger = 100;
        }
        else if(hunger + calories <= 0){
            hunger = 0;
            updateHealth(-2);
        }
        else{
            if(treat){
                hunger += calories;
                updateHappy(5);
                updateHealth(-2);
            }
            else{
                hunger += calories;
            }
        }
    }

    public void updateThirst(int water){
        if(thirst + water > 100)
            thirst = 100;
        else if(thirst + water <= 0) {
            thirst = 0;
            updateHealth(-2);
        }
        else
            thirst += water;
    }

    public void updateHealth(int heal){
        if(health + heal > 100)
            health = 100;
        else if(health + heal <= 0) {
            health = 0;
            updateDeath();
        }
        else
            health += heal;
    }

    public void updateDeath(){
        if(death + DEATH_COST > 100) {
            death = 100;
        }
        else if(death + DEATH_COST < 0){
            death = 0;
        } else {
            death += DEATH_COST;
        }
    }

    public void updateHappy(int gameCost){
        if(happy + gameCost > 100){
            happy = 100;
        } else if(happy + gameCost <= 0){
            happy = 0;
            updateHealth(-2);
        } else {
            happy += gameCost;
        }
    }

    public void giveShot(){
        updateHealth(SHOT_COST);
    }

    public void updateAwayTime(int time){
        // 15 minutes
        if((time - awayTime) / 900 > 0){
            int hoursAway = (time - awayTime) / 900;
            updateHunger(-hoursAway, false);
            updateThirst(-hoursAway);
            updateHappy(-hoursAway);
        }
        awayTime = time;
    }

    public boolean ageUp(int time){
        if(time - ageTime >= 86400){
            age += (time - ageTime) / 86400;
            ageTime = time;
            return true;
        }
        return false;
    }

    public boolean isDead(){
        if(death >= 100)
            return true;
        return false;
    }

    public void play(){
        updateHappy(20);
    }

    public String randomEvent(){
        randomEvent = abs((int) (Math.random() * events.size()) - 1);
        if(randomEvent == 0){
            updateHappy(-10);
            updateHealth(-15);
        } else if(randomEvent == 1){
            updateHappy(15);
        } else if(randomEvent == 2){
            updateHappy(-10);
        } else if(randomEvent == 3){
            updateHappy(-10);
            updateHealth(-15);
        } else if(randomEvent == 4){
            updateHappy(-10);
        } else if(randomEvent == 5){
            updateHunger(2, false);
            updateHealth(-2);
        } else if(randomEvent == 6){
            updateHappy(20);
        } else if(randomEvent == 7){
            updateHappy(-15);
        } else if(randomEvent == 8){
            updateHealth(-15);
        } else if(randomEvent == 9){
            updateHappy(-15);
            updateHealth(-10);
        } else if(randomEvent == 10){
            updateHappy(-10);
        } else if(randomEvent == 12){
            updateHealth(-2);
        }
        return events.get(randomEvent);
    }
}
