import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_n과m9 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result;
	static boolean[] visit;
	static Set<String> set;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		visit=new boolean[N];
		result = new int[M];
		set=new LinkedHashSet<>();
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		dfs(0,"");
		
		for(String re:set) {
			System.out.println(re);
		}
	}

	private static void dfs(int depth,String s) {
		if(depth==M) {
			set.add(s);
			return;
		}
		for(int i=0;i<arr.length;i++) {
			if(visit[i]==false) {
				visit[i]=true;
				dfs(depth+1,s+arr[i]+" ");
				visit[i]=false;
			}
		}
	}
	
}