public class Floor {
    int floorNr = 1, tilesMax = 3, tilesNr;

    public Floor() {
    }
    public int floorIncrease() {return ++this.floorNr;} //Stockwerkszahl erhöhung
    public int tilesDecrease() {return --this.tilesNr;} //verkleinern der Ebene
    public int FloorSize() {return this.tilesMax + this.floorNr;} //Bestimmung der Größe der Ebene
}
