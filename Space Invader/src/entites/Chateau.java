package entites;

import java.awt.Color;
import java.awt.Graphics;

import ressources.Audio;
import ressources.Constantes;

public class Chateau extends Entite {

	/**** VARIABLES ****/
	
	private final int NBRE_LIGNES = Constantes.HAUTEUR_CHATEAU/Constantes.DIMENSION_BRIQUE;
	private final int NBRE_COLONNES = Constantes.LARGEUR_CHATEAU/Constantes.DIMENSION_BRIQUE;
	
	// tableau contenant les briques du ch�teau (la case contient true pour une brique et false pour vide)
	boolean tabChateau[][]= new boolean[NBRE_LIGNES][NBRE_COLONNES]; 
	
/**** CONSTRUCTEUR ****/
	
	public Chateau(int xPos) {
		super.xPos = xPos; // Abscisse du point le plus � gauche du ch�teau
		super.yPos = Constantes.Y_POS_CHATEAU; // Ordonn�e du sommet du ch�teau
		
		this.initTabChateau();
	}
	
/**** METHODES ****/
	
	// Cr�ation du tableau initial associ� au ch�teau sans d�g�t
	public void initTabChateau() {
		// On remplit toutes les cases du tableau avec true
		for(int ligne=0; ligne < NBRE_LIGNES; ligne++) {
			for(int colonne=0; colonne < NBRE_COLONNES; colonne++) {
				tabChateau[ligne][colonne]= true;
			}	
		}			
		// On remplit toutes les cases sans brique du tableau avec false	
		// Biseautage du haut du ch�teau
		for(int colonne=0; colonne < 6; colonne++) {
			for(int ligne=0; ligne < 2; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
		for(int colonne=0; colonne < 4; colonne++) {
			for(int ligne=2; ligne < 4; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
		for(int colonne=0; colonne < 2; colonne++) {
			for(int ligne=4; ligne < 6; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
		// Entr�e du ch�teau
		for(int ligne=18; ligne < NBRE_LIGNES; ligne++) {
			for(int colonne=10; colonne < NBRE_COLONNES-10; colonne++) {
				tabChateau[ligne][colonne]= false;
			}	
		}	
		// Biseautage entr�e du ch�teau
		for(int colonne=12; colonne < NBRE_COLONNES-12; colonne++) {
			for(int ligne=16; ligne < 18; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
		for(int colonne=14; colonne < NBRE_COLONNES-14; colonne++) {
			for(int ligne=14; ligne < 16; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
		for(int colonne=0; colonne < 2; colonne++) {
			for(int ligne=4; ligne < 6; ligne++) {
				tabChateau[ligne][colonne]= false;
				tabChateau[ligne][NBRE_COLONNES-colonne-1]= false;
			}
		}
	}
	
	// Dessin du ch�teau
	public void dessinChateau(Graphics g2) {
		for(int ligne=0; ligne < NBRE_LIGNES; ligne++) {
			for(int colonne=0; colonne < NBRE_COLONNES; colonne++) {
				if(tabChateau[ligne][colonne]== true) {g2.setColor(Color.GREEN);
				} 
				else {
					g2.setColor(Color.BLACK);
				}
				g2.fillRect(this.xPos + Constantes.DIMENSION_BRIQUE*colonne, this.yPos + Constantes.DIMENSION_BRIQUE*ligne, 
						Constantes.DIMENSION_BRIQUE, Constantes.DIMENSION_BRIQUE);
			}				
		}
	}
	
	public int trouveColonneChateau(int xMissile) {
		// Trouve la colonne du tableau associ� au ch�teau touch� par le tir
		int colonne = -1;
		colonne = (xMissile - this.xPos) / Constantes.DIMENSION_BRIQUE;	
		return colonne;
	}
	
	public int trouveBrique(int colonne) {
		// Trouve la premi�re brique en paratnt du bas de la colonne du tableau associ� au ch�teau ou renvoie -1
		int ligne = NBRE_LIGNES-1;
		while(ligne >= 0 && tabChateau[ligne][colonne] == false) {
			ligne--;
		}		
		return ligne;		
	}
	
	private void enleveBriques(int ligne, int colonne) {
		// Elimination des 6 premi�res briques de la colonne en partant du bas si elles existent
		for(int compteur=0; compteur < 6; compteur++) {
			if(ligne - compteur >= 0) {
				tabChateau[ligne - compteur][colonne] = false;
				if(colonne < NBRE_COLONNES - 1) {
					tabChateau[ligne - compteur][colonne + 1] = false;
				}
			}			
		}	
	}
	
	public void casseBriques(int xTir) {
		// R�capitule les 3 m�thodes qui pr�c�dent
		Audio.playSound("/sons/sonCasseBrique.wav");
		int colonne = this.trouveColonneChateau(xTir);
		this.enleveBriques(trouveBrique(colonne), colonne);
	}	
	
	public int trouveBriqueHaut(int colonne) {
		// Trouve la premi�re brique en partant du haut de la colonne du tableau 
		// associ� au ch�teau ou renvoie -1
		int ligne = 0;
		if(colonne != -1) {
		  while(ligne < NBRE_LIGNES && tabChateau[ligne][colonne] == false) {
			  ligne++;
		  }
		}			
		return ligne;		
	}
	
	private void enleveBriquesHaut(int ligne, int colonne) {
		// Elimination des 6 premi�res briques de la colonne en partant du haut si elles existent
		for(int compteur=0; compteur < 6; compteur++) {
			if(ligne + compteur < NBRE_LIGNES && colonne != -1) {
				tabChateau[ligne + compteur][colonne] = false;
				if(colonne < NBRE_COLONNES - 1) {
					tabChateau[ligne + compteur][colonne + 1] = false;
				}
			}			
		}	
	}
	
	public void casseBriquesHaut(int xTir) {
		// R�capitule les 3 m�thodes qui pr�c�dent
		Audio.playSound("/sons/sonCasseBrique.wav");
		int colonne = this.trouveColonneChateau(xTir);
		this.enleveBriquesHaut(trouveBriqueHaut(colonne), colonne);
	}	
}
