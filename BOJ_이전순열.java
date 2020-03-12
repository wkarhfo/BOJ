/**
 * 다음순열과 반대로!!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_이전순열 {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int size=Integer.parseInt(st.nextToken());
		arr=new int[size];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<arr.length;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		if (PreviousPermitation()) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}
	private static boolean PreviousPermitation() {
		int temp=arr.length-1;
		while(temp>0&&arr[temp-1]<arr[temp]) {
			temp--;
		}
		if(temp<=0) 
			return false;
		int last=arr.length-1;
		while(arr[last]>=arr[temp-1]) {
			last--;
		}
		
		int swap=arr[temp-1];
		arr[temp-1]=arr[last];
		arr[last]=swap;
		
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=temp;i<arr.length;i++) {
			list.add(arr[i]);
		}
		Collections.sort(list);
		for(int i=temp;i<arr.length;i++) {
			arr[i]=list.remove(list.size()-1);
		}
		
		return true;
	}

}
