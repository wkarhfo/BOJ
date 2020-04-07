import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 
 * @author 정호
 *	unoinfind를 사용하여서 해당 루트노드가 모든 같으면 이동가능, 아니라면 불가능
 *
 *
 */
public class BOJ_여행가자 {
	static int[][] arr;
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int M = Integer.parseInt(br.readLine().trim());
		arr = new int[N + 1][N + 1];
		result = new int[N + 1];
		// makeset
		for (int i = 1; i < result.length; i++) {
			result[i] = i;
		}

		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// union find 실행
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				if (i < j && arr[i][j] == 1) {
					union(i, j);
				}
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int temp=0;
		Set<Integer> set=new HashSet<Integer>();
		for (int i = 0; i < M; i++) {
			int a = Integer.parseInt(st.nextToken());
			temp=findSet(a);
			set.add(temp);
		}
		if(set.size()==1) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}

	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a == b)
			return;
		result[b] = a;
	}

	private static int findSet(int num) {
		if (result[num] == num)
			return num;
		int temp = findSet(result[num]);
		result[num] = temp;
		return temp;
	}
}
