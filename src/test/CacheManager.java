package test;


import java.util.HashSet;

public class CacheManager {
    private int size;
    private CacheReplacementPolicy ceche;
    private HashSet<String> hash;

    public CacheManager(int size, CacheReplacementPolicy policy) {
        this.size = size;
        this.ceche = policy;
        this.hash = new HashSet<>(size);
    }

    public boolean query(String word) {
        if (hash.contains(word)) {
            ceche.add(word);
            return true;
        }
        return false;
    }

    public void add(String word) {
        if (hash.size() == size) {
            String removed = ceche.remove();
            hash.remove(removed);
        }
        hash.add(word);
        ceche.add(word);
    }


}
