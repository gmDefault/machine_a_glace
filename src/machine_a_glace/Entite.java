package machine_a_glace;

public class Entite {
	
	private Direction d;
	private int col, line;
	
	public Entite(int x, int y){
		line=x;
		col=y;
		d=Direction.Nord;
	}
	
	
	public void Avancer(int pas){
		switch (d){
		case Nord:line--;
			break;
		case Est:col++;
			break;
		case Sud:line++;
			break;
		case Ouest:col--;
		
		}
	}
	
	public void Tourner(Direction d){
		this.d=d;
	}
	
}
