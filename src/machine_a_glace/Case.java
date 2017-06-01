package machine_a_glace;

public class Case {
	private Couleur coul;
	private Contenu cont;
	
	public Case(){
		coul=Couleur.Neutre;
		cont=Contenu.Vide;
	}
	
	public boolean isAccessible(){
		return (cont != Contenu.Obstacle && cont != Contenu.Joueur && cont != Contenu.Robot);
	}
	
	public void setCase(Contenu c){
		cont=c;
	}
	
	public void setCouleur(Couleur c){
		coul=c;
	}
	
	public String toString(){
		return cont.affichage();
	}
}
