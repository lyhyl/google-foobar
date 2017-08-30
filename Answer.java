/**
 * Created by Wingkou on 8/29/2017.
 */

import java.lang.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Answer {
    public static void main(String[] args) {
        // L1
        System.out.println(answer("wrw blf hvv ozhg mrtsg'h vkrhlwv?"));
        System.out.println(answer("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));

        // L2-1
        System.out.println(answer(new int[]{3, 1, 4, 1}));
        System.out.println(answer(new int[]{3, 1, 4, 1, 5, 9}));
        System.out.println(answer(new int[]{9, 8, 3, 5, 3, 8, 6, 2, 8}));

        // L2-2
        printArray(answer(new String[]{"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"}));
        printArray(answer(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"}));

        // L3-1
        System.out.println(answer("2", "4"));
        System.out.println(answer("2", "1"));
        System.out.println(answer("4", "7"));

        // L3-2
        printArray(answer(new int[][]{
                {0, 0, 12, 0, 15, 0, 0, 0, 1, 8},
                {0, 0, 60, 0, 0, 7, 13, 0, 0, 0},
                {0, 15, 0, 8, 7, 0, 0, 1, 9, 0},
                {23, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {37, 35, 0, 0, 0, 0, 3, 21, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}));
        printArray(answer(new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        }));
        printArray(answer(new int[][]{
                {0, 2, 1, 0, 0},
                {0, 0, 0, 3, 4},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        }));
    }

    private static void printArray(int[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]);
            System.out.print(" , ");
        }
        System.out.println();
    }

    private static <T> void printArray(T[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]);
            System.out.print(" , ");
        }
        System.out.println();
    }

    // L1
    public static String answer(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z')
                ans.append((char) (25 - (s.charAt(i) - 'a') + 'a'));
            else
                ans.append((s.charAt(i)));
        return ans.toString();
    }

    // L2-1
    private static int[] m = new int[10];

    public static boolean remove(int x) {
        for (int i = x; i <= 9; i += 3)
            if (m[i] > 0) {
                m[i]--;
                return true;
            }
        return false;
    }

    public static int answer(int[] l) {
        for (int i = 0; i < 10; i++)
            m[i] = 0;
        int sum = 0;
        for (int i = 0; i < l.length; i++) {
            sum += l[i];
            m[l[i]]++;
        }
        switch (sum % 3) {
            case 0:
                break;
            case 1:
                if (remove(1))
                    break;
                if (remove(2) && remove(2))
                    break;
                return 0;
            case 2:
                if (remove(2))
                    break;
                if (remove(1) && remove(1))
                    break;
                return 0;
        }
        int ans = 0;
        for (int n = 9; n >= 0; n--)
            for (int i = 0; i < m[n]; i++)
                ans = ans * 10 + n;
        return ans;
    }

    // L2-2
    public static class V {
        public Integer[] v;
        public String r;

        public V(String s) {
            r = s;
            ArrayList<Integer> vv = new ArrayList<>();
            String[] sp = s.split("\\.");
            for (String ss : sp)
                vv.add(Integer.valueOf(ss));
            while (vv.size() < 3)
                vv.add(-1);
            v = vv.toArray(new Integer[3]);
        }

        @Override
        public String toString() {
            return r;
        }
    }

    public static String[] answer(String[] l) {
        V[] vs = new V[l.length];
        for (int i = 0; i < l.length; i++)
            vs[i] = new V(l[i]);
        Arrays.sort(vs, new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                if (o1.v[0].compareTo(o2.v[0]) == 0) {
                    if (o1.v[1].compareTo(o2.v[1]) == 0) {
                        return o1.v[2].compareTo(o2.v[2]);
                    } else
                        return o1.v[1].compareTo(o2.v[1]);
                } else
                    return o1.v[0].compareTo(o2.v[0]);
            }
        });
        String[] ans = new String[l.length];
        for (int i = 0; i < l.length; i++) {
            ans[i] = vs[i].toString();
        }
        return ans;
    }

    // L3-1
    public static String answer(String M, String F) {
        BigInteger m = new BigInteger(M);
        BigInteger f = new BigInteger(F);
        BigInteger c = BigInteger.ZERO;
        while (!(m.equals(BigInteger.ZERO) || f.equals(BigInteger.ZERO))) {
            int cmp = m.compareTo(f);
            if (cmp < 0) {
                BigInteger[] r = f.divideAndRemainder(m);
                c = c.add(r[0]);
                f = r[1];
            } else if (cmp > 0) {
                BigInteger[] r = m.divideAndRemainder(f);
                c = c.add(r[0]);
                m = r[1];
            } else
                return "impossible";
        }
        if ((m.equals(BigInteger.ONE) && f.equals(BigInteger.ZERO)) ||
                (m.equals(BigInteger.ZERO) && f.equals(BigInteger.ONE))) {
            c = c.subtract(BigInteger.ONE);
            return c.toString();
        } else
            return "impossible";
    }

    // L3-2
    public static long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static class Num {
        public long z, m;

        public Num(long a) {
            z = a;
            m = 1;
        }

        public Num(long a, long b) {
            z = a;
            m = b;
            red();
        }

        public Num(Num v) {
            z = v.z;
            m = v.m;
            red();
        }

        public Num add(Num o) {
            return new Num(z * o.m + o.z * m, m * o.m);
        }

        public Num sub(Num o) {
            return new Num(z * o.m - o.z * m, m * o.m);
        }

        public Num mul(Num o) {
            return new Num(z * o.z, m * o.m);
        }

        public Num div(Num o) {
            return new Num(z * o.m, m * o.z);
        }

        private void red() {
            long c = gcd(z, m);
            z /= c;
            m /= c;
            if (m < 0) {
                m = -m;
                z = -z;
            }
        }

        public void swap(Num o) {
            long a = z, b = m;
            z = o.z;
            m = o.m;
            o.z = a;
            o.m = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Num))
                return false;
            Num o = (Num) obj;
            return o.z == z && o.m == m;
        }

        @Override
        public String toString() {
            return z + "/" + m;
        }
    }

    public static class Mat {
        public Num[][] v;
        public int m, n;

        public Mat(int a, int b) {
            m = a;
            n = b;
            v = new Num[a][b];
            for (int i = 0; i < a; i++)
                for (int j = 0; j < b; j++)
                    v[i][j] = new Num(0);
        }

        public Mat(int a, int b, int x) {
            m = a;
            n = b;
            v = new Num[a][b];
            for (int i = 0; i < a; i++)
                for (int j = 0; j < b; j++)
                    if (i != j)
                        v[i][j] = new Num(0);
                    else
                        v[i][j] = new Num(x);
        }

        public Mat mul(Mat o) {
            if (n != o.m) {
                int a = 1 / 0;
            }
            // m x n=o.m x o.n
            Mat r = new Mat(m, o.n);
            for (int i = 0; i < m; i++)
                for (int j = 0; j < o.n; j++)
                    for (int k = 0; k < n; k++)
                        r.v[i][j] = r.v[i][j].add(v[i][k].mul(o.v[k][j]));
            return r;
        }

        public Mat sub(Mat o) {
            if (m != o.m || n != o.n) {
                int a = 1 / 0;
            }
            Mat r = new Mat(m, n);
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    r.v[i][j] = v[i][j].sub(o.v[i][j]);
            return r;
        }

        public Mat inv() {
            if (m != n) {
                int a = 1 / 0;
            }
            int s = m;
            Num[][] t = new Num[s][s];
            for (int i = 0; i < s; i++)
                for (int j = 0; j < s; j++)
                    t[i][j] = new Num(v[i][j]);
            Mat r = new Mat(s, s, 1);
            for (int i = 0; i < s; i++) {
                int row = i;
                for (int j = i; j < s; j++)
                    if (t[i][j].z != 0) {
                        row = j;
                        break;
                    }
                // swap
                if (row != i) {
                    for (int j = i; j < s; j++)
                        t[i][j].swap(t[row][j]);
                    for (int j = 0; j < s; j++)
                        r.v[i][j].swap(r.v[row][j]);
                }
                // dig
                Num p = t[i][i];
                for (int j = i; j < s; j++)
                    t[i][j] = t[i][j].div(p);
                for (int j = 0; j < s; j++)
                    r.v[i][j] = r.v[i][j].div(p);

                for (int j = i + 1; j < s; j++) {
                    p = t[j][i];
                    if (p.z == 0)
                        continue;
                    for (int k = i; k < s; k++)
                        t[j][k] = t[j][k].div(p).sub(t[i][k]);
                    for (int k = 0; k < s; k++)
                        r.v[j][k] = r.v[j][k].div(p).sub(r.v[i][k]);
                }
            }
            // rev-dig
            for (int i = s - 1; i > 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    for (int k = 0; k < s; k++) {
                        r.v[j][k] = r.v[j][k].sub(t[j][i].mul(r.v[i][k]));
                    }
                }
            }
            return r;
        }
    }

    private static int sum(int[] r) {
        int s = 0;
        for (int i : r)
            s += i;
        return s;
    }

    public static int[] answer(int[][] m) {
        int[] sumRow = new int[m.length];
        for (int i = 0; i < m.length; i++)
            sumRow[i] = sum(m[i]);
        int tc = 0;
        for (int i = 0; i < m.length; i++)
            if (sumRow[i] == 0)
                tc++;
        int ntc = m.length - tc;

        if (ntc == 0) {
            int[] ans = new int[m.length + 1];
            ans[0] = 1;
            for (int i = 1; i < m.length; i++)
                ans[i] = 0;
            ans[m.length] = 1;
            return ans;
        }

        // init Q
        Mat Q = new Mat(ntc, ntc);
        for (int qi = 0, mi = 0; qi < ntc; mi++) {
            if (sumRow[mi] != 0) {
                for (int qj = 0, mj = 0; qj < ntc; mj++) {
                    if (sumRow[mj] != 0) {
                        Q.v[qi][qj] = new Num(m[mi][mj], sumRow[mi]);
                        qj++;
                    }
                }
                qi++;
            }
        }

        // init R
        Mat R = new Mat(ntc, tc);
        for (int ri = 0, mi = 0; ri < ntc; mi++) {
            if (sumRow[mi] != 0) {
                for (int rj = 0, mj = 0; rj < tc; mj++) {
                    if (sumRow[mj] == 0) {
                        R.v[ri][rj] = new Num(m[mi][mj], sumRow[mi]);
                        rj++;
                    }
                }
                ri++;
            }
        }

        Mat I = new Mat(ntc, ntc, 1);
        Mat I_Q = I.sub(Q);
        Mat F = I_Q.inv();
        Mat FR = F.mul(R);
        int[] ans = new int[tc + 1];
        for (int i = 0; i < tc; i++)
            ans[i] = (int) FR.v[0][i].z;
        long com = 1;
        for (int i = 0; i < tc; i++)
            com = lcm(com, FR.v[0][i].m);
        ans[tc] = (int) com;
        for (int i = 0; i < tc; i++)
            ans[i] *= com / FR.v[0][i].m;
        return ans;
    }
}
