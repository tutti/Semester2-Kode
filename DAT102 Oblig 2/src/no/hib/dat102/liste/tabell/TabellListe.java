package no.hib.dat102.liste.tabell;
import no.hib.dat102.liste.adt.*;
//  TabellListe.java
//  Representerer en tabellimplementasjon av en liste. Foran i listen er
//  ved indeks 0. Denne Klassen vil bli utvidet ved arv for å gi en
//  spesialisering. Vi sier at en klasse er avledet av en superklasse.
///********************************************************************

public abstract class TabellListe<T> implements ListeADT<T> {
   // Avledet klasse arver medlemmer fra basisklassen(super)
   // Konstruktør arves ikke.
   // Det som er deklarert public eller protected kan nås.
   // private    - medlemmet er tilgjengelig kun for klassen.
   // public     - medlemmet er tilgjengelig for alle klaser.
   // proteceted – medlemmet er tilgjengelige for avledete klasser
   //              og fra alle klasser i samme pakke.
   protected final static int STDK = 100;
   protected final static int IKKE_FUNNET = -1;
   protected int bak;
   protected T[] liste;
   
   
   public TabellListe(){
     this(STDK);
   }
   
   
   public TabellListe (int startKapsitet){
      bak   = 0;
      liste = (T[])(new Comparable [startKapsitet]);
   }

   @Override
   public abstract T fjern(T element);
   
   
@Override
public abstract boolean inneholder(T element);
   
   @Override
public T fjernSiste (){
       T resultat = null;

      if (!erTom()){
         bak--;
         resultat = liste[bak];
         liste[bak] = null;
         }
      return resultat;
   }
   
   
   @Override
public T fjernFørste(){
      T resultat = null;
      if (!erTom()){
          resultat = liste[0];
          bak--;
      /** skifter elementene en plass oppover */
      for (int i=0; i < bak; i++){
          liste[i] = liste[i+1];
      }
                  
      }//if
      
      return resultat;
   }//
   
        
   
   @Override
public T første() {
      T resultat = null;
      if (!erTom())
          resultat = liste[0];
      return resultat;
   }

  
   @Override
public T siste(){
      T resultat = null;
      if (!erTom())
       resultat = liste[bak-1];

      return resultat;
   }

          
   @Override
public boolean erTom(){
      return (bak == 0);
   }
    
   
   @Override
public int antall(){
      return bak;
   }
   
   @Override
public String toString(){
      String resultat = "";

      for (int i=0; i < bak; i++){
         resultat = resultat + liste[i].toString() + "\n";
      }
      return resultat;
   }
        
   protected void utvid()  {
      T[] hjelpeTabell = (T[])(new Comparable[liste.length*2]);

      for (int i=0; i < liste.length; i++)
         hjelpeTabell[i] = liste[i];

      liste = hjelpeTabell;
   }

}//class
