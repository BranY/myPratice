/**
 * Created by wuyan on 2015/12/18.
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */

import com.google.protobuf.Internal;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
public class Solution57 {

    public List<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<>();

        for(Interval each: intervals){
            if(each.end < newInterval.start){
                res.add(each);
            }else if(each.start > newInterval.end){
                res.add(newInterval);
                newInterval = each;
            }else if(each.end >= newInterval.start || each.start <= newInterval.end){
                int ns = Math.min(each.start, newInterval.start);
                int ne = Math.max(newInterval.end, each.end);
                newInterval = new Interval(ns, ne);
            }
        }

        res.add(newInterval);

        return res;
    }

    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String[] str1 = cin.nextLine().split(" ");
            String str2 = cin.nextLine();
            ArrayList<Interval> list = new ArrayList<>();
            for (int i = 0; i < str1.length; i++) {
                if (str1[i].contains(",")) {
                    int a = str1[i].indexOf(",");
                    int start = Integer.parseInt(str1[i].substring(1, a));
                    int end = Integer.parseInt(str1[i].substring(a + 1, str1[i].length() - 1));
                    Interval tmp = new Interval(start, end);
                    list.add(tmp);
                }
            }
            int b = str2.indexOf(",");
            int s = Integer.parseInt(str2.substring(1, b));
            int e = Integer.parseInt(str2.substring(b + 1, str2.length() - 1));
            Interval in = new Interval(s, e);
            Solution57 II = new Solution57();
            List<Interval> result = II.insert(list, in);
            for (Interval elem : result) {
                System.out.print("[" + elem.start + "," + elem.end + "] ");
            }
            System.out.println();
        }
        cin.close();
    }
}
