public class Enemy {
    int mobLife, mobStrength, mobExp, mobStun, mobBlock;
    String name;


    public Enemy(String name, int mobLife, int mobStrength, int mobExp, int mobStun, int mobBlock) {
        this.name = name;
        this.mobLife = mobLife;
        this.mobStrength = mobStrength;
        this.mobExp = mobExp;
        this.mobStun = mobStun;
        this.mobBlock = mobBlock;
    }
    public void enemyPresentation() {
        System.out.println("\nEin " + this.name + " erscheint!\n");
    }
    public void enemyDeath() {System.out.print("Der " + this.name + " ist tot!\nDu hast 1 Erfahrung erhalten\n");}


}
