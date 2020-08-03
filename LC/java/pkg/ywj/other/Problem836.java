package pkg.ywj.other;

/**
 * Description link: https://leetcode-cn.com/problems/rectangle-overlap/
 * Tag:
 * 2020/7/27_21:56  BranY
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 */
public class Problem836 {
    public static void main(String[] args) {

    }

    /**
     * 矩形 rec1 在矩形 rec2 的左侧: rec1[2] <= rec2[0]
     *
     * 矩形 rec1 在矩形 rec2 的右侧: rec1[0] >= rec2[2]
     *
     * 矩形 rec1 在矩形 rec2 的上方: rec1[1] >= rec2[3]
     *
     * 矩形 rec1 在矩形 rec2 的下方: rec1[3] <= rec2[1]
     *
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }
}
