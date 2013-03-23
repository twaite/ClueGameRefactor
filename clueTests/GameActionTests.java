package clueTests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
import clueGame.Suggestion;

public class GameActionTests {
	
	Board board;
	Solution solution;
	ArrayList<BoardCell> cells;
	ClueGame game;
	ArrayList<Player> players;
	ArrayList<Card> cards;
	Card mustardCard;
	Card scarletCard;
	Card plumCard;
	Card whiteCard;
	Card greenCard;
	Card peacockCard;
	Card candlestickCard;
	Card ropeCard;
	Card daggerCard;
	Card wrenchCard;
	Card revolverCard;
	Card pipeCard;
	Card studyCard;
	Card kitchenCard;
	Card libraryCard;
	Card conservatoryCard;
	
	@Before
	public void initialize() {
		Solution solution = new Solution("Miss Scarlet", "Study", "Candlestick");
		board = new Board();
		try{
			board.loadConfigFiles();
		}
		catch(BadConfigFormatException e){
			e = new BadConfigFormatException(board);
			System.out.println(e.toString());
		}
		cells = board.getCells();
		game = new ClueGame();
		game.loadConfigFiles();
		players = game.getPlayers();
		cards = game.getCards();
		
		//Characters
		mustardCard = new Card(Card.cardType.PERSON, "Colonel Mustard");
		scarletCard = new Card(Card.cardType.PERSON, "Miss Scarlet");
		plumCard = new Card(Card.cardType.PERSON, "Mr. Plum");
		whiteCard = new Card(Card.cardType.PERSON, "Mrs. White");
		greenCard = new Card(Card.cardType.PERSON, "Mr. Green");
		peacockCard = new Card(Card.cardType.PERSON, "Mrs. Peacock");
		
		//Weapons
		candlestickCard = new Card(Card.cardType.WEAPON, "Candlestick");
		ropeCard = new Card(Card.cardType.WEAPON, "Rope");
		daggerCard = new Card(Card.cardType.WEAPON, "Dagger");
		wrenchCard = new Card(Card.cardType.WEAPON, "Wrench");
		revolverCard = new Card(Card.cardType.WEAPON, "Revolver");
		pipeCard = new Card(Card.cardType.WEAPON, "Lead Pipe");
		
		//Rooms
		kitchenCard = new Card(Card.cardType.ROOM, "Kitchen");
		studyCard = new Card(Card.cardType.ROOM, "Study");
		conservatoryCard = new Card(Card.cardType.ROOM, "Conservatory");
		libraryCard = new Card(Card.cardType.ROOM, "Library");

	}

	@BeforeClass
	public static void setup() {
		
	}

	@Test
	public void TestDeal() {
		int cardsDealt = 0;
		//COUNT THE NUMBER OF CARDS DEALT
		Player mustardPlayer = new Player();
		for ( Player p : players ) {
			cardsDealt += p.getCards().size();
			assertTrue( p.getCards().size() == 3 );
			if ( p.getCards().contains(mustardCard) ) {
				mustardPlayer = p;
				players.remove(p); // Removes p from the list so we can search the rest later.
			}
		}

		//CHECK THAT THE NUMBER OF CARDS DEALT IS CORRECT
		assertEquals(18, cardsDealt);

		//CHECK TO SEE THAT THE SAME CARD WASN'T DEALT TO 2 PEOPLE:
		for ( Player play : players ) {
			assertFalse(play.getCards().contains(mustardCard));
		}
		players.add(mustardPlayer);

	}

	@Test
	public void TestLoadPlayers() {
		
		// CHECK THE SIZE OF THE players ARRAYLIST IS CORRECT
		assertEquals(6, players.size());
		
		// CHECK TO SEE THAT THE NAMES ARE CORRECT FOR EACH PLAYER
		assertEquals("Miss Scarlet", players.get(0).getName());
		assertEquals("Mr. Green", players.get(1).getName());
		assertEquals("Mrs. White", players.get(2).getName());
		assertEquals("Colonel Mustard", players.get(3).getName());
		assertEquals("Professor Plum", players.get(4).getName());
		assertEquals("Mrs. Peacock", players.get(5).getName());

		// CHECK THAT THE COLOR IS CORRECT FOR EACH PLAYER
		assertEquals(Color.red, players.get(0).getColor());
		assertEquals(Color.green, players.get(1).getColor());
		assertEquals(Color.white, players.get(2).getColor());
		assertEquals(Color.yellow, players.get(3).getColor());
		assertEquals(Color.magenta, players.get(4).getColor());
		assertEquals(Color.blue, players.get(5).getColor());
		
	}

