import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_효율적인해킹 {
   static ArrayList<Integer> list[] = (ArrayList<Integer>[]) new ArrayList[10001];
   static boolean[] visit;
   static int num;
   static int[] arr;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      num = sc.nextInt();
      arr = new int[10001];
      
      for (int i = 1; i < 10001; i++) {
         list[i] = new ArrayList<Integer>();
      }
      int order = sc.nextInt();
      for (int i = 0; i < order; i++) {
         int end = sc.nextInt();
         int start = sc.nextInt();
         list[start].add(end);
      }
      
      
      
      for (int i = 1; i < 10001; i++) {
         bfs(i);
      }
      
      int max = 0;
      for(int i=1;i<arr.length;i++) {
         if(max < arr[i])
            max = arr[i];
      }
      
      for(int i =1; i < arr.length; i++) {
         if(max == arr[i])
            System.out.print(i + " ");
      }
   }

   static void bfs(int idx) {
      visit = new boolean[10001];
      Queue<Integer> q = new LinkedList<>();
      q.add(idx);
      visit[idx] = true;
      while (!q.isEmpty()) {
         int temp = q.poll();
         for (int k = 0; k < list[temp].size(); k++) {
            int v = list[temp].get(k);
            if (visit[v] == false) {
               arr[idx]++;
               visit[v] = true;
               q.add(v);
            }

         }
      }
   }

}

