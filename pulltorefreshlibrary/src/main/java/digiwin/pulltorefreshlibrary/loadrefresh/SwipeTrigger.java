package digiwin.pulltorefreshlibrary.loadrefresh;

/**
 * Created by qGod on 2017/5/22
 * Thank you for watching my code
 */
public interface SwipeTrigger {
    void onPrepare();

    void onMove(int y, boolean isComplete, boolean automatic);

    void onRelease();

    void onComplete();

    void onReset();
}
