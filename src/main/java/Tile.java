
public class Tile {
	protected  String Color;
	protected  int Number;
	  String[] Colors = { "Blue", "Black", "Red", "Yellow" };
	  int[] Numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
	

	public  Tile createTile(int x, int y) { // create a tile with the specific color and number
		this.Color = Colors[x - 1];
		this.Number = Numbers[y - 1];
		return this;
	}

	public String getColor() {
		return Color;
	}

	public int getNumber() {
		return Number;
	}

	public Tile addWild() { // used to add a wild tile to the deck
		this.Color = "Wild";
		this.Number = 14;
		return this;
	}

}
