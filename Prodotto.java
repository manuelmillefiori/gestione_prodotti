/**
 * @author Manuel Millefiori
 * @date 2023-10-31
 */

public class Prodotto
{
   private String nome;
   private float prezzo;
   private int quantita;

   /**
    * @brief
    * Costruttore per l'inizializzazione di un prodotto
    * 
    * @param nome
    * Nome del prodotto
    * 
    * @param prezzo
    * Prezzo del prodotto
    * 
    * @param quantita
    * Quantità del prodotto
    */
   public Prodotto(String nome, float prezzo, int quantita)
   {
      // Inizializzo i vari campi
      this.nome = nome;
      this.prezzo = prezzo;
      this.quantita = quantita;
   }

   /**
    * @brief
    * Metodo per verificare se esiste già un prodotto
    * con il nome passato come parametro
    * 
    * @param nome
    * Nome del prodotto da comparare
    * 
    * @return
    * true = Prodotto già esistente
    * false = Prodotto inesistente
    */
   public boolean esiste(String nome)
   {
      return this.nome.equals(nome);
   }

   // Override del toString
   public String toString()
   {
      return "Nome: " + this.nome + "\nPrezzo: " + this.prezzo + "\nQuantità: " + quantita;
   }
}
