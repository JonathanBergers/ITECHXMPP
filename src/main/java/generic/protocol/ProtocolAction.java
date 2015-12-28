package generic.protocol;

import generic.Element;
import generic.xml.XMLElement;

/**
 * Created by jonathan on 1-11-15.
 *
 /**
 * Created by jonathan on 30-10-15.
 *
 * when a element conforms a protocol , describe the action to do
 *
 * hiermee kunnen actiess gedefineerd worden die uitgevoerd moeten worden wanneer een element voldoet aan een bepaald protocol.
 * bijvoorbeeld wanneer een bericht ingelezen is, en deze voldoet aan het message protocol, dan moet deze verstuurd worden naar de gebruiker.
 *
 */
public abstract class ProtocolAction<E extends Element, P extends Protocol<E>> {

    private final XMLElement protocol;

    public ProtocolAction(XMLElement protocol) {
        this.protocol = protocol;
    }


    public void handle(XMLElement element){
        if(element.checkProtocol(protocol)){
            onHandle(element);
        }

        System.out.println("handle protocol failed for el" + element);

    }

    public abstract void onHandle(XMLElement element);
}
