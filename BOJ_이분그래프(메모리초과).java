import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algo2_구미_4반_서정호 {
	static int V, E;
	static int[][] arr;
	static boolean[][] visit;
	static int[] color;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // 테스트케이스 값을 입력받음
		for (int tc = 1; tc <= TC; tc++) { // 테스트케이스만큼 루프를 돔
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점의 갯수 입력받음
			E = Integer.parseInt(st.nextToken()); // 간선의 갯수 입력받음
			arr = new int[V + 1][V + 1]; // 간선에 대한 정보를 저장하기 위한 배열
			visit = new boolean[V + 1][V + 1]; // 체크 배열
			color = new int[V + 1]; // 정점의 이분그래프를 체크하기 위한 배열

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 간선에 대한 첫번째 정점
				int b = Integer.parseInt(st.nextToken()); // 간선에 대한 두번째 정점
				arr[a][b] = 1; // 각 간선이 이어진것을 표시함
				arr[b][a] = 1; // 각 간선이 이어진것을 표시함
			}

			boolean flag = true;
			Queue<Integer> q = new LinkedList<>(); // bfs를 이용하여 이분그래프를 파악하기 위해서 선언
			color[1] = 1; // 첫번째 시작 정점의 1로 표시 (이분그래프이므로 1,2로 나누어 져야함)
			for (int i = 1; i < arr.length; i++) {
				if (arr[i][1] == 1) { // 첫번째 시작 정점과 연결된 정점일시에
					q.add(i);
					visit[i][1] = true; // 방문체크
					visit[1][i] = true; // 방문체크
					color[i] = 2; // 1과 연결된 정점이기 때문에 해당 정점을 2로 표시
				}
			}

			while (!q.isEmpty()) {
				int temp = q.poll();
				for (int i = 1; i < arr.length; i++) {
					if (arr[i][temp] == 0 || visit[i][temp]) // 해당 간선의 정보가 없거나 이미 방문한곳이면 pass
						continue;
					if (color[temp] == color[i]) { // 만약 이전의 정점과 현재 정점의 숫자가 같다는것은 연결된 두정점이 같은 집합이라는 것이기 때문에 이분그래프가 되지않기에
													// NO출력,break
						flag = false;
//						System.out.println("NO");
						break;
					}

					visit[i][temp] = true; // 방문체크
					visit[temp][i] = true; // 방문체크
					if (color[temp] == 1) { // 이전 정점이 1이라면 다음 정점은 2로 표시
						color[i] = 2;
					} else { // 이전 정점이 2라면 다음 정점은 1로 표시
						color[i] = 1;
					}
					q.add(i);
				}

			}
			if (flag)
				System.out.println("YES"); // 이분그래프라면 YES표시
			else
				System.out.println("NO");
		}
	}
}
