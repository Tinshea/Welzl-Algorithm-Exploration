package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import supportGUI.Circle;
import supportGUI.Line;


// Ce code a été réalisé par Malek Bouzarkouna 28706508
public class DefaultTeam {

  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }
    
    Point p=points.get(0);
    Point q=points.get(1);


    return new Line(p,q);
  }
  
  private static Circle cercleCirconcrit(Point a, Point b, Point c) {
	    double d = (a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y))*2;// Calcul du dénominateur    
	    if (d == 0) {
	        return new Circle(a, 0);
	    }
	    else{
	    	 // Calcul des coordonnées du centre du cercle
		    double x =((norme(a)*(b.y-c.y))+(norme(b)*(c.y - a.y))+(norme(c)*(a.y - b.y))) / d;
		    double y =((norme(a) * (c.x - b.x)) + (norme(b) * (a.x - c.x)) + (norme(c) * (b.x - a.x))) / d;
		    
		    Point center = new Point((int)x, (int)y); // Création du point centre du cercle
		    double radius = Math.ceil(center.distance(a));// Calcul du rayon du cercle
		    
		    return new Circle(center, (int)radius);	
	    }   
	}

	private static int norme(Point a) {
	    return (a.x*a.x)+(a.y*a.y);
	}

	//Cette version de l'algorithme naïf a été donner par le prof dans sa correction du tme1 
  static Circle AlgoNaif(ArrayList<Point> inputPoints){
      ArrayList<Point> points = (ArrayList<Point>) inputPoints.clone();
      if (points.size()<1) return null;
      
      double cX,cY,cRadius,cRadiusSquared;
      for (Point p: points){
          for (Point q: points){
              cX = .5*(p.x+q.x);
              cY = .5*(p.y+q.y);
              cRadiusSquared = 0.25*((p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y));
              boolean allHit = true;
              for (Point s: points)
                  if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>cRadiusSquared){
                      allHit = false;
                      break;
                  }
              if (allHit) return new Circle(new Point((int)cX,(int)cY),(int)Math.sqrt(cRadiusSquared));
          }
      }
      
      double resX=0;
      double resY=0;
      double resRadiusSquared=Double.MAX_VALUE;
      for (int i=0;i<points.size();i++){
          for (int j=i+1;j<points.size();j++){
              for (int k=j+1;k<points.size();k++){
                  Point p=points.get(i);
                  Point q=points.get(j);
                  Point r=points.get(k);
                  //si les trois sont colineaires on passe
                  if ((q.x-p.x)*(r.y-p.y)-(q.y-p.y)*(r.x-p.x)==0) continue;
                  //si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les echange
                  if ((p.y==q.y)||(p.y==r.y)) {
                      if (p.y==q.y){
                          p=points.get(k); //ici on est certain que p n'est sur la meme ligne de ni q ni r
                          r=points.get(i); //parce que les trois points sont non-colineaires
                      } else {
                          p=points.get(j); //ici on est certain que p n'est sur la meme ligne de ni q ni r
                          q=points.get(i); //parce que les trois points sont non-colineaires
                      }
                  }
                  //on cherche les coordonnees du cercle circonscrit du triangle pqr
                  //soit m=(p+q)/2 et n=(p+r)/2
                  double mX=.5*(p.x+q.x);
                  double mY=.5*(p.y+q.y);
                  double nX=.5*(p.x+r.x);
                  double nY=.5*(p.y+r.y);
                  //soit y=alpha1*x+beta1 l'equation de la droite passant par m et perpendiculaire a la droite (pq)
                  //soit y=alpha2*x+beta2 l'equation de la droite passant par n et perpendiculaire a la droite (pr)
                  double alpha1=(q.x-p.x)/(double)(p.y-q.y);
                  double beta1=mY-alpha1*mX;
                  double alpha2=(r.x-p.x)/(double)(p.y-r.y);
                  double beta2=nY-alpha2*nX;
                  //le centre c du cercle est alors le point d'intersection des deux droites ci-dessus
                  cX=(beta2-beta1)/(double)(alpha1-alpha2);
                  cY=alpha1*cX+beta1;
                  cRadiusSquared=(p.x-cX)*(p.x-cX)+(p.y-cY)*(p.y-cY);
                  if (cRadiusSquared>=resRadiusSquared) continue;
                  boolean allHit = true;
                  for (Point s: points)
                      if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>cRadiusSquared){
                          allHit = false;
                          break;
                      }
                  if (allHit) {resX=cX;resY=cY;resRadiusSquared=cRadiusSquared;}
              }
          }
      }
      return new Circle(new Point((int)resX,(int)resY),(int)Math.sqrt(resRadiusSquared));
  }
  
//Cette méthode calcule le cercle minimum englobant pour un ensemble de points.
  private static Circle B_MINIDISK(ArrayList<Point> points, ArrayList<Point> R){
	// Création d'une copie de la liste de points pour éviter de modifier la liste originale.
	  ArrayList<Point> P = (ArrayList<Point>)points.clone();
	  Circle D = null;
	  // Base de la récursion : si aucun point n'est à traiter ou si R contient 3 points.
      if (points.size() < 1 || R.size() == 3 ) { 
    	// Calcule le cercle minimum avec les points de R.
    	  D =  b_md(R);
    	  }
      
      else {
    	  Point p =  P.get(ThreadLocalRandom.current().nextInt(0,  P.size()));
    	  P.remove(p);
    	  D = B_MINIDISK(P, R);
    	  
    	  if (D != null && !appartient(D,p)) {	 
    		// Si 'p' n'est pas dans le cercle, ajoutez-le à la liste R et faites un autre appel récursif.
    		  R.add(p);
    		  D = B_MINIDISK(P, R);
    		  // Une fois l'appel récursif terminé, retirez 'p' de R pour restaurer l'état.
    		  R.remove(p);
    	  }
      }
      return D;
  }
  
  public static Circle b_md(ArrayList<Point> R) {
		switch(R.size()){
		case 1:
			  return new Circle(R.get(0), 0); 
  		case 2:
			double x = (R.get(0).x + R.get(1).x) / 2;
			double y = (R.get(0).y + R.get(1).y) / 2;
			Point p = new Point((int) x, (int) y);
			
			double d = R.get(0).distance(R.get(1)) / 2;
			
			return new Circle(p, (int) Math.ceil(d));
  		case 3 :
				return cercleCirconcrit(R.get(0), R.get(1), R.get(2));// le cercle circonscrit
		} 
   			return new Circle(new Point(0, 0), 0);
	}
  
  public static Boolean appartient(Circle c, Point p) {
	return p.distance(c.getCenter()) <= c.getRadius();
}
  
  static Circle Welzl(ArrayList<Point> points){
      return B_MINIDISK(points, new ArrayList<Point>());
  }
  
  public Circle calculCercleMin(ArrayList<Point> points) {
	 //return Welzl(points);
	 return AlgoNaif(points);
  }
}
