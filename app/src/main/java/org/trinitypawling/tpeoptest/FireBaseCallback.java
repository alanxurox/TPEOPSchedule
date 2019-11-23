package org.trinitypawling.tpeoptest;

import java.util.List;

/**
 * This interface is designed for loading periods only after firebase data is retrieved, and after that
 * MyDrawable could use the periods array list to draw the schedule, avoiding a nullpointer exception.
 @method onCallBack
 */
public interface FireBaseCallback {
    void onCallBack(List<?> list);
}
