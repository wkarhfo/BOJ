import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_보물섬 {
	static char[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int max = 0;
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hang = sc.nextInt();
		int yeul = sc.nextInt();
		arr = new char[hang][yeul];
		for (int i = 0; i < hang; i++) {
			arr[i] = sc.next().toCharArray();
		}
		list=new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 'L') {
					visit=new boolean[hang][yeul];
					bfs(i, j);
				}
			}
		}
		for(int i=0;i<list.size();i++)
			if(max<list.get(i)) {
				max=list.get(i);
			}
		System.out.println(max);
	}

	private static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		Data start = new Data(h, y, 0);
		q.add(start);
		visit[h][y] = true;
		int temp=0;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if(temp<tmp.cnt) {
				temp=tmp.cnt;
			}
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] == true
						|| arr[ah][ay] == 'W') {
					continue;
				}
				Data newStart = new Data(ah, ay, tmp.cnt + 1);
				q.add(newStart);
				visit[ah][ay] = true;
			}
		}
		list.add(temp);
	}

	static class Data {
		int h;
		int y;
		int cnt;

		public Data(int h, int y, int cnt) {
			this.h = h;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
