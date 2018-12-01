package offer;

/**
 * Created by yang on 2017/4/8.
 ************************************************************************************************
 * 替换空格
 *
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 ************************************************************************************************
 */
public class Problem05 {

    /**
     * 数组长度会增加，C语言需要考虑原数组能否容纳。
     * Step 1. 扫描数组中的空格数量
     * Step 2. 从后向前将每个字符复制到正确位置（替换空格）
     */

    /**
     * 直接使用StringBuffer的内置方法……
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if(str == null) {
            return null;
        }
        for (int i = 0; i < str.length(); ) {
            if (str.charAt(i) == ' ') {
                str.deleteCharAt(i);
                str.insert(i, "%20");
                i += 3;
            } else {
                i++;
            }
        }

        return str.toString();
    }

    /**
     * 用StringBuilder，依次向其中添加修改后的内容即可
     * @param str，待修改的字符串
     * @return
     */

    public String replaceSpace1(StringBuffer str) {
        if (str == null) return null;
        int blankNum = 0;
        int length = str.length();
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == ' ') {
                blankNum++;
            }
        }

        int newLength = length + 2 * blankNum;

        int index1 = length-1;
        int index2 = newLength - 1;

        char[] ch = new char[newLength];
        while (index1 >= 0 && index2 >= index1) {
            if (str.charAt(index1) == ' ') {
                ch[index2--] = '0';
                ch[index2--] = '2';
                ch[index2--] = '%';

            } else {
                ch[index2--] = str.charAt(index1);
            }
            --index1;
        }
        return new String(ch);
    }


    /**
     * 单元测试
     * 1. 包含空格的字符串（开头、结尾、中间；单个、连续多个）
     * 2. 没有空格的字符串
     * 3. 特殊输入（null、空字符串、只包含空格的字符串）
     * @param args
     */
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("t r s");
        System.out.println(new Problem05().replaceSpace(sb));

        sb = new StringBuffer("  ");
        System.out.println(new Problem05().replaceSpace(sb));

        sb = new StringBuffer("");
        System.out.println(new Problem05().replaceSpace(sb));

        sb = new StringBuffer("a b");
        System.out.println(new Problem05().replaceSpace1(sb));
    }
}
