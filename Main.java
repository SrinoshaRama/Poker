import java.util.*;

class Cards implements Comparable<Cards>{

	int pip;
	String suit;
	static String[] names  = new String[14];
	
	static{
		for(int i=0;i<=13;++i)
			names[i]= Integer.toString(i);
		
		names[1]  =  "ACE";
		names[11] = "JOKER";
		names[12] = "QUEEN";
		names[13] = "KING";
	
	}
	
	
	Cards(int pip , String suit){
		this.pip = pip;
		this.suit = suit;
	}
	
	int getCardNumber(){
		return pip;
	}
	
	String getCardSuit(){
		return suit;
	}
	
	public int compareTo(Cards c2){
			return (c2.getCardNumber() - this.getCardNumber() ); 	
	}
	
	public String printCard(){
		 return "( "+names[this.getCardNumber()]+","+ this.getCardSuit()+" ) ";
	}
}



class Hand{

	ArrayList<Cards> seq = new ArrayList<Cards>();
	 boolean[] taken = new boolean[52];
	 String[] suit = new String[4];
	 
	
	{
		suit[0]= "CLUB";
		suit[1]= "DIAMOND";
		suit[2]= "HEARTS";
		suit[3]= "SPADES";
	}
	
	
	Hand(){
		
		int count = 0 ;
		Random rand = new Random();
		int randomNum;
			
		while(count<5){
			randomNum = rand.nextInt(52);
			
			if(!taken[randomNum]){
				taken[randomNum] =true;
				seq.add(new Cards(randomNum%13+1 ,suit[randomNum/13]));
				++count;	
			}
					
		}
		
		Collections.sort(seq);
	}
	
	public String toString(){
		String ans ="";
		for(Cards c: seq)
			ans += c.printCard() + " ";
			
		return ans;
		
	}
	
	
}

class Poker{

	Hand h1;
	Hand h2;
	
	Poker(){
	
		h1 = new Hand();
		h2 = new Hand();
	
	}
	
	public void result(){
	
		ArrayList<Boolean> result1 = new ArrayList<Boolean>();
		ArrayList<Boolean> result2 = new ArrayList<Boolean>();
		Hand winner ,loser;
	
		result1.add(royalflush(h1));
		result1.add(straightflush(h1));
		result1.add(fourofakind(h1));
		result1.add(fullhouse(h1));
		result1.add(flush(h1));
		result1.add(straight(h1));
		result1.add(threeofakind(h1));
		result1.add(twopair(h1));
		result1.add(onepair(h1));
		result1.add(highCard(h1));
	
		result2.add(royalflush(h2));
		result2.add(straightflush(h2));
		result2.add(fourofakind(h2));
		result2.add(fullhouse(h2));
		result2.add(flush(h2));
		result2.add(straight(h2));
		result2.add(threeofakind(h2));
		result2.add(twopair(h2));
		result2.add(onepair(h2));
		result2.add(highCard(h2));
	
		//System.out.println(" Hand1 category is  : \n" + result1);
		//System.out.println(" Hand2 category is  : \n" + result2+"\n\n\n");
		
		if( result1.indexOf(true) - result2.indexOf(true)  <= 0){
				winner = h1;
				loser=h2;
		}else{
				winner = h2;
				loser=h1;
		}
		
		System.out.println("Winner is "+ winner +"\nloser is  "+ loser);
	
	}
		
	
	public  boolean flush (Hand hand) {
		for (int i=0;i<4;i++) {
			if  ( !hand.seq.get(i).getCardSuit().equals(hand.seq.get(i+1).getCardSuit()) )
					return false;
		}
		
		for(int i=0;i<4;++i)
			if(hand.seq.get(i).getCardNumber() - hand.seq.get(i).getCardNumber() != 1)
				return true;
		
		return false;
	}
	
	public  boolean fourofakind (Hand hand) {
		int count=0;
		int i=0;
		
		for(i=0;i<4;i++) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i+1).getCardNumber() ){
					count++;
					i++;
		}
			else
				i++;
		
		if(count != 4)
	 		return false;
		}
		
		return true;
	}
		
	public  boolean straightflush(Hand hand) {
		for(int i=0;i<4;i++) { 
			if(!(hand.seq.get(i).getCardSuit().equals(hand.seq.get(i+1).getCardSuit())) || !(hand.seq.get(i).getCardNumber() - hand.seq.get(i+1).getCardNumber() != 1 ))
				return false;
		}
		
		return true;
	}
	public  boolean royalflush (Hand hand) {
	
		for(int i=0;i<4;i++) { 
			if(!(hand.seq.get(i).getCardSuit().equals(hand.seq.get(i+1).getCardSuit())))
				return false;
		}
		
		ArrayList<Cards> royalseq = new ArrayList<Cards>();
		
		royalseq.add(new Cards(13, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Cards(12, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Cards(11, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Cards(10, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Cards(1, hand.seq.get(0).getCardSuit()));
		
		for(int i =0 ;i<5 ;++i)
			if(royalseq.get(i).compareTo(hand.seq.get(i)) != 0)
				return false;
		
		return true;
		
	}

	public  boolean straight (Hand hand) {
		for(int i=0;i<4;i++) { 
			if(hand.seq.get(i).getCardNumber() - hand.seq.get(i+1).getCardNumber() != 1 )
				return false;
		}
		
		return true;
	}
	
	public  boolean threeofakind (Hand hand) {
		int count=0;
		int i=0;
		while (i<3) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i+1).getCardNumber() && hand.seq.get(i+1).getCardNumber() == hand.seq.get(i+2).getCardNumber() ){
					count++;
					i++;
			}
			else
				i++;
		}
		
		if(count == 3)
	 		return true;
	 	
	 	return false;
	}
	
	public  boolean twopair (Hand hand) {
		int count=0;
		int i=0;
		while (i<4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i+1).getCardNumber() ){
					count++;
					i++;
			}
			else
				i++;
		}
		
		if(count == 2)
	 		return true;
	 	
	 	return false;
	}
	public  boolean onepair (Hand hand) {
		int count=0;
		int i=0;
		while (i<4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i+1).getCardNumber() ){
					count++;
					i++;
			}
			else
				i++;
		}
		if(count == 1)
	 		return true;
	 	
	 	return false;
	}
	
	public  boolean fullhouse (Hand hand) {
		int count=0;
		int i=0;
		while (i<4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i+1).getCardNumber() ){
					count++;
					i++;
			}
			else
				i++;
		}
		if(count == 3)
	 		return true;
	 	
	 	return false;
	}
	
	public  boolean highCard (Hand hand) {
		
		return true;
	}


}	
	
public class Main{
	public static void main(String[] args){
			Poker game = new Poker();
			game.result();

	}

}

