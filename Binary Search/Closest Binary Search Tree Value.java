Problem: Closest Binary Search Tree Value



//Solution 1:
public int closestValue(TreeNode root, double target) {
    int ret = root.val;   
    while(root != null){
        if(Math.abs(target - root.val) < Math.abs(target - ret)){
            ret = root.val;
        }      
        root = root.val > target? root.left: root.right;
    }     
    return ret;
}


//Solution 2: Recursive Version

public int closestValue(TreeNode root, double target) {
    int a = root.val;
    TreeNode kid = target < a ? root.left : root.right;
    if (kid == null) return a;
    int b = closestValue(kid, target);	// find the closest value to target in Tree kid
    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
}