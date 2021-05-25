package com.company.coursework.pizza;

public class Dijkstra {

    private static int max = Integer.MAX_VALUE;
    private static int dist[] = new int[6];
    private static int prve[] = new int[6];
    private static int a[][] = {
            {0, max, 10, max, 30, 100},
            {max, 0, 5, max, max, max},
            {max, max, 0, 50, max, max},
            {max, max, max, 0, max, 10},
            {max, max, max, 20, 0, 60},
            {max, max, max, max, max, 0}
    };

    public void dijkstra(int v, int dist[], int prve[]) {
        int n = dist.length - 1;
        boolean[] s = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = a[v][i];
            s[i] = false;
            if (dist[i] < Integer.MAX_VALUE)
                prve[i] = v;
            else
                prve[i] = -1;
        }

        dist[v] = 0;
        s[v] = true;
        for (int i = 1; i <= n; i++) {
            int temp = Integer.MAX_VALUE;
            int u = v;
            for (int j = 1; j <= n; j++) {
                if ((!s[j]) && dist[j] < temp) {
                    u = j;
                    temp = dist[j];
                }
            }
            s[u] = true;
            for (int j = 0; j <= n; j++) {
                if ((!s[j]) && a[u][j] < Integer.MAX_VALUE) {
                    int newDist = dist[u] + a[u][j];
                    if (newDist < dist[j]) {
                        dist[j] = newDist;
                        prve[j] = u;
                    }
                }
            }
        }
    }

    public void outPath(int m, int[] p, int[] d) {
        for (int i = 0; i < dist.length; i++) {
            if (d[i] < Integer.MAX_VALUE && i != m) {
                System.out.print("v" + i + "<--");
                int next = p[i];
                while (next != m) {
                    System.out.print("v" + next + "<--");
                    next = p[next];
                }
                System.out.println("v" + m + ":" + d[i]);
            } else if (i != m)
                System.out.println("v" + i + "<--" + "v" + m + ":no path");
        }
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Dijkstra D = new Dijkstra();
//        D.dijkstra(0, a, dist, prve);
//        D.outPath(0, prve, dist);
//    }
}
