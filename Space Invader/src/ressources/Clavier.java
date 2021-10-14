package ressources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jeu.Main;

public class Clavier implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.scene.vaisseau.isVivant() == true) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){Main.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){Main.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(Main.scene.tirVaisseau.isVaisseauTire() == false) {	
					Audio.playSound("/sons/sonTirVaisseau.wav");
					Main.scene.tirVaisseau.setyPos(Constantes.Y_POS_VAISSEAU - Constantes.HAUTEUR_TIR_VAISSEAU);
					Main.scene.tirVaisseau.setxPos(Main.scene.vaisseau.getxPos() + Constantes.LARGEUR_VAISSEAU/2 - 1);	
					Main.scene.tirVaisseau.setVaisseauTire(true);
				}
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Main.scene.vaisseau.setDx(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
