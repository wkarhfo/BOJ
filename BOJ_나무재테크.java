import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_나무재테크 {
	static int N, M, K;
	static int[][] arr; // 추가 되는 양분 배열
	static int[][] map;// 양분 배열
	static ArrayList<Data>[][] list;
	static int x, y, z;
	static int[] dh = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		map = new int[N][N];
		list = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
				list[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			z = Integer.parseInt(st.nextToken());
			list[x][y].add(new Data(z, true));
		}

		for (int k = 1; k <= K; k++) {
			spring();
			summer();
			fall();
			winter();
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				ArrayList<Data> tmp = list[i][j];
//				for (int k = 0; k < tmp.size(); k++) {
//					if (tmp.get(k).isAlive)
//						count++;
//				}
				
				count+=list[i][j].size();
			}
		}

		System.out.println(count);

	}

	static void spring() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Data> temp = list[i][j];
				Collections.sort(temp); // 작은 것부터 오름차순으로 정렬
				int amount = map[i][j];// 현재 양분의 양
				for (int k = 0; k < temp.size(); k++) {
					Data tmp = temp.get(k);
					if (amount >= tmp.age) { // 자신의 나이보다 양분이 많은 경우
						amount -= tmp.age;
						tmp.age++;
					} else {
						tmp.isAlive = false;
					}
				}
				map[i][j] = amount; // 감소된 영양분을 다시 저장

			}
		}
	}

	static void summer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Data> temp = list[i][j];
				for (int k = 0; k < temp.size(); k++) {
					Data tmp = temp.get(k);
					if (tmp.isAlive)
						continue;
					else {
						map[i][j] += tmp.age / 2;
						list[i][j].remove(k--);
					}
				}

			}
		}
	}

	static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Data> temp = list[i][j];
				for (int k = 0; k < temp.size(); k++) {
					Data tmp = temp.get(k);
					if (tmp.isAlive && tmp.age % 5 == 0) {
						for (int m = 0; m < 8; m++) {
							int ah = i + dh[m];
							int ay = j + dy[m];
							if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
								continue;
							list[ah][ay].add(new Data(1, true));
						}
					}
				}
			}
		}
	}

	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += arr[i][j];
			}
		}
	}

	static class Data implements Comparable<Data> {

		int age; // 나이
		boolean isAlive; // 살아있는 나무인지, 죽은 나무인지 판단하기 위해서

		public Data(int age, boolean isAlive) {
			super();
			this.age = age;
			this.isAlive = isAlive;
		}

		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.age, o.age);
		}

	}
}
