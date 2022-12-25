package test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LFU implements CacheReplacementPolicy {

       public LinkedHashMap <String,Integer> cache;
       public int minimum;
       String FirstWord;

        public LFU()
        {
            this.minimum=0;
            this.cache = new LinkedHashMap<>();
        }

        public void add(String word) {
           if(!cache.containsKey(word)){
               if (cache.isEmpty())
                   FirstWord=word;
               cache.put(word,1);
           }
           else{
               cache.put(word,cache.get(word)+1);
               minimum=1;
           }
        }

        public String remove() {
            for (String key : cache.keySet()) {
                if (cache.get(key) == minimum) {
                    cache.remove(key);
                    return key;
                }
            }
            return FirstWord;
        }


    }