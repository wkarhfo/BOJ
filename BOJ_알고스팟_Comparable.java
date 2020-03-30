import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_알고스팟_Comparable {
	static int hang, yeul;
	static int[][] arr;
	static int endh, endy;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		yeul = Integer.parseInt(st.nextToken());
		hang = Integer.parseInt(st.nextToken());
		arr = new int[hang][yeul];
		visit = new boolean[hang][yeul];

		endh = hang - 1;
		endy = yeul - 1;

		for (int i = 0; i < hang; i++) {
			String s = br.readLine();
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		PriorityQueue<Data> q = new PriorityQueue<Data>(new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				return o1.broke - o2.broke;
			}
		});
		q.add(new Data(0, 0, 0));
		visit[0][0] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (tmp.h == endh && tmp.y == endy) {
				System.out.println(tmp.broke);
				break;
			}
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;
				if (arr[ah][ay] == 1) {
					q.add(new Data(ah, ay, tmp.broke + 1));
				} else {
					q.add(new Data(ah, ay, tmp.broke));
				}
				visit[ah][ay] = true;
			}

		}

	}

	static class Data implements Comparable<Data> {
		int h;
		int y;
		int broke;

		public Data(int h, int y, int broke) {
			super();
			this.h = h;
			this.y = y;
			this.broke = broke;
		}

		@Override
		public int compareTo(Data o) {
			if(this.broke<o.broke) return -1;
			else if(this.broke>o.broke) return 1;
			return 0;
		}
	}

}
