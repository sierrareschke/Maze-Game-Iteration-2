package csci.ooad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Room {

    /* *
     *  FIELDS
     * */

    private String name;
    private List<Character> occupants;
    private List<Food> foods;


    /* *
     *  CONSTRUCTORS
     * */

    /**
     * Default constructor, initializes name & occupants to default values
     */
    public Room() {
        this.name = "";
        this.occupants = new ArrayList<>(); // Initializes with an empty array
    }


    /**
     * Room constructor with a room name. Initializes the room name to name & occupants to default values
     * @param name - name of Room
     */
    public Room(String name){
        this.name = name;
        this.occupants = new ArrayList<>(); // Initializes with an empty a
    }


    /* *
     *  METHODS
     * */

    /* GETTERS & SETTERS (w/ some other basic methods) */

        // Name & Index

    public String getName() { return this.name; }


        // Occupants

    public List<Character>getOccupants(){
        return this.occupants;
    }

    public void addOccupant(Character occupant) { occupants.add(occupant); }

    public void removeOccupant(Character occupant) { occupants.remove(occupant); }

    public void emptyRoom() {
        occupants.clear();
    }

        // Foods

    public List<Food> getFood() {
        return foods;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }



    /* COMPLEX METHODS */

    // TODO - getHealthiestAdventurer

    // TODO - getHealthiestCreature


    public Boolean isFoodPresent () {
        if(foods == null) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean isAdventurerPresent () {
        boolean isPresent = false;
        for (Character occupant : occupants) {
            if(occupant instanceof Adventurer) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public List<Adventurer> getAdventurers() {
        return occupants.stream()
                .filter(character -> character instanceof Adventurer) // Filter for Adventurer instances
                .map(character -> (Adventurer) character)             // Cast to Adventurer
                .sorted((a1, a2) -> Double.compare(a1.getHealth(), a2.getHealth())) // Sort by health
                .collect(Collectors.toList()); // Collect the results into a List
    }

    public Boolean isCreaturePresent () {
        boolean isPresent = false;
        for (Character occupant : occupants) {
            if(occupant instanceof Creature) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public List<Creature> getCreatures() {
        return occupants.stream()
                .filter(character -> character instanceof Creature) // Filter for Creature instances
                .map(character -> (Creature) character)             // Cast to Creature
                .sorted((a1, a2) -> Double.compare(a1.getHealth(), a2.getHealth())) // Sort by health
                .collect(Collectors.toList()); // Collect the results into a List
    }





    /**
     * Method to remove an Adventurer occupant from a Room
     * @param occupant - Adventurer to remove from Room
     * @return the Adventurer that was removed
     */
    public Character removeCharacter(Character occupant) {
        // Check if the occupant is in the room
        boolean found = false;
        for (Character character : occupants) {
            if (character instanceof Adventurer && character.equals(occupant)) {
                found = true;
                break;
            }
        }

        if (!found) {
            return null; // Adventurer not found
        }

        // Remove the adventurer from the list of occupants
        occupants.remove(occupant);

        // Return the removed adventurer
        return occupant;
    }

    public boolean hasCharacter(Character character) {
        boolean characterFound = false;
        for (Character occupant : occupants) {
            if (occupant.getName() == character.getName()) {
                characterFound = true;
                break;
            }
        }
        return characterFound;
    }








    /**
     * toString method of the Room to print out its occupants
     * @return String version of the room
     *
     * Ex. "Northwest: No occupants are here."
     * Ex. "Southeast: Adventurer testAdventurer is here."
     */
    @Override
    public String toString() {
        String result = name + ":\n";

        // If the room is empty
        if (occupants.isEmpty()) {
            result += "No occupants are here.";
        } else {
            // Add information about each occupant
            for (Character occupant : occupants) {
                String occupantType = (occupant instanceof Adventurer) ? "Adventurer" : "Creature";
                result += occupantType + " " + occupant + " is here. ";
            }
        }

        return result;
    }

}
