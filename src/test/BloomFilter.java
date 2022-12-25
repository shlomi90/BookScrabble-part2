package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;


public class BloomFilter {
    BitSet bitSet;
    int size;
    String[] hashFunctions;

    public BloomFilter(int Length,String... hashFunctions) {
        this.size = Length;
        this.hashFunctions = hashFunctions;
        bitSet = new BitSet();
    }


   public void add(String word) {
        byte[] bytes;
        for (String string : hashFunctions) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bytes = md.digest(word.getBytes());
                BigInteger bigInt = new BigInteger(bytes);
                int hash =bigInt.intValue();
                hash= Math.abs(hash) % size;
                bitSet.set(hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean contains(String word) {
        byte[] bytes;
        for (String string : hashFunctions) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bytes = md.digest(word.getBytes());
                BigInteger bigInt = new BigInteger(bytes);
                int hash =bigInt.intValue();
                hash= Math.abs(hash) % size;
                if (bitSet.get(hash))
                    return true;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? "1" : "0");
        }
        return sb.toString();
    }




}
