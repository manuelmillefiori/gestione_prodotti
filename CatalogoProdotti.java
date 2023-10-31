/**
 * @author Manuel Millefiori
 * @date 2023-10-31
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CatalogoProdotti
{
   /** ArrayList di amministratori che hanno accesso al catalogo */
   private List<Amministratore> amministratori = new ArrayList<>();

   /** ArrayList di utenti che hanno accesso al catalogo */
   private List<Utente> utenti = new ArrayList<>();

   /** ArrayList di prodotti relativi al catalogo */
   private List<Prodotto> prodotti = new ArrayList<>();

   /** Handle per l'inserimento dei dati via tastiera */
   private Scanner input = new Scanner(System.in);

   /**
    * @brief
    * Costruttore per l'avvio dell'applicazione
    */
   public CatalogoProdotti()
   {
      // Variabile per conservare l'opzione scelta dall'utente
      int opzione;

      // Token per effettuare le varie operazioni
      String token;

      // Aggiungo dei dati di prova
      utenti.add(new Utente("manuel", "manuelino"));
      amministratori.add(new Amministratore("geppo", "geppetto"));

      do
      {
         // Stampa menù accesso
         System.out.println("Scegli il tipo d'accesso:");
         System.out.println("1 -> Accedi come utente");
         System.out.println("2 -> Accedi come amministratore");
         System.out.println("9 -> Esci");

         System.out.print("Opzione: ");
         opzione = input.nextInt();

         switch (opzione)
         {
            case 1:
            {
               // Inserimento credenziali utente
               System.out.print("Username: ");
               String username = input.next();
               System.out.print("Password: ");
               String password = input.next();

               token = this.loginUtente(username, password);

               // Verifico il corretto accesso
               if (token != null)
               {
                  System.out.println("\nAccesso effettuato correttamente!\n");

                  // Mostro il menù dell'utente
                  menuUtente(token);
                  
               }
               else
               {
                  System.out.println("Credenziali errate!");
               }

               break;
            }
            case 2:
            {
               // Inserimento credenziali utente
               System.out.print("Username: ");
               String username = input.next();
               System.out.print("Password: ");
               String password = input.next();

               token = this.loginAmministratore(username, password);

               // Verifico il corretto accesso
               if (token != null)
               {
                  System.out.println("\nAccesso effettuato correttamente!\n");

                  // Mostro il menù dell'utente
                  menuAmministratore(token);
                  
               }
               else
               {
                  System.out.println("\nCredenziali errate!\n");
               }

               break;
            }
            case 9:
            {
               System.out.println("Uscita dal programma!");

               break;
            }
            default:
            {
               System.out.println("Scelta non valida!");

               break;
            }
         }
      } while (opzione != 9);

      // Chiudo l'handle
      input.close();
   }

   /**
    * @brief
    * Metodo per mostrare il menù dell'utente
    * 
    * @param token
    * Token per verificare l'accesso
    * 
    * @return
    * true = Accesso al menù effettuato correttamente
    * false = Accesso al menù negato
    */
   private boolean menuUtente(String token)
   {
      // Flag per verificare l'accesso
      boolean accesso = this.verificaAccessoUtente(token);

      // Variabile per conservare l'opzione scelta dall'utente
      int opzione;

      // Verifico l'accesso
      if (accesso)
      {
         do
         {
            System.out.println(" --- MENU UTENTE --- ");
            System.out.println("1 -> Visualizza prodotti");
            System.out.println("2 -> Ricerca prodotto");
            System.out.println("9 -> Esci");

            System.out.print("Opzione: ");
            opzione = input.nextInt();

            switch (opzione)
            {
               case 1:
               {
                  // Visualizzo i prodotti
                  visualizzaProdotti(token);

                  break;
               }
               case 2:
               {
                  // Svuoto il buffer
                  input.nextLine();

                  // Inserimento dati del vecchio prodotto
                  System.out.print("Nome prodotto da ricercare: ");
                  String nomeDaRicercare = input.nextLine();

                  // Ricerco il prodotto
                  Prodotto prodotto = ricercaProdotto(nomeDaRicercare, token);

                  // Verifico che il prodotto sia stato trovato
                  if (prodotto != null)
                  {
                     System.out.println("\n" + prodotto + "\n");
                  }
                  else
                  {
                     System.out.println("\nLa ricerca non ha restituito risultati\n");
                  }

                  break;
               }
               case 9:
               {
                  System.out.println("Uscita dal menù!");

                  break;
               }
               default:
               {
                  System.out.println("Scelta non valida!");

                  break;
               }
            }
         } while (opzione != 9);
      }

      // Restituisco l'esito dell'accesso
      return accesso;
   }

   /**
    * @brief
    * Metodo per mostrare il menù dell'amministratore
    * 
    * @param token
    * Token per verificare l'accesso
    * 
    * @return
    * true = Accesso al menù effettuato correttamente
    * false = Accesso al menù negato
    */
   private boolean menuAmministratore(String token)
   {
      // Flag per verificare l'accesso
      boolean accesso = this.verificaAccessoAmministratore(token);

      // Variabile per conservare l'opzione scelta dall'amministratore
      int opzione;

      // Verifico l'accesso
      if (accesso)
      {
         do
         {
            System.out.println(" --- MENU AMMINISTRATORE --- ");
            System.out.println("1 -> Visualizza prodotti");
            System.out.println("2 -> Aggiungi prodotto");
            System.out.println("3 -> Rimuovi prodotto");
            System.out.println("4 -> Modifica prodotto");
            System.out.println("5 -> Ricerca prodotto");
            System.out.println("9 -> Esci");

            System.out.print("Opzione: ");
            opzione = input.nextInt();

            switch (opzione)
            {
               case 1:
               {
                  // Visualizzo i prodotti
                  visualizzaProdotti(token);

                  break;
               }
               case 2:
               {
                  // Svuoto il buffer
                  input.nextLine();

                  // Inserimento dati del prodotto
                  System.out.print("Nome: ");
                  String nome = input.nextLine();
                  System.out.print("Prezzo: ");
                  float prezzo = input.nextFloat();
                  System.out.print("Quantità: ");
                  int quantita = input.nextInt();

                  // Aggiungo il prodotto
                  if (aggiungiProdotto(nome, prezzo, quantita, token))
                  {
                     System.out.println("\nProdotto inserito correttamente!\n");
                  }
                  else
                  {
                     System.out.println("\nInserimento prodotto fallito!\n");
                  }

                  break;
               }
               case 3:
               {
                  // Svuoto il buffer
                  input.nextLine();

                  // Inserimento dati del prodotto
                  System.out.print("Nome: ");
                  String nome = input.nextLine();

                  // Rimuovo il prodotto
                  if (rimuoviProdotto(nome, token))
                  {
                     System.out.println("\nProdotto rimosso correttamente!\n");
                  }
                  else
                  {
                     System.out.println("\nRimozione prodotto fallita\n");
                  }
               }
               case 4:
               {
                  // Svuoto il buffer
                  input.nextLine();

                  // Inserimento dati del vecchio prodotto
                  System.out.print("Nome prodotto da ricercare: ");
                  String nomeDaRicercare = input.nextLine();

                  // Inserimento dati nuovo prodotto
                  System.out.print("Nome: ");
                  String nome = input.nextLine();
                  System.out.print("Prezzo: ");
                  float prezzo = input.nextFloat();
                  System.out.print("Quantità: ");
                  int quantita = input.nextInt();

                  // Aggiorno il prodotto
                  if (aggiornaProdotto(nomeDaRicercare, nome, prezzo, quantita, token))
                  {
                     System.out.println("\nProdotto aggiornato correttamente\n");
                  }
                  else
                  {
                     System.out.println("\nAggiornamento prodotto fallito!\n");
                  }

                  break;
               }
               case 5:
               {
                  // Svuoto il buffer
                  input.nextLine();

                  // Inserimento dati del vecchio prodotto
                  System.out.print("Nome prodotto da ricercare: ");
                  String nomeDaRicercare = input.nextLine();

                  // Ricerco il prodotto
                  Prodotto prodotto = ricercaProdotto(nomeDaRicercare, token);

                  // Verifico che il prodotto sia stato trovato
                  if (prodotto != null)
                  {
                     System.out.println("\n" + prodotto + "\n");
                  }
                  else
                  {
                     System.out.println("\nLa ricerca non ha restituito risultati\n");
                  }

                  break;
               }
               case 9:
               {
                  System.out.println("Uscita dal menù!");

                  break;
               }
               default:
               {
                  System.out.println("Scelta non valida!");

                  break;
               }
            }
         } while (opzione != 9);
      }

      // Restituisco l'esito dell'accesso
      return accesso;
   }

   /**
    * @brief
    * Metodo per la ricerca di un determinato prodotto
    * identificato dal nome
    * 
    * @param nomeDaRicercare
    * Nome del prodotto da ricercare
    * 
    * @param token
    * Token con cui verificare l'accesso dell'utente
    * 
    * @return
    * Riferimento al prodotto trovato
    * null = Nessun prodotto trovato
    */
   private Prodotto ricercaProdotto(String nomeDaRicercare, String token)
   {
      // Riferimento al prodotto
      // Inizializzazione pessimistica
      Prodotto prodotto = null;

      // Flag per verificare l'accesso
      boolean accesso = verificaAccessoUtente(token);

      // Verifico l'accesso tramite amministratore
      if (!accesso)
      {
         accesso = verificaAccessoAmministratore(token);
      }

      // Verifico l'accesso
      if (accesso)
      {
         // Flag per verificare di aver trovato il prodotto
         boolean trovato = false;

         // Visualizzo i prodotti
         for (int j = 0; j < prodotti.size() && !trovato; j++)
         {
            // Verifico l'esistenza del prodotto
            if (prodotti.get(j).esiste(nomeDaRicercare))
            {
               // Inizializzo il prodotto da restituire
               prodotto = prodotti.get(j);

               // Aggiorno il flag
               trovato = true;
            }
         }
      }

      // Restituisco il riferimento al prodotto
      return prodotto;
   }

   /**
    * @brief
    * Metodo per aggiornare il prodotto
    * 
    * @param nomeDaRicercare
    * Nome del prodotto da ricercare
    * 
    * @param nome
    * Nuovo nome del prodotto
    * 
    * @param prezzo
    * Nuovo prezzo del prodotto
    * 
    * @param quantita
    * Nuova quantità del prodotto
    * 
    * @param token
    * Token da verificare
    * 
    * @return
    * true = Prodotto aggiornato correttamente
    * false = Aggiornamento prodotto fallito
    */
   private boolean aggiornaProdotto(String nomeDaRicercare, String nome, float prezzo, int quantita, String token)
   {
      // Flag per verificare se il prodotto è stato aggiornato
      // Inizializzazione pessimsitica
      boolean aggiornato = false;

      // Verifica amministratore
      if (verificaAccessoAmministratore(token))
      {
         // Flag per verificare la preesistenza del prodotto
         // Inizializzazione pessimistica
         boolean esiste = false;

         // Indice del prodotto da aggiornare
         int indice = -1;

         // Scorro i prodotti per verificare l'esistenza del prodotto
         for (int j = 0; j < prodotti.size() && !esiste; j++)
         {
            esiste = prodotti.get(j).esiste(nomeDaRicercare);
            indice = j;
         }

         // Se non esiste aggiungo il prodotto
         if (esiste)
         {
            // Aggiorno il prodotto
            prodotti.set(indice, new Prodotto(nome, prezzo, quantita));

            // Aggiorno il flag
            aggiornato = true;
         }
      }

      // Restitusico il flag
      return aggiornato;
   }

   /**
    * @brief
    * Metodo per rimuovere un prodotto dal catalogo
    * identificato dal nome
    * 
    * @param nome
    * Nome del prodotto
    * 
    * @param token
    * Token dell'amministratore
    * 
    * @return
    * true = Prodotto rimosso correttamente
    * false = Nessun prodotto trovato
    */
   private boolean rimuoviProdotto(String nome, String token)
   {
      // Flag per verificare la rimozione
      boolean rimosso = false;

      // Verifico l'accesso come amministratore
      if (verificaAccessoAmministratore(token))
      {
         // Rimuovo il prodotto identificato dal nome
         rimosso = prodotti.removeIf(prodotto ->
            prodotto.esiste(nome)
         );
      }

      // Restituisco il flag
      return rimosso;
   }

   /**
    * @brief
    * Metodo per aggiungere un prodotto al catalogo
    * 
    * @param nome
    * Nome del prodotto
    * 
    * @param prezzo
    * Prezzo del prodotto
    * 
    * @param quantita
    * Quantità del prodotto da inserire
    * 
    * @return
    * true = Prodotto inserito correttamente
    * false = Inserimento del prodotto fallito
    */
   private boolean aggiungiProdotto(String nome, float prezzo, int quantita, String token)
   {
      // Flag per verificare l'inserimento del prodotto
      boolean inserito = false;

      // Verifico l'accesso come amministratore
      if(verificaAccessoAmministratore(token))
      {
         // Flag per verificare la preesistenza del prodotto
         // Inizializzazione pessimistica
         boolean esiste = false;

         // Scorro i prodotti per verificare l'esistenza del prodotto
         for (int j = 0; j < prodotti.size() && !esiste; j++)
         {
            esiste = prodotti.get(j).esiste(nome);
         }

         // Se non esiste aggiungo il prodotto
         if (!esiste)
         {
            // Aggiungo il prodotto
            prodotti.add(new Prodotto(nome, prezzo, quantita));

            // Aggiorno il flag
            inserito = true;
         }
      }

      // Restituisco il flag
      return inserito;
   }

   /**
    * @brief
    * Metodo per visualizzare tutti i prodotti
    * 
    * @param token
    * Token da verificare per l'accesso
    * 
    * @return
    * true = Accesso alla visualizzazione dei prodotti
    * effettuato correttamente
    * false = Accesso alla visualizzazione dei prodotti
    * fallito
    */
   private boolean visualizzaProdotti(String token)
   {
      // Flag per verificare l'accesso
      boolean accesso = verificaAccessoUtente(token);

      // Verifico l'accesso tramite amministratore
      if (!accesso)
      {
         accesso = verificaAccessoAmministratore(token);
      }

      // Verifico l'accesso
      if (accesso)
      {
         // Vado a capo
         System.out.println();

         // Visualizzo i prodotti
         for (int j = 0; j < prodotti.size(); j++)
         {
            System.out.println("Prodotto " + (j + 1) + "\n" + prodotti.get(j));
         }

         // Vado a capo
         System.out.println();
      }

      // Restituisco il flag
      return accesso;
   }

   /**
    * @brief
    * Metodo per verificare l'accesso come utente
    * 
    * @param token
    * Token da verificare
    * 
    * @return
    * true = Accesso verificato correttamente
    * false = Accesso negato
    */
   private boolean verificaAccessoUtente(String token)
   {
      // Flag per verificare l'accesso
      // Inizializzazione pessimistica
      boolean accesso = false;

      // Verifico il token per ogni utente
      for (int j = 0; j < utenti.size() && !accesso; j++)
      {
         // Verifico l'accesso del singolo utente
         accesso = utenti.get(j).verificaAccesso(token);
      }

      // Restituisco il flag
      return accesso;
   }

   /**
    * @brief
    * Metodo per verificare l'accesso come amministratore
    * 
    * @param token
    * Token da verificare
    * 
    * @return
    * true = Accesso verificato correttamente
    * false = Accesso negato
    */
   private boolean verificaAccessoAmministratore(String token)
   {
      // Flag per verificare l'accesso
      // Inizializzazione pessimistica
      boolean accesso = false;

      // Verifico il token per ogni amministratore
      for (int j = 0; j < amministratori.size() && !accesso; j++)
      {
         // Verifico l'accesso del singolo utente
         accesso = amministratori.get(j).verificaAccesso(token);
      }

      // Restituisco il flag
      return accesso;
   }

   /**
    * @brief
    * Metodo per effettuare il login come utente
    * 
    * @param username
    * Username dell'utente
    * 
    * @param password
    * Password dell'utente
    * 
    * @return
    * Token da utilizzare per effettuare le operazioni sul catalogo
    * null = Accesso negato
    */
   private String loginUtente(String username, String password)
   {
      // Token da restituire
      String token = null;

      // Flag per verificare l'accesso
      // Inizializzazione pessimistica
      boolean accesso = false;

      // Ricerca del token
      for (int j = 0; j < utenti.size() && !accesso; j++)
      {
         // Ottengo il token
         token = utenti.get(j).login(username, password);

         // Verifico che il token sia stato realmente restituito
         if (token != null)
         {
            // Aggiorno il flag
            accesso = true;
         }
      }

      // Restituisco il token
      return token;
   }

   /**
    * @brief
    * Metodo per effettuare il login come amministratore
    * 
    * @param username
    * Username dell'amministratore
    * 
    * @param password
    * Password dell'amministratore
    * 
    * @return
    * Token da utilizzare per effettuare le operazioni sul catalogo
    * null = Accesso negato
    */
   private String loginAmministratore(String username, String password)
   {
      // Token da restituire
      String token = null;

      // Flag per verificare l'accesso
      // Inizializzazione pessimistica
      boolean accesso = false;

      // Ricerca del token
      for (int j = 0; j < amministratori.size() && !accesso; j++)
      {
         // Ottengo il token
         token = amministratori.get(j).login(username, password);

         // Verifico che il token sia stato realmente restituito
         if (token != null)
         {
            // Aggiorno il flag
            accesso = true;
         }
      }

      // Restituisco il token
      return token;
   }
}
