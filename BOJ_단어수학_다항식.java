import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_단어수학_다항식 {
	static int N;
	static int[] eng = new int[26];
	static int[] result=new int[26];
	static String[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr=new String[N];
		for(int i=0;i<N;i++) {
			String s=br.readLine();
			arr[i]=s;
			int tmp=1;
			for(int k=s.length()-1;k>=0;k--) {
				eng[s.charAt(k)-'A']+=tmp;
				tmp*=10;
			}
			
		}
		
		int num=9;
		while(true) {
			int max=0;
			int count=0;
			for(int i=0;i<eng.length;i++) {
				if(eng[i]!=0)
					count++;
				if(eng[i]>eng[max]) {
					max=i;
				}
			}
			if(count==0)
				break;
			result[max]=num;
			eng[max]=0;
			num--;
		}
		
		int sum=0;
		for(String s:arr) {
			String temp="";
			for(int i=0;i<s.length();i++) {
				temp+=result[s.charAt(i)-'A'];
			}
			sum+=Integer.parseInt(temp);
		}
		System.out.println(sum);
		
	}
}
