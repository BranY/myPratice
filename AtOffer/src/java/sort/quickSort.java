package sort;

public class quickSort {

    public static void quickSort(int[] n) {
        if (n.length == 0)
            return;

        quickSort(n, 0, n.length - 1);
    }

    public static void quickSort(int[] n, int l, int h) {
        if (n.length == 0)
            return;

        if (l < h) {
            int pivot = partion(n, l, h);
            quickSort(n, l, pivot - 1);
            quickSort(n, pivot + 1, h);
        }
    }

    private static int partion(int[] n, int start, int end) {

        int tmp = n[start];
        while (start < end) {
            while (n[end] >= tmp && start < end)
                end--;
            if (start < end) {
                n[start++] = n[end];
            }
            while (n[start] < tmp && start < end)
                start++;
            if (start < end) {
                n[end--] = n[start];
            }
        }
        n[start] = tmp;
        return start;
    }

    public static void main(String[] args) {
        int[] arr = {37, 40, 38, 42, 461, 5, 7, 9, 12};


        quickSort(arr);
        for(int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + ", ");
    }
}
