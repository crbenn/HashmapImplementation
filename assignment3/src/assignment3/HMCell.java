package assignment3;

public interface HMCell {
	  public void setKey(String newKey);
	  public String getKey();
	  public void setValue(Value newValue);
	  public Value getValue();
	  public void setNext(HMCell nextCell);
	  public HMCell getNext();
}
