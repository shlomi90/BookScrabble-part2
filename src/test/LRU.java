
package test;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class LRU implements CacheReplacementPolicy {

    private LinkedHashSet<String> Words;
    private int size;
    public LRU()
    {
        this.size=size;
        this.Words = new LinkedHashSet<>(size);

    }

public void add(String word) {
    if(Words.contains(word)){
        Words.remove(word);
        Words.add(word);
    }
    else{
        Words.add(word);
    }
}

    public String remove() {
      String[] word = Words.toArray(new String[Words.size()]);
        Words.remove(word[0]);
        return word[0];

}

}



