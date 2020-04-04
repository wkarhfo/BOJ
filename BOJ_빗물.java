import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_빗물 {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hang = Integer.parseInt(st.nextToken());
		int yeul = Integer.parseInt(st.nextToken());
		arr = new int[yeul];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[max] < arr[i])
				max = i;
		}
		int temp = arr[max];
		int count = 0;
		while (temp > 0) {
			int idx = max;
			if (max - 1 >= 0) {
				for (int i = max - 1; i >= 0; i--) {
					if (arr[i] >= temp && idx - i > 1) {
						count += idx - i - 1;
						idx = i;
					} else if (arr[i] >= temp && idx - i == 1) {
						idx = i;
					}
				}
			}
			idx = max;
			if (max + 1 < arr.length) {
				for (int i = max + 1; i < arr.length; i++) {
					if (arr[i] >= temp && i - idx > 1) {
						count += i - idx - 1;
						idx = i;
					}else if (arr[i] >= temp && i-idx == 1) {
						idx = i;
					}
				}
			}
			temp--;
		}
		System.out.println(count);
	}
}
