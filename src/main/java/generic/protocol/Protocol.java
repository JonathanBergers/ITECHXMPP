package generic.protocol;

import generic.Element;

/**
 * Created by jonathan on 29-10-15.
 */
public interface Protocol<T extends Element> {


    boolean conforms(T input);

}
