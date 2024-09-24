package csci.ooad;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Polymorphia {

    private static final String[] CREATURE_TYPES = {"Ogre", "Goblin", "Troll", "Werewolf", "Vampire", "Gnome", "Zombie"};
    private int turnCount;
    private Creature[] creatures;
    private Adventurer[] adventurers;
    private Room[] rooms;
    private Dice dice;
    private Maze maze; // TOOD
    private Character winner; // TODO - delete ??
    // TODO
    private boolean atLeastOneAdventurer; // there is at least one adventurer alive
    private boolean atLeastOneCreature; // there is at least one creature alive



     // Constructor to create the maze with 4 rooms and set turn count to 0
    public Polymorphia() {
        this.turnCount = 0;
        createRooms();
        dice = new Dice();
        winner = null;
    }

    /**
     * Accessor method to get the list of rooms in the maze
     * @return Room[] - array of rooms
     */
    public Room[] getRooms() {
        return rooms;
    }

    public Character getWinner() {
        return winner;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    public Adventurer[] getAdventurers() {
        return adventurers;
    }


    // TODO
    // createRooms: creates 4 rooms (NW, NE, SE, SW)
    private void createRooms() {
        // Initialize the rooms array with 4 rooms (Northwest, Northeast, Southeast, Southwest)
        rooms = new Room[4];

        // Create and assign rooms to the respective positions
        rooms[0] = new Room("Northwest", 0);  // Empty occupants for now
        rooms[1] = new Room("Northeast", 1);
        rooms[2] = new Room("Southeast", 2);
        rooms[3] = new Room("Southwest", 3);
    }

    /**
     * beginGame: method to start the game (call maze constructor, ask for user input for adventurer name,
     * randomly selects type of creature, creates creature and adventure objects, and randomly places each
     * in one of the 4 rooms). Calls the takeTurn method until a player dies (health = 0)
     */
    public void playGame() {
        // Print that the game is starting
        System.out.println("Starting the game...");

        // get user input for adventurer's name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter adventurer's name: ");
        String adventurerName = scanner.nextLine();

        // TODO
        /**
         * randomlyDistributeCharacters(adventurers[], creatures[], maze(??) )
         */
        randomlyDistributeCharacters(adventurerName);

        // set boolean values for adventurer and creature present initially to true
        atLeastOneAdventurer = true;
        atLeastOneCreature = true;

        // Print the initial state of the game
        System.out.println("\nInitial game state:");
        printMaze();

        // begin taking turns
        // continue taking turns until one of the character's health = 0
        System.out.println("\nGame is ready. Adventurer and creature are placed in rooms.");
        System.out.println("Starting turns.");

        // TODO
        // call takeTurn while both creatures are alive
        while (atLeastOneAdventurer && atLeastOneAdventurer) {
            takeTurn(adventurers, creatures); //TODO
        }

        // TODO
        /**
         * // getting here means either all adventurers or all creatures are dead
         * // both dead:
         * if (!atLeastOneAdventurer && !atLeastOneAdventurer) {}
         * // creature(s) wins:
         * else if (!atLeastOneAdventurer) {}
         * // adventurer(s) wins:
         * else if (!atLeastOneCreature) {}
         *
         * need to update how winner is stored (can't be a Creature)
         */
        // getting here means one (or both) of the characters has died --> end game
        // nobody wins if both creature and adventurer's health is <= 0
        if (creature.getHealth() <= 0 && adventurer.getHealth() <= 0) {
            System.out.println("Both players die. Nobody wins. Game over.\n");
        }
        // otherwise, the creature wins if the adventurer's health is <= 0
        else if (adventurer.getHealth() <= 0) {
            winner = creature;
            System.out.println("Boo, the creature won. Game over.\n");
        }
        // if the creature's health is <= 0, the adventurer wins
        else {
            winner = adventurer;
            System.out.println("Adventurer " + adventurer.getName() + " has defeated the creature! Game over");
        }

        System.out.println("Exited beginGame method.\n");

    }

    // TODO

    /**
     * adjust to take in args for each character, random room
     * no println
     */

    public void randomlyDistributeCharacters(String adventurerName) {
        System.out.println("Adventurer's name is: " + adventurerName);

        // randomly select a creature type
        Random random = new Random();
        String randomCreatureType = CREATURE_TYPES[random.nextInt(CREATURE_TYPES.length)];
        System.out.println("Randomly selected creature: " + randomCreatureType);

        // create Creature and Adventurer objects
        creature = new Creature(randomCreatureType);
        adventurer = new Adventurer(adventurerName);
        System.out.println("Created adventurer: " + adventurer);
        System.out.println("Created creature: " + creature);

        // Randomly place each character in one of the rooms (they can be placed in the same room)
        int randomRoomIndexForAdventurer = random.nextInt(rooms.length); // Random room for adventurer
        int randomRoomIndexForCreature = random.nextInt(rooms.length);   // Random room for creature

        // Add adventure and creature to respective random rooms
        rooms[randomRoomIndexForAdventurer].addOccupant(adventurer);
        rooms[randomRoomIndexForCreature].addOccupant(creature);

        // Report placement of adventurer and creature
        System.out.println("Adventurer placed in: " + rooms[randomRoomIndexForAdventurer].getName());
        System.out.println("Creature placed in: " + rooms[randomRoomIndexForCreature].getName());
    }

    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creatures and adventurers
     * and calls the fight method if they creature and adventurer are in the same room.
     * If only adventurer(s) and food (no creature), adventurers eat food while available.
     * Otherwise, moves the adventurer to a random neighboring room
     * Increments turnCount
     */
    public void takeTurn(Adventurer[] adventurers, Creature[] creatures) {

        turnCount++; // increment turn count
        printMaze();  // Print current state of the maze


        // TODO
        for (Room room : rooms) {
            List<Adventurer> adventurersPresent = room.getAdventurers();
            List<Creature> creaturesPresent = room.getCreatures();
            List<Food> foodPresent = room.getFood();

            int numAdventurersPresent = adventurersPresent.size();
            int numCreaturesPresent = creaturesPresent.size();
            int numFoodPresent = foodPresent.size();

            if (numAdventurersPresent == 0) {
                continue;
            }

            // check if there is at least creature in the room
            if (numCreaturesPresent > 0) {
                Adventurer healthiestAdventurer = adventurersPresent.get(0);
                Creature healthiestCreature = creaturesPresent.get(0);

                fight(healthiestAdventurer, healthiestCreature);
                System.out.println("Adventurer " + healthiestAdventurer.getName() + "(health: " + healthiestAdventurer.getHealth() + ") " +
                        "fought Creature" + healthiestCreature.getName() + " (health: " + healthiestCreature.getHealth() + ")");

                // if there is a second adventurer, move it
                if (numAdventurersPresent == 2) {
                    Adventurer weakerAdventurer = adventurersPresent.get(0);
                    moveAdventurer(weakerAdventurer);
                    System.out.println("Adventurer " + weakerAdventurer.getName() + "(health: " + weakerAdventurer.getHealth() + ") moves rooms.");
                }
            }
            // if the room contains only adventurers and food
            else if (numFoodPresent > 0) {
                int currentAdventurerIndex = 0;
                while (numFoodPresent > 0 && currentAdventurerIndex < numAdventurersPresent) {
                    adventurersPresent.get(currentAdventurerIndex).eatFood();
                    foodPresent.remove(0);
                    numFoodPresent--;
                    currentAdventurerIndex++;
                }
            }
            // if the room contains only adventurers and no food or creatures, move adventurers
            else {
                int currentAdventurerIndex = 0;
                // move adventurers while there are still adventurers to move
                System.out.println("Adventurer and creature are not in same room.\n");
                while (currentAdventurerIndex < numAdventurersPresent) {
                    // TODO previousRoom = currentAdventurer.getRoom(); moveAdventurer()
                    // TODO newRoom = currentAdventurer.getRoom();
                    Adventurer currentAdventurer = adventurersPresent.get(currentAdventurerIndex);
                    System.out.println("Adventurer " + currentAdventurer.getName() + "(health: " + currentAdventurer.getHealth() + ") moves rooms.");
                    moveAdventurer(currentAdventurer);
                    currentAdventurerIndex++;
                }
            }
        }
    }


    /**
     * printMaze: prints the maze in the following (example) format:
     * Polymorphia MAZE: turn 1
     * Northwest:
     * Northeast:
     * Creature Ogre(health: 3) is here
     * Southwest:
     * Adventurer Bill(health: 5) is here
     * Southeast:
     */
    public void printMaze() {
        System.out.println();

        String mazeType = ""; // Initialize an empty string for the maze type

        System.out.println("Polymorphia Maze: turn " + turnCount);


        for (Room room : rooms) {
            System.out.println(room.toString()); // print out each room and its occupants
        }
    }


    // TODO

    /**
     * moveAdventurer: moves the adventurer to a random neighboring room
     * neighboring room = roomIndex +- 1
     * ensure index is in range of rooms array
     */
    public void moveAdventurer(Adventurer adventurer) {
        // Get the current room index of the adventurer
        Room roomOfAdventurer = getRoomOfCharacter(adventurer);
        int currentRoomIndex = roomOfAdventurer.getIndex();

        // Randomly decide whether to move to the next room (index + 1) or previous room (index - 1)
        Random random = new Random();
        int direction = random.nextBoolean() ? 1 : -1; // 1 for next, -1 for previous

        // Calculate the new room index using modulus to wrap around
        int newRoomIndex = (currentRoomIndex + direction + rooms.length) % rooms.length;

        // remove the adventurer from the current room, update index, add to new room
        roomOfAdventurer.removeAdventurer(adventurer);
        rooms[newRoomIndex].addOccupant(adventurer);

        // decrement adventurer's health by 0.25
        adventurer.subtractFromHealth(-0.25);

        System.out.println("Adventurer moved to " + rooms[newRoomIndex].getName() + ".");
    }

    /**
     * fight: calls Character.rollDie() for Creature and Adventurer. Character with lower roll takes damage
     * equal to the difference in the rolls.
     */
    public void fight(Adventurer adventurer, Creature creature) {
        System.out.println("The adventurer and the creature are in the same room! A fight breaks out!\n");

        // adventurer and creature both roll a die
        int adventurerRoll = dice.rollDice();
        int creatureRoll = dice.rollDice();

        System.out.println("Adventurer rolls: " + adventurerRoll);
        System.out.println("Creature rolls: " + creatureRoll);

        // compare the rolls, character with lower roll will take damage equal to the difference between the rolls
        // if both rolls are the same, neither character takes damage
        if (adventurerRoll == creatureRoll) { // if both rolls are the same, nothing happens
            System.out.println("Fight is a tie!");
        } else if (adventurerRoll > creatureRoll) { // adventurer wins, subtract the difference from the creature's health
            int damage = adventurerRoll - creatureRoll;
            creature.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Adventurer wins the round. Creature takes " + damage + " damage.");
        } else { // creature wins, subtract the difference from the adventurer's health
            int damage = creatureRoll - adventurerRoll;
            adventurer.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Creature wins the round. Adventurer takes " + damage + " damage.");
        }
    }


    /**
     * Method to find which room a character is in
     *
     * @param character to find which room in
     * @return Room that the character is in
     */
    public Room getRoomOfCharacter(Character character) {
        for (Room room : rooms) {
            for (Character occupant : room.getOccupants()) {
                if (occupant.equals(character)) {
                    return room; // character is an occupant in this room
                }
            }
        }
        return null; // Character not found in any room
    }

    /**
     * Method to clear all rooms of their occupants
     */
    public void clearRooms() {
        for (Room room : rooms) {
            room.emptyRoom();
        }
    }

}
