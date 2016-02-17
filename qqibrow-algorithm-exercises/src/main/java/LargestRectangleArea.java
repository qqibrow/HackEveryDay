/**
 * Created by lniu on 2/7/16.
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
        System.out.println(new LargestRectangleArea().largestRectangleArea(new int[]{3,6,5,7,4,8,1,0}));
    }
    public int largestRectangleArea(int[] heights) {
        int[] lastToRight = new int[heights.length];
        int[] lastToLeft = new int[heights.length];
        int[] minToRight = new int[heights.length];
        int[] mintToLeft = new int[heights.length];

        for(int i = heights.length - 1; i >=0; --i) {
            minToRight[i] = (i == heights.length - 1) ? heights[i] : Math.min(heights[i], minToRight[i + 1]);
            lastToRight[i] = (i == heights.length - 1) ? 1 : (heights[i] > heights[i + 1] ? 1 : lastToRight[i + 1] + 1);
        }
        for(int i = 0; i < heights.length; ++i) {
            mintToLeft[i] = (i == 0) ? heights[i] : Math.min(heights[i], mintToLeft[i - 1]);
            lastToLeft[i] = (i == 0) ? 1 : (heights[i] <= heights[i - 1] ? lastToLeft[i - 1] + 1 : 1);
        }
        int max = 0;
        for(int i = 0; i < heights.length; ++i) {
            int m1 = Math.max(minToRight[i] * (heights.length - i), mintToLeft[i] * (i + 1));
            int m2 = heights[i] * (lastToLeft[i] + lastToRight[i] - 1);
            int maxSoFar = Math.max(m1, m2);
            max = Math.max(max, maxSoFar);
        }
        return max;
    }

}
