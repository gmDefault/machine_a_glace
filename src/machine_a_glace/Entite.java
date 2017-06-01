package machine_a_glace;

public class Entite {
	
	private Direction d;
	private int col, line;
	
	public Entite(int x, int y){
		if(Terrain.terrain[x][y].isAccessible()){
			line=x;
			col=y;
			d=Direction.Nord;
		}else{
			throw new JeuException("entite non creable sur une case non accessible");
		}
		
		
	}
	
	public Direction direction(){
		return d;
	}
	
	public void Avancer(int pas){
		switch (d){
		case Nord:line-=pas;
			break;
		case Est:col+=pas;
			break;
		case Sud:line+=pas;
			break;
		case Ouest:col-=pas;
		
		}
	}
	
	public void Tourner(Direction d){
		this.d=d;
	}
	
	public Case next_case(){
		switch(d){
		case Nord: return Terrain.terrain[line-1][col];
		case Est: return Terrain.terrain[line][col+1];
		case Sud: return Terrain.terrain[line+1][col];
		case Ouest: return Terrain.terrain[line][col-1];
		default : return Terrain.terrain[line][col];
		}
	}
	
	public String toString(){
		return "( "+line+","+col+" )";
	}
}
