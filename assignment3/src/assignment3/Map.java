package assignment3;

/* Behavior: A Map will provide this collection of operations:

put: 
  in: a string (the key) and a Value object (associated with the key)
  return: a Value object, or null
  effect: 
    if key is NOT in the structure
       add a new cell to the structure and put the key into it, the value into it
       and return null
    if key IS in the structure
       do not add a new cell
       instead, replace the Value object in the existing cell
                with the new Value object
                return the old Value object (the one being replaced)

get: 
  in: a string (the key) 
  return: a Value object, or null
  effect: 
    if key is NOT in the structure
       return null (this includes when the structure is empty, size 0)
    if key IS in the structure
       return the Value object from the cell containing the key

remove: 
  in: a string (the key of the pair to be removed)
  return: nothing in all cases (return void)
  effect: 
    if the key IS in the structure
      take the whole cell out of the structure... the (key,value) pair will not be in the
      structure after
    if the key is NOT in the structure
      make no changes to the state of the structure 
   
hasKey: 
  in: a string (the key)
  return: boolean
  effect: 
    if key IS in the structure (meaning there is a (key,value) pair for the key), 
      return true
    if key is NOT in the structure, return false
    in both cases, no change to the structure tate 

size:
  in: nothing
  return: int, the number of (key,value) pairs stored in the map structure
  effect: no change to state of tree nodes
*/

//ADT operations

public interface Map {
	public Value put ( String k, Value v ); // recursive
	public Value get (String k);            // recursive
	public void remove (String k);          // recursive
	public boolean hasKey(String k);        // recursive
	public int size();
}
