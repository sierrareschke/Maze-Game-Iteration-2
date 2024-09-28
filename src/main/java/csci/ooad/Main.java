//package csci.ooad;
//
//import java.util.ArrayList;
//
//public class Main {
//
//
//    public static void main(String[] args) {
//
//        ArrayList<Adventurer> adventurers = new ArrayList<>();
//        adventurers.add(new Adventurer("Bill"));
//        adventurers.add(new Adventurer("Ted"));
//
//
//        ArrayList<Creature> creatures = new ArrayList<>();
//        creatures.add(new Creature("Ogre"));
//        creatures.add(new Creature("Troll"));
//        creatures.add(new Creature("Werewolf"));
//        creatures.add(new Creature("Vampire"));
//        creatures.add(new Creature("Zombie"));
//
//        ArrayList<Food> foods = new ArrayList<>();
//        foods.add(new Food("Apple"));
//        foods.add(new Food("Bread"));
//        foods.add(new Food("Carrot"));
//        foods.add(new Food("Pork"));
//        foods.add(new Food("Steak"));
//        foods.add(new Food("Chicken"));
//        foods.add(new Food("Pumpkin Pie"));
//        foods.add(new Food("Cake"));
//        foods.add(new Food("Mango"));
//        foods.add(new Food("Pear"));
//
//        ArrayList<Room> rooms = new ArrayList<>();
//        Room roomOne = new Room("Room One");
//        Room roomTwo = new Room("Room Two");
//        Room roomThree = new Room("Room Three");
//        Room roomFour = new Room("Room Four");
//        Room roomFive = new Room("Room Five");
//        Room roomSix = new Room("Room Six");
//        Room roomSeven = new Room("Room Seven");
//        Room roomEight = new Room("Room Eight");
//        Room roomNine = new Room("Room Nine");
//
//        rooms.add(roomOne);
//        rooms.add(roomTwo);
//        rooms.add(roomThree);
//        rooms.add(roomFour);
//        rooms.add(roomFive);
//        rooms.add(roomSix);
//        rooms.add(roomSeven);
//        rooms.add(roomEight);
//        rooms.add(roomNine);
//
//        Maze maze = new Maze(rooms,adventurers,creatures,foods);
//
//        Polymorphia poly = new Polymorphia(maze);
//
//        poly.playGame();
//
//    }
//}
