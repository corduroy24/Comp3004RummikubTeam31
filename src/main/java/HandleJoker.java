import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class HandleJoker {
	//split hand into 5 lists with 4 colors red, blue, green, orange and joker.
		ArrayList<Tile> blue = new ArrayList<Tile>();
		ArrayList<Tile> green = new ArrayList<Tile>();
		ArrayList<Tile> red = new ArrayList<Tile>();
		ArrayList<Tile> orange = new ArrayList<Tile>();
		ArrayList<Tile> joker = new ArrayList<Tile>();
		Support function = new Support();

	// check the valid color and value of joker for the set
	public boolean isSet(ArrayList<Tile> l) {
		if(l.size() < 3 || l.size() > 4) return false;
		Collections.sort(l, new SortSmallestToBiggest());
		int check_point = 0;
		HashSet<String> set = new HashSet<>();
		for(int i =0; i <l.size();i++) {
			if (set.size() == 0) {
				if(l.get(i).isJoker()) {
					set.add(l.get(i).getJokerColor());
					check_point = l.get(i).getJokerPoint();}
				else {
					check_point = l.get(i).getNumber();
					set.add(l.get(i).getColor());
				}
			}
			else if(!l.get(i).isJoker()) {
				if (check_point != l.get(i).getNumber()) return false;
				if(!set.add(l.get(i).getColor()))
					return false;
			}
			else {
				if (check_point != l.get(i).getJokerPoint()) return false;
				if(!set.add(l.get(i).getJokerColor())) 
					return false;
			}
		}
		return true;
	};
	// check the valid color and value of joker for the sequences
	public  boolean isRun(ArrayList<Tile> l) {
		if(l.size() < 3) return false;
		Collections.sort(l, new SortSmallestToBiggest());
		String color = "";
		if(l.get(0).isJoker()) color = l.get(0).getJokerColor();
		else color = l.get(0).getColor();
		
		for(int u =0; u < l.size()-1;u++) {
			int current, next;
			if(u == l.size()-1) return true;
			String current_color;
			if(l.get(u).isJoker()) {
				current = l.get(u).getJokerPoint();
				current_color = l.get(u).getJokerColor();
			}
			else {
				current = l.get(u).getNumber();
				current_color = l.get(u).getColor();
			}
			
			if(!color.equals(current_color)) return false;
			
			if (l.get(u+1).isJoker()) next = l.get(u+1).getJokerPoint();
			else next = l.get(u+1).getNumber();			
			
			if(current +1 != next) return false;
		}
		return true;
	};
	
	
	//sort tiles by number including joker
	static class SortSmallestToBiggest implements Comparator<Tile> 
	{  public int compare(Tile a, Tile b) 
	  {	
		int first;
		if(!a.isJoker()) first = a.getNumber();  
		else first = a.getJokerPoint();
		
		int second;
		if(!b.isJoker()) second = b.getNumber();  
		else second = b.getJokerPoint();
		return first - second;
		} 
	}

	//get sequences has joker then get all the sets. It depends on the break point, if p1 doesn't have
	// the first initial turn, p1 will get the sequences has joker which is has the highest point.
	public ArrayList<ArrayList<Tile>> getFirst(boolean complete_first_turn, int num, ArrayList<Tile> sample) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>>run;
		ArrayList<ArrayList<Tile>> set;
		blue = new ArrayList<Tile>();
		green = new ArrayList<Tile>();
		red = new ArrayList<Tile>();
		orange = new ArrayList<Tile>();
		joker = new ArrayList<Tile>();
		separateList(sample);
		run = getJokerSequences(complete_first_turn,num,sample); 
		sample = function.renew(sample, run);
		int check_new_list = NumberOfJoker(sample);
		set = getJokerSet(complete_first_turn,check_new_list,sample);
		//set = getJokerSet(complete_first_turn,num,sample);
		if(run != null && run.size() != 0) output.addAll(run);
		if (set != null && set.size() != 0) output.addAll(set);	
		return output;
	}
	// get set has joker then get all the sequences .It depends on the break point, if p1 doesn't have
	// the first initial turn, p1 will find the set has joker which is has the highest point.
	public ArrayList<ArrayList<Tile>> getSecond(boolean complete_first_turn, int num, ArrayList<Tile> sample) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------------");
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> run;
		ArrayList<ArrayList<Tile>> set;
		blue = new ArrayList<Tile>();
		green = new ArrayList<Tile>();
		red = new ArrayList<Tile>();
		orange = new ArrayList<Tile>();
		joker = new ArrayList<Tile>();
		set = getJokerSet(complete_first_turn,num,sample);
		sample = function.renew(sample, set);
		int check_new_list = NumberOfJoker(sample);
		separateList(sample);
		run = getJokerSequences(complete_first_turn,check_new_list,sample); 
		if(run != null && run.size() != 0) output.addAll(run);
		if (set != null && set.size() != 0) output.addAll(set);	
		return output;
	}
	
	
		//Initial the value of the joker tile after getting the Joker set
		public ArrayList<ArrayList<Tile>> getJokerSet(boolean b, int num, ArrayList<Tile> sample) {
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			ArrayList<Tile> current = sample;
			Collections.sort(current,new BiggestToSmallest());
			output = getFirstSets(current);
			initialOutput(output,sample);
			return output;
		}
		
		// Find the Joker set has the most value.
		private ArrayList<ArrayList<Tile>> getFirstSets(ArrayList<Tile> hand){
			
			int num = NumberOfJoker(hand);
			int count = 0;
			if(hand == null || hand.size() == 0) return null;
			ArrayList<ArrayList<Tile>> sets = new ArrayList<ArrayList<Tile>>();
			ArrayList<Tile> check = new ArrayList<Tile>();
			HashSet<String> string = new HashSet<String>();
			int value = hand.get(0).getNumber();
			
			ArrayList<Tile> check1 = new ArrayList<Tile>();
			HashSet<String> string1 = new HashSet<String>();
			
			for(int i =num; i < hand.size();i++) {
				if(value  == hand.get(i).getNumber()) {
					if(check.size() == 0) {
						check.add(hand.get(i));
						string.add(hand.get(i).getColor());
					}
					else {
						if(string.size() >= 3 && string1.size() >= 2) {
							if (string1.add(hand.get(i).getColor())) {
								check1.add(hand.get(i));
								string1.add(hand.get(i).getColor());
							}
						}
						else if(string.add(hand.get(i).getColor())) {
							check.add(hand.get(i));
							string.add(hand.get(i).getColor());
						}
						else if (string1.add(hand.get(i).getColor())) {
							check1.add(hand.get(i));
							string1.add(hand.get(i).getColor());
						}
					}
				}
				else {
					if(check.size() > 2) sets.add(check);
					else if (check.size() == 2 && hand.get(count).isJoker()) {
						Tile l = new Tile();
						l.setJokerPoint(value);
						check.add(l);
						sets.add(check);
						count++;
					}
					else if (check1.size()== 2 && hand.get(count).isJoker()) {
						Tile l = new Tile();
						l.setJokerPoint(value);
						check1.add(l);
						sets.add(check1);
						count++;
					}
					check = new ArrayList<Tile>();
					string = new HashSet<String>();
					value = hand.get(i).getNumber();
					check.add(hand.get(i));
					string.add(hand.get(i).getColor());
					
					check1 = new ArrayList<Tile>();
					string1 = new HashSet<String>();
				}
			}
			if(check.size()>2) sets.add(check);
			if(check1.size() > 2) sets.add(check1);
			else if (check.size() == 2 && hand.get(count).isJoker()) {
				Tile l = new Tile();
				l.setJokerPoint(value);
				check.add(l);
				sets.add(check);
				count++;
			}
			else if (check1.size()== 2 && hand.get(count).isJoker()) {
				Tile l = new Tile();
				l.setJokerPoint(value);
				check1.add(l);
				sets.add(check1);
				count++;
			}
			if (count == num) return sets;
			else {
				while(count < num) {
					Tile l = new Tile();
					l.setJokerPoint(value);
					check.add(l);
					count++;
					if(count == num) break;
				}
				return sets;
			}
		}


		//get the most efficient sequences depends on the first initial turn
		// return the output could be most tiles or most point due to first initial turn complete or not
		// The algorithm try using a single joker on each single list with a specific color, then return the highest point or the most number of tiles.
		public ArrayList<ArrayList<Tile>> getJokerSequences(boolean complete_first_turn,int num, ArrayList<Tile> sample2) {
			ArrayList<ArrayList<Tile>> max_tile = new ArrayList<ArrayList<Tile>>();
			ArrayList<ArrayList<Tile>> max_point = new ArrayList<ArrayList<Tile>>();
			int numberOfTile = 0;
			int maxPoint = 0;
			for(int i =0; i < 4; i++) {
				ArrayList<ArrayList<Tile>> sample = new ArrayList<ArrayList<Tile>>();
				Tile j = new Tile(14,14);
				Tile j1 = new Tile(14,14);
				//System.out.println(point);
				ArrayList<Tile> current = new ArrayList<Tile>();
				if(i == 0) {
					if(num == 1) {current.add(j);}
					else if(num == 2){current.add(j);current.add(j1);}
					current.addAll(blue);
					sample.addAll(getSequencesHasJoker(current,complete_first_turn));
					sample.addAll(getSequences(green));
					sample.addAll(getSequences(red));
					sample.addAll(getSequences(orange));
					numberOfTile = function.getSizeOf(sample);
					maxPoint = getPoint(sample);
					max_tile = new ArrayList<ArrayList<Tile>>(sample);
					max_point = new ArrayList<ArrayList<Tile>>(sample);
				}
				
				else if(i == 1) {
					sample = new ArrayList<ArrayList<Tile>>();
					if(num == 1) {current.add(j);}
					else if(num == 2){current.add(j);current.add(j1);}
					current.addAll(green);
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
					if(num == 1) {current.add(j);}
					else if(num == 2) {current.add(j);current.add(j1);}
					current.addAll(red);
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
					};
				}
				
				else if(i == 3) {
					sample = new ArrayList<ArrayList<Tile>>();
					if(num == 1) {current.add(j);}
					else if(num == 2) {current.add(j);current.add(j1);} 
					current.addAll(orange);
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
			
			
			if(complete_first_turn) {
				initialOutput(max_tile,sample2);
				return max_tile;
			}
			else {
				initialOutput(max_point,sample2);
				return max_point;
			}
		}



		//function used to initial Joker' point and color in a Joker set or a Joker sequence, 
		public void initialOutput(ArrayList<ArrayList<Tile>> s, ArrayList<Tile> hand) {
			Collections.sort(hand, new SmallestToBiggest());
			int num = NumberOfJoker(hand);
			int index = 0;
			
			if(s!= null && hand != null && s.size() > 0 && hand.size() > 0) {
			for(int i = 0; i < s.size();i++) {
				for(int u =0; u < s.get(i).size();u++) {
					if(s.get(i).get(u).isJoker()) {
						if(hand.size() -num <= hand.size()-1) {
						hand.get(hand.size()-num).setJokerColor(s.get(i).get(u).getJokerColor());
						hand.get(hand.size()-num).setJokerPoint(s.get(i).get(u).getJokerPoint());}
						else {
							hand.get(hand.size()-1).setJokerColor(s.get(i).get(u).getJokerColor());
							hand.get(hand.size()-1).setJokerPoint(s.get(i).get(u).getJokerPoint());
						}
						num--;
					}
				}
			}
			}
		}
		
		

		// GENERATE SEQUENCES WITH JOKERS AND THE STRATEGY DEPENDS ON THE FIRST INITUAL TURN
			private ArrayList<ArrayList<Tile>> getSequencesHasJoker(ArrayList<Tile> list, boolean b) {
				ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
				int number_joker = NumberOfJoker(list);
				ArrayList<ArrayList<Tile>> sample = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> sample1 = new ArrayList<ArrayList<Tile>>();
				
				ArrayList<Tile> current = new ArrayList<Tile>();
				ArrayList<Tile> duplicate = new ArrayList<Tile>();
				// do from the biggest to the smallest
				Collections.sort(list,new BiggestToSmallest());
				int count = 0;
				for(int i =number_joker; i < list.size();i++) {
					int current_point= 0;
					if(current.size() >0) {
						if(current.get(current.size()-1).isJoker()) 
							current_point = current.get(current.size()-1).getJokerPoint(); 
						else
							current_point = current.get(current.size()-1).getNumber();}
					if(current.size() == 0) current.add(list.get(i));
					else if(current_point-1 == list.get(i).getNumber())
						current.add(list.get(i));
					else if (current_point == list.get(i).getNumber()) {
						duplicate.add(list.get(i));;
					}
					else if (current_point-2 == list.get(i).getNumber()) {
						if(list.get(count).isJoker()) {
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							l.setJokerPoint((current_point-1));
							current.add(l);
							current.add(list.get(i));
							count++;
						}
						else {
							if (current.size() > 2) sample.add(current);
							if(duplicate.size() > 2) sample.add(duplicate);
							duplicate = new ArrayList<Tile>();
							current = new ArrayList<Tile>();
							current.add(list.get(i));		
						}
					}
					else if (current_point-3 == list.get(i).getNumber()) {
						if(list.get(count).isJoker() && list.get(count+1).isJoker()) {
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							l.setJokerPoint((current_point-1));
							Tile l1 = new Tile();
							l1.setJokerColor(list.get(i).getColor());
							l1.setJokerPoint((current_point-2));
							current.add(l);
							current.add(l1);
							current.add(list.get(i));
							count++;count++;
						}
					}
					else {
						
						if (current.size() > 2) sample.add(current);
						else if (current.size() == 2 && list.get(count).isJoker()) {
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							if(current.get(0).getNumber() == 13)
								l.setJokerPoint((current_point-1));
							else
								l.setJokerPoint(current.get(0).getNumber()+1);
							current.add(l); 
							count ++;
							sample.add(current);
						}
						if(duplicate.size() > 2) sample.add(duplicate);
						else if (duplicate.size() == 2 && list.get(count).isJoker()) {
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							l.setJokerPoint((current_point-1));
							duplicate.add(l); count ++;
							sample.add(duplicate);
						}
						current = new ArrayList<Tile>();
						duplicate = new ArrayList<Tile>();
						current.add(list.get(i));
					}
				}
				
				if(count < number_joker) {
					int a = number_joker - count;
					if(current.size() < 3 && current.size() + a >= 3) {
						while(a > 0) {
							Tile l = new Tile();
							current.add(l);
							a--;
							if(a == 0) break;
						}
						setJoker(current);
					}
					else if(duplicate.size() < 3 && duplicate.size() + a >= 3) {
						while(a > 0) {
							Tile l = new Tile();
							duplicate.add(l);
							a--;
							if(a == 0) break;
						}
						setJoker(duplicate);
					}
					else if (current.size() > 3) {
						while(a > 0) {
							Tile l = new Tile();
							l.setJokerColor(current.get(current.size()-1).getColor());
							l.setJokerPoint(current.get(current.size()-1).getNumber()-1);
							
							current.add(l);
							a--;
							if(a == 0) break;
						}
					}
					
				}
				
				if(current.size() >2) sample.add(current);
				if(duplicate.size() >2)sample.add(duplicate);
				
				
				
				// DO THE SAME STRATEGY AS ABOVE BUT START FROM THE SMALLEST TO THE BIGGEST
				current = new ArrayList<Tile>();
				duplicate = new ArrayList<Tile>();
				Collections.sort(list, new SmallestToBiggest());
				count = 0;
				int joker_index = list.size()-1;
				
				for(int i =0; i < list.size();i++) {
					int current_point= 0;
					if(current.size() >0) {
						if(current.get(current.size()-1).isJoker()) current_point = current.get(current.size()-1).getJokerPoint(); 
						else current_point = current.get(current.size()-1).getNumber();}
					
					if(current.size() == 0) current.add(list.get(i));
					else if(current_point+1 == list.get(i).getNumber()) {
						current.add(list.get(i));
						if(list.get(i).isJoker()) count++;
					}
					else if (current_point == list.get(i).getNumber()) {
						duplicate.add(list.get(i));
					}
					else if (current_point+2 == list.get(i).getNumber()) {
						if(list.get(joker_index).isJoker()) {	
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							l.setJokerPoint(current_point+1);
							current.add(l);
							current.add(list.get(i));
							count++;
							joker_index--;
						}
						else {
							if (current.size() > 2) sample1.add(current);
							if(duplicate.size() > 2) sample1.add(duplicate);
							duplicate = new ArrayList<Tile>();
							current = new ArrayList<Tile>();
							current.add(list.get(i));		
						}
					}
					else {
						if (current.size() > 2) sample1.add(current);
						else if (current.size() == 2 && list.get(joker_index).isJoker()) {
							Tile l = new Tile();
							l.setJokerColor(list.get(i).getColor());
							l.setJokerPoint(current_point+1);
							current.add(l); count ++;
							joker_index--;
							sample1.add(current);
						}
						if(duplicate.size() > 2) sample.add(duplicate);
						else if (duplicate.size() == 2 && list.get(joker_index).isJoker()) {
							Tile l = new Tile();
							current_point = duplicate.get(duplicate.size()-1).getNumber();
							l.setJokerColor(duplicate.get(duplicate.size()-1).getColor());
							l.setJokerPoint(current_point+1);
							duplicate.add(l); count ++;
							joker_index--;
							sample1.add(duplicate);
						}
						current = new ArrayList<Tile>();
						duplicate = new ArrayList<Tile>();
						current.add(list.get(i));
					}
				}
				if(count < number_joker) {
					int a = number_joker - count;
					if(current.size() < 3 && current.size() + a >= 3) {
						while(a > 0) {
							Tile l = new Tile();
							current.add(l);
							a--;
							if(a == 0) break;
						};
						setJoker(current);
					}
					else if(duplicate.size() < 3 && duplicate.size() + a >= 3) {
						while(a > 0) {
							Tile l = new Tile();
							duplicate.add(l);
							a--;
							if(a == 0) break;
						}
						if(current.size() > 2) setJoker(duplicate);
					}
				}
				
				if(current.size() >2) sample1.add(current);
				if(duplicate.size() >2)sample1.add(duplicate);
				
				if(b) { 
					if(function.getSizeOf(sample) < function.getSizeOf(sample1))
						output = sample1;
					else
						output = sample;
				}
				else {
					if(getPoint(sample) < getPoint(sample1))
						output = sample1;
					else
						output = sample;	
				}
				return output;
			}
		

		//SET JOKER VALUE FOR THE SAMPLE 
		public void setJoker(ArrayList<Tile> subset) {
			int NumberOfJoker = NumberOfJoker(subset);
			Collections.sort(subset,new SmallestToBiggest());
			if(subset.get(0).isJoker()) return;
			String color = "";
			int point = 0;
			for(int i =0; i < subset.size()-NumberOfJoker-1;i++) {
				color = "";
				point = 0 ;
				if(subset.get(i).isJoker()) {
					color = subset.get(i).getJokerColor();
					point = subset.get(i).getJokerPoint();
				}
				else {
					color = subset.get(i).getColor();
					point = subset.get(i).getNumber();
				}
				
				if(subset.get(i).getNumber()+2 == subset.get(i+1).getNumber() && subset.size()-NumberOfJoker >= 0) {
						subset.get(subset.size()-NumberOfJoker).setJokerColor(color);
						subset.get(subset.size()-NumberOfJoker).setJokerPoint(point);
						NumberOfJoker--;
				}
			}
			while(NumberOfJoker > 0) {
				if(subset.get(subset.size()-NumberOfJoker-1).isJoker()) {
					color = subset.get(subset.size()-NumberOfJoker-1).getJokerColor();
					point = subset.get(subset.size()-NumberOfJoker-1).getJokerPoint();
				}
				else {
					color = subset.get(subset.size()-NumberOfJoker-1).getColor();
					point = subset.get(subset.size()-NumberOfJoker-1).getNumber();
					
				}
				int current_point;
				if(subset.get(subset.size()- NumberOfJoker(subset)).isJoker()) current_point = subset.get(subset.size()- NumberOfJoker(subset)).getJokerPoint();
				else current_point = subset.get(subset.size()- NumberOfJoker(subset)).getNumber();
				if(current_point == 13 || current_point == 12) {
					subset.get(subset.size()-NumberOfJoker).setJokerColor(subset.get(0).getColor());
					subset.get(subset.size()-NumberOfJoker).setJokerPoint(point-1);					
					NumberOfJoker--;
				}
				else {
					subset.get(subset.size()-NumberOfJoker).setJokerColor(color);
					subset.get(subset.size()-NumberOfJoker).setJokerPoint(point+1);	
					if(subset.get(subset.size()-NumberOfJoker).getJokerPoint() == 14) subset.get(subset.size()-NumberOfJoker).setJokerPoint(subset.get(0).getNumber()-1);
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

		public void separateList(ArrayList<Tile> l ) {
			for(int i =0; i < l.size();i++) {
				if(l.get(i).getColor() == "R") red.add(l.get(i));
				else if(l.get(i).getColor() == "G") green.add(l.get(i));
				else if(l.get(i).getColor() == "B") blue.add(l.get(i));
				else if(l.get(i).getColor() == "O") orange.add(l.get(i));
				else joker.add(l.get(i));
			}
		}
		//get total point of a list
		public int getPoint(ArrayList<ArrayList<Tile>> t) {
			int in = 0;
			if(t== null || t.size() == 0) return 0;
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
			public int getPointOfSeq(ArrayList<Tile> t) {
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
		public int NumberOfJoker(ArrayList<Tile> t) {
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
		
		public boolean isJokerSequences(ArrayList<Tile> l) {
			Collections.sort(l, new SmallestToBiggest());
			int num = 0, num1 = 0;
			if(l.get(0).isJoker()) num = l.get(0).getJokerPoint();
			else num = l.get(0).getNumber();
			
			if(l.get(1).isJoker()) num1 = l.get(1).getJokerPoint();
			else num1 = l.get(1).getNumber();
			if(num1 -1 != num) return false;
			
			for(int i =0; i <l.size();i++) {
				if(l.get(i).isJoker())
					return true;
			}
			return false;
		}
		
		public boolean isJokerSet(ArrayList<Tile> l) {
			Collections.sort(l, new SmallestToBiggest());
			int num = 0, num1 = 0;
			if(l.get(0).isJoker()) num = l.get(0).getJokerPoint();
			else num = l.get(0).getNumber();
			
			if(l.get(1).isJoker()) num1 = l.get(1).getJokerPoint();
			else num1 = l.get(1).getNumber();
			if(num1  != num) return false;
			
			for(int i =0; i <l.size();i++) {
				if(l.get(i).isJoker())
					return true;
			}
			return false;
		}
		
		
}
