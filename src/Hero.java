
class Hero {
    int health, str, intel, def, mana, lvl, death, exp, manadrain, gold, accuracy;
    String name;
    boolean stun, isMage, isWarrior;
    Inventory inventory;
    Floor floor;

    public Hero(String name, int health, int str, int intel, int def, int mana, int lvl,
                int death, int manadrain, int gold, int accuracy, boolean stun, boolean isWarrior, boolean isMage) {
        this.name = name;
        this.health = health;
        this.str = str;
        this.intel = intel;
        this.def = def;
        this.mana = mana;
        this.lvl = lvl;
        this.death = death;
        this.manadrain = manadrain;
        this.gold = gold;
        this.accuracy = accuracy;
        this.stun = stun;
        this.isWarrior = isWarrior;
        this.isMage = isMage;
        this.inventory = new Inventory();
        this.floor = new Floor();
    }
    public void printCharacter() {
        System.out.println("\nDeine Klasse ist: Magier\nDein Name ist: " + this.name + "\nGesundheit: " + this.health +
                "\nStärke: " + this.str + "\nIntelligenz: " + this.intel + "\nVerteidigung: " + this.def +
                "\nGenauigkeit: " + this.accuracy + "\nMana: " + this.mana + "\nStufe: " + this.lvl + "\nGold: " + this.gold +"\n\n");
    }
    public static void lvl(Hero player){

        ++player.lvl;
        player.health += 10;
        player.death -= 10;
        if(player.isMage)
            ++player.intel;
        if(player.isWarrior)
            ++player.str;
        System.out.print("Stufe gestiegen!");
        System.out.print("\nGesundheit: " + player.health + "\nStärke: " + player.str + "\nIntelligenz: " + player.intel + "\nDu wurdest um 10 Gesundheit geheilt\n");
        player.exp -= 10;
    }
}
