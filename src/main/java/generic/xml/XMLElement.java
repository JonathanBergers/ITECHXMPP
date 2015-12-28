package generic.xml;

import generic.NestedElement;
import generic.protocol.Protocol;
import interfaces.Writable;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonathan on 27-10-15.
 */
public class XMLElement extends NestedElement<XMLElement> implements Writable{







    private LinkedList<XMLAttribute> attributes = new LinkedList<>();

    public static void main(String[] args) {

        XMLElement e = new XMLElement(null, "StanzaMessage", "dit is een bericht");
        e.addAttribute(new XMLAttribute("id", "100"));
        e.addElement("body", "jooo");
        System.out.println(e.toString());

    }







    public XMLElement(final String name, final String value) {
        super(null, name, value);
    }
    public XMLElement(final String name) {
        super(null, name);
    }

    public XMLElement(XMLElement parent, final String name){
        super(parent, name);
    }
    public XMLElement(XMLElement parent, final String name, final String value){
        super(parent, name, value);
    }






    public boolean withText(){
        return value !=null;
    }


    @Override
    public void write(XMLStreamWriter writer) throws XMLStreamException {


        writer.writeStartElement(name);


        for(XMLAttribute a: attributes) a.write(writer);
        if(value != null){
            writer.writeCharacters(value);
        }

        for(XMLElement e: getChildren()){

            e.write(writer);

        }

        writer.writeEndElement();


    }

    public XMLElement addElement(final String name, final String text){
        XMLElement element = new XMLElement(this, name, text);
        addChild(element);
        return element;
    }
    public XMLElement addElement(final String name){
        XMLElement element = new XMLElement(this, name);
        addChild(element);
        return element;
    }
    public XMLElement addElement(XMLElement element){
        element.setParent(this);
        addChild(element);
        return element;
    }
    public XMLElement addAttribute(XMLAttribute attribute){
        attributes.addLast(attribute);
        return this;
    }


    public XMLElement addAttribute(String name, String value){
        attributes.addLast(new XMLAttribute(name, value));
        return this;
    }



    public XMLElement addAttributes(List<XMLAttribute> attributes){
        this.attributes.addAll(attributes);
        return this;
    }


    @Override
    public String toString() {

        String toString  = "<" + name;

        for(XMLAttribute a: attributes){
            toString += " "+  a.toString();
        }

        toString += ">";

        if(value != null){
            toString += value;
        }

        for(XMLElement e: getChildren()){
            toString += "\n" + "\t" +  e.toString() + "\n";
        }

        toString += "</" + name + ">";
        return createToString("", 0);
    }

    public String createToString(String s, int tabs){


        String space = "";
        for (int i = 0; i < tabs; i++) {

            space +="\t";
        }
        String toString  = space + "<" + name;

        for(XMLAttribute a: attributes){
            toString += " "+  a.toString();
        }

        toString += ">";

        if(value != null){
            toString += value;
        }
        if(getChildren().isEmpty()){
            toString += "</" + name + ">";
            return toString;
        }
        for(XMLElement e: getChildren()){
            toString += "\n" + e.createToString(toString, tabs + 1) + "\n";
        }

        toString += space + "</" + name + ">";

        return toString;
    }



    public void setValue(String value) {
        this.value = value;
    }


    public XMLAttribute getAttributeAt(int index){
        return attributes.get(index);
    }

    public LinkedList<XMLAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XMLElement element = (XMLElement) o;

