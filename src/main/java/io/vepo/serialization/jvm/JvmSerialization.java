package io.vepo.serialization.jvm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JvmSerialization {
    public static void main(String[] args) throws Exception {
        System.out.println("Serializing");
        serialize(new SimpleObject("Value 1", 1, 5, BigDecimal.valueOf(2.5)), Paths.get(".", "value-1.obj"));
        serialize(new SimpleObject("Value 2", 10, null, BigDecimal.valueOf(5.7)), Paths.get(".", "value-2.obj"));

        System.out.println("Deserializing");
        System.out.println(deserialize(Paths.get(".", "value-1.obj")));
        System.out.println(deserialize(Paths.get(".", "value-2.obj")));
    }

    private static <T> void serialize(T obj, Path outputFile) throws Exception {
        System.out.println("Saving object=" + obj + " to file=" + outputFile.toAbsolutePath());
        try (var fos = new FileOutputStream(outputFile.toFile());
                var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    private static Object deserialize(Path inputFile) throws Exception {
        try (var fis = new FileInputStream(inputFile.toFile());
                var ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
}
