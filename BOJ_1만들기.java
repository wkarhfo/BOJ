import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1만들기 {
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		result=new int[num+1];
		System.out.println(dfs(num));
	}
	private static int dfs(int num) {
		if(num==1) {
			return 0;
		}
		if(result[num]>0) {
			return result[num];
		}
		result[num]=dfs(num-1)+1;
		if(num%3==0) {
			result[num]=Math.min(result[num], dfs(num/3)+1);
		}
		if(num%2==0) {
			result[num]=Math.min(result[num], dfs(num/2)+1);
		}
		return result[num];
	}
}
