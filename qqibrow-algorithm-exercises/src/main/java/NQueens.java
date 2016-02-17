import java.util.ArrayList;
import java.util.List;

/**
 * Created by lniu on 2/3/16.
 */
public class NQueens {
        List<List<String>> res = null;
        public static void main(String[] args) {
            List<List<String>> results = new NQueens().solveNQueens(4);
            System.out.println(results);
        }
        public List<List<String>> solveNQueens(int n) {
            res = new ArrayList<List<String>>();
            int[] temp = new int[n];
            boolean[] visited = new boolean[n];
            for(int i = 0; i < visited.length; ++i) {
                visited[i] = false;
            }
            helper(temp, 0, visited);
            return res;
        }

        public List<String> getOneResult(int[] temp) {
            List<String> ret = new ArrayList<String>();
            for(int i = 0; i < temp.length; ++i) {
                StringBuffer line = new StringBuffer();

                for(int j = 0; j < temp.length; ++j) {
                    line.append(".");
                }

                line.setCharAt(temp[i], 'Q');
                ret.add(line.toString());
            }
            return ret;
        }
        public boolean canVisit(int valueToChoose, int index, int[] temp, boolean[] visited) {
            if(visited[valueToChoose] == true)
                return false;

            for(int prev = 0; prev < index; ++prev) {
                int choosedAtPrev = temp[prev];
                if(index - prev == Math.abs(choosedAtPrev - valueToChoose)) {
                    return false;
                }
            }
            return true;
        }

        public void helper(int[] temp, int index, boolean[] visited) {
            if(index >= temp.length) {
                res.add(getOneResult(temp));
            } else {
                // pick a value
               for(int i = 0; i < visited.length; ++i) {
                   if(canVisit(i, index, temp, visited)) {
                       visited[i] = true;
                       temp[index] = i;
                       helper(temp, index+1, visited);
                       visited[i] = false;
                   }
               }
            }
        }
    }
