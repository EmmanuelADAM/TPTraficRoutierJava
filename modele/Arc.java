package modele;

import java.util.List;

public class Arc
{
   /**noeud de depart*/
   private Noeud start;
   /**noeud d'arrivee*/
   private Noeud end;
   /**noeuds secondaire entre les deux noeuds principaux*/
   Noeud[] petitsNoeuds;
   /**liste des voitures sur noeud*/
   List<Voiture> cars;
   /**nom de l'arc*/
   String name;

   public Arc() {   }


   public Arc(Noeud start, Noeud end) {
      super();
      this.start = start;
      this.end = end;
      this.name = ""+start.id+"-"+end.id;
   }
   /**construceur d'arcs entre les noeuds start, end et les nbPetitsNoeuds entre eux*/
   public Arc(Noeud start, Noeud end, int nbPetitsNoeuds) {
      super();
      this.start = start;
      this.end = start;
      petitsNoeuds = new Noeud[nbPetitsNoeuds];
      double pasX = (end.x-start.x)/(nbPetitsNoeuds+1);
      double pasY = (end.y-start.y)/(nbPetitsNoeuds+1);
      Noeud noeudAvant = start;
      for(int i=1; i<=nbPetitsNoeuds; i++)
      {
         double coorX = start.x + i*pasX;
         double coorY = start.y + i*pasY;
         Noeud petitsNoeud = new Noeud(coorX, coorY, false);
         petitsNoeuds[i-1] = petitsNoeud;
         Arc arc = new Arc(noeudAvant, petitsNoeuds[i-1]);
         ReseauRoutier.addArc(arc);            
         ReseauRoutier.addNoeud(petitsNoeuds[i-1] );
         noeudAvant = petitsNoeuds[i-1];
      }
      Arc arc = new Arc(noeudAvant, end);
      ReseauRoutier.addArc(arc);
   }   

   /**@return the ieme petit noeud*/
   public Noeud getPetitNoeud(int i)
   {return petitsNoeuds[i];}

   public Noeud getStart() { return start; }
   public Noeud getEnd() { return end; }

   public void setStart(Noeud start) { this.start = start; }
   public void setEnd(Noeud end) { this.end = end; }


}
