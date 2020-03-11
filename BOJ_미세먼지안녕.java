import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_미세먼지안녕 {
	static int TIME;
	static int[][] arr;
	static int[][] result;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Data> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hang = sc.nextInt();
		int yeul = sc.nextInt();
		TIME = sc.nextInt();
		arr = new int[hang][yeul];
		list = new ArrayList<>();
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == -1) {
					Data tmp = new Data(i, j);
					list.add(tmp);
				}
			}
		}
		int count=0;
		while (TIME > 0) {
			
			result = new int[hang][yeul];
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j] != -1 || arr[i][j] != 0) {
						bad(i, j);
					}

				}
			}

			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					arr[i][j] += result[i][j];
				}
			}
			
			// 윗층 공기청정기
			for (int i = list.get(0).h - 1; i >= 1; i--) {
				arr[i][0] = arr[i - 1][0];
			}
			for (int i = 0; i <= arr[0].length - 2; i++) {
				arr[0][i] = arr[0][i + 1];
			}
			for (int i = 0; i <= list.get(0).h - 1; i++) {
				arr[i][arr[0].length - 1] = arr[i + 1][arr[0].length - 1];
			}
			for (int i = arr[0].length - 1; i >= list.get(0).y + 1; i--) {
				if (i == list.get(0).y + 1) {
					arr[list.get(0).h][i] = 0;
					break;
				}
				arr[list.get(0).h][i] = arr[list.get(0).h][i - 1];
			}
			// 아랫층 공기청정기
			for (int i = list.get(1).h + 1; i <= arr.length - 2; i++) {
				arr[i][0] = arr[i + 1][0];
			}
			for(int i=0;i<=arr[0].length-2;i++) {
				arr[arr.length-1][i]=arr[arr.length-1][i+1];
			}
			for(int i=arr.length-1;i>=list.get(1).h+1;i--) {
				arr[i][arr[0].length-1]=arr[i-1][arr[0].length-1];
			}
			for(int i=arr[0].length-1;i>=list.get(1).y+1;i--) {
				if (i == list.get(1).y + 1) {
					arr[list.get(1).h][i] = 0;
					break;
				}
				arr[list.get(1).h][i] = arr[list.get(1).h][i - 1];
			}
			
			TIME--;
		}
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				if(arr[i][j]>0) {
					count+=arr[i][j];
				}
			}
		}
		System.out.println(count);
	}

	private static void bad(int i, int j) {
		int count = 0;
		for (int k = 0; k < 4; k++) {
			int ah = i + dh[k];
			int ay = j + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || arr[ah][ay] == -1) {
				continue;
			}
			count++;
			result[ah][ay] += arr[i][j] / 5;
		}
		arr[i][j] -= (arr[i][j] / 5) * count;
	}

	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			this.h = h;
			this.y = y;
		}

	}
}
