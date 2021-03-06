package io.vepo.serialization;

import static java.util.stream.Collectors.toList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class SerializationBenchmark {
    private static final Path JVM_OUT_PATH = Paths.get(".", "benchmark", "values.obj");
    private static final Path XML_OUT_PATH = Paths.get(".", "benchmark", "values.xml");
    private static final Path JSON_OUT_PATH = Paths.get(".", "benchmark", "values.json");

    public static void main(String[] args) throws Exception {
        var objects = new ComplexObjects(IntStream.range(0, 1_000)
                                                  .mapToObj(index -> new ComplexObject("object " + index,
                                                                                       index,
                                                                                       new BigDecimal(index + 0.5),
                                                                                       index + 0.5f,
                                                                                       Stream.of(Type.values())
                                                                                             .findAny()
                                                                                             .get(),
                                                                                       new SubObject("subObject-"
                                                                                               + index,
                                                                                                     "name-" + index)))
                                                  .collect(toList()));

        JVM_OUT_PATH.toFile().delete();
        XML_OUT_PATH.toFile().delete();
        JSON_OUT_PATH.toFile().delete();

        long jvmWriteDurationMs = writeUsingJvm(objects);
        long xmlWriteDurationMs = writeUsingXml(objects);
        long jsonWriteDurationMs = writeUsingJson(objects);

        long jvmReadDurationMs = readUsingJvm(objects);
        long xmlReadDurationMs = readUsingXml(objects);
        long jsonReadDurationMs = readUsingJson(objects);

        System.out.println("Serialization Benchmark:\n\n" +
                "Write JVM  -> " + jvmWriteDurationMs + "ms\n" +
                "Write XML  -> " + xmlWriteDurationMs + "ms\n" +
                "Write JSON -> " + jsonWriteDurationMs + "ms\n\n" +
                "Read JVM   -> " + jvmReadDurationMs + "ms\n" +
                "Read XML   -> " + xmlReadDurationMs + "ms\n" +
                "Read JSON  -> " + jsonReadDurationMs + "ms\n\n" +
                "Size JVM   -> " + Files.size(JVM_OUT_PATH) + "\n" +
                "Size XML   -> " + Files.size(XML_OUT_PATH) + "\n" +
                "Size JSON  -> " + Files.size(JSON_OUT_PATH));
    }

    private static long writeUsingJson(ComplexObjects objects) throws Exception {
        Jsonb jsonb = JsonbBuilder.create();
        long start = System.currentTimeMillis();
        try (FileOutputStream fos = new FileOutputStream(JSON_OUT_PATH.toFile())) {
            jsonb.toJson(objects, fos);
        }
        return System.currentTimeMillis() - start;
    }

    private static long readUsingJson(ComplexObjects objects) throws Exception {
        Jsonb jsonb = JsonbBuilder.create();
        ComplexObjects readValue;
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(JSON_OUT_PATH.toFile())) {
            readValue = jsonb.fromJson(fis, ComplexObjects.class);
        }
        long totalTimeMs = System.currentTimeMillis() - start;
        if (!objects.equals(readValue)) {
            throw new IllegalStateException();
        }
        return totalTimeMs;
    }

    private static long writeUsingXml(ComplexObjects objects) throws Exception {
        JAXBContext contextObj = JAXBContext.newInstance(ComplexObjects.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        long start = System.currentTimeMillis();
        try (FileOutputStream fos = new FileOutputStream(XML_OUT_PATH.toFile())) {
            marshallerObj.marshal(objects, fos);
        }
        return System.currentTimeMillis() - start;
    }

    private static long readUsingXml(ComplexObjects objects) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ComplexObjects.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        long start = System.currentTimeMillis();
        ComplexObjects readValue = (ComplexObjects) jaxbUnmarshaller.unmarshal(XML_OUT_PATH.toFile());
        long totalTimeMs = System.currentTimeMillis() - start;
        if (!objects.equals(readValue)) {
            throw new IllegalStateException();
        }
        return totalTimeMs;
    }

    private static long writeUsingJvm(ComplexObjects objects) throws Exception {
        long start = System.currentTimeMillis();
        try (var fos = new FileOutputStream(JVM_OUT_PATH.toFile());
                var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objects);
            oos.flush();
        }
        return System.currentTimeMillis() - start;
    }

    private static long readUsingJvm(ComplexObjects objects) throws Exception {
        ComplexObjects readValue;
        long start = System.currentTimeMillis();
        try (var fis = new FileInputStream(JVM_OUT_PATH.toFile());
                var ois = new ObjectInputStream(fis)) {
            readValue = (ComplexObjects) ois.readObject();
        }
        long totalTimeMs = System.currentTimeMillis() - start;
        if (!objects.equals(readValue)) {
            throw new IllegalStateException();
        }
        return totalTimeMs;
    }

}
