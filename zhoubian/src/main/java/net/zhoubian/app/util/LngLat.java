package net.zhoubian.app.util;

public class LngLat {
	private double lngX;
	private double latY;
	public LngLat(double lngX,double latY){
		this.lngX = lngX;
		this.latY = latY;
	}
	public double getLngX() {
		return lngX;
	}
	public void setLngX(double lngX) {
		this.lngX = lngX;
	}
	public double getLatY() {
		return latY;
	}
	public void setLatY(double latY) {
		this.latY = latY;
	}
	
}
