package assignment3;

/* Behavior: A HashMap will provide Map operations, and also these:

minKey: 
  in: none
  return: string, the key from the hash table that is smallest
  effect: no hash table state change
    if hash table size if 0, return null

maxKey: 
  in: none
  return: string, the key from the hash table that is largest
  effect: no hash table state change
    if hash table size if 0, return null

getKeys 
  in: nothing
  return: an array of strings, containing just the keys from the hash table cells
  effect: the array of strings is produced in unspecified, which means it is ok 
    to just go through the table subscript 0 up and pull keys from the cells

hash
  in: the key (a String), and table size (an int)
  return: integer hash value for the given key and table size
  effect: no change to hash table state

lambda
  in: nothing
  returns: the lambda value for the table (a double)
  effect: no change in the state of the hash table

extend
  in: nothing
  returns: a new lambda value, after the table array has been extended
  effect: the array that is the hash table is doubled in size, and the elements 
    in the old table are rehashed (using the new array size) and stored 
    in the new table array
    number of elements stored is unchanged, array is doubled in size 
    (and so lambda gets cut in half)

// leave this in... for grader
// also specific to tree structure
public HMCell[] getTable();


*/

//ADT operations

public interface HashMap extends Map {
	  public String maxKey(); 
	  public String minKey(); 
	  public String[] getKeys();  // in random order
	  
	  public int hash(String key, int tabSize);
	  
	  public double lambda();  // compute lamda load factor
	  public double extend();    // double table size, rehash, return new lambda
	  
	  // leave this in... for grader
	  // also specific to hash table structure
	  public HMCell[] getTable();
}
