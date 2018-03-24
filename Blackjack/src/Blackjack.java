import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	
	//define all cards by name and point value
	static ArrayList<Card> createCardArray() {
		Card ace = new Card(11, "Ace");
		Card two = new Card(2, "Two");
		Card three = new Card(3, "Three");
		Card four = new Card(4, "Four");
		Card five = new Card(5, "Five");
		Card six = new Card(6, "Six");
		Card seven = new Card(7, "Seven");
		Card eight = new Card(8, "Eight");
		Card nine = new Card(9, "Nine");
		Card ten = new Card(10, "Ten");
		Card jack = new Card(10, "Jack");
		Card queen = new Card(10, "Queen");
		Card king = new Card(10, "King");
		ArrayList<Card> cardList = new ArrayList<Card>();
		for (int i = 1; i <= 4; i++) {
			cardList.add(ace);
			cardList.add(two);
			cardList.add(three);
			cardList.add(four);
			cardList.add(five);
			cardList.add(six);
			cardList.add(seven);
			cardList.add(eight);
			cardList.add(nine);
			cardList.add(ten);
			cardList.add(jack);
			cardList.add(queen);
			cardList.add(king);
		}
		return cardList;
	} 
	
	static Card drawCard(ArrayList<Card> cardList) {
		Random random = new Random();
		int drawnCardIndex = random.nextInt(cardList.size());
		Card drawnCard = new Card(cardList.get(drawnCardIndex).value, cardList.get(drawnCardIndex).name);
		cardList.remove(drawnCardIndex);
		return drawnCard;
	}
	
	static int findPlayerHandValue(ArrayList<Card> playerHand) {
		int playerSum = 0;
		for (int i = 0; i<playerHand.size(); i++) {
			playerSum += playerHand.get(i).value;
		}
		return playerSum;
	}
	
	static ArrayList<Card> flipPlayerAce(ArrayList<Card> playerHand) {
		Card smallAce = new Card(1, "Ace");
		for (int i = 0; i<playerHand.size(); i++) {
			int cardValue = playerHand.get(i).value;
			if(cardValue == 11) {playerHand.set(i, smallAce);}
		}
		return playerHand;
	}
	
	static int findDealerHandValue(ArrayList<Card> dealerHand) {
		int dealerSum = 0;
		for (int i = 0; i<dealerHand.size(); i++) {
			dealerSum += dealerHand.get(i).value;
		}
		return dealerSum;
	}
	
	static ArrayList<Card> flipDealerAce(ArrayList<Card> dealerHand) {
		Card smallAce = new Card(1, "Ace");
		for (int i = 0; i<dealerHand.size(); i++) {
			int cardValue = dealerHand.get(i).value;
			if(cardValue == 11) {dealerHand.set(i, smallAce);}
		}
		return dealerHand;
	}
	
	static void displayCards(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		System.out.println("Your Hand: ");
		for (int i = 0; i<playerHand.size(); i++) {
			System.out.print(playerHand.get(i).name + " ");
		}
		System.out.println("\nDealer\'s Hand: ");
		System.out.print("UNKNOWN");
		for (int i = 1; i<dealerHand.size(); i++) {
			System.out.print(" " + dealerHand.get(i).name );
		}
		System.out.println("\n=========================");
	}
	
	static int playBlackjack() {
		ArrayList<Card> cardList = createCardArray();
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(drawCard(cardList));
		playerHand.add(drawCard(cardList));		
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(drawCard(cardList));
		dealerHand.add(drawCard(cardList));
		displayCards(playerHand, dealerHand);
		
		boolean playerBlackjack = findPlayerHandValue(playerHand) == 21;
//		if (playerBlackjack) { //alter this to first check for player blackjack, then return different outcomes based on dealer blackjack
//			if (playerBlackjack == dealerBlackjack) {
//				int outcome = 2;
//				System.out.println("You and the dealer both have blackjack. Push!");
//				return outcome;
//			}
//			else {
//				int otcome = 
//			}
//		}
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		boolean playerBusted = false;
		int outcome = 999;
		//everything in the while loop represents what happens in the player's turn
		while (!input.equals("stand") && !playerBusted) {
			displayCards(playerHand, dealerHand);
			if (input.equals("hit")) {
			playerHand.add(drawCard(cardList));
				//print the hand, then ask for another option
				displayCards(playerHand, dealerHand);
//				for (int i = 0; i<playerHand.size(); i++) {
//					System.out.print(playerHand.get(i).name + " ");
//				}
//				System.out.println(dealerHand.get(0).name);
				
				boolean over21 = findPlayerHandValue(playerHand) > 21;
				if (over21) {
					playerHand = flipPlayerAce(playerHand);
				}
				
				playerBusted = findPlayerHandValue(playerHand)  > 21;
				if (playerBusted) {
					
					System.out.println("Busted!");
					outcome = 1;
					return outcome;
					//1 = player loss
				}
				input = scanner.nextLine();
				
			}
			
			else {
			System.out.println("PICK A VALID OPTION YOU IMBECILE!");
			input = scanner.nextLine();
			}
			
		}
		int playerHandValue = findPlayerHandValue(playerHand);
		int dealerHandValue = findDealerHandValue(dealerHand);
		

		//At this point, we define how the dealer should behave
		boolean dealerBusted = false;
		while (!dealerBusted) {
			//while dealer sum < 17, dealer hits, recalculate dealer sum
			while (dealerHandValue < 17) {
				
				dealerHand.add(drawCard(cardList));
				dealerHandValue = findDealerHandValue(dealerHand);
				displayCards(playerHand, dealerHand);
				
				
				boolean over21 = findDealerHandValue(dealerHand) > 21;
				if (over21) {
					dealerHand = flipDealerAce(dealerHand);
				}
				dealerBusted = findDealerHandValue(dealerHand)  > 21;
				dealerHandValue = findDealerHandValue(dealerHand);
				if (dealerBusted) {
					displayCards(playerHand, dealerHand);
					System.out.println("Dealer Busted!");
					if (playerBlackjack) {
						outcome = 4;
					}
					else {
						outcome = 0;
					}
					return outcome;
				}
			}
			//at this point, the dealer has either busted, or we have dealer hand value >= 17
			//if dealer sum > player sum, the dealer should stand
			
			if (dealerHandValue > playerHandValue) {
				displayCards(playerHand, dealerHand);
				System.out.println("The Dealer\'s score is greater than yours. You lose");
				outcome = 1;
				return outcome;
				//1 = player loss
			}
			while (dealerHandValue < playerHandValue) {
				dealerHand.add(drawCard(cardList));
				dealerHandValue = findDealerHandValue(dealerHand);
				displayCards(playerHand, dealerHand);
				//System.out.println(dealerHandValue);
				
				
				boolean over21 = findDealerHandValue(dealerHand) > 21;
				if (over21) {
					dealerHand = flipDealerAce(dealerHand);
				}
				dealerBusted = findDealerHandValue(dealerHand)  > 21;
				dealerHandValue = findDealerHandValue(dealerHand);
				if (dealerBusted) {
					displayCards(playerHand, dealerHand);
					System.out.println("Dealer Busted!");
					if (playerBlackjack) {
						outcome = 4;
					}
					else {
						outcome = 0;
					}
					return outcome;
				}
			}
			displayCards(playerHand, dealerHand);
			if (dealerHandValue == playerHandValue) {
				System.out.println("Push!");
				outcome = 3;
				return outcome;
			}
			else {
				System.out.println("The Dealer\'s score is greater than yours. You lose.");
				outcome = 1;
				return outcome;
			}
			
					
		}
		//determinePayout method
		return outcome;
		
	}
	
		static double payoutCalculator(int outcome, double balance, double wager) {
			switch (outcome) {
			case 0: balance = balance + wager;
					break;
			case 1: balance = balance - wager;
					break;
			case 2:
					break;
			case 3:
					break;
			case 4: 
					balance = balance + (wager*1.5);
					break;
			}
			return balance;
		}
	
	public static void main(String[] args) {
		double balance = 100;
		boolean playAgain = true;
		Scanner scanner = new Scanner(System.in);
		while (playAgain == true && balance > 0) {
			System.out.println("How much money would you like to wager?");
			double wager = scanner.nextDouble();
			int outcome = playBlackjack();
			balance = payoutCalculator(outcome, balance, wager);
			
			System.out.println("Your balance is now $" + balance + ".");
			String input = "";
			System.out.println("Would you like to play again? Please answer \"yes\" or \"no\".");
			
			while (!input.equals("yes") && !input.equals("no")) {
				input = scanner.nextLine();
			}
		}
		scanner.close();
	}

}
