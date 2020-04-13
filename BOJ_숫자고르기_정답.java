import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_숫자고르기_정답 {

	static int N;
	static int[] arr;
	static boolean[] visit;
	static int point;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		N = Integer.parseInt(br.readLine().trim());
		arr=new int[N+1];
		
		for(int i=1;i<arr.length;i++) {
			arr[i]=Integer.parseInt(br.readLine().trim());
		}
		list=new ArrayList<>();
		for(int i=1;i<arr.length;i++) {
			visit=new boolean[N+1];
			point=i;
			if(dfs(i)) {
				list.add(arr[i]);
			}
		}
		
		Collections.sort(list);
		sb.append(list.size()).append("\n");
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static boolean dfs(int idx) {
		if(arr[idx]==point) {
			return true;
		}
		if(visit[idx])
			return false;
		visit[idx]=true;
		return dfs(arr[idx]);
		
	}

}
