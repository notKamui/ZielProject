package model;

public class Joueur {

	private double x;
	private double y;
	
	public Joueur(double cx, double cy) {
		this.x = cx;
		this.y = cy;
	}
	
	public void setx(double newX) {
	 this.x = newX;
	}
	
	public void sety(double newY) {
		 this.y = newY;
	}
}
