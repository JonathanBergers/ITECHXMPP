package xmpp;

import generic.xml.XMLElement;

/**
 * Created by jonathan on 28-12-15.
 */
public interface XMPPProtocols {
    
    
    
    public static XMLElement login(){


        XMLElement root = new XMLElement("iq");
        root.addAttribute("type", "get").addProtocolNameValue();
        root.addAttribute("to", "");
        root.addAttribute("id", "auth_1").addProtocolNameValue();

        root.addElement("query").addElement("username");


        System.out.println("LOGIN PROTOCOL:" + root.toString());
        return root;

    }
    
    
    
    
}
