import java.util.Scanner;

public class BOJ_N퀸 {
	static int[][] arr;
	static int size;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		arr = new int[size][size];
		count = 0;
		dfs(0);
		System.out.println(count);
	}

	static void dfs(int depth) {
		if (depth == size) {
			count++;
			return;
		}
		for (int j = 0; j < size; j++) {
			if (isPromising(depth,j)) {
				arr[depth][j] = 1;
				dfs(depth + 1);
				arr[depth][j] = 0;
			}
		}
	}
	static boolean isPromising(int i,int j) {
		for(int m=i;m>=0;m--) {
			if(arr[m][j]==1) {
				return false;
			}
		}
		
		for(int m=i,n=j ;m>=0&&n<size ; m--,n++) {
			if(arr[m][n]==1) {
				return false;
			}
		}
		
		for(int m=i,n=j;m>=0&&n>=0;m--,n--) {
			if(arr[m][n]==1) {
				return false;
			}
		}
		
		return true;
	}
}
