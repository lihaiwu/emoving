package net.zhoubian.app.util;

public class Hilbert {
	private int[][] grid; //二维网格数组
	private int[][] series; //放置hilbert序号对应的横坐标，纵坐标的数组
	int rank; //阶数
	public Hilbert(int n){
		this.rank = n;
		grid = new int[(int)Math.pow(2, n)][(int)Math.pow(2, n)];
		series = new int[(int)Math.pow(2, 2*n)][2];
		hilbertCurve();
	}
	public void hilbertCurve(){
		//生成hilbert填充曲线
		for(int i=1;i<=rank;i++){
			if(i==1){
				grid[0][0] = 0;
				series[0] = new int[]{0,0};
				grid[0][1] = 3;
				series[3] = new int[]{0,1};
				grid[1][0] = 1;
				series[1] = new int[]{1,0};
				grid[1][1] = 2;
				series[2] = new int[]{1,1};
			}else{
				for(int j=0;j<(int)Math.pow(2, (i-1));j++){
					for(int k=0;k<(int)Math.pow(2, (i-1));k++){
						//第1象限对应的网格
						grid[j+(int)Math.pow(2,(i-1))][k] = (int)(grid[j][k]+Math.pow(4,(i-1)));
						series[(int)(grid[j][k]+Math.pow(4,(i-1)))] = new int[]{j+(int)Math.pow(2,(i-1)),k};
						//第2象限对应的网格
                        grid[j+(int)Math.pow(2,(i-1))][k+(int)Math.pow(2,(i-1))] = (int)(grid[j][k]+2*Math.pow(4,(i-1)));
                        series[(int)(grid[j][k]+2*Math.pow(4,(i-1)))] = new int[]{j+(int)Math.pow(2,(i-1)),k+(int)Math.pow(2,(i-1))};
                      //第三象限对应的网格
                        grid[(int)(Math.pow(2,(i-1))-1)-k][(int)(Math.pow(2,i)-1)-j] = (int)(grid[j][k]+3*Math.pow(4,(i-1)));
                        series[(int)(grid[j][k]+3*Math.pow(4,(i-1)))] = new int[]{(int)(Math.pow(2,(i-1))-1)-k,(int)(Math.pow(2,i)-1)-j};
					}
				}
				for(int j=0;j<(int)Math.pow(2,(i-1));j++){
					for(int k=0;k<=j;k++){
						int temp = grid[j][k];
						grid[j][k] = grid[k][j];
						series[grid[k][j]] = new int[]{j,k};
						grid[k][j] = temp;
						series[temp] = new int[]{k,j};
					}
				}
			}
		}
	}
	//输入数组坐标(x,y)，返回Hilbert排序的序号
	int hilbertCurve(int i,int j){
		int value = grid[i][j];
		return value;
	}
	int[][] getHilbertGrid(){
		return grid;
	}
	//输入Hilbert序号，返回数组坐标(x,y)
	int[] hilbertDecoding(int code){
//		int[] decode = new int[2];
//		for(int i=0;i<(int)Math.pow(2, rank);i++){
//			for(int j=0;j<(int)Math.pow(2, rank);j++){
//				if(grid[i][j]!=code)
//					continue;
//				else{
//					decode[0] = i;
//					decode[1] = j;
//					break;
//				}
//			}
//		}
//		return decode;
		return series[code];
	}
	public void print(){
		for(int i=0;i<(int)Math.pow(2, rank);i++){
			for(int j=0;j<(int)Math.pow(2, rank);j++){
				if(j==0){
					System.out.println("");
					System.out.print(grid[i][j]);
				}else{
					System.out.print(","+grid[i][j]);
				}
			}
		}
	}
}
