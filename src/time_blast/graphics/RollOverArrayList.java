package time_blast.graphics;
import java.util.ArrayList;
import java.util.Collection;


// this simple class overrides the default .get method for hashmaps. This allows an integer > than the length of the array to be inputted without crashing the code.
// it will roll over back to the beginning of the array.
public class RollOverArrayList<E> extends ArrayList<E> {
    public RollOverArrayList(Collection<? extends E>c){super(c);}
    public RollOverArrayList(){super();}
    @Override
    public E get(int index){return super.get(index%this.size());}
}
