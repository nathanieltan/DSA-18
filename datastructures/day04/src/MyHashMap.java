import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per map before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per map before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        // TODO
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.
        int hashCode;
        if(key == null){
            return buckets[0];
        }
        else{
            hashCode = Math.abs(key.hashCode());
        }
        return buckets[hashCode%buckets.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        LinkedList<Entry> bucket = chooseBucket(key);
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).key.equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        LinkedList<Entry> bucket;
        for(int i = 0; i < buckets.length; i++){
            bucket = buckets[i];
            if(bucket.size() != 0) {
                for(int j = 0; j < bucket.size(); j++) {
                    System.out.println(bucket.get(j).value);
                    if (bucket.get(j).value == null) {
                        if(value == null) {
                            return true;
                        }
                    }
                    else {
                        if (bucket.get(j).value.equals(value)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        LinkedList<Entry> bucket;

        bucket =  chooseBucket(key);

        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).key.equals(key)){
                return bucket.get(i).value;
            }
        }
        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        // TODO: Complete this method
        // hint: use chooseBucket() to determine which bucket to place the pair in
        // hint: use rehash() to appropriately grow the hashmap if needed
        V toReturn = null;
        LinkedList<Entry> bucket =  chooseBucket(key);
        Entry newEntry = new Entry(key,value);

        if(containsKey(key)){
            toReturn = remove(key);
        }
//        if (newEntry.value == null){
//            System.out.println(newEntry.key);
//        }
        //System.out.println(newEntry.value);
        bucket.add(newEntry);
        size++;

        // Rehashes if necessary
        if(((float)size)/buckets.length > ALPHA){
            rehash(2.0);
        }
        return toReturn;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        // TODO
        // hint: use chooseBucket() to determine which bucket the key would be
        // hint: use rehash() to appropriately grow the hashmap if needed

        LinkedList<Entry> bucket =  chooseBucket(key);
        V toReturn = null;
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).key.equals(key)){
                toReturn = bucket.remove(i).value;
                size--;
                break;
            }
        }

        if(((float)size)/buckets.length < BETA && buckets.length > MIN_BUCKETS){
            rehash(SHRINK_FACTOR);
        }

        return toReturn;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        // TODO
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place
        int bucketAmount = (int)((double)buckets.length * growthFactor);
        LinkedList<Entry>[] tempList = buckets;

        initBuckets(bucketAmount);
        size = 0;
        LinkedList<Entry> bucket;
        for(int i = 0; i < tempList.length; i++){
            bucket = tempList[i];
            for(int j = 0; j < bucket.size(); j++){
                put(bucket.get(j).getKey(),bucket.get(j).getValue());
            }
        }

    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}