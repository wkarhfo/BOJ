import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_게리멘더링_리스트배열활용 {
	static int N;
	static int[] people;
	static ArrayList<Integer>[] list;
	static int[] arr;
	static boolean[] visit;
	static int sum;
	static int MIN=Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		people = new int[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				int num = Integer.parseInt(st.nextToken());
				list[i].add(num);
			}
		}

		// 1.조합으로 지역구 나누기
		for (int i = 1; i <= N / 2; i++) {
			arr = new int[N + 1];
			dfs(1, 0, i);
		}
		if(MIN==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(MIN);
		}
	}

	static void dfs(int start, int depth, int dest) {
		if (depth == dest) {
			visit = new boolean[N + 1];
			int result1=0;
			int result2=0;
			sum=0;
			// 1번째 선거구 인원이 같은 선거구인지 기록하기
			for (int i = 1; i < arr.length; i++) {
				if (!visit[i] && arr[i] == 1) {
					gary(i);
					result1=sum;
					break;
				}
			}
			sum=0;
			for (int i = 1; i < arr.length; i++) {
				if (!visit[i] && arr[i] == 0) {
					gary(i);
					result2=sum;
					break;
				}
			}
			
			for(int i=1;i<visit.length;i++) {
				if(!visit[i]) //방문하지 않은, 즉 두 선거구에 있는 어느 지역과도 연결지을 수 없는 곳이라는 뜻이다.
					return;
			}
			MIN=Math.min(MIN, Math.abs(result2-result1));
			return;
		}
		for (int i = start; i < N + 1; i++) {
			arr[i] = 1;
			dfs(i + 1, depth + 1, dest);
			arr[i] = 0;
		}
	}

	static void gary(int idx) {
		visit[idx] = true;
		sum+=people[idx];
		for (int i = 0; i < list[idx].size(); i++) {
			int temp = list[idx].get(i);
			if (visit[temp])
				continue;
			if (arr[temp] != arr[idx])
				continue;
			gary(temp);
		}
	}
}
