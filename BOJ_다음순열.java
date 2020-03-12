import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_다음순열 {
	static int[] result;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		if (NextPermitation()) {
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}

	static boolean NextPermitation() {
		int tmp=result.length-1;
		while(tmp>0&&result[tmp-1]>=result[tmp]) {
			tmp--;
		}
		if(tmp<=0) 
			return false;
		int temp=result.length-1;
		while(result[tmp-1]>=result[temp]) {
			temp--;
		}
		
		int swap=result[tmp-1];
		result[tmp-1]=result[temp];
		result[temp]=swap;
		
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=tmp;i<result.length;i++) {
			list.add(result[i]);
		}
		Collections.sort(list);
		for(int i=tmp;i<result.length;i++) {
			result[i]=list.remove(0);
		}
		return true;
	}
}
