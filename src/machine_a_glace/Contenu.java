package machine_a_glace;

public enum Contenu {
	Vide(" "),Obstacle("O"),Opérateur("W"),Robot("R"),Joueur("J");
	
	private String affichage;
	
	private Contenu(String s){
		affichage=s;
	}
	
	public String affichage(){
		return affichage;
	}
}
