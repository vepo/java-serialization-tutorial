package io.vepo.serialization.xml;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlSerialization {

    public static void main(String[] args) throws Exception {
        System.out.println("Serializing");
        serialize(new SimpleXmlObject("Value 1", 1, 5, BigDecimal.valueOf(2.5)), Paths.get(".", "value-1.xml"));
        serialize(new SimpleXmlObject("Value 2", 10, null, BigDecimal.valueOf(5.7)), Paths.get(".", "value-2.xml"));

        System.out.println("Deserializing");
        System.out.println(deserialize(SimpleXmlObject.class, Paths.get(".", "value-1.xml")));
        System.out.println(deserialize(SimpleXmlObject.class, Paths.get(".", "value-2.xml")));
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserialize(Class<T> objClass, Path inputFile) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(objClass);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(inputFile.toFile());

    }

    private static <T> void serialize(T obj, Path outputFile) throws Exception {
        System.out.println("Saving object=" + obj + " to file=" + outputFile.toAbsolutePath());
        JAXBContext contextObj = JAXBContext.newInstance(obj.getClass());

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try (FileOutputStream fos = new FileOutputStream(outputFile.toFile())) {
            marshallerObj.marshal(obj, fos);
        }
    }
}
