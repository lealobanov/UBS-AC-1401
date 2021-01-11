package ubsassessmentcentre;

//Introduce a new data structure to allow for mapping of (Instrument, Market) keys to VWAP values
    //https://stackoverflow.com/questions/36407743/enum-based-table-matrix-in-java
    
public class PriorityTuple{    
  final Instrument i;
  final Market m;

  public PriorityTuple(Intrument i, Market m) {
    this.i = i;
    this.m = m;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof PriorityTuple)) {
      return false;
    }
    PriorityTuple prioritykey = (PriorityTuple) o;
    return this.i.equals(prioritykey.i) && this.m.equals(prioritykey.m);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 23 * hash + Objects.hashCode(this.i);
    hash = 23 * hash + Objects.hashCode(this.m);
    return hash;
  }
}


