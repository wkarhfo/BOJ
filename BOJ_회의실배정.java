import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_회의실배정 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		Data[] list=new Data[test];
		for(int i=0;i<test;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			list[i]=new Data(x,y);
		}
		Arrays.sort(list,new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				if(o1.end==o2.end) {
					return o1.start-o2.start;
				}
				return o1.end-o2.end;
			}
		});
		
		int count=1;
		int last=list[0].end;
		for(int i=1;i<list.length;i++) {
			if(last<=list[i].start) {
				last=list[i].end;
				count++;
			}
		}
		System.out.println(count);
	}
	static class Data{
		int start;
		int end;
		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
	}
}
