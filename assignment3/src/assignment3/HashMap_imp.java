package assignment3;

public class HashMap_imp implements HashMap {
	  HMCell[] tab;
	  int nelts;
	  int tableSize;
	  
	  //-------------------------------------------------------------

	HashMap_imp (int num) { 
	    this.tab = new HMCell[num];
	    // for (int i=0; i<num; i++) { tab[i] = null; }
	    // we can rely on the Java compiler to fill the table array with nulls
	    // another way would be Array.fill()
	    this.nelts = 0; 
	    this.tableSize = num;
	}

	  //-------------------------------------------------------------
	  
	public int hash (String key, int tabSize) {
		int hval = 7;
		for (int i=0; i<key.length(); i++) {
			hval = (hval*31) + key.charAt(i);
		}
		hval = hval % tabSize;
		if (hval<0) { hval += tabSize; }
		return hval;
	}
	  
	//-------------------------------------------------------------

	// dont change 
	@Override
	public HMCell[] getTable() { return this.tab; }

	@Override
	public Value put(String k, Value v) {
		if (!hasKey(k)) {
			nelts = nelts + 1;
		}
		
		if (lambda() >= 1) {
			extend();
		}
		
		int index = hash(k, tableSize);
		HMCell currCell = tab[index];
		
		if (hasKey(k)) {
			Value oldVal = null;
			
			while (currCell != null) {
				if (currCell.getKey().equals(k)) {
					oldVal = currCell.getValue();
					currCell.setValue(v);
				}
				currCell = currCell.getNext();
			}
			
			return oldVal;
		} else {
			HMCell toAdd = new HMCell_imp(k, v);
			
			put(toAdd, index, this.tab);
			
			return null;
		}
		
		
	}
	
	private void put(HMCell toAdd, int index, HMCell[] array) {
		HMCell currCell = array[index];
		
		if (currCell == null) {
			array[index] = toAdd;
			toAdd.setNext(null);
		} else {
			toAdd.setNext(currCell);
			array[index] = toAdd;
		}
	}

	@Override
	public Value get(String k) {
		if (!hasKey(k)) {
			return null;
		} 
		int index = hash(k, tableSize);
			
		HMCell currCell = tab[index];
		while (currCell != null) {
			if (currCell.getKey().equals(k)) {
				return currCell.getValue();
			}
			currCell = currCell.getNext();
		}
		
		return null;
	}

	@Override
	public void remove(String k) {
		if (hasKey(k)) {
			int index = hash(k, tableSize);
			
			nelts--;
			
			HMCell parent = null;
			HMCell currCell = tab[index];
			while (currCell != null) {
				if (currCell.getKey().equals(k)) {
					if (parent == null) {
						if (currCell.getNext() == null) {
							tab[index] = null;
						} else {
							tab[index] = currCell.getNext();
						}
						
					} else {
						parent.setNext(currCell.getNext());
					}
				}
				parent = currCell;
				currCell = currCell.getNext();
			}
		}
	}

	@Override
	public boolean hasKey(String k) {
		int index = hash(k, tableSize);
		
		HMCell currCell = tab[index];
		while (currCell != null) {
			if (currCell.getKey().equals(k)) {
				return true;
			}
			currCell = currCell.getNext();
		}
		
		return false;
	}

	@Override
	public int size() {
		return nelts;
	}

	@Override
	public String maxKey() {
		if (tableSize == 0 || nelts == 0) {
			return null;
		}
		String currMax = "";
		HMCell currCell;
		
		for (int i = 0; i < tableSize; i++) {
			if (tab[i] != null) {
				currCell = tab[i];
				
				while (currCell != null) {
					if (currCell.getKey().compareTo(currMax) > 0) {
						currMax = currCell.getKey();
					}
					currCell = currCell.getNext();
				}
			}
		}
		
		return currMax;
	}

	@Override
	public String minKey() {
		if (tableSize == 0 || nelts == 0) {
			return null;
		}
		
		String currMin = null;
		HMCell currCell;
		
		for (int i = 0; i < tableSize; i++) {
			if (tab[i] != null) {
				currCell = tab[i];
				
				if (currMin == null) {
					currMin = tab[i].getKey();
				}
				
				while (currCell != null) {
					if (currCell.getKey().compareTo(currMin) < 0) {
						currMin = currCell.getKey();
					}
					currCell = currCell.getNext();
				}
			}
		}
		
		return currMin;
	}

	@Override
	public String[] getKeys() {
		String[] keys = new String[nelts];
		int currIndex = 0;
		HMCell currCell;
		
		for (int i = 0; i < tableSize; i++) {
			if (tab[i] != null) {
				currCell = tab[i];
				
				while (currCell != null) {
					keys[currIndex] = currCell.getKey();
					currCell = currCell.getNext();
					currIndex++;
				}
			}
		}
		
		return keys;
	}

	@Override
	public double lambda() {
		if (tableSize > 0) {
			double lambda = (double) nelts / tableSize;
			return lambda;
		} else {
			return 0;
		}
		
	}

	@Override
	public double extend() {
		int oldTableSize = tableSize;
		this.tableSize = tableSize * 2;
		HMCell[] newTable = new HMCell[tableSize];
		
		int newIndex;
		HMCell currCell;
		HMCell cellToPlace;
		for (int i = 0; i < oldTableSize; i++) {
			if (tab[i] != null) {
				currCell = tab[i];
				
				while (currCell != null) {
					newIndex = hash(currCell.getKey(), tableSize);
					cellToPlace = currCell;
					currCell = currCell.getNext();
					
					put(cellToPlace, newIndex, newTable);
				}
			}
		}
		this.tab = newTable;
		return lambda();
	}
}

