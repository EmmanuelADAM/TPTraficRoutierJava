package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modele.Noeud;
import modele.ReseauRoutier;
import modele.Voiture;
/**
 * classe gérant le trafic routier
 * créer le réseau et les voitures
 * */
public class ControleTrafic
{
   /**les voitures*/
   List<Voiture> voitures;
   /**le hasard*/
   Random hasard;
   /**creation du reseau et des premieres voitures*/
   public ControleTrafic()
   {
      ReseauRoutier.creerReseau();
      voitures = new ArrayList<>();
      hasard= new Random();
      //on debute avec 2 voitures, l'une en délacement en est-ouest, l'autre en sud-nord
      addVoiture(true);
      addVoiture(false);
   }
   
   /**ajoute 1 voitures se deplacant soit  du sud au nord, soit de l'ouest vers l'est*/
   public Voiture addVoiture(boolean ligne)
   {
      Voiture v = null;
      //TODO: creer une voiture placee aleatoirement
      voitures.add(v);
      return v;
   }

   /**retirer une voiture du circuit
   @param v la voiture a supprimer
   @return la nouvelle liste de voitures*/
   public List<Voiture> removeCar(Voiture v)
   {
      v.getNoeudSuivi().removeCar(v);
      voitures.remove(v);
      return voitures;
   }

   
   /**demande à chaque voiture de calculer leurs noeuds suivants*/
   public void calculerPointsSuivants() {
      for(Voiture v:voitures)
      {
      //si la voiture pense etre dans un bouchon, lui demander de verifier
         if(v.isBouchon())v.verifBouchon();
         if(!v.isArrivee() && !v.isPause())
         {
          //TODO:si la voiture n'est pas arrivee ou en pause ou stoppe, lui demander prendre le  noeud suivant
         }
      }
   }
   
   /**demande à chaque voiture de bouger (se retirer du noeud courant)*/
   public void bougerVoitures()
   {
      for(Voiture v:voitures)
      {
         if(!v.isPause())
         {
          //TODO:si la voiture n'est pas en pause ou stoppe, lui demander se se retirer du noeud suivi
            Noeud n = v.getNoeudSuivi();
            if(n!=null) n.removeCar(v);
         }
      }      
   }
   
   /**met en pause la voiture no i*/
   public void pauseVoiture(int i)
   {
      Voiture v = voitures.get(i);
      v.setPause(!v.isPause());
   }
   
   /**@return  la liste des voitures*/
   public List<Voiture> getVoitures()
   {
      return voitures;
   }
}
