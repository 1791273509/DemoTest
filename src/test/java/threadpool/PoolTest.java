package threadpool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wenbaoxie
 * @Date 2020/10/27
 */
public class PoolTest {

    static ThreadLocal<String> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        int[] tem = new int[3];
        tem[0] = 1;
        tem[1] = 2;
        tem[2] = 3;
        new Solution().getPermutation(3, 3);
    }

    static class Solution {

        boolean[] vis;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> perm = new ArrayList<Integer>();
            vis = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(nums, ans, 0, perm);
            return ans;
        }

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            rightViewDfs(root,res,0);
            return res;
        }

        public void rightViewDfs(TreeNode root, List<Integer> res, int dep) {
            if (root == null) {
                return;
            }
            if (dep == res.size()) {
                res.add(root.val);
            }
            rightViewDfs(root.right,res,dep + 1);
            rightViewDfs(root.left,res,dep + 1);
        }

        public String getPermutation(int n, int k) {
            StringBuilder res = new StringBuilder();
            int per[] = new int[n];
            per[0] = 1;
            List<Integer> list = new LinkedList<>();
            for (int i = 1; i < n; i++) {
                list.add(i);
                per[i] = per[i - 1] * i;
            }
            per[0] = 0;
            list.add(n);
            System.out.println(Arrays.toString(per));
            k--;
            for (int i = n - 1; i > 0; i--) {
                int index = k / per[i];
                res.append(list.get(index));
                list.remove(index);
                k -= index * per[i];
            }
            res.append(list.get(0));
            return res.toString();
        }

        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                if (i > n) {
                    return res;
                }
                res.add(i);
                dfs(res, n, i);
            }
            return res;
        }

        public void dfs(List<Integer> res, int n, int cur) {
            if (cur > n) {
                return;
            }
            res.add(cur);
            for (int i = 0; i < 10; i++) {
                dfs(res, n, cur * 10 + i);
            }
        }

        public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
            if (idx == nums.length) {
                ans.add(new ArrayList<Integer>(perm));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                    continue;
                }
                perm.add(nums[i]);
                vis[i] = true;
                backtrack(nums, ans, idx + 1, perm);
                vis[i] = false;
                perm.remove(idx);
            }
        }
    }

    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            executorService.execute(() ->
                    System.out.println(Thread.currentThread().getName()));
        }
        executorService.shutdown();
    }

    public void ThreadPoolTets() {

        /*new ThreadPoolExecutor(2,10,3, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),
                new Th);*/
    }

    //    /**
    // Definition for a binary tree node.
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution1 {

        int res = 0;

        public int sumNumbers(TreeNode root) {

            if (root == null) {
                return 0;
            }
            add(root, 0);
            return res;
        }

        public void add(TreeNode root, int tem) {
            if (root.left == null && root.right == null) {
                tem += root.val;
                res += tem;
                return;
            }
            tem += root.val;
            if (root.left != null) {
                add(root.left, tem * 10);
            }
            if (root.right != null) {
                add(root.right, tem * 10);
            }
        }
    }
}
