package net.zhoubian.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GridUtil {
	private static class Grid256{
		private double bottomLat;
		private double topLat;
		private double leftLng;
		private double rightLng;
		private String code;
		public Grid256(){}
		
		public double getBottomLat() {
			return bottomLat;
		}

		public void setBottomLat(double bottomLat) {
			this.bottomLat = bottomLat;
		}

		public double getTopLat() {
			return topLat;
		}

		public void setTopLat(double topLat) {
			this.topLat = topLat;
		}

		public double getLeftLng() {
			return leftLng;
		}

		public void setLeftLng(double leftLng) {
			this.leftLng = leftLng;
		}

		public double getRightLng() {
			return rightLng;
		}

		public void setRightLng(double rightLng) {
			this.rightLng = rightLng;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String toString(){
			return "bottomLat = "+bottomLat+", topLat = "+topLat+", leftLng = "+leftLng+", rightLng = "+rightLng+", code = "+code;
		}
	}
	private static class LatStrip{
		private double bottomLat;
		private double topLat;
		private double centerLat;
		private double radii;
		private double lng256;
		private double lngPerDegree;
		private String code;
		private List<Grid256> grids = new ArrayList<Grid256>();
		public LatStrip(double bottomLat,double topLat,String code){
			this.bottomLat = bottomLat;
			this.topLat = topLat;
			this.code = code;
			this.centerLat = (topLat+bottomLat)/2;
			radii = earthRadii*Math.cos(centerLat*2*Math.PI/360);
			lng256 = Math.round((256000/(2*Math.PI*radii))*360*1000000)/1000000.0;
			lngPerDegree = 2*Math.PI*radii/360;
			int gridNum = (int)Math.ceil((rightbottom.getLngX()-lefttop.getLngX())/lng256);
			for(int i=0;i<gridNum;i++){
				Grid256 grid = new Grid256();
				grid.setBottomLat(bottomLat);
				grid.setTopLat(topLat);
				grid.setLeftLng(lefttop.getLngX()+i*lng256);
				grid.setRightLng(lefttop.getLngX()+(i+1)*lng256);
				grid.setCode(code+genCode(i,2));
				grids.add(grid);
			}
		}
		public double getBottomLat() {
			return bottomLat;
		}
		public void setBottomLat(double bottomLat) {
			this.bottomLat = bottomLat;
		}
		public double getTopLat() {
			return topLat;
		}
		public void setTopLat(double topLat) {
			this.topLat = topLat;
		}
		public double getCenterLat() {
			return centerLat;
		}
		public void setCenterLat(double centerLat) {
			this.centerLat = centerLat;
		}
		public List<Grid256> getGrids() {
			return grids;
		}
		public void setGrids(List<Grid256> grids) {
			this.grids = grids;
		}
		public double getRadii() {
			return radii;
		}
		public void setRadii(double radii) {
			this.radii = radii;
		}
		public double getLng256() {
			return lng256;
		}
		public void setLng256(double lng256) {
			this.lng256 = lng256;
		}
		public double getLngPerDegree() {
			return lngPerDegree;
		}
		public void setLngPerDegree(double lngPerDegree) {
			this.lngPerDegree = lngPerDegree;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String toString(){
			return "bottomLat = "+bottomLat+", topLat = "+topLat+", lng256 = "+lng256+", gridNum = "+grids.size()+", code = "+code;
			
		}
	}
	private static LngLat lefttop = new LngLat(73.666667,53.55);
	private static LngLat rightbottom = new LngLat(135.041667,3.866667);
	private static double lat256;
	//每纬度代表地球上的多少米
	private static double latPerDegree = 111195;
	private static Log logger = LogFactory.getLog(GridUtil.class);
	/**
	 * 地球平均半径，以米为单位
	 */
	private static final double earthRadii = 6371004;
	private static List<LatStrip> latStrips = new ArrayList<LatStrip>();
	public static void main(String[] args){
		//中国最东端，135.041667
		double lngright = 135+2/60.0+30/3600.0;
		lngright = Math.round(lngright*1000000)/1000000.0;
		logger.info("lngright = "+lngright);
		//中国最西端，73.666667
		double lngleft = 73+40/60.0;
		lngleft = Math.round(lngleft*1000000)/1000000.0;
		logger.info("lngleft = "+lngleft);
		//中国最南端，3.866667
		double latbottom = 3+52/60.0;
		latbottom = Math.round(latbottom*1000000)/1000000.0;
		logger.info("latbottom = "+latbottom);
		//中国最北端，53.55
		double lattop = 53+33/60.0;
		lattop = Math.round(lattop*1000000)/1000000.0;
		logger.info("lattop = "+lattop);
		GridUtil gridUtil = new GridUtil(new LngLat(lngleft,lattop),new LngLat(lngright,latbottom));
		
	}
	public GridUtil(){
		init();
	}
	public GridUtil(LngLat lefttop,LngLat rightbottom){
		this.lefttop = lefttop;
		this.rightbottom = rightbottom;
		init();
	}
	public void init(){
		//每256公里代表纬度上的几度
		lat256 = Math.round((256000/(2*Math.PI*earthRadii))*360*1000000)/1000000.0;
		logger.info("lat256 = "+lat256);
		latPerDegree = 2*Math.PI*earthRadii/360;
		logger.info("latPerDegree = "+latPerDegree);
		int stripNum = (int)Math.ceil((lefttop.getLatY() - rightbottom.getLatY())/lat256);
		for(int i=0;i<stripNum;i++){
			latStrips.add(new LatStrip(rightbottom.getLatY()+i*lat256,rightbottom.getLatY()+(i+1)*lat256,genCode(i,2)));
		}
		int gridNum = 0;
		for(int i=0;i<stripNum;i++){
			LatStrip latStrip = latStrips.get(i);
			logger.info(latStrip);
			for(int j=0;j<latStrip.grids.size();j++){
				gridNum ++;
				logger.info(latStrip.grids.get(j));
			}
		}
		logger.info("gridNum = "+gridNum);
		logger.info("stripNum = "+stripNum);
	}
	public static String genCode(int i, int length){
		int l = 1;
		if(i>0){
			l = (int)Math.ceil(Math.log10(i));
		}
		String r = "";
		if(l<length){
			for(int j=0;j<length-l;j++){
				r += "0";
			}
		}
		r += i;
		return r;
	}
	public static long getOwnGridCode(double latY,double lngX){
		return 0;
	}
	public static long[] getRelatedGridCode(double latY,double lngX){
		return new long[]{0};
	}
}
