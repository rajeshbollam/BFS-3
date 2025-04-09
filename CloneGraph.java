//The approach here is to use BFS to traverse the given graph and to create a new one.
//At each node, we create a deep copy of the node if it's not already created, and traverse through it's neighbors, create deep copies for them if not already created, then add those new neighbors to the new nodes
//We also use a hashmap to store the nodes and their deep copies
//Time Complexity: O(V+E) where V is the vertices and E is the edges of the graph
//Space Complexity: O(V+E)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        q.add(node);
        while(!q.isEmpty()){
            Node curr = q.poll();
            List<Node> neighbors = curr.neighbors;
            for(Node ne: neighbors){
                if(!map.containsKey(ne)){
                    Node copyNode = new Node(ne.val);
                    map.put(ne,copyNode);
                    q.add(ne);
                }
                //add 2' as neighbors of 1'
                map.get(curr).neighbors.add(map.get(ne));
            }
        }
        return newNode;
    }
}