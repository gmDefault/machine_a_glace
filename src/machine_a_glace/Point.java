package machine_a_glace;

public class Point {

	private float x, y;
	
	Point(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public boolean equals(Point p2) {
		return ((int)this.x == (int)p2.x && (int)this.y == (int)p2.y);
	}
}