        return element.hasName(getName());

    }




    //================== PROTOCOL ==============





    public final boolean checkProtocol(XMLElement other){



        if(!this.protocol.conforms(other)){

            System.out.println(other.getName().equals(this.getName()));

            System.out.println("no conform");


            System.out.println("THIS NAME" + getName());
            System.out.println("OTHER NAME" + other.getName());

            System.out.printf("THIS VALUE" + getValue());
            System.out.println("OTHER VALUE" + other.getValue());


            System.out.println(other.toString());
            System.out.println(this.toString());
            return false;
        }


        //CHECK ATTRIBUTES
        List<XMLAttribute> other_attr = other.getAttributes();

        List<XMLAttribute> attributes = getAttributes();


        if(!attributes.containsAll(other_attr)){
            System.out.println("attrs doesnt contain protocols");
            return false;
        }

        // find mathcing attr

        for(XMLAttribute a: other_attr){


            XMLAttribute matchinAtrribute = attributes.stream().filter(xmlAttribute -> xmlAttribute.equals(a)).findFirst().get();
            if(!a.getProtocol().conforms(matchinAtrribute)){

                System.out.println("PROTOCOLS NOT MATCHING FOR ATTRS");
                System.out.println(a.toString());
                System.out.println(matchinAtrribute.toString());

                return false;
            }
        }


        //CHECK CHILDREN
        List<XMLElement> other_children = other.getChildren();

        List<XMLElement> children = getChildren();

        if(!children.containsAll(other_children)){
            System.out.println("child elements dont contain protocols");
            return false;
        }

        // find mathcing attr

        for(XMLElement a: other_children){
            XMLElement matchingElement = children.stream().filter(element -> element.equals(a)).findFirst().get();

            if(!a.checkProtocol(matchingElement)){
                System.out.println("recursion checking element, no match");
                return false;
            }

        }

        return protocol.conforms(other);


    }

//    /**checks recursive if the element conforms the protocol.
//     *
//     * @param element
//     * @param protocol
//     * @return
//     */
//    public final boolean checkRecursive(XMLElement element, XMLProtocol<XMLElement> protocol) {
//
//        boolean startBoolean = true;
//        if (!protocol.conforms(element)) {
//
//            return false;
//        } else if(!checkAttributes(element, protocol)) {
//
//            //System.out.println("ATTRIBUTE CHECK failed");
//            return false;
//        }else{
//
//            List<XMLElement> elementChildren = element.getChildren();
//            List<XMLProtocol> childProtocols = getChildren();
//
//
//            int elSize = elementChildren.size();
//            int proSize = childProtocols.size();
//
////            // aantal elementen komt niet overeen
////            if (elSize != proSize) {
////                System.out.println("Size of elements doesnt match");
////                System.out.println("element size = : " + elSize);
////                System.out.println("protocol el size = : " + proSize);
////                return false;
////            }
//
//            for (int i = 0; i < elSize; i++) {
//
//                XMLElement elmt = elementChildren.get(i);
//                XMLProtocol prot = childProtocols.get(i);
//
//                if(elmt == null || prot == null){
//                    break;
//                }
//                boolean b = prot.checkRecursive(elmt, prot, startBoolean);
//
//                if (!b) {
//                    return false;
//                }
//
//            }
//            return true;
//
//        }
//
//    }
//
//    private final boolean checkRecursive(XMLElement element, XMLProtocol protocol, boolean b) {
//
//        boolean startBoolean = true;
//        if (!protocol.conforms(element)) {
//
//            return false;
//        } else if(!checkAttributes(element, protocol)) {
//
//            return false;
//        }else{
//
//            List<XMLElement> elementChildren = element.getChildren();
//            List<XMLProtocol> childProtocols = getChildren();
//
//
//            int elSize = elementChildren.size();
//            int proSize = childProtocols.size();
//
//            // aantal elementen komt niet overeen
////                if (elSize != proSize) {
////                    return false;
////                }
//
//
//
//            for (int i = 0; i < elSize; i++) {
//
//                XMLElement elmt = elementChildren.get(i);
//                XMLProtocol prot = childProtocols.get(i);
//
//                b = prot.checkRecursive(elmt, prot, startBoolean);
//
//                if (!b) {
//                    return false;
//                }
//
//            }
//            return b;
//
//        }
//    }
//
//    /**
//     * checks if the attributes are valid
//     * @param element
//     * @param attributeProtocol
//     * @return
//     */
//    public final boolean checkAttributes(XMLElement element, XMLProtocol attributeProtocol) {
//
//        List<XMLAttribute> elementChildren = element.getAttributes();
//        List<Protocol<XMLAttribute>> childProtocols = attributeProtocol.getAttributes();
//
//        int elSize = elementChildren.size();
//        int proSize = childProtocols.size();
//
////        // aantal elementen komt niet overeen
////        if (elSize != proSize) {
////            System.out.println("Size of attributes doesnt match");
////
////            return false;
////        }
//
//        for (int i = 0; i < elSize; i++) {
//
//            XMLAttribute elmt = elementChildren.get(i);
//            Protocol<XMLAttribute> prot = childProtocols.get(i);
//
//
//
//            if (!prot.conforms(elmt)) {
//                return false;
//            }
//        }
//        return true;
//
//    }



}
