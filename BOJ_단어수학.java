import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_단어수학 {
	static int N;
	static String[] arr;
	static int[] eng = new int[26];
	static int count;
	static boolean[] visit = new boolean[10];
	static int[] result;
	static int MAX=Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new String[N];
		for (int i = 0; i < eng.length; i++)
			eng[i] = -1;

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}

		for (String s : arr) {
			for (int i = 0; i < s.length(); i++) {
				eng[s.charAt(i) - 'A'] = 1;
			}
		}
		count = 0;
		for (int i = 0; i < eng.length; i++) {
			if (eng[i] == 1)
				count++;
		}
		result=new int[count];
		dfs(0);
		System.out.println(MAX);
	}

	private static void dfs(int depth) {
		if (depth == count) {
			int count=0;
			for(int i=0;i<eng.length;i++) {
				if(eng[i]!=-1) {
					eng[i]=result[count];
					count++;
				}
			}
			int sum=0;
			for(String s:arr) {
				String temp="";
				for(int i=0;i<s.length();i++) {
					temp+=eng[s.charAt(i)-'A'];
				}
				sum+=Integer.parseInt(temp);
			}
//			System.out.println(sum);
			MAX=Math.max(MAX, sum);
			return;
		}
		for (int i = 0; i < 10; i++) {
			if(!visit[i]) {
				visit[i]=true;
				result[depth]=i;
				dfs(depth+1);
				visit[i]=false;
			}
		}
	}
}
