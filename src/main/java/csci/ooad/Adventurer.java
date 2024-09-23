package csci.ooad;


public class Adventurer extends Character {
    /**
     * Constructor for Adventurer objects, calls constructor of Creature superclass
     * @param adventurerName - name of adventurer
     */
    public Adventurer(String name) {
        super(name);
    }

    // Method to increase health
    public void eat(Food foodItem) {
        int healAmount = foodItem.getHealthGranted();
        double characterHealth = this.getHealth();
        characterHealth += healAmount;
        this.setHealth(characterHealth);
    }

}
