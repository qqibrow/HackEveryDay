/**
 * Created by lniu on 5/25/16.
 */
public class QuickSort {
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public void partition(int[] array, int lo, int high) {
        int pivot = array[high];
        int lessThanPivotIndex = lo;
        for(int i = lo; i < high; ++i) {
            if (array[i] <= pivot) {
                 swap(array, lessThanPivotIndex++, i);
            }
        }
        swap(array, lessThanPivotIndex, high);
    }

    public static  void main(String[] args) {
        int[] s = new int[]{4, 5, 2, 1, 3};
        new QuickSort().partition(s, 0, s.length-1);
        for(int a : s) {
            System.out.println(a);
        }
    }

}
