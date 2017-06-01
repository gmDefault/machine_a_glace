package machine_a_glace;

public class Terrain {
	private static int taille = 10;
	public static Case terrain[][] = new Case[taille][taille];

	private Terrain() {

	}

	public static void initialiser() {
		for (int i = 0; i < taille; i++) {
			if (i == 0 || i == taille - 1) {
				for (int j = 0; j < taille; j++) {
					terrain[i][j] = new Case();
					terrain[i][j].setCase(Contenu.Obstacle);
				}
			} else {
				for (int j = 0; j < taille; j++) {
					if (j == 0 || j == taille - 1) {
						terrain[i][j] = new Case();
						terrain[i][j].setCase(Contenu.Obstacle);
					} else {
						terrain[i][j] = new Case();
						if (Math.random() < 0.20) {
							terrain[i][j].setCase(Contenu.Obstacle);
						}
					}

				}
			}

		}
	}

	public static void afficher() {
		for (int i = 0; i < 10; i++) {
			for (Case c : terrain[i]) {
				System.out.print(c.toString() + "|");
			}
			System.out.println();
			for (int j = 0; j < 10; j++) {
				System.out.print("--");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Terrain.initialiser();
		Terrain.afficher();

		Entite e = new Entite(4, 4);

		while (true) {
			if(e.next_case().isAccessible()){
				e.Avancer(1);
			}else{
				if(Math.random()<0.5){
					switch (e.direction()){
					case Nord:e.Tourner(Direction.Est);
					break;
					case Est:e.Tourner(Direction.Sud);
					break;
					case Sud:e.Tourner(Direction.Ouest);
					break;
					case Ouest:e.Tourner(Direction.Nord);
					break;
					}
				}else{
					switch (e.direction()){
					case Nord:e.Tourner(Direction.Ouest);
					break;
					case Est:e.Tourner(Direction.Nord);
					break;
					case Sud:e.Tourner(Direction.Est);
					break;
					case Ouest:e.Tourner(Direction.Sud);
					break;
					}
				}
			}
			
			System.out.println(e.toString());
		}
	}
}
