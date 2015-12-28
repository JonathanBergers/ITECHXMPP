package model;

import interfaces.Writable;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by jonathan on 1-11-15.
 */
public class QueuedMessage implements Writable{


    private final Writable wrappedMessage;
    private final User user;

    public QueuedMessage(Writable wrappedMessage, User u) {
        this.wrappedMessage = wrappedMessage;
        this.user = u;
    }

    public User getUser() {
        return user;
    }


    @Override
    public void write(XMLStreamWriter writer) throws XMLStreamException {
        wrappedMessage.write(writer);
    }
}
