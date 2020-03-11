import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_123더하기 {
	static int num;
	static int count;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			num=sc.nextInt();
			count=0;
			for(int i=1;i<=3;i++) {
				findSum(i);
			}
			System.out.println(count);
		}
	}

	static void findSum(int i) {
		Queue<Data> q=new LinkedList<>();
		q.add(new Data(i,i));
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(tmp.sum==num) {
				count++;
			}
			for(int k=1;k<=3;k++) {
				if(tmp.sum+k<=num) {
					q.add(new Data(k,tmp.sum+k));
				}
			}
		}
	}
	static class Data{
		int idx;
		int sum;
		public Data(int idx, int sum) {
			this.idx = idx;
			this.sum = sum;
		}
	}
}
