import java.util.Scanner;

public class BOJ_로또 {
	static int num;
	static int[] arr;
	static int[] result;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			num=sc.nextInt();
			if(num==0)
				return;
			arr=new int[num];
			result=new int[6];
			for(int i=0;i<num;i++) {
				arr[i]=sc.nextInt();
			}
			dfs(0,0);
			System.out.println();
			
		}
		
	}
	static void dfs(int begin,int depth) {
		if(depth==6) {
			for(int i=0;i<result.length;i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=begin;i<arr.length;i++) {
			result[depth]=arr[i];
			dfs(i+1,depth+1);
		}
	}

}
