import java.util.Scanner;

public class BOJ_로봇청소기 {
	static int[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hang = sc.nextInt();
		int yeul = sc.nextInt();
		arr = new int[hang][yeul];
		int startH = sc.nextInt();
		int startY = sc.nextInt();
		int way = sc.nextInt();
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int h = startH;
		int y = startY;
		int result=1;
		while (true) {
			if (way == 0) {
				arr[h][y] = 2;
				int ah = h;
				int ay = y - 1;
				int count=0;
				for(int k=0;k<4;k++) {
					int tempH=h+dh[k];
					int tempY=y+dy[k];
					if(arr[tempH][tempY]==1||arr[tempH][tempY]==2) {
						count++;
					}
				}
				if(count==4) {
					int tempH=h+1;
					int tempY=y;
					if(arr[tempH][tempY]==1) {
						System.out.println(result);
						return;
					}
					h=tempH;
					y=tempY;
					continue;
				}
				if (arr[ah][ay] == 2||arr[ah][ay]==1) {
					way=3;
					continue;
				}
				h=ah;
				y=ay;
				way = 3;
				result++;
			} else if (way == 1) {
				arr[h][y] = 2;
				int ah = h - 1;
				int ay = y;
				int count=0;
				for(int k=0;k<4;k++) {
					int tempH=h+dh[k];
					int tempY=y+dy[k];
					if(arr[tempH][tempY]==1||arr[tempH][tempY]==2) {
						count++;
					}
				}
				if(count==4) {
					int tempH=h;
					int tempY=y-1;
					if(arr[tempH][tempY]==1) {
						System.out.println(result);
						return;
					}
					h=tempH;
					y=tempY;
					continue;
				}
				if (arr[ah][ay] == 2||arr[ah][ay]==1) {
					way=0;
					continue;
				}
				h=ah;
				y=ay;
				way=0;
				result++;
			} else if (way == 2) {
				arr[h][y] = 2;
				int ah=h;
				int ay=y+1;
				int count=0;
				for(int k=0;k<4;k++) {
					int tempH=h+dh[k];
					int tempY=y+dy[k];
					if(arr[tempH][tempY]==1||arr[tempH][tempY]==2) {
						count++;
					}
				}
				if(count==4) {
					int tempH=h-1;
					int tempY=y;
					if(arr[tempH][tempY]==1) {
						System.out.println(result);
						return;
					}
					h=tempH;
					y=tempY;
					continue;
				}
				if (arr[ah][ay] == 2||arr[ah][ay]==1) {
					way=1;
					continue;
				}
				h=ah;
				y=ay;
				way=1;
				result++;
			} else {
				arr[h][y] = 2;
				int ah=h+1;
				int ay=y;
				int count=0;
				for(int k=0;k<4;k++) {
					int tempH=h+dh[k];
					int tempY=y+dy[k];
					if(arr[tempH][tempY]==1||arr[tempH][tempY]==2) {
						count++;
					}
				}
				if(count==4) {
					int tempH=h;
					int tempY=y+1;
					if(arr[tempH][tempY]==1) {
						System.out.println(result);
						return;
					}
					h=tempH;
					y=tempY;
					continue;
				}
				if (arr[ah][ay] == 2||arr[ah][ay]==1) {
					way=2;
					continue;
				}
				h=ah;
				y=ay;
				way=2;
				result++;
			}

		}

	}
}
