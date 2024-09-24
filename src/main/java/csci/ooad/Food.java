package csci.ooad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Food {
    private String name;
    private int healthGranted;


    public Food() {
        // Generate random food nameddfdfdd
        List<String> foodNameOptions = new ArrayList<>();
        foodNameOptions.add("Bread");
        foodNameOptions.add("Cake");
        foodNameOptions.add("Chicken");
        foodNameOptions.add("Fish");
        foodNameOptions.add("Steak");

        // Set food name
        Random random = new Random();
        int randomIndex = random.nextInt(foodNameOptions.size());
        this.name = foodNameOptions.get(randomIndex);

        // Set healthGranted to 1 (as defined by assignment
        this.healthGranted = 1;
    }

    public Food(String name) {
        this.name = name;
        this.healthGranted = 1;
    }

    public String getName() {
        return this.name;
    }

    public int getHealthGranted() {
        return this.healthGranted;
    }
}
