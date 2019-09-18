package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        
        @Test
	// On vérifie que si la somme insere n'est pas suffisante on n'iprime pas le ticket
	// S3 : impression impossible si balace inferieure au prix du ticket
	public void doNotPrintIfInsufisantBalance() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(20);
                assertFalse("Impression possible meme si balance inferieure au prix du ticket", machine.printTicket());
	}
        
        
        @Test
	// On vérifie que la somme insere est suffisante pour imprimer le ticket
	// S4 : impression possible si balace egal au prix du ticket
	public void PrintIfSufisantBalance() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(50);
                assertTrue("impression impossible si balance egal au prix du ticket", machine.printTicket());
	}
        
        @Test
	// On vérifie que la somme insere est suffisante pour imprimer le ticket
	// S4 : impression possible si balace superieure au prix du ticket
	public void PrintIfSuperiorBalance() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(60);
                assertTrue("impression impossible si balance superieure au prix du ticket", machine.printTicket());
	}
        
        
        @Test
	// On vérifie que lea balance apres impression est decremente du prix du ticket
	// S5 : la balance est mise a jour apres impression
	public void changeBalanceAfterPrint() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(PRICE+10);
                machine.printTicket();
		assertEquals("modification de la balance apres impression incorecte", 10, machine.getBalance());
	}
        
        
        @Test
	// On vérifie que le montant total collecte est mise a jour apres impression
	// S6 : le montant total collecte est mise a jour apres impression
	public void TotalUpdatedAfterPrint() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(PRICE+PRICE);
                machine.printTicket();
                machine.printTicket();
		assertEquals("Total non mis a jour apres impression", PRICE+PRICE, machine.getTotal());
	}
        
        @Test
	// On vérifie que la machine rend la monnaie correctement
	// S7 : la machine rend la monnaie correctement
	public void machineRefundgoodAmountOfMoney() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(10);
		assertEquals("la machine ne rend pas la monnaie correctement", 10, machine.refund());
	}
        
        @Test
	// On vérifie qu'apres avoir rendu la monnaie la balnce est reise a 0
	// S8 : la balance est remise a 0 apres avoir rendu la monnaie
	public void balanceSetToZeroAfterRefund() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(10);
                machine.refund();
		assertEquals("Balance non remise a 0 apres avoir rendu la monnaie", 0, machine.getBalance());
	}
        
        @Test
	// On vérifie que le montant insere nest pas negatif
	// S9 : le montant insere ne doit pas etre negatif
	public void iseredAmoundCantBeNegative() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
                machine.insertMoney(10);
                int bal=machine.getBalance();
                try{
                    machine.insertMoney(-10);
                    fail("le montant insere est negatif");
                }catch(Exception e){
                    assertEquals("le montant insere est negatif", bal, machine.getBalance());
                }
	}
        
        @Test
	// On vérifie qu'on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	public void ticketPriceCantBeNegative() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
               TicketMachine machine2;
                try{
                   machine2=new TicketMachine(-PRICE);
                   fail("le prix du ticket est negatif");
                }catch(Exception e){
                   machine2=new TicketMachine(PRICE);
                   assertEquals("le prix du ticket est negatif", 50, machine2.getPrice());
                }
	}

}
