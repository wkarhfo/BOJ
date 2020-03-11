import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_물통 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		boolean[][][] visit = new boolean[A + 1][B + 1][C + 1];
		
		ArrayList<Integer> list=new ArrayList<>();
		Queue<Data> q = new LinkedList<>();
		Data start = new Data(0, 0, C);
		q.add(start);
		visit[0][0][C] = true;
		boolean flag = false;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int i = 0; i < 6; i++) {
				int tmpA = tmp.a;
				int tmpB = tmp.b;
				int tmpC = tmp.c;
				
				if (i == 1) {
					if (tmpB < B) {
						int full = B - tmpB;
						if (tmpA >= full) {
							tmpB += full;
							tmpA -= full;
						} else {
							tmpB += tmpA;
							tmpA = 0;
						}
					}
				} else if (i == 2) {
					if (tmpC < C) {
						int full = C - tmpC;
						if (tmpA >= full) {
							tmpC += full;
							tmpA -= full;
						} else {
							tmpC += tmpA;
							tmpA = 0;
						}
					}
				} else if (i == 3) {
					if (tmpA < A) {
						int full = A - tmpA;
						if (tmpB >= full) {
							tmpA += full;
							tmpB -= full;
						} else {
							tmpA += tmpB;
							tmpB = 0;
						}
					}
				} else if (i == 4) {
					if (tmpC < C) {
						int full = C - tmpC;
						if (tmpB >= full) {
							tmpC += full;
							tmpB -= full;
						} else {
							tmpC += tmpB;
							tmpB = 0;
						}
					}
				} else if (i == 5) {
					if (tmpA < A) {
						int full = A - tmpA;
						if (tmpC >= full) {
							tmpA += full;
							tmpC -= full;
						} else {
							tmpA += tmpC;
							tmpC = 0;
						}
					}
				} else {
					if (tmpB < B) {
						int full = B - tmpB;
						if (tmpC >= full) {
							tmpB += full;
							tmpC -= full;
						} else {
							tmpB += tmpC;
							tmpC = 0;
						}
					}
				}
				if (flag) {
					if (visit[tmpA][tmpB][tmpC] == true)
						continue;
				}
				if(tmpA==0) {
					if(!list.contains(tmpC)) {
						list.add(tmpC);
					}
				}
				Data newData = new Data(tmpA, tmpB, tmpC);
				visit[tmpA][tmpB][tmpC]=true;
//				System.out.println(tmpA+" "+tmpB+" "+tmpC);
				q.add(newData);
				
			}
			flag=true;
		}
		
		Collections.sort(list);
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+" ");
		}
		
	}

	static class Data {
		int a;
		int b;
		int c;

		public Data(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}
}
