package net.zhoubian.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GridUtil {
	public static class Grid256{
		private double bottomLat;
		private double topLat;
		private double leftLng;
		private double rightLng;
		private int code;
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

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String toString(){
			return "bottomLat = "+bottomLat+", topLat = "+topLat+", leftLng = "+leftLng+", rightLng = "+rightLng+", code = "+code;
		}
	}
	public static class LatStrip{
		private double bottomLat;
		private double topLat;
		private double centerLat;
		private double radii;
		private double lng256;
		private double lngPerDegree;
		private int code;
		private List<Grid256> grids = new ArrayList<Grid256>();
		public LatStrip(double bottomLat,double topLat,int code){
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
				grid.setCode(topDecode.size()+1);
				topDecode.add(new int[]{i,code});
				topEncode[i][code] = grid.getCode();
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
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String toString(){
			return "bottomLat = "+bottomLat+", topLat = "+topLat+", lng256 = "+lng256+", gridNum = "+grids.size()+", code = "+code;
			
		}
	}
	private static Hilbert h8;
	private static int[][] topEncode;
	private static List topDecode;
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
	public static List<LatStrip> latStrips = new ArrayList<LatStrip>();
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
		h8 = new Hilbert(8);
		h8.print();
		topEncode = new int[27][22];
		topDecode = new ArrayList(507);
		for(int i=0;i<27;i++){
			for(int j=0;j<22;j++){
				topEncode[i][j] = -1;
			}
		}
		//每256公里代表纬度上的几度
		lat256 = Math.round((256000/(2*Math.PI*earthRadii))*360*1000000)/1000000.0;
		logger.info("lat256 = "+lat256);
		latPerDegree = 2*Math.PI*earthRadii/360;
		logger.info("latPerDegree = "+latPerDegree);
		int stripNum = (int)Math.ceil((lefttop.getLatY() - rightbottom.getLatY())/lat256);
		for(int i=0;i<stripNum;i++){
			latStrips.add(new LatStrip(rightbottom.getLatY()+i*lat256,rightbottom.getLatY()+(i+1)*lat256,i));
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
	/**
	 * 查找该经纬度坐标所属的网格编号
	 * @param latY
	 * @param lngX
	 * @return
	 */
	public static long getOwnGridCode(double latY,double lngX){
		if(latY<rightbottom.getLatY() || latY>lefttop.getLatY() || lngX<lefttop.getLngX() || lngX>rightbottom.getLngX()){
			return -1;
		}
		int y = (int)((latY-rightbottom.getLatY())/lat256);
		LatStrip latStrip = latStrips.get(y);
		double lng256 = latStrip.getLng256();
		int x = (int)((lngX-lefttop.getLngX())/lng256);
		Grid256 grid256 = latStrip.getGrids().get(x);
		int topCode = topEncode[x][y];
		if(topCode == -1){
			return -1;
		}
		int x1 = (int)((lngX - grid256.getLeftLng())/(grid256.getRightLng()-grid256.getLeftLng())*256);
		int y1 = (int)((latY - grid256.getBottomLat())/(grid256.getTopLat()-grid256.getBottomLat())*256);
		int hcode = h8.hilbertCurve(x1, y1);
		int code = topCode*100000+hcode;
		return code;
	}
	/**
	 * 查找与该经纬度相关的网格编号
	 * @param latY
	 * @param lngX
	 * @param range 表示周边几平方公里，一般分为三个等级，分别是1平方公里，4平方公里，16平方公里
	 * @return
	 */
	public static List<Integer> getRelatedGridCode(double lngX,double latY, int range){
		List<Integer> result = new ArrayList<Integer>();
		if(latY<rightbottom.getLatY() || latY>lefttop.getLatY() || lngX<lefttop.getLngX() || lngX>rightbottom.getLngX()){
			return null;
		}
		int y = (int)((latY-rightbottom.getLatY())/lat256);
		LatStrip latStrip = latStrips.get(y);
		double lng256 = latStrip.getLng256();
		LngLat topleft = new LngLat(lngX-128*range/lng256,latY+128*range/lat256);
		LngLat topright = new LngLat(lngX+128*range/lng256,latY+128*range/lat256);
		LngLat bottomleft = new LngLat(lngX-128*range/lng256,latY-128*range/lat256);
		LngLat bottomright = new LngLat(lngX+128*range/lng256,latY-128*range/lat256);
		int[] gridTopLeft = getX1Y1(topleft.getLngX(),topleft.getLatY());
		int x1TopLeft = gridTopLeft[0];
		int y1TopLeft = gridTopLeft[1];
		int[] gridTopRight = getX1Y1(topright.getLngX(),topright.getLatY());
		int x1TopRight = gridTopRight[0];
		int y1TopRight = gridTopRight[1];
		int[] gridBottomLeft = getX1Y1(bottomleft.getLngX(),bottomleft.getLatY());
		int x1BottomLeft = gridBottomLeft[0];
		int y1BottomLeft = gridBottomLeft[1];
		int[] gridBottomRight = getX1Y1(bottomright.getLngX(),bottomright.getLatY());
		int x1BottomRight = gridBottomRight[0];
		int y1BottomRight = gridBottomRight[1];
		if(gridTopLeft[3]==gridBottomLeft[3]){
			//不跨纬度条
			if(gridTopLeft[2]==gridTopRight[2]){
				//所有网格均位于同一个顶级网格内
				addGrid(result,gridTopLeft,gridBottomRight);
			}else{
				//位于同纬度条的两个顶级网格内
				addGrid(result,gridTopLeft,new int[]{255,gridBottomLeft[1],gridTopLeft[2],gridTopLeft[3]});
				addGrid(result,new int[]{0,gridTopRight[1],gridBottomRight[2],gridBottomRight[3]},gridBottomRight);
			}
		}else{
			//跨纬度条
			//上面纬度条计算
			if(gridTopLeft[3]==gridTopRight[3]){
				//不跨顶级网格
				addGrid(result,gridTopLeft,new int[]{gridTopRight[0],0,gridTopRight[2],gridTopRight[3]});
			}else{
				//跨顶级网格
				addGrid(result,gridTopLeft,new int[]{255,0,gridTopLeft[2],gridTopLeft[3]});
				addGrid(result,new int[]{0,gridTopRight[1],gridTopRight[2],gridTopRight[3]},new int[]{gridTopRight[0],0,gridTopRight[2],gridTopRight[3]});
			}
			//下面纬度条计算
			if(gridBottomLeft[3] == gridBottomLeft[3]){
				//不跨顶级网格
				addGrid(result,new int[]{gridBottomLeft[0],255,gridBottomLeft[2],gridBottomLeft[3]},gridBottomRight);
			}else{
				//跨顶级网格
				addGrid(result,new int[]{gridBottomLeft[0],255,gridBottomLeft[2],gridBottomLeft[3]},new int[]{255,gridBottomLeft[1],gridBottomLeft[2],gridBottomLeft[3]});
				addGrid(result,new int[]{0,255,gridBottomRight[2],gridBottomRight[3]},gridBottomRight);
			}
		}
		return result;
	}
	private static void addGrid(List result,int[] gridTopLeft,int[] gridBottomRight){
		for(int i=gridTopLeft[0];i<=gridBottomRight[0];i++){
			for(int j=gridTopLeft[1];j<=gridBottomRight[1];j++){
				int topCode = topEncode[gridTopLeft[2]][gridTopLeft[3]];
				int hcode = h8.hilbertCurve(i, j);
				int code = topCode*100000+hcode;
				result.add(code);
			}
		}
	}
	private static int[] getX1Y1(double lngX, double latY){
		int y = (int)((latY-rightbottom.getLatY())/lat256);
		LatStrip latStrip = latStrips.get(y);
		double lng256 = latStrip.getLng256();
		int x = (int)((lngX-lefttop.getLngX())/lng256);
		int topCode = topEncode[x][y];
		Grid256 grid256 = latStrip.getGrids().get(x);
		int x1 = (int)((lngX - grid256.getLeftLng())/(grid256.getRightLng()-grid256.getLeftLng())*256);
		int y1 = (int)((latY - grid256.getBottomLat())/(grid256.getTopLat()-grid256.getBottomLat())*256);
		int hcode = h8.hilbertCurve(x1, y1);
		int code = topCode*100000+hcode;
		return new int[]{x1,y1,x,y,topCode,hcode,code};
	}
	/**
	 * 根据编码反查其所属的网格的经纬度数据
	 * @param code
	 * @return 数组的第一个元素是左上角的经纬度，第二个元素是右下角的经纬度
	 */
	public static LngLat[] getGrid(long code){
		LngLat[] result = new LngLat[2];
		int topCode = (int)(code/100000);
		int[] a = (int[])topDecode.get(topCode);
		int x = a[0];
		int y = a[1];
		Grid256 grid256 = latStrips.get(y).getGrids().get(x);
		int hCode = (int)(code % 100000);
		int[] a1 = h8.hilbertDecoding(hCode);
		int x1 = a1[0];
		int y1 = a1[1];
		result[0] = new LngLat(x1*(grid256.rightLng-grid256.leftLng)/256,y1*(grid256.topLat-grid256.bottomLat)/256);
		result[1] = new LngLat((x1+1)*(grid256.rightLng-grid256.leftLng)/256,(y1+1)*(grid256.topLat-grid256.bottomLat)/256);
		return result;
	}
}
