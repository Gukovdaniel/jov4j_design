package ru.job4j.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean gaz;
    @XmlAttribute
    private int howOld;
    private int numberMotor;
    String[] colors;

    public Car() {

    }

    public Car(boolean gaz, int howOld, int numberMotor, String[] colors) {
        this.gaz = gaz;
        this.howOld = howOld;
        this.numberMotor = numberMotor;
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Car{"
                + "gaz=" + gaz
                + ", howOld=" + howOld
                + ", numberMotor=" + numberMotor
                + ", colors=" + Arrays.toString(colors)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {

        final Car car = new Car(false, 10, 123321, new String[]{"Red", "Beig"});

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String xmlRsl = writer.getBuffer().toString();
            System.out.println(xmlRsl);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
