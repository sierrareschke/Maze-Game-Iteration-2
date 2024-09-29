package csci.ooad;

import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polymorphia {

    /* *
     *  FIELDS
     * */

    private static final Logger logger = LoggerFactory.getLogger(Polymorphia.class);

    private int turnCount;
    private Dice dice;
    private Maze maze;

    /**
     * ------------- EXAMPLE OF ENCAPSULATION -------------
     *  The polymorphism class uses a Dice without being able to alter its state.
     *  For example, polymorphism can't set the die value to be 4. It has
     *  to roll the dice in order to get a value. That is encapsulation and its
     *  important to have since we don't want to allow essentially "cheating" or
     *  manipulation of the dice.
     */

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

        logger.info("Starting play...");

        // Print the initial state of the game
        printMaze();

        // While both adventurers and creatures alive in Maze, take turns
        int numAdventurersAlive = maze.getNumAdventurers();
        int numCreaturesAlive = maze.getNumCreatures();

        logger.info("numAdventurersAlive" + numAdventurersAlive);


        while (maze.getNumAdventurers() > 0 && maze.getNumCreatures() > 0) {
            takeTurn();
        }

        // Get updated counts after the game ends
        int finalAdventurersAlive = maze.getNumAdventurers();
        int finalCreaturesAlive = maze.getNumCreatures();

        // The game has ended and a winner is determined by Characters left
        determineWinner(finalCreaturesAlive, finalAdventurersAlive);

    }


    public int determineWinner(int numCreaturesAlive, int numAdventurersAlive) {
        // RESULT #1 : All Adventures & Creatures have died, no winner
        if(numAdventurersAlive == 0 && numCreaturesAlive == 0) {
            logger.info("All adventurers & creatures have died, no winner!");
            return 0;
        }
        // RESULT #2 : Adventurers have killed all of the Creatures
        else if (numAdventurersAlive > 0 && numCreaturesAlive <= 0) {
            logger.info("Yay, the Adventurers won!");
            return 1;
        }
        // RESULT #3 : Creatures have killed all of the Adventurers
        else if (numCreaturesAlive > 0 && numAdventurersAlive <= 0) {
            logger.info("Boo, the Creatures won!");
            return 2;
        } else {
            throw new IllegalStateException("Unexpected state: unable to determine a winner");
        }
    }


    /**
     * ------------- EXAMPLE OF COHESION -------------
     *  takeTurn is an example of cohesion because it is responsible for all functionallities
     *  related to one turn of the game. It is separate for other concerns of game play and
     *  handles logic in an organized way.
     */

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
            List<Adventurer> adventurersPresent = room.getAdventurers();
            int numAdventurersPresent = adventurersPresent.size();

            // NO -> No action needed, move on to next room
            if (numAdventurersPresent == 0) {
                continue;
            }

            // YES -> Action needed, continue to eval state of room

            // Check #2: Are there any creatures present?
            List<Creature> creaturesPresent = room.getCreatures();
            int numCreaturesPresent = creaturesPresent.size();


            // YES -> A fight will take place between the healthiest adventurer and creature, plus other adventurer moves
            if (numCreaturesPresent > 0) {
                Adventurer healthiestAdventurer = adventurersPresent.get(0);
                Creature healthiestCreature = creaturesPresent.get(0);

                fight(healthiestAdventurer, healthiestCreature);
                logger.info("Adventurer " + healthiestAdventurer.getName() + "(health: " + healthiestAdventurer.getHealth() + ") " +
                        "fought Creature " + healthiestCreature.getName() + " (health: " + healthiestCreature.getHealth() + ")" + "\n");

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
                        adventurer.eatFood(food, maze);
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
            List<Adventurer> allAdventures = maze.getAllAdventurers();
            for(Adventurer adventurer : allAdventures){
                adventurer.move(maze);
                // Note: Don't subtract .25 since homework example didn't do this
                //adventurer.subtractFromHealth(0.25);
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

        logger.info("Polymorphia Maze: turn " + turnCount + "\n");
        logger.info(maze.toString()); // TODO
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
            logger.info("Fight is a tie! ");
        } else if (adventurerRoll > creatureRoll) { // adventurer wins, subtract the difference from the creature's health
            int damage = adventurerRoll - creatureRoll;
            creature.subtractFromHealth(damage);  // take damage
            if(!creature.isAlive()) {
                kill(creature);
            }
            logger.info("Adventurer wins the round. Creature takes " + damage + " damage. ");
        } else { // creature wins, subtract the difference from the adventurer's health
            int damage = creatureRoll - adventurerRoll;
            adventurer.subtractFromHealth(damage);  // take damage
            if(!adventurer.isAlive()) {
                kill(adventurer);
            }
            logger.info("Creature wins the round. Adventurer takes " + damage + " damage. ");
        }
    }



    /**
     * ------------- EXAMPLE OF POLYMORPHISM -------------
     *  kill() is an example of polymorphism because it treats both Adventurer and
     *  Creature as an instance of Character since they both are subclasses.
     */

    /**
     * ------------- EXAMPLE OF INHERITANCE -------------
     *  kill is also an example of inheritance because neither Adventurer nor
     *  Creature have toString defined in their class. toString is defined in
     *  Character, but it's able to be used in kill because they inherit the
     *  method from their superclass.
     */

    public void kill (Character characterToDie) {
        // Remove them from maze
        maze.purge(characterToDie);

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
