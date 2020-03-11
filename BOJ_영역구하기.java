import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_영역구하기 {
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int count;
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int yeul = sc.nextInt();
		int hang = sc.nextInt();
		int order = sc.nextInt();
		arr = new int[hang][yeul];
		visit = new boolean[hang][yeul];
		count = 0;
		list=new ArrayList<>();
		for (int i = 0; i < order; i++) {
			int h1 = sc.nextInt();
			int y1 = sc.nextInt();
			int h2 = sc.nextInt();
			int y2 = sc.nextInt();

			for (int m = h1; m < h2; m++) {
				for (int n = y1; n < y2; n++) {
					arr[m][n] = 1;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0 && visit[i][j] == false) {
					bfs(i, j);
				}
			}
		}
		Collections.sort(list);
		System.out.println(count);
		while(!list.isEmpty()) {
			System.out.print(list.remove(0)+" ");
		}

	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		Data start = new Data(h, y);
		q.add(start);
		visit[h][y] = true;
		int area=1;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.ha + dh[k];
				int ay = tmp.ye + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || arr[ah][ay] == 1
						|| visit[ah][ay] == true)
					continue;
				visit[ah][ay] = true;
				Data newData = new Data(ah, ay);
				q.add(newData);
				area++;
			}
		}
		list.add(area);
		count++;
	}

	static class Data {
		int ha;
		int ye;

		public Data(int ha, int ye) {
			this.ha = ha;
			this.ye = ye;
		}
	}
}
