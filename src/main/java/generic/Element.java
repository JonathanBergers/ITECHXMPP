package generic;

import generic.protocol.Protocol;

/**
 * Created by jonathan on 30-10-15.
 */
public abstract class Element {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        return element.hasName(getName());

    }

    //standard equals protocol
    protected Protocol<Element> protocol = input -> input.hasName(this.getName());

    private boolean hasNameAndValue(String name, String value) {
        return hasName(name) && hasValue(value);
    }

    public Protocol getProtocol(){
        return protocol;
    }


    public void addProtocol(Protocol<Element> protocol){
        this.protocol = protocol;
    }

    public void addProtocolNameValue(){
        this.protocol = input -> input.hasNameAndValue(getName(), getValue());
    }


    protected final String name;
    protected String value;

    protected Element(String name) {
        this.name = name;
    }

    public Element(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


    public boolean hasName(final String name){
        return this.name.equals(name);
    }



    public boolean hasValue(final String value){

        if(value == null && this.value == null){
            return true;
        }
        if(this.value == null) return false;

        return this.value.equals(value);
    }

}
