
public class Tile {
	static Tile tile;
	private static String Color;
	protected static int Number;
	static String[] Colors = { "Blue", "Black", "Red", "Yellow" };
	static int[] Numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

	public static Tile createTile(int x, int y) { // create a tile with the specific color and number
		tile.Color = Colors[x - 1];
		tile.Number = Numbers[y - 1];
		return tile;
	}

	public static String getColor() {
		return Color;
	}

	public static int getNumber() {
		return Number;
	}

	public static Tile addWild() { // used to add a wild tile to the deck
		tile.Color = "Wild";
		tile.Number = 14;
		return tile;
	}

}
