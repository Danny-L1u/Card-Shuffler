package Grade_11;
import java.util.*;

/**
 * This program shuffles a deck based on the users input, then takes every fourth output 
 * of the shuffled deck and organizes them by suit and value. First the program takes in 
 * 10 inputs of x, one input for every shuffle. One shuffle is when every x'th card to added
 * an empty stack of cards, and after that all even indexed cards are added to the top of the stack
 * and all odd indexed cards are added to the bottom of the stack. After being shuffled 10 times 
 * every fourth card is taken and added to a "West" hand. Then every card in the West hand is 
 * sorted by suits and by value from greatest to smallest. 
 * 
 * @author dliu
 *
 */
public class CardShuffler {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//Deck
		ArrayList<String> B = new ArrayList<String>(Arrays.asList(
				//Clubs
				"2C","3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "11C", "12C", "13C", "14C",
				//Diamonds
				"2D","3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "11D", "12D", "13D", "14D",
				//Hearts
				"2H","3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "11H", "12H", "13H", "14H",
				//Spades
				"2S","3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "11S", "12S", "13S", "14S"));

		//User inputs skip
		System.out.println("Welcome to Danny's Deck Shuffler!");
		System.out.println("Please input 10 numbers all between 2 and 9 (inclusive) seperated by a space:");
		String x[] = in.nextLine().split(" ");

		//Shuffle ten times based on user input
		for (int i = 0; i < 10; i++) {
			B = shuffle(B, Integer.parseInt(x[i]));
		}

		int k = 0;
		String West[] = new String[B.size()/4];
		//Add every fourth card from the shuffled deck into the "West" hand
		for (int i = 3; i < B.size(); i += 4) {
			West[k] = B.get(i);
			k++;
		}

		//Output prompt
		System.out.println("This is West's hand, ordered by suits and value (from largest to smallest):");
		//Output an all Spades cards from the West and from largest to smallest
		Organize("spades", West);
		//Output an all Hearts cards from the West and from largest to smallest
		Organize("hearts", West);
		//Output an all Diamonds cards from the West and from largest to smallest
		Organize("diamonds", West);
		//Output an all Clubs cards from the West and from largest to smallest
		Organize("clubs", West);
	}

	/**
	 * Takes in an arrayList, the deck (B), and an int value, x and shuffles the deck based on
	 * the value of x. Every x'th card from the deck will be added to another stack, then once 
	 * every x'th card is added, all the even indexed cards left are added to the top of the other stack
	 * and all the odd indexed card are added to the bottom of stack A.
	 * 
	 * @param B
	 * @param x
	 * @return A
	 */
	public static ArrayList<String> shuffle(ArrayList<String> B, int x) {
		ArrayList<String> A = new ArrayList<String>();

		//Add every x'th card from stack B to stack A
		for (int i = x - 1; i < B.size(); i+= x) {
			A.add(B.get(i));
			B.remove(i);
			i--;
		}

		//Take rest of cards in stack B and add all even indexed cards on top of stack A and 
		//add all odd indexed cards to the bottom of stack A
		for (int k = 0; k < B.size(); k++) {
			if (k % 2 == 0) {
				A.add(B.get(k));
			} 
			else {
				A.add(0, B.get(k));
			} 
		}

		//return the shuffled deck
		return A;
	}


	public static void Organize(String suit, String[] West) {
		//Add all of a specified suit cards from the West hand into an array
		ArrayList<Integer> Suit = new ArrayList<Integer>();;

		//Add all of a specified suit from array West into arrayList Suit
		for (int i = 0; i < West.length; i++) {
			//Take the first letter of the suit specified and add all matching suits
			//into the array, leaving out the suit indicator (letter).
			if (West[i].indexOf(suit.substring(0, 1).toUpperCase()) != -1) {
				//Add only the number into arrayList Suit.
				Suit.add(Integer.parseInt(West[i].substring(0, West[i].length() - 1)));
			}
		}

		//If there are no cards from that suit, just don't output anything
		if (Suit.size() != 0) {
			System.out.print(" " + suit + ": ");

			//Organize the cards from the suit from largest to smallest using a selection sort method
			Integer[] Organize = Suit.toArray(new Integer[0]);
			selectionSort(Organize);

			//Output all values of the organize array
			for (int i = Organize.length - 1; i >= 0; i--) {

				//Output proper card "symbols"
				//if value is less than 10, just output the number
				if (Organize[i] < 10) {
					System.out.print(Organize[i] + " ");
				}
				//if value is 10, output "T"
				else if(Organize[i] == 10) {
					System.out.print("T ");
				}
				//if value is 11, output "J"
				else if(Organize[i] == 11) {
					System.out.print("J ");
				}
				//if value is 12, output "Q"
				else if(Organize[i] == 12) {
					System.out.print("Q ");
				}
				//if value is 13, output "K"
				else if(Organize[i] == 13) {
					System.out.print("K ");
				}
				//if value is anything else (only 14 is left), output "A"
				else {
					System.out.print("A ");
				}
			}
		}
	}


	/**
	 * This method is a selection sort method, it sorts the values 
	 * from an Integer array from lowest to highest.
	 * 
	 * @param organize
	 */
	public static void selectionSort(Integer[] organize) {

		//Go through all the values of the array, every iteration
		//the index where it starts increases by 1 and starts at 0
		for (int i = 0; i < organize.length - 1; i++) {
			//s starts as index i
			int s = i;
			//Go through all the values of the array
			for (int j = i + 1; j < organize.length; j++) {
				//Compare and see which value is smaller
				if (organize[j] < organize[s]){
					//s becomes the index of the smaller number
					s = j;
				}
			}

			//Switch the s indexed value to the i index and switch the i indexed
			//value to the s index
			int t = organize[i];
			organize[i] = organize[s];
			organize[s] = t;
		}
	}

}
