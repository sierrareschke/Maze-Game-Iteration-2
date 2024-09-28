package csci.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodTest {

    @Test
    public void testDefaultConstructor() {
        // Test the default constructor
        Food food = new Food();

        // Valid food names stored in an ArrayList
        List<String> validFoodNames = new ArrayList<>();
        validFoodNames.add("Bread");
        validFoodNames.add("Cake");
        validFoodNames.add("Chicken");
        validFoodNames.add("Fish");
        validFoodNames.add("Steak");

        // Check if the generated name is valid
        assertTrue(validFoodNames.contains(food.getName()), "Food name should be one of the valid options");

        // Check that healthGranted is set to 1
        assertEquals(1, food.getHealthGranted(), "Health granted should be 1");
    }

    @Test
    public void testParameterizedConstructor() {
        // Test the parameterized constructor
        String testName = "Apple";
        Food food = new Food(testName);

        // Verify that the name is correctly assigned
        assertEquals(testName, food.getName(), "Food name should match the given name");

        // Verify that healthGranted is set to 1
        assertEquals(1, food.getHealthGranted(), "Health granted should be 1");
    }

    @Test
    public void testRandomDistribution() {
        // Define the number of iterations for randomness testing
        int iterations = 1000;

        // Create a map to count occurrences of each food name
        Map<String, Integer> foodCount = new HashMap<>();
        foodCount.put("Bread", 0);
        foodCount.put("Cake", 0);
        foodCount.put("Chicken", 0);
        foodCount.put("Fish", 0);
        foodCount.put("Steak", 0);

        // Generate multiple instances of Food and count the occurrences
        for (int i = 0; i < iterations; i++) {
            Food food = new Food();
            foodCount.put(food.getName(), foodCount.get(food.getName()) + 1);
        }

        // Check if each food name appeared at least once using an ArrayList
        List<String> foodNames = new ArrayList<>();
        foodNames.add("Bread");
        foodNames.add("Cake");
        foodNames.add("Chicken");
        foodNames.add("Fish");
        foodNames.add("Steak");

        for (String foodName : foodNames) {
            assertTrue(foodCount.get(foodName) > 0, "Food name '" + foodName + "' should appear at least once");
        }

        // Check if the distribution is reasonably balanced
        int average = iterations / foodNames.size();
        int tolerance = (int) (average * 0.3); // Allow a 30% variance

        for (String foodName : foodNames) {
            int count = foodCount.get(foodName);
            assertTrue(count >= (average - tolerance) && count <= (average + tolerance),
                    "Food name '" + foodName + "' is outside the expected distribution range");
        }
    }

    // TODO - testFoodRemoved

}
