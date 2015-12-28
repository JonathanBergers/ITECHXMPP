package generic.xml;

import generic.Element;
import interfaces.Writable;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by jonathan on 27-10-15.
 */
public class XMLAttribute extends Element implements Writable {


    public XMLAttribute(String name, String value) {
        super(name, value);
    }

    @Override
    public void write(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute(name, value);
    }

    @Override
    public String toString() {
        return name + "=\"" + value + "\"";
    }

}
