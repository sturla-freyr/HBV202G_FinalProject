package is.hi.tms.ui;

/**
 * Subject interface used in the Observer design pattern.
 * Allows observers to subscribe/unsubscribe and be notified of changes.
 */
public interface Subject {
    /**
     * Adds an observer to the subject's list.
     *
     * @param observer the observer to add
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the subject's list.
     *
     * @param observer the observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers with a message.
     *
     * @param message the message to send
     */
    void notifyObservers(String message);
}
