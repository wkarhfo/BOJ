import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_연산자끼워넣기2{
	static int max=Integer.MIN_VALUE;
	static int min=Integer.MAX_VALUE;
	static int s;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		String[] arr=br.readLine().split(" ");
		int[] a=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=Integer.parseInt(arr[i]);
			
		}
		int plus=0,minus=0,mul=0,div=0;
		String[] pm=br.readLine().split(" ");
		for(int i=0;i<4;i++) {
			plus=Integer.parseInt(pm[0]);
			minus=Integer.parseInt(pm[1]);
			mul=Integer.parseInt(pm[2]);
			div=Integer.parseInt(pm[3]);
		}
		s=plus+minus+mul+div;
		go(a,0,a[0],plus,minus,mul,div);
		System.out.println(max+"\n"+min);
	}

	private static void go(int[] a, int index, int sum, int plus, int minus, int mul, int div) {
		if(index==a.length-1) {
			if(plus+minus+mul+div==s-a.length+1) {
				max=Math.max(max, sum);
				min=Math.min(min, sum);
				return;
			}
			return;
		}
		if(plus<0||minus<0||mul<0||div<0) return;
		if(plus>0) go(a,index+1,sum+a[index+1],plus-1,minus,mul,div);
		if(minus>0) go(a,index+1,sum-a[index+1],plus,minus-1,mul,div);
		if(mul>0) go(a,index+1,sum*a[index+1],plus,minus,mul-1,div);
		if(div>0) go(a,index+1,sum/a[index+1],plus,minus,mul,div-1);
	}
}