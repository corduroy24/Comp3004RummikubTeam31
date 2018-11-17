import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class p1 implements PlayerStrategy{
	Support function = new Support();
	HandleJoker checkMeld = new HandleJoker();
	
	PlayerStrategy1 old_p1 = new PlayerStrategy1();
	//split hand into 5 lists with 4 colors red, blue, green, orange and joker.
	ArrayList<Tile> blue = new ArrayList<Tile>();
	ArrayList<Tile> green = new ArrayList<Tile>();
	ArrayList<Tile> red = new ArrayList<Tile>();
	ArrayList<Tile> orange = new ArrayList<Tile>();
	ArrayList<Tile> joker = new ArrayList<Tile>();
	
	
	
	
	public boolean playTheGame(Player p) {
		int num = NumberOfJoker(p.getHand().getTiles());
		ArrayList<ArrayList<Tile>> max_tile = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> max_point = new ArrayList<ArrayList<Tile>>();
		boolean complete_first_turn = p.getIsFirstMeldComplete();
		int numberOfTile = 0;
		int maxPoint = 0;
		separateList(p.getHand().getTiles()); //  initial tile for each list of 5
		if(num == 0) return old_p1.playTheGame(p);
		else if (num == 1) {
			Tile j = joker.get(0);
			ArrayList<ArrayList<Tile>> sample = new ArrayList<ArrayList<Tile>>();
			for(int i =0; i < 4; i++) {
				ArrayList<Tile> current = new ArrayList<Tile>();
				if(i == 0) {
					current.addAll(blue); current.add(j);
					sample.addAll(getSequencesHasJoker(current,complete_first_turn));
					sample.addAll(getSequences(green));
					sample.addAll(getSequences(red));
					sample.addAll(getSequences(orange));
					numberOfTile = function.getSizeOf(sample);
					maxPoint = getPoint(sample);
					max_tile = sample;
					max_point = sample;
				}
				
				else if(i == 1) {
					sample = new ArrayList<ArrayList<Tile>>();
					current.addAll(green); current.add(j);
					sample.addAll(getSequences(blue));
					sample.addAll(getSequencesHasJoker(current,complete_first_turn));
					sample.addAll(getSequences(red));
					sample.addAll(getSequences(orange));
					if(numberOfTile < function.getSizeOf(sample)) { 
						numberOfTile= function.getSizeOf(sample);
						max_tile = sample;}
					if(maxPoint < getPoint(sample)) {
						maxPoint = getPoint(sample);
						max_point = sample;
					}	
				}

				else if(i == 2) {
					sample = new ArrayList<ArrayList<Tile>>();
					current.addAll(red); current.add(j);
					sample.addAll(getSequences(blue));
					sample.addAll(getSequences(green));
					sample.addAll(getSequencesHasJoker(current,complete_first_turn));
					sample.addAll(getSequences(orange));
					if(numberOfTile < function.getSizeOf(sample)) { 
						numberOfTile= function.getSizeOf(sample);
						max_tile = sample;}
					if(maxPoint < getPoint(sample)) {
						maxPoint = getPoint(sample);
						max_point = sample;
					}
				}
				
				else if(i == 3) {
					sample = new ArrayList<ArrayList<Tile>>();
					current.addAll(orange); current.add(j);
					sample.addAll(getSequences(blue));
					sample.addAll(getSequences(green));
					sample.addAll(getSequences(red));
					sample.addAll(getSequencesHasJoker(current,complete_first_turn));
					if(numberOfTile < function.getSizeOf(sample)) { 
						numberOfTile= function.getSizeOf(sample);
						max_tile = sample;}
					if(maxPoint < getPoint(sample)) {
						maxPoint = getPoint(sample);
						max_point = sample;
					}
				}
			}	
		}
		else if(num == 2) {
			
		}
		if(complete_first_turn) {
			System.out.println("tile " +max_tile);}
		else
			System.out.println("point " + max_point);
		return false;
	}
	
	
	
	
	
	
	
	// GENERATE SEQUENCES WITH JOKERS AND THE STRATEGY DEPENDS ON THE FIRSTI INITUAL TURN
	private ArrayList<ArrayList<Tile>> getSequencesHasJoker(ArrayList<Tile> current,
			boolean b) {
		Table check = new Table();
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		int point = 0;
		int size = 0;
		int length = current.size();
		ArrayList<Tile> run = new ArrayList<Tile>();
		// idea to find subsets is from https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
		for (int i = 0; i < (1<<length); i++) {
			ArrayList<Tile> subset = new ArrayList<Tile>();
			for (int j = 0; j < length; j++)  
                if ((i & (1 << j)) > 0) 
                	subset.add(current.get(j));
			if(b) {
				if(check.isSequence(subset)) {
					if(size < subset.size()) {
						setJoker(subset);
						size = subset.size();
						run = subset;
					}
				}
			}
			else {
				if(check.isSequence(subset)) {
					if(point < getPointOfSeq(subset)) {
						setJoker(subset);
						point = getPointOfSeq(subset);
						run = subset;
					}
				}
			}
		}
		output.add(run);
		for(int i =0; i< run.size();i++) {
			if(current.contains(run))
				current.remove(run);
		}
		return output;
	}



 




	private void setJoker(ArrayList<Tile> subset) {
		int NumberOfJoker = NumberOfJoker(subset);
		Collections.sort(subset,new SmallestToBiggest());
			for(int i =0; i < subset.size()-NumberOfJoker-1;i++) {
				if(subset.get(i).getNumber()+2 == subset.get(i+1).getNumber()) {
						subset.get(subset.size()-NumberOfJoker).setJokerColor(subset.get(i).getColor());
						subset.get(subset.size()-NumberOfJoker).setJokerPoint(subset.get(i).getNumber()+1);
						NumberOfJoker--;
				}
			}
			while(NumberOfJoker > 0) {
				if(subset.get(subset.size()- NumberOfJoker(subset)).getNumber() == 13) {
					subset.get(subset.size()-NumberOfJoker).setJokerColor(subset.get(0).getColor());
					subset.get(subset.size()-NumberOfJoker).setJokerPoint(subset.get(0).getNumber()-1);
					NumberOfJoker--;
				}
				else {
					subset.get(subset.size()-NumberOfJoker).setJokerColor(subset.get(subset.size()-NumberOfJoker-1).getColor());
					subset.get(subset.size()-NumberOfJoker).setJokerPoint(subset.get(subset.size()-NumberOfJoker-1).getNumber()+1);
					NumberOfJoker--;
				}
				if (NumberOfJoker == 0) break;
			}
	}







	//get all sequences of a list with specific color
	private  ArrayList<ArrayList<Tile>> getSequences(ArrayList<Tile> l) {
		Collections.sort(l,new SmallestToBiggest());
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> first_meld = new ArrayList<Tile>();
		ArrayList<Tile> second_meld = new ArrayList<Tile>();
		if(l.size() < 3) return output;
		else {
			for(int i =0; i <l.size();i++) {
				if(first_meld.size() == 0) {
					first_meld.add(l.get(i));
				}
				else if (first_meld.get(first_meld.size()-1).getNumber() +1 == l.get(i).getNumber())
					first_meld.add(l.get(i));
				else if (first_meld.get(first_meld.size()-1).getNumber()  == l.get(i).getNumber())
					second_meld.add(l.get(i));
				else {
					if (first_meld.size() > 2) output.add(first_meld);
					if (second_meld.size() > 2) output.add(second_meld);
					first_meld = new ArrayList<Tile>();
					second_meld = new ArrayList<Tile>();
					first_meld.add(l.get(i));
				}
			}
			
		}
		if (first_meld.size() > 2) output.add(first_meld);
		if (second_meld.size() > 2) output.add(second_meld);
		
		return output;
	}

	
	

	private void separateList(ArrayList<Tile> l ) {
		for(int i =0; i < l.size();i++) {
			if(l.get(i).getColor() == "R") red.add(l.get(i));
			else if(l.get(i).getColor() == "G") green.add(l.get(i));
			else if(l.get(i).getColor() == "B") blue.add(l.get(i));
			else if(l.get(i).getColor() == "O") orange.add(l.get(i));
			else joker.add(l.get(i));
		}
	}
	
	//get total point of a list
	private int getPoint(ArrayList<ArrayList<Tile>> t) {
		int in = 0;
		for(int i =0; i < t.size();i++) {
			for(int u =0; u < t.get(i).size();u++) {
			if(!t.get(i).get(u).isJoker())
				in += t.get(i).get(u).getNumber();
			else
				in += t.get(i).get(u).getJokerPoint();
			}
		}
		return in;
	}
	
	//get total point of a list
		private int getPointOfSeq(ArrayList<Tile> t) {
			int in = 0;
			for(int i =0; i < t.size();i++) {
				if(!t.get(i).isJoker())
					in += t.get(i).getNumber();
				else
					in += t.get(i).getJokerPoint();
			}
			return in;
		}
	
	
	//get number of jokers on a list
	private int NumberOfJoker(ArrayList<Tile> t) {
		int u = 0;
		for(int i =0; i < t.size();i++) {
			if(t.get(i).isJoker()) u++;;
		}
		return u;
	}
	
	// sort from smallest to the biggest by point
	 class SmallestToBiggest implements Comparator<Tile> 
	{ public int compare(Tile a, Tile b) 
	    {  return a.getNumber() - b.getNumber();} }
	
	//sort from biggest to the smallest by point
	class BiggestToSmallest implements Comparator<Tile> 
	{ public int compare(Tile a, Tile b) 
	    {  return b.getNumber() - a.getNumber();} }
	
}
