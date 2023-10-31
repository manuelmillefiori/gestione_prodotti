/**
 * @author Manuel Millefiori
 * @date 2023-10-31
 */

import java.security.SecureRandom;

public class Utente
{
   private String username;
   private String password;
   private String token;

   /**
    * @brief
    * Costruttore per la creazione di un utente
    * generando un token per autorizzare le operazioni
    * sul catalogo
    * 
    * @param username
    * Username dell'utente
    * 
    * @param password
    * Password dell'utente
    */
   public Utente(String username, String password)
   {
      // Inizializzo i vari campi
      this.username = username;
      this.password = password;

      // Genero un token da 32 caratteri
      generaToken(32);
   }

   /**
    * @brief
    * Metodo per la generazione di un token casuale
    * 
    * @param lunghezza
    * Lunghezza del token
    */
   private void generaToken(int lunghezza)
   {
      // Caratteri ammessi dal token
      final String CARATTERI = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

      // Handle per la generazione di un numero casuale
      SecureRandom random = new SecureRandom();

      // Token temporaneo da generare
      String tokenGenerato = "";

      // Ciclo per generare il token
      for (int i = 0; i < lunghezza; i++)
      {
         // Generazione casuale di un carattere
         int randomIndex = random.nextInt(CARATTERI.length());
         char randomChar = CARATTERI.charAt(randomIndex);
         tokenGenerato += randomChar;
      }

      // Inizializzo il token
      this.token = tokenGenerato;
   }

   /**
    * @brief
    * Metodo per effettuare il login
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
   public String login(String username, String password)
   {
      // Restituisco il token
      return (this.username.equals(username) && this.password.equals(password)) ? this.token : null;
   }

   /**
    * @brief
    * Metodo per verificare se il token
    * corrisponde al token dell'utente
    * 
    * @param token
    * Token per verificare l'accesso
    * 
    * @return
    * true = Accesso verificato correttamente
    * false = Accesso negato
    */
   public boolean verificaAccesso(String token)
   {
      // Verifico se il token corrisponde
      return this.token.equals(token);
   }
}
