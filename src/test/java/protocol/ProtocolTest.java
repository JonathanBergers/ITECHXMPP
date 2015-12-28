package protocol;

import generic.xml.XMLElement;
import sun.corba.OutputStreamFactory;

import javax.xml.stream.XMLStreamReader;
import java.io.*;

/**
 * Created by jonathan on 28-12-15.
 */
public class ProtocolTest {


    public static void main(String[] args) {




//        XMLElement xmlElement = makeElement();
//
//        XMLElement other = makeElement();
//
//
//
//        System.out.println(xmlElement.checkProtocol(other));




    }


    public static XMLElement  makeElement(){
        XMLElement xmlElement = new XMLElement("naaam", "valueee");
        xmlElement.addAttribute("jo", "niks");
        xmlElement.addAttribute("gast", "asd");

        xmlElement.addElement("sub_naaam").addAttribute("subattr", "asd");
        return xmlElement;

    }




}
