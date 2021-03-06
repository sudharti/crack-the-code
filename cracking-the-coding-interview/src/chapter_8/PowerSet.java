package chapter_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PowerSet {
    public List<List<String>> subsets(String[] set) {
        List<List<String>> subsets = new ArrayList<List<String>>();
        subsets(set, 0, new Stack<String>(), subsets);
        return subsets;
    }

    @SuppressWarnings("unchecked")
    private void subsets(String[] set, int index, Stack<String> result, List<List<String>> subsets) {
        subsets.add((List<String>) result.clone());
        for (int i = index; i < set.length; i++) {
            result.add(set[i]);
            subsets(set, i + 1, result, subsets);
            result.pop();
        }
    }

    @SuppressWarnings("unchecked")
    public List<List<String>> subsetsIterative(String[] set) {
        List<List<String>> subsets = new ArrayList<List<String>>();
        Queue<Wrapper> queue = new LinkedList<Wrapper>();
        queue.add(new Wrapper(new Stack<String>(), 0));

        while (!queue.isEmpty()) {
            Wrapper wrapper = queue.poll();
            subsets.add(wrapper.stack);

            for (int i = wrapper.startIndex; i < set.length; i++) {
                wrapper.stack.add(set[i]);
                queue.add(new Wrapper((Stack<String>) wrapper.stack.clone(), i + 1));
                wrapper.stack.pop();
            }
        }

        return subsets;
    }

    public List<List<String>> subsetsUsingBits(String[] set) {
        List<List<String>> subsets = new ArrayList<List<String>>();
        int num = (1 << (set.length));
        for (int i = 0; i < num; i++) {
            subsets.add(translateBitToSet(i, set));
        }
        return subsets;
    }

    private List<String> translateBitToSet(int n, String[] set) {
        int i = 0;
        List<String> subset = new ArrayList<String>();
        while (n > 0) {
            if ((n & 1) == 1) {
                subset.add(set[i]);
            }
            i++;
            n >>= 1;
        }
        return subset;
    }

    class Wrapper {
        Stack<String> stack = new Stack<String>();
        int startIndex;

        public Wrapper(Stack<String> stack, int startIndex) {
            this.stack = stack;
            this.startIndex = startIndex;
        }
    }
}
