package server;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import generic.protocol.ProtocolAction;
import generic.xml.XMLAttribute;
import generic.xml.XMLElement;
import model.Connection;
import model.Model;
import org.xml.sax.InputSource;
import xmpp.XMPPProtocols;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 28-12-15.
 */
public class XMPPConnection extends Connection {


    private final ServerSettings serverSettings;

    public XMPPConnection(Socket socket, Model model, ServerSettings serverSettings) {
        super(socket, model);
        this.serverSettings = serverSettings;
    }

    @Override
    protected void init() {
        try {
            xmlStreamReader = XMLStreamReaderFactory.create(new InputSource(socket.getInputStream()), false);
            xmlStreamWriter = XMLStreamWriterFactory.create(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }


    }

    @Override
    protected void connect() {





        System.out.println("trying to connect");
       boolean correct = true;

        while (correct){


            try {

                correct = xmlStreamReader.getEventType() == XMLEvent.START_DOCUMENT;
                xmlStreamReader.next();

                correct = xmlStreamReader.getEventType() == XMLEvent.START_ELEMENT;
                correct = xmlStreamReader.getLocalName().equals("stream");

                List<XMLAttribute> attributes = checkAttributes(xmlStreamReader);

                String to = null;
                for (XMLAttribute a: attributes){
                    if(a.hasName("to")){
                        to =a.getValue();
                    }
                }
                correct= (to != null);

                // connection made, now send a response
                XMLElement response = new XMLElement("stream:stream");
                response.addAttribute("from", serverSettings.getXmppAddress());
                response.addAttribute("xmlns:stream", "http://etherx.jabber.org/streams");
                response.write(xmlStreamWriter);
                xmlStreamReader.next();
                return;




            } catch (XMLStreamException e) {
                e.printStackTrace();
                correct  = false;


            }




        }
        close();



    }

    @Override
    protected void serve() {


        try {

            System.out.println(xmlStreamReader.getEventType());
            while (xmlStreamReader.hasNext()){

                XMLElement element = readStream(xmlStreamReader);
                System.out.println(element);

                login().handle(element);









            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void close() {

        try {
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println("CLOSING");
    }

    @Override
    public void run() {
        init();
        connect();
        serve();
    }






    private XMLElement readStream(XMLStreamReader streamReader) throws XMLStreamException {


        XMLElement el = null;
        try {
            if(!streamReader.hasNext()){
                return el;
            }
            streamReader.next();
            if (streamReader.isStartElement()) {

                // recursion
                String localName = streamReader.getLocalName();
                el = new XMLElement(localName);
                el.addAttributes(checkAttributes(streamReader));
                return readElement(streamReader, el);

            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return el;


    }


    /**
     * Reads xml from the stream and builds elements
     * recursive
     *
     * @param streamReader
     * @param element
     * @return
     */
    private XMLElement readElement(XMLStreamReader streamReader, XMLElement element) throws XMLStreamException {

        if(element.hasName("stream")) return element;

        while (streamReader.hasNext()) {
            try {


                streamReader.next();


                if (streamReader.isStartElement()) {



                    // recursion
                    XMLElement el = new XMLElement(element, streamReader.getLocalName());
                    el.addAttributes(checkAttributes(streamReader));
                    element.addElement(readElement(streamReader, el));

                } else if (streamReader.isCharacters()) {
                    element.setValue(streamReader.getText());
                } else {
                    return element;
                }
                //streamReader.next();

            } catch (XMLStreamException e) {
                e.printStackTrace();
            }

        }

        return element;


    }


    private List<XMLAttribute> checkAttributes(XMLStreamReader reader) {


        List<XMLAttribute> attributes = new ArrayList<>();
        int attrCount = reader.getAttributeCount();

        //System.out.println("Attribute count : " + attrCount);

        if (attrCount > 0) {

            // voeg attributen toe
            for (int i = 0; i < attrCount; i++) {

                String attrName = reader.getAttributeName(i).getLocalPart();
                String attrValue = reader.getAttributeValue(i);

                XMLAttribute attribute = new XMLAttribute(attrName, attrValue);
                //System.out.println("Attribute: " + attrName + " " + attrValue);
                // System.out.println(attribute.toString());

                attributes.add(attribute);


            }

        }
        return attributes;

    }

    int get10(){
        return 10;
    }



    private ProtocolAction login(){


        return new ProtocolAction(XMPPProtocols.login()) {
            @Override
            public void onHandle(XMLElement element) {
                final String username = element.getChildAt(0).getChildAt(0).getValue();
                System.out.println("username " + username);


            }
        };
    }



}
