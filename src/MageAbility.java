import java.util.Random;

public class MageAbility {
    private final Random rand = new Random();
    private final String oom = "Du hast zu wenig Mana, andere Wahl treffen";

    public void abilityMage(int abilityNr, Hero player, Enemy enemy, int weapon) {
        if (!hasEnoughMana(player, abilityNr)) {
            System.out.println(oom);
            return;
        }

        switch (abilityNr) {
            case 1 -> heal(player);
            case 2 -> fireball(player, enemy, weapon);
            case 3 -> { if (player.lvl > 1) iceTouch(player, enemy, weapon); }
            case 4 -> { if (player.lvl >= 2) arcaneMissiles(player, enemy, weapon); }
            case 5 -> { if (player.lvl > 2) iceShield(player, enemy); }
        }
    }

    private boolean hasEnoughMana(Hero player, int cost) {
        return (player.mana - player.manadrain) >= cost;
    }

    private void heal(Hero player) {
        int heal = rand.nextInt(5) + 5 + player.intel / 2;
        player.death = Math.max(0, player.death - heal);
        player.manadrain++;
        System.out.println("Du hast dich um " + heal + " Gesundheit geheilt!");
        printMana(player);
    }

    private void fireball(Hero player, Enemy enemy, int weapon) {
        int dmg = rand.nextInt(15) + 10 + player.intel * 2 + weapon;
        enemy.mobLife -= dmg;
        player.manadrain += 2;
        System.out.println("Dein Feuerball hat " + dmg + " Schaden gemacht!");
        printMobLife(enemy);
        printMana(player);
    }

    private void iceTouch(Hero player, Enemy enemy, int weapon) {
        int dmg = rand.nextInt(6) + player.intel * 2 + weapon;
        enemy.mobLife -= dmg;
        enemy.mobStun++;
        player.manadrain += 3;
        System.out.println("Deine eisige Berührung hat " + dmg + " Schaden gemacht!");
        System.out.println("Der Gegner ist für eine Runde eingefroren!");
        printMobLife(enemy);
        printMana(player);
    }

    private void arcaneMissiles(Hero player, Enemy enemy, int weapon) {
        int hits = rand.nextInt(3) + 1;
        int dmg = (rand.nextInt(3) + 10 + player.intel * 2 + weapon) * hits;
        enemy.mobLife -= dmg;
        player.manadrain += 4;
        System.out.println("Deine arkane Geschosse haben " + hits + " Mal getroffen und " + dmg + " Schaden gemacht!");
        printMobLife(enemy);
        printMana(player);
    }

    private void iceShield(Hero player, Enemy enemy) {
        enemy.mobBlock++;
        player.manadrain += 4;
        System.out.println("Du schaffst ein mächtiges Eisschild, das den nächsten Angriff blockiert!");
        printMana(player);
    }

    private void printMana(Hero player) {
        System.out.println("Du hast jetzt noch " + (player.mana - player.manadrain) + " Mana!");
    }

    private void printMobLife(Enemy enemy) {
        if (enemy.mobLife > 0) {
            System.out.println("Der Gegner hat noch " + enemy.mobLife + " Gesundheit!");
        }
    }
}
