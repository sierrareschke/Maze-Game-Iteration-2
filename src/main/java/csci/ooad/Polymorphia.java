package csci.ooad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polymorphia {

    /* *
     *  FIELDS
     * */

    private static final Logger logger = LoggerFactory.getLogger(Character.class);

    private static final String[] CREATURE_TYPES = {"Ogre", "Goblin", "Troll", "Werewolf", "Vampire", "Gnome", "Zombie"};
    private int turnCount;
    private Dice dice;
    private Maze maze;

    /* *
     *  CONSTRUCTORS
     * */

    public Polymorphia(Maze maze) {
        this.maze = maze;
        this.turnCount = 0;
        dice = new Dice();
    }


    /* *
     *  METHODS
     * */

    /* GETTERS & SETTERS */


    /* COMPLEX METHODS  */


    /**
     * beginGame: method to start the game (call maze constructor, ask for user input for adventurer name,
     * randomly selects type of creature, creates creature and adventure objects, and randomly places each
     * in one of the 4 rooms). Calls the takeTurn method until a player dies (health = 0)
     */
    public void playGame() {

        // Print the initial state of the game
        printMaze();

        // While both adventurers and creatures alive in Maze, take turns
        int numAdventurersAlive = maze.getAdventurers().size(); // TODO - REPLACE (SIERRA)
        int numCreaturesAlive = maze.getCreatures().size(); // TODO - REPLACE (SIERRA)

        while (numAdventurersAlive > 0 && numCreaturesAlive > 0) {
            takeTurn();
        }

        // The game has ended and a winner is determined by Characters left

        // RESULT #1 : All Adventures & Creatures have died, no winner
        if(numAdventurersAlive <= 0 && numCreaturesAlive <= 0) {
            logger.info("All adventurers & creatures have died, no winner!");
        }
        // RESULT #2 : Adventurers have killed all of the Creatures
        else if (numAdventurersAlive > 0) {
            logger.info("Yay, the Adventurers won!");
        }
        // RESULT #3 : Creatures have killed all of the Adventurers
        else if (numCreaturesAlive > 0) {
            logger.info("Boo, the Creatures won!");
        } else {
            throw new IllegalStateException("Unexpected state: unable to determine a winner");
        }


    }



    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creatures and adventurers
     * and calls the fight method if they creature and adventurer are in the same room.
     * If only adventurer(s) and food (no creature), adventurers eat food while available.
     * Otherwise, moves the adventurer to a random neighboring room
     * Increments turnCount
     */
    public void takeTurn() {

        turnCount++; // increment turn count
        printMaze();  // Print current state of the maze

        // get states of room using maze class
        ArrayList<Room> rooms = maze.getRooms();



        ArrayList<Adventurer> adventurersToMove = new ArrayList<>();

        for (Room room : rooms) {

            // Check #1: Are there any adventures present?
            List<Adventurer> adventurersPresent = room.getAdventurers(); // TODO - MAKE SURE RETURNS IN SORTED ORDER (SIERRA in RoomTest)
            int numAdventurersPresent = adventurersPresent.size();

            // NO -> No action needed, move on to next room
            if (numAdventurersPresent == 0) {
                continue;
            }

            // YES -> Action needed, continue to eval state of room

            // Check #2: Are there any creatures present?
            List<Creature> creaturesPresent = room.getCreatures(); // TODO - MAKE SURE RETURNS IN SORTED ORDER
            int numCreaturesPresent = creaturesPresent.size();


            // YES -> A fight will take place between the healthiest adventurer and creature, plus other adventurer moves
            if (numCreaturesPresent > 0) {
                Adventurer healthiestAdventurer = adventurersPresent.get(0);
                Creature healthiestCreature = creaturesPresent.get(0);

                fight(healthiestAdventurer, healthiestCreature);
                logger.info("Adventurer " + healthiestAdventurer.getName() + "(health: " + healthiestAdventurer.getHealth() + ") " +
                        "fought Creature" + healthiestCreature.getName() + " (health: " + healthiestCreature.getHealth() + ")");

                // if there is a second adventurer, move it
                if (numAdventurersPresent == 2) {
                    Adventurer weakerAdventurer = adventurersPresent.get(0);
                    adventurersToMove.add(weakerAdventurer);
                }
                continue;
            }

            // NO -> Adventurer(s) don't need to fight, so they may get food

            // Check #3: Is there food present?
            List<Food> foodPresent = room.getFood();
            int numFoodPresent = foodPresent.size();

            // YES -> Adventurers eat one food item each in order of healthiest to least health
            if (numFoodPresent > 0) {

                // Sort adventurers by health from highest to lowest
                adventurersPresent.sort(Comparator.comparingDouble(Adventurer::getHealth).reversed());

                // Each adventurer eats one food item if available
                for (Adventurer adventurer : adventurersPresent) {
                    if (!foodPresent.isEmpty()) {
                        Food food = foodPresent.remove(0); // Get the first available food
                        adventurer.eatFood(food);
                    } else {
                        logger.info(adventurer.getName() + " has no food to eat.");
                    }
                }
                continue; // Note: adventures stay in room after eating in this scenario
            }

            // NO -> All adventurers must move to a neighboring room

            // Move adventurers while there are still adventurers to move
            adventurersToMove.addAll(adventurersPresent);
        }

        // After iterating through Rooms in Maze, move Adventurers to neighbors
        if(adventurersToMove != null) {
            for(Adventurer adventurer : adventurersToMove){
                adventurer.move(maze);
            }
            adventurersToMove.clear();
        }
    }



    /**
     * printMaze: prints the maze in the following (example) format:
     * Polymorphia MAZE: turn 1
     * ( MAZE.toString - see Maze class for details )
     */
    public void printMaze() {

        logger.info("Polymorphia Maze: turn " + turnCount);
        logger.info(maze.toString());

    }





    /**
     * fight: calls Character.rollDie() for Creature and Adventurer. Character with lower roll takes damage
     * equal to the difference in the rolls.
     */
    public void fight(Adventurer adventurer, Creature creature) {

        // adventurer and creature both roll a die
        int adventurerRoll = dice.rollDice();
        int creatureRoll = dice.rollDice();

        // compare the rolls, character with lower roll will take damage equal to the difference between the rolls
        // if both rolls are the same, neither character takes damage
        if (adventurerRoll == creatureRoll) { // if both rolls are the same, nothing happens
            logger.info("Fight is a tie!");
        } else if (adventurerRoll > creatureRoll) { // adventurer wins, subtract the difference from the creature's health
            int damage = adventurerRoll - creatureRoll;
            creature.subtractFromHealth(damage);  // take damage
            if(creature.getHealth() < 0) {// TODO - ISALIVE METHOD ??? (DO THIS LAST)
                kill(creature);
            }
            logger.info("Adventurer wins the round. Creature takes " + damage + " damage.");
        } else { // creature wins, subtract the difference from the adventurer's health
            int damage = creatureRoll - adventurerRoll;
            adventurer.subtractFromHealth(damage);  // take damage
            if(adventurer.getHealth() < 0) {
                kill(adventurer);
            }
            logger.info("Creature wins the round. Adventurer takes " + damage + " damage.");
        }
    }



    public void kill (Character characterToDie) {
        // Remove them from maze
        //maze.removeCharacter(characterToDie); // TODO - IMPLEMENT IN MAZE & UNCOMMENT (COME BACK)

        // Remove from Polymorphia field
        if(characterToDie instanceof Adventurer) {
            logger.info("Adventurer " + characterToDie.toString() + " was killed.");
        }else if(characterToDie instanceof Creature) {
            logger.info("Creature " + characterToDie.toString() + " was killed.");
        } else {
            throw new IllegalStateException("Should be no instance of Character, cannot kill.");
        }

    }



}
