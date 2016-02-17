import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lniu on 2/4/16.
 */
public class Combinations {
    static public void main(String[] args) {
        System.out.println(new Combinations().ddd(4,2));
    }
    public List<List<Integer>> ddd(int n, int k) {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        List<List<Integer>> results = new ArrayList<>();
        int[] temp = new int[k];
        helper(0, temp, visited, results);
        return results;
    }

    private void helper(int i, int[] temp, boolean[] visited, List<List<Integer>> results) {
        if(i == temp.length) {
            List<Integer> result = new ArrayList<>();
            for(int number : temp) {
                result.add(number + 1);
            }
            results.add(result);
            return;
        }

        int k = visited.length;
        for(int option = i == 0? 0 : temp[i-1]; option < k; ++option) {
            if(visited[option] == false) {
                visited[option] = true;
                temp[i] = option;
                helper(i + 1, temp, visited, results);
                visited[option] = false;
            }

        }
    }

}
