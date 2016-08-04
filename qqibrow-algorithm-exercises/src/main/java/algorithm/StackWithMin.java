package algorithm;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by lniu on 3/28/16.
 */
public class StackWithMin extends Stack<Integer> {
    private class IntegerWithCount {
        public  Integer value;
        public int count;
        public IntegerWithCount(Integer v, int c) {
            value = v;
            count = c;
        }
    }
    private Stack<IntegerWithCount> mins;

    public StackWithMin() {
        mins = new Stack<>();
    }

    @Override
    public Integer push(Integer item) {
        if(mins.empty()) {
            mins.push(new IntegerWithCount(item, 1));
        } else if(item < mins.peek().value) {
            mins.push(new IntegerWithCount(item, 1));
        } else if(item == mins.peek().value) {
            mins.peek().count++;
        }
        return super.push(item);
    }

    @Override
    public Integer pop() {
        Integer peek = super.pop();
        if(peek.equals(mins.peek().value)) {
            mins.peek().count--;
            if(mins.peek().count == 0) {
                mins.pop();
            }
        }
        return peek;
    }

    public Integer getMin() {
        if(!mins.empty()) {
            return mins.peek().value;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        StackWithMin stackWithMin = new StackWithMin();
        stackWithMin.push(2);
        Assert.assertEquals(new Integer(2), stackWithMin.getMin());
        stackWithMin.push(5);
        Assert.assertEquals(new Integer(2), stackWithMin.getMin());
        stackWithMin.push(2);
        stackWithMin.push(2);
        Assert.assertEquals(new Integer(2), stackWithMin.getMin());
        stackWithMin.pop();
        stackWithMin.pop();
        stackWithMin.pop();
        stackWithMin.push(1);
        Assert.assertEquals(new Integer(1), stackWithMin.getMin());
    }
}
