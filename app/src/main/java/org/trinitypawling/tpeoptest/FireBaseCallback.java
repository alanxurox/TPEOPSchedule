package org.trinitypawling.tpeoptest;

import java.util.List;

/**
 * This interface is designed for loading periods only after firebase data is retrieved, and after that
 * MyDrawable could use the periods array list to draw the schedule, avoiding a nullpointer exception.
 @method onCallBack is designed to hold the draw functions so that only after onCallBack is called would         
 * they run, not before the periods are loaded. 
 */
public interface FireBaseCallback {
    void onCallBack(List<?> list);
}
