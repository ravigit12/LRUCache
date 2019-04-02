package com.rpstech;
import java.util.HashMap;

public class LRUCache {
    public class EnteryNode{
        public EnteryNode left, right;
        public Integer key,value;
    }

    HashMap<Integer,EnteryNode> lruCache;
    EnteryNode start,end;
    Integer LRU_SIZE = 4;
    public LRUCache(){
        lruCache = new HashMap<>();
    }

    public int get(Integer key){
        if(lruCache.containsKey(key)){
            EnteryNode enteryNode = lruCache.get(key);
            removeNode(enteryNode);
            addAtStart(enteryNode);
            return enteryNode.value;
        }
        return 1;
    }
    public  void put(Integer key , Integer value){
            if(lruCache.containsKey(key)){
                EnteryNode enteryNode = lruCache.get(key);
                enteryNode.value = value;
                removeNode(enteryNode);
                addAtStart(enteryNode);
            } else {
                EnteryNode newNode = new EnteryNode();
                newNode.left = null;
                newNode.right = null;
                newNode.key = key;
                newNode.value = value;

                if(lruCache.size()>LRU_SIZE){
                   lruCache.remove(end.key);
                    removeNode(end);
                   addAtStart(newNode);

                } else {
                    addAtStart(newNode);
                }
                lruCache.put(key,newNode);
            }

    }

    private void addAtStart(EnteryNode node){
        node.right = start;
        node.left = null;
        if(start != null){
            start.left = node;
        }
        start = node;
        if(end == null){
            end = start;
        }

    }

    private void removeNode(EnteryNode node){
            if(node.left != null){
                node.left.right = node.right;
            } else {
                start = node.right;
            }

            if(node.right != null){
                node.right.left = node.left;
            } else {
                end = node.left;
            }
    }

    public static void main(String[] args) {


        LRUCache lrucache = new LRUCache();
        lrucache.put(11, 2);
        lrucache.put(1, 5);
        lrucache.put(5, 10);
        lrucache.put(11, 6);
        lrucache.put(2, 1);
        lrucache.put(18, 11);
        lrucache.put(12, 26);

        System.out.println(lrucache.get(1));
        System.out.println(lrucache.get(11));
        System.out.println(lrucache.get(5));
    }
}
