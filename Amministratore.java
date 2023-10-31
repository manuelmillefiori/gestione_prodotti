/**
 * @author Manuel Millefiori
 * @date 2023-10-31
 */

public class Amministratore extends Utente
{
   /**
    * @brief
    * Costruttore per la creazione di un amministratore
    * generando un token per autorizzare le operazioni
    * sul catalogo
    * 
    * @param username
    * Username dell'amministratore
    * 
    * @param password
    * Password dell'amministratore
    */
   public Amministratore(String username, String password)
   {
      // Invoco il costruttore della superclasse per istanziare
      // l'amministratore
      super(username, password);
   }
}
