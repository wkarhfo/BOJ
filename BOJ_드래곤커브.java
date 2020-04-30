import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_드래곤커브 {
	static int N;
	static int x, y, d, g;
	static boolean[][] arr;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new boolean[100 + 1][100 + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 열
			y = Integer.parseInt(st.nextToken()); // 행
			d = Integer.parseInt(st.nextToken()); // 방향
			g = Integer.parseInt(st.nextToken()); // 최대 세대

			ArrayList<Integer> list = new ArrayList<>();
			list.add(d);
			for (int k = 1; k <= g; k++) {
				for (int m = list.size() - 1; m >= 0; m--) {
					int temp = list.get(m);
					if (temp == 3)
						temp = 0;
					else
						temp++;
					list.add(temp);
				}
			}
			
			
			arr[y][x] = true;
			int tempy = y;
			int tempx = x;
			for (int k = 0; k < list.size(); k++) {
				if (list.get(k) == 0) {
					tempx += 1;
					arr[tempy][tempx] = true;
				} else if (list.get(k) == 1) {
					tempy -= 1;
					arr[tempy][tempx] = true;
				} else if (list.get(k) == 2) {
					tempx -= 1;
					arr[tempy][tempx] = true;
				} else if (list.get(k) == 3) {
					tempy += 1;
					arr[tempy][tempx] = true;
				}
			}
		}
	
		count = 0;
		for (int m = 0; m < arr.length - 1; m++) {
			for (int n = 0; n < arr[0].length - 1; n++) {
				if (arr[m][n]&&arr[m + 1][n] && arr[m][n + 1] && arr[m + 1][n + 1])
					count++;
			}
		}
		System.out.println(count);
	}
}