	@Test
	public void TestLoadCards() {
		//CHECK THAT DECK HAS CORRECT NUMBER OF CARDS
		assertEquals(21, cards.size());
		int numWeapons = 0;
		int numPeople = 0;
		int numRooms = 0;
		//CHECK THAT DECK HAS CORRECT NUMBER OF EACH TYPE OF CARD
		for ( Card c : cards ) {
			if ( c.getType() == Card.cardType.WEAPON )
				++numWeapons;
			else if ( c.getType() == Card.cardType.PERSON )
				++numPeople;
			else if ( c.getType() == Card.cardType.ROOM )
				++numRooms;
		}

		assertEquals(6, numWeapons);
		assertEquals(6, numPeople);
		assertEquals(9, numRooms);

		//CHECK THAT DECK CONTAINS 3 SPECIFIC NAMED CARDS
		assertTrue(cards.contains(mustardCard));
		assertTrue(cards.contains(pipeCard));
		assertTrue(cards.contains(kitchenCard));

	}

	@Test
	public void TestCheckAccusation() {
		//ACCUSATION IS CORRECT
		assertTrue(game.checkAccusation(new Solution("Miss Scarlet", "Study", "Candlestick")));

		//ACCUSATION IS INCORRECT - PERSON IS WRONG
		assertFalse(game.checkAccusation(new Solution("a", "Study", "Candlestick")));

		//ACCUSATION IS INCORRECT - ROOM IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "b", "Candlestick")));

		//ACCUSATION IS INCORRECT - WEAPON IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "Study", "c")));

		//ACCUSATION IS INCORRECT - ALL THREE ARE WRONG
		assertFalse(game.checkAccusation(new Solution("a", "b", "c")));
	}

	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just four targets
		board.calcTargets(2, 8, 2);
		int loc_0_8_Tot = 0;
		int loc_1_7_Tot = 0;
		int loc_3_7_Tot = 0;
		int loc_4_8_Tot = 0;
		
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(0, 8)))
				loc_0_8_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(1, 7)))
				loc_1_7_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(3, 7)))
				loc_3_7_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(4, 8)))
				loc_4_8_Tot++;
			else
				fail("Invalid target selected");
		}
		
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_0_8_Tot + loc_1_7_Tot + loc_3_7_Tot + loc_4_8_Tot);
		
		// Ensure each target was selected more than once
		assertTrue(loc_0_8_Tot > 1);
		assertTrue(loc_1_7_Tot > 1);
		assertTrue(loc_3_7_Tot > 1);
		assertTrue(loc_4_8_Tot > 1);
	}

	@Test
	public void TestDisproveSuggestionOnePlayerOneMatch() {
		//test one player, one correct match
		Player player = new ComputerPlayer();
		
		//deal six cards to player
		player.addCard(mustardCard);
		player.addCard(scarletCard);
		player.addCard(pipeCard);
		player.addCard(candlestickCard);
		player.addCard(studyCard);
		player.addCard(kitchenCard);
		
		//disproveSuggestion tests
		assertEquals("Revolver", player.disproveSuggestion(mustardCard, studyCard, revolverCard));
		assertEquals("Conservatory", player.disproveSuggestion(mustardCard, conservatoryCard, pipeCard));
		assertEquals("Mr. Plum", player.disproveSuggestion(plumCard, studyCard, pipeCard));
		assertEquals(null , player.disproveSuggestion(mustardCard, studyCard, pipeCard));
		
	}
	
	@Test
	public void TestDisproveSuggestionOnePlayerMultipleMatch() {
		Player player = new ComputerPlayer();
		
		//test one player, multiple possible matches
				player = new ComputerPlayer();
				player.addCard(mustardCard);
				player.addCard(kitchenCard);
				player.addCard(pipeCard);
				
				//Run 100 times
				int mustardDisp = 0;
				int kitchenDisp = 0;
				int pipeDisp = 0;
				
				for(int i = 0; i < 100; ++i) {
					String dispCard = player.disproveSuggestion(scarletCard, studyCard, candlestickCard);
					if ( dispCard == mustardCard.getName() )
						mustardDisp++;
					else if ( dispCard == kitchenCard.getName() )
						kitchenDisp++;
					else if ( dispCard == pipeCard.getName() ) 
						pipeDisp++;
					else 
						fail("Gave card player does not have");
				}
				
				//check that each card was given at least once
				assertTrue( mustardDisp > 1 );
				assertTrue( kitchenDisp > 1 );
				assertTrue( pipeDisp > 1 );
	}
	
	@Test
	public void TestPlayersQueried() {
		//test all players are queried
				ArrayList<ComputerPlayer> compPlayers = new ArrayList<ComputerPlayer>();
				
				ComputerPlayer player = new ComputerPlayer("Mr. Plum", board.calcIndex(18, 3));
				player.addCard(mustardCard);
				compPlayers.add(player);
				compPlayers.get(0).updateSeen(scarletCard);
				compPlayers.get(0).updateSeen(pipeCard);
				compPlayers.get(0).updateSeen(studyCard);
				compPlayers.get(0).updateSeen(conservatoryCard);
				
				player = new ComputerPlayer("Colonel Mustard", board.calcIndex(2,22));
				compPlayers.add(player);
				compPlayers.get(1).addCard(scarletCard);
				
				player = new ComputerPlayer();
				compPlayers.add(player);
				compPlayers.get(2).addCard(pipeCard);
				
				player = new ComputerPlayer("Miss Scarlet" , board.calcIndex(1, 1));
				compPlayers.add(player);
				compPlayers.get(3).addCard(conservatoryCard);
				
				HumanPlayer human = new HumanPlayer();
				human.addCard(studyCard);
				
				compPlayers.get(0).createSuggestion("kitchen");
				assertEquals(null, game.handleSuggestion(compPlayers.get(0)));
				
				compPlayers.get(1).createSuggestion("study");
				assertEquals("Study", game.handleSuggestion(human));
				
				compPlayers.get(3).createSuggestion("Convervatory");
				assertEquals(null, game.handleSuggestion(human));
				
				human.createSuggestion("Miss Scarlett", "Conservatory", "Lead Pipe");
				int compOne = 0;
				int compTwo = 0;
				int compThree = 0;
				
				for ( int i = 0; i < 100; ++i ) {
					String response = game.handleSuggestion(human).getName();
					if ( response == "Miss Scarlet" ) 
						compOne++;
					else if ( response == "Lead Pipe" )
						compTwo++;
					else if ( response == "Conservatory" ) 
						compThree++;
					else
						fail("Gave card player does not have");
				}
				
				assertTrue(compOne > 1);
				assertTrue(compTwo > 1);
				assertTrue(compThree > 1);
	}
			
	@Test
	public void TestCreateSuggestion() {
		ComputerPlayer player = new ComputerPlayer("Miss. Scarlet", board.calcIndex(18, 3));
		
		//Show player all but two of each card.
		player.updateSeen(scarletCard);
		player.updateSeen(plumCard);
		player.updateSeen(peacockCard);
		player.updateSeen(whiteCard);
		player.updateSeen(pipeCard);
		player.updateSeen(ropeCard);
		player.updateSeen(candlestickCard);
		player.updateSeen(wrenchCard);
		player.updateSeen(daggerCard);
		
		int greenPick = 0;
		int mustardPick = 0;
		
		for(int i = 0; i < 100; ++i) {
			player.createSuggestion("Kitchen");
			if(player.getSuggestion().getPerson() == "Mr. Green")
				greenPick++;
			if(player.getSuggestion().getPerson() == "Colonel Mustard")
				mustardPick = 0;
		}
		
		assertTrue(greenPick > 1);
		assertTrue(mustardPick > 1);
		assertEquals("Revolver", player.getSuggestion().getWeapon());
		assertEquals("Kitchen", player.getSuggestion().getRoom());
	}
}