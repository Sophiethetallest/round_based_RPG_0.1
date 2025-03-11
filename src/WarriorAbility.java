import java.util.Random;

public class WarriorAbility {
    private final Random rand = new Random();
    private final String oom = "Du hast zu wenig Energie, andere Wahl treffen";

    public void abilityWarrior(int abilityNr, Hero player, Enemy enemy, int weapon) {
        if (!hasEnoughEnergy(player, abilityNr)) {
            System.out.println(oom);
            return;
        }

        switch (abilityNr) {
            case 1 -> blockAttack(player, enemy);
            case 2 -> heavyStrike(player, enemy, weapon);
            case 3 -> { if (player.lvl > 1) groinKick(player, enemy, weapon); }
            case 4 -> { if (player.lvl >= 2) rapidStrikes(player, enemy, weapon); }
        }
    }

    private boolean hasEnoughEnergy(Hero player, int cost) {
        return (player.mana - player.manadrain) > cost;
    }

    private void blockAttack(Hero player, Enemy enemy) {
        enemy.mobBlock++;
        player.manadrain++;
        System.out.println("Du blockst den nächsten Angriff!");
        printEnergy(player);
    }

    private void heavyStrike(Hero player, Enemy enemy, int weapon) {
        int dmg = rand.nextInt(5) + 10 + player.str * 2 + weapon;
        enemy.mobLife -= dmg;
        player.manadrain += 2;
        System.out.println("Dein harter Schlag hat " + dmg + " Schaden gemacht!");
        printMobLife(enemy);
        printEnergy(player);
    }

    private void groinKick(Hero player, Enemy enemy, int weapon) {
        int dmg = rand.nextInt(6) + player.str * 2 + weapon;
        enemy.mobLife -= dmg;
        enemy.mobStun++;
        player.manadrain += 3;
        System.out.println("Dein Tritt in die Eier hat " + dmg + " Schaden gemacht! Der " + enemy.name + " krümmt sich am Boden vor Schmerz!");
        System.out.println("Der Gegner ist außerdem für eine Runde betäubt!");
        printMobLife(enemy);
        printEnergy(player);
    }

    private void rapidStrikes(Hero player, Enemy enemy, int weapon) {
        int hits = rand.nextInt(3) + 1;
        int dmg = (rand.nextInt(3) + 10 + player.str * 2 + weapon) * hits;
        enemy.mobLife -= dmg;
        player.manadrain += 4;
        System.out.println("Deine schnellen Hiebe haben " + hits + " Mal getroffen und " + dmg + " Schaden gemacht!");
        printMobLife(enemy);
        printEnergy(player);
    }

    private void printEnergy(Hero player) {
        System.out.println("Du hast jetzt noch " + (player.mana - player.manadrain) + " Energie!");
    }

    private void printMobLife(Enemy enemy) {
        if (enemy.mobLife > 0) {
            System.out.println("Der Gegner hat noch " + enemy.mobLife + " Gesundheit!");
        }
    }
}
