import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_연산자끼워넣기 {
	static int num;
	static int[] arr;
	static boolean[] visit;
	static int[] result;
	static ArrayList<Integer> oper;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		arr = new int[num];
		for (int i = 0; i < num; i++) {
			arr[i] = sc.nextInt();
		}
		oper = new ArrayList<>();
		int plus = sc.nextInt();
		for (int i = 0; i < plus; i++) {
			oper.add(1);
		}
		int minus = sc.nextInt();
		for (int i = 0; i < minus; i++) {
			oper.add(2);
		}
		int gop = sc.nextInt();
		for (int i = 0; i < gop; i++) {
			oper.add(3);
		}
		int na = sc.nextInt();
		for (int i = 0; i < na; i++) {
			oper.add(4);
		}
		result = new int[num - 1];
		visit = new boolean[oper.size()];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	static int max;
	static int min;

	static void dfs(int depth) {
		if (depth == result.length) {
			int count = 0;
			int sum = 0;

			while (true) {
				if (count + 1 >= arr.length) {
					break;
				}
				int a = arr[count];
				int b = arr[count + 1];
				if (count == 0) {
					if (result[count] == 1) {
						sum += a + b;
					} else if (result[count] == 2) {
						sum += a - b;
					} else if (result[count] == 3) {
						sum += a * b;
					} else {
						if (a < 0) {
							a = Math.abs(a);
							sum += -(a / b);
						} else {
							sum += a / b;
						}
					}
				} else {
					if (result[count] == 1) {
						sum += b;
					} else if (result[count] == 2) {
						sum -= b;
					} else if (result[count] == 3) {
						sum *= b;
					} else {
						if (sum < 0) {
							int tmp = Math.abs(sum);
							sum = -(tmp / b);
						} else {
							sum /= b;
						}
					}
				}

				count++;
			}
			if (max < sum) {
				max = sum;
			}
			if (min > sum) {
				min = sum;
			}
			return;
		}
		for (int i = 0; i < oper.size(); i++) {
			if (visit[i] == false) {
				visit[i] = true;
				result[depth] = oper.get(i);
				dfs(depth + 1);
				visit[i] = false;
			}
		}
	}

}
