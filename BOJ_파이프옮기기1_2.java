import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_파이프옮기기1_2 {
	static int[][] arr;
	static int[] dh = { 0, 1, 1 }; // 가로, 세로, 대각선
	static int[] dy = { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size=Integer.parseInt(st.nextToken());
		arr = new int[size + 1][size + 1];
		for(int i=1;i<arr.length;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<arr[0].length;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(1, 2, 0));
		int count = 0;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (tmp.h == size && tmp.y == size) {
				count++;
				continue;
			}

			for (int i = 0; i < 3; i++) {
				if (tmp.way == 0 && i == 1)
					continue;
				else if (tmp.way == 1 && i == 0)
					continue;
				if (IsPromising(tmp.h, tmp.y, i)) {
					q.add(new Data(tmp.h + dh[i], tmp.y + dy[i], i));
				}
			}
		}
		System.out.println(count);

	}

	private static boolean IsPromising(int h, int y, int way) {
		int ah = h + dh[way];
		int ay = y + dy[way];
		if (ah >= arr.length || ay >= arr[0].length) {
			return false;
		}
		if (way == 2) {
			if (arr[ah][ay] == 0 && arr[ah - 1][ay] == 0 && arr[ah][ay - 1] == 0) {
				return true;
			}
		} else {
			if (arr[ah][ay] == 0) {
				return true;
			}
		}
		return false;
	}

	static class Data {
		int h;
		int y;
		int way;

		public Data(int h, int y, int way) {
			this.h = h;
			this.y = y;
			this.way = way;
		}
	}
}
