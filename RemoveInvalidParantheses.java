//The idea here is to check all possible combinations by removing 1 extra parantheses at each level.
//We traverse this graph using BFS. So we process each level at a time and we also maintain a flag to see if there is a valid parantheses found at a level. If we find a valid one and then processing others at same level, we do not make babies for further ones because we know found the result with minimum removals at this level itself, thus optimizing the solution.
//We take size variable here because we need to process level by level
//We also maintain a set to avoid duplicates to add in the result.
//Time Complexity: O(n^n) where n is the length of the string
//Space Complexity: O(n^n)
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s); set.add(s);
        boolean flag = false;
        while(!q.isEmpty() && !flag){
            int size = q.size();
            for(int i = 0; i< size; i++){
                String curr = q.poll();
                if(isValid(curr)){
                    result.add(curr);
                    flag = true;
                }
                if(!flag){
                    //make the babies only if the string is invalid
                    for(int j = 0; j<curr.length(); j++){
                        char ch = curr.charAt(j);
                        if(Character.isAlphabetic(ch)) continue;
                        String baby = curr.substring(0, j) + curr.substring(j+1); //removing ith character
                        if(!set.contains(baby)){
                            q.add(baby);
                            set.add(baby);
                        }
                    }
                }                
            }
        }
        return result;
    }

    private boolean isValid(String s){
        int count = 0;
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isAlphabetic(ch)) continue;
            if(ch == '('){
                count++;
            } else {
                if(count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
}