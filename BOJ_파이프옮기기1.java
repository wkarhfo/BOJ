import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_파이프옮기기1 {
	static int[][] arr;
	static int size;
	static int[] dh = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		arr = new int[size + 1][size + 1];
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		Queue<Data> q = new LinkedList<>();
		Data start = new Data(1, 1, 1, 2);
		q.add(start);
		int count = 0;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (tmp.h2 == size && tmp.y2 == size) {
				count++;
			}
//			System.out.println(tmp.h1+" "+tmp.y1+" "+tmp.h2+" "+tmp.y2);
			if (tmp.h1 == tmp.h2 && tmp.y1 != tmp.y2) {// 가로일 경우
				outer: for (int k = 0; k < 2; k++) {
					if (k == 0) { // 가로로 옮기는 경우
						int ah = tmp.h2;
						int ay = tmp.y2 + 1;
						if (ay >= arr[0].length || arr[ah][ay] == 1)
							break outer;
						Data newData = new Data(tmp.h2, tmp.y2, ah, ay);
						q.add(newData);
					} else { // 대각선으로 옮기는 경우
						for (int m = 0; m < 3; m++) {
							int ah = tmp.h2 + dh[m];
							int ay = tmp.y2 + dy[m];
							if (ah >= arr.length || ay >= arr[0].length || arr[ah][ay] == 1)
								break outer;
						}
						Data newData = new Data(tmp.h2, tmp.y2, tmp.h2 + 1, tmp.y2 + 1);
						q.add(newData);

					}
				}
			} else if (tmp.h1 != tmp.h2 && tmp.y1 == tmp.y2) { // 세로일 경우
				outer: for (int k = 0; k < 2; k++) {
					if (k == 0) { // 세로로 옮기는 경우
						int ah = tmp.h2 + 1;
						int ay = tmp.y2;
						if (ah >= arr.length || arr[ah][ay] == 1)
							break outer;
						Data newData = new Data(tmp.h2, tmp.y2, ah, ay);
						q.add(newData);
					} else { // 대각선으로 옮기는 경우
						for (int m = 0; m < 3; m++) {
							int ah = tmp.h2 + dh[m];
							int ay = tmp.y2 + dy[m];
							if (ah >= arr.length || ay >= arr[0].length || arr[ah][ay] == 1)
								break outer;
						}
						Data newData = new Data(tmp.h2, tmp.y2, tmp.h2 + 1, tmp.y2 + 1);
						q.add(newData);

					}
				}
			} else { // 대각선 일 경우
				outer: for (int k = 0; k < 3; k++) {
					if (k == 0) { // 가로로 옮기는 경우
						int ah = tmp.h2;
						int ay = tmp.y2 + 1;
						if (ay >= arr[0].length || arr[ah][ay] == 1)
							continue;
						Data newData = new Data(tmp.h2, tmp.y2, ah, ay);
						q.add(newData);
					} else if (k == 1) { // 세로로 옮기는 경우
						int ah = tmp.h2 + 1;
						int ay = tmp.y2;
						if (ah >= arr.length || arr[ah][ay] == 1)
							break outer;
						Data newData = new Data(tmp.h2, tmp.y2, ah, ay);
						q.add(newData);
					} else { // 대각선으로 옮기는 경우
						for (int m = 0; m < 3; m++) {
							int ah = tmp.h2 + dh[m];
							int ay = tmp.y2 + dy[m];
							if (ah >= arr.length || ay >= arr[0].length || arr[ah][ay] == 1)
								break outer;
						}
						Data newData = new Data(tmp.h2, tmp.y2, tmp.h2 + 1, tmp.y2 + 1);
						q.add(newData);
					}
				}
			}

		}
		System.out.println(count);

	}

	static class Data {
		int h1;
		int y1;
		int h2;
		int y2;

		public Data(int h1, int y1, int h2, int y2) {
			this.h1 = h1;
			this.y1 = y1;
			this.h2 = h2;
			this.y2 = y2;
		}

	}
}
