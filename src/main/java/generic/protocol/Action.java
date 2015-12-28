package generic.protocol;

import generic.Element;

/**
 * Created by jonathan on 28-12-15.
 */
public interface Action<T extends Element> {

    boolean onHandle(T element);


}
