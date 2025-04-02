package is.hi.tms.ui;

/**
 * Observer interface used in the Observer design pattern.
 * Classes implementing this interface receive updates from Subjects they are subscribed to.
 */
public interface Observer {
    /**
     * Called by the Subject to notify this Observer of a change.
     *
     * @param message the update message
     */
    void update(String message);
}
