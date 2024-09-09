package UnionFindSet;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

// 注意类名必须为 Main, 不要有任何 package xxx 信息


// https://www.nowcoder.com/practice/e7ed657974934a30b2010046536a5372

public class NewCoderUnionFindSet {
    public static int MAXN = 1000001;
    public static int[] father = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int[] stack = new int[MAXN];
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build();
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                } else {
                    union(x, y);
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void build() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static int find(int a) {
        int size = 0;

        while (father[a] != a) {
            stack[size++] = a;
            a = father[a];
        }

        while (size > 0) {
            father[stack[--size]] = a;
        }

        return a;
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            if (size[fa] >= size[fb]) {
                size[fa] += size[fb];
                father[fb] = father[fa];
            } else {
                size[fb] += size[fa];
                father[fa] = father[fb];
            }
        }
    }
}