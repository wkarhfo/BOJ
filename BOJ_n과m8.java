import java.util.Arrays;
import java.util.Scanner;

public class BOJ_n과m8 {
	static int N;
	static int M;
	static int[] arr;
	static int[] result;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		arr=new int[N];
		result=new int[M];
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		Arrays.sort(arr);
		dfs(0,0);
	}
	static void dfs(int begin, int depth) {
		if(depth==M) {
			for(int i=0;i<result.length;i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=begin;i<arr.length;i++) {
			result[depth]=arr[i];
			dfs(i,depth+1);
		}
		
	}

}
