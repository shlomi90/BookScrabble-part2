package test;

import java.io.IOException;

public class Dictionary {
    private CacheManager cache;
    private CacheManager cache2;
private BloomFilter bloomFilter;
String[] filename;

public Dictionary(String...FileNames)
{
    this.cache = new CacheManager(400,new LRU());
    this.cache2= new CacheManager(100,new LFU());
    this.bloomFilter = new BloomFilter(256,"MD5","SHA1");
    filename=FileNames.clone();
    for (String file:filename) {
       bloomFilter.add(file);
    }
}

    public boolean query(String word) {
        if(cache.query(word))
        {
            return true;
        } else if (cache2.query(word)) {
            return false;
        } else if (bloomFilter.contains(word)) {
            cache.add(word);
        }
        cache2.add(word);
        return true;
    }

    public boolean challenge(String word) {
        try {
            if(IOSearcher.search(word, filename)){
                cache.add(word);
                return true;
            }
            cache2.add(word);
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
