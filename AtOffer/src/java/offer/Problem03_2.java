package offer;

// 不能改变数组
public class Problem03_2 {


    public int duplicate(int numbers[],int length) {
        if(numbers == null || length <= 0) {
            return -1;
        }

        // 检查是否数字都在[0,n-1]之内
        for (int i = 0; i < length; i++) {
            if(numbers[i] < 0 || numbers[i] >= length) {
                return -1;
            }
        }
        int start = 1, end = length - 1;
        while (end >= start) {
            int middle = (start + end) / 2;

            int count = getRange(numbers, length, start, middle);

            if (end == start) {
                if (count > 1) return start;
                else
                    break;
            }

            if (count > (middle - start + 1))
                end = middle;
            else
                start = middle + 1;
        }

        return -1;
    }

    int getRange(int numbers[],int length, int l, int r) {
        if (numbers == null) return 0;

        int count = 0;
        for(int i = 0; i <length; i++) {
            if (numbers[i] >= l && numbers[i] <= r)
                ++count;
        }
        return count;
    }
    public static void main(String[] args) {
        Problem03_2 duplicationInArray = new Problem03_2();

        int[] array = new int[]{2,3,5,4,3,2,6, 7};

        System.out.println(duplicationInArray.duplicate(array, array.length));
    }
}
