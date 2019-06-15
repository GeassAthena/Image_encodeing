/**
 * Created by 17637 on 2019/6/16.
 */
public class Shannon {
    private double[] p;
    private int len;
    private int[] li;
    private double[] acl;
    private int[][] code;

    public Shannon(double[] p, int len) {
        this.p = new double[len];
        System.arraycopy(p, 0, this.p, 0, len);
        this.len = len;
        setCodeLen();
        setAclPR();
        setBinaryCode();
        System.out.println("现在是香农编码：");
        for (int i = 0; i < len; i++) {
            System.out.print("信符" + i + "  概率：" + p[i] + " ，码长：" + li[i] + " ，码字：");
            for (int j = 0; j < code[i].length; j++) {
                System.out.print(code[i][j]);
            }
            System.out.println();
        }
        System.out.println("平均码长为：" + getAvgCodeLen());
    }

    /**
     * 计算每个信源符号的码长
     *
     * @return
     */
    private void setCodeLen() {
        li = new int[len];
        double temp = 0;
        for (int i = 0; i < len; i++) {
            temp = -(Math.log(p[i]) / Math.log(2.0));
            li[i] = (int) Math.ceil(temp);
        }
    }

    /**
     * 计算信源符号累加概率
     *
     * @return
     */
    private void setAclPR() {
        acl = new double[len];
        double temp = 0;
        for (int i = 0; i < len; i++) {
            acl[i] = temp;
            temp += p[i];
        }
    }

    /**
     * 计算信符的二进制码字
     */
    private void setBinaryCode() {
        code = new int[len][];
        for (int i = 0; i < len; i++) {
            code[i] = new int[li[i]];
        }

        double a[] = new double[len + 1];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < li[i]; j++) {
                if (j == 0) {
                    a[0] = acl[i] * 2.0;
                } else {
                    a[j] = a[j - 1] * 2.0;
                }
                if (a[j] > 1) {
                    code[i][j] = 1;
                    a[j]--;
                } else {
                    code[i][j] = 0;
                }
            }
        }
    }

    /**
     * 计算平均码长
     *
     * @return
     */
    public double getAvgCodeLen() {
        double avg_len = 0;
        for (int i = 0; i < len; i++) {
            avg_len += p[i] * li[i];
        }
        return avg_len;
    }
}
