import java.util.Scanner;

public class BOJ_벌꿀 {
	static int size;
	static int M;
	static int C;
	static int[][] map, maxMap;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			size = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			map = new int[size][size];
			maxMap = new int[size][size];
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			makeMaxMap();
			System.out.println("#"+tc+" "+getMaxBenefit());
			
			
		}

	}

	static void makeMaxMap() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= size - M; j++) {
					makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}

	// i:행 j:열 cnt:고려한 원소 수
	// sum: 부분집합에 속한 원소의 합
	// powSum: 부분집합에 속한 원소의 이익
	static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
		if(sum>C) return; //부분집합의 합이 목표량 C를 초과하면 리턴
		if (cnt == M) {
			if(maxMap[i][j-M]<powSum) {
				maxMap[i][j-M]=powSum;
			}
			return;
		}

		// i,j위치 원소 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]);

		// i,j위치 원소 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);
	}
	static int getMaxBenefit() {
		int max=0,temp=0; //max: 조합적 선택 후의 최대이익값
		//1. 일꾼A 기준 선택
		for (int i = 0; i < size; i++) {
			for(int j=0;j<=size-M;j++) {//a일꾼
				//2.일꾼B선택
				//같은 행 기준 선택
				for(int k=j+M;k<=size-M;k++) {
					temp=maxMap[i][j]+maxMap[i][k];
					if(max<temp) {
						max=temp;
					}
				}
				//다음행부터 마지막행까지 선택
				for(int k=i+1;k<size;k++) {
					for(int l=0;l<=size-M;l++) {
						temp=maxMap[i][j]+maxMap[k][l];
						if(max<temp) {
							max=temp;
						}
					}
				}
			}
		}
		
		return max;
	}
}
