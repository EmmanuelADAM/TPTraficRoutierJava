package modele;

import java.util.ArrayList;
import java.util.List;

public class ReseauRoutier
{
   /**liste des carrefours constituants le reseau routier*/
   private static List<Noeud> noeuds = new ArrayList<>();
   /**liste des routes constituants le reseau routier*/
   private static List<Arc> arcs = new ArrayList<>();
   /**dim max trouvee en x ou y (utilise pour mise a l'echelle dans la partie graphique)*/
   private static double dimMax;

   public ReseauRoutier(){}


   /**ajoute deux noeuds et un arc entre eux
    * @param xo x du noeud origine
    * @param yo y du noeud origine
    * @param xd x du noeud destination
    * @param yd x du noeud destination
    * */
   private static void addArcs(Noeud origine, Noeud destination)
   {
      //creation de 3 noeuds secondaires entre origine et destination
      Arc a = new Arc(origine,destination, 3);
   }
   
   /**retourne le noeud en coordonnee x,y s'il existe*/
   public static Noeud getNoeud(int x, int y)
   {
      Noeud result = null;
      boolean found = false;
      int i=0;
      int size= noeuds.size();
      Noeud n=null;
      while (i<size && !found)
      {
         n=noeuds.get(i++);
         found = (n.getX()==x && n.getY()==y);
      }
      if (found) result = n;
      return result;
   }
   
   /**creation du reseau routier*/
   public static void creerReseau()
   {
      //TODO: pas necessaire mais avancer par pas de 5O (i++ -> i+=50, idem pour j, remplacer 6 par 250, ....)
      for(int i=0; i<6; i++)
         for(int j=0; j<6; j++)
         {
            if(i==0 && (j==0 || j==5)) continue;
            if(i==5 && (j==0 || j==5)) continue;
            noeuds.add( new Noeud(i,j));
         }
      Noeud o=null;
      Noeud d=null;
      for(int x=0; x<5; x++)
         for(int y=5; y>0; y--)
         {
            o = getNoeud(x,y);
            if(o==null)continue;
            if (y!=0 && y!=5)
            {
               d = getNoeud(x+1,y);
               if(d==null)continue;
               addArcs(o,d);
            }
            if (x!=0 && x!=5)
            {
               d = getNoeud(x,y-1);
               if(d==null)continue;
               addArcs(o,d);
            }
         }
   }

   /**trouve la dimension max en x ou y sur l'ensemble des noeuds*/
   private static void trouverDimMax()
   {
      double max = noeuds.get(0).x;
      for(Noeud n:noeuds)
      {
         if (n!=null)
         {
            if(max<n.x) max = n.x;
            if(max<n.y) max = n.y;
         }
      }
      dimMax = max;
   }
   
   public static void addNoeud(Noeud n) {noeuds.add(n);}
   public static void addArc(Arc a) {arcs.add(a);}
   public static double getDimMax() {return dimMax;}

   public static List<Noeud> getNoeuds() { return noeuds; }
   public static List<Arc> getArcs() { return arcs; }

   /**pour debogage eventuel, retourne la liste des noeuds du reseau*/
   public static String toStringue()   
   {
      StringBuilder sb = new StringBuilder("reseau, noeuds = ");
      noeuds.forEach(n->{if(n.isPrincipal()) sb.append(n).append("--");});
      return sb.toString();            
   }

}
