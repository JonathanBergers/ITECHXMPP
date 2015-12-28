package interfaces;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by jonathan on 12-10-15.
 * Deze interface zorgt ervoor dat objecten verzonden kunnen worden over een socket.
 *
 */
public interface Writable {


    public void write(XMLStreamWriter writer) throws XMLStreamException;



}
