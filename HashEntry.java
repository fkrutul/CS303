package lab9;

public class HashEntry {
      private long key;
      private String value;

      // Creates a new hash entry holding a key and it's associated value
      HashEntry(long key, String value) {
          this.key = key;
          this.value = value;
    }     
      
      // Gets the key of a hash entry
      public long getKey() {
    	      return key;
      }

      // Gets the data within a hash entry
      public String  getValue() {
            return value;
      }
      
      // Sets the data within a hash entry
      public void setValue(String val) {
            this.value = val;
      }
}
