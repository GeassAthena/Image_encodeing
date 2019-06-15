import java.io.*;

/**
 * Created by 17637 on 2019/6/16.
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("data.txt");
        InputStream inputStream;
        Reader reader;
        BufferedReader bufferedReader;

        double[] p = new double[10]; //double数组保存各信符概率
        int len = 0;

        try {
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                p[len] = Double.parseDouble(line);
                len++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sort_desc(p, len);
        Shannon shannon = new Shannon(p, len);
        double Lc = shannon.getAvgCodeLen();
        double Hx = getHx(p, len);
        System.out.println("香农编码的效率为：" + Hx / Lc);
    }

    /**
     * 使用冒泡排序，从大到小排序排序
     *
     * @param p
     * @param len
     */
    private static void sort_desc(double[] p, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (p[i] < p[j]) {
                    double temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }
    }

    /**
     * 使用冒泡排序，从大到小排序排序
     *
     * @param p
     * @param len
     */
    private static void sort_add(double[] p, int len) {   //使用冒泡排序，从小到大排序排序
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (p[i] > p[j]) {
                    double temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }
    }

    /**
     * 计算信息熵
     *
     * @return
     */
    private static double getHx(double[] p, int len) {
        double Hx = 0;
        for (int i = 0; i < len; i++) {
            Hx -= p[i] * (Math.log(p[i]) / Math.log(2.0));
        }
        return Hx;
    }
}
