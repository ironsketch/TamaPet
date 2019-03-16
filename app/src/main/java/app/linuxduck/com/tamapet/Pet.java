package app.linuxduck.com.tamapet;

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

    public String getName(){return name;}
    public int getAge(){return age;}
    public int getHunger(){return hunger;}
    public int getThirst(){return thirst;}
    public int gethealth(){return health;}
    public int getDeath(){return death;}
    public int getHappy(){return happy;}

    public Pet(String newName, int newAge, int newHunger, int newThirst, int newHealth,
               int newDeath, int newHappy, int newAgeTime, int newAwayTime){
        name = newName;
        age = newAge;
        hunger = newHunger;
        thirst = newThirst;
        health = newHealth;
        death = newDeath;
        happy = newHappy;
        ageTime = newAgeTime;
        awayTime = newAwayTime;
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
        if((time - awayTime) / 3600 > 0){
            int hoursAway = (time - awayTime) / 3600;
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
}
