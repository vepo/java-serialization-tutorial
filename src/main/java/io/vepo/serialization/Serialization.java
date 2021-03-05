package io.vepo.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serialization {
    public static void main(String[] args) throws Exception {
        System.out.println("Serializing");
        serialize(new SimplepObject("Value 1", 1, 5), Paths.get(".", "value-1.txt"));
        serialize(new SimplepObject("Value 2", 10, null), Paths.get(".", "value-2.txt"));

        System.out.println("Deserializing");
        System.out.println(deserialize(Paths.get(".", "value-1.txt")));
        System.out.println(deserialize(Paths.get(".", "value-2.txt")));
    }

    public static <T> void serialize(T obj, Path outputFile) throws IOException {
        System.out.println("Saving objec=" + obj + " to file=" + outputFile.toAbsolutePath());
        try (var fos = new FileOutputStream(outputFile.toFile());
                var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    static Object deserialize(Path inputFile) throws Exception {
        try (var fis = new FileInputStream(inputFile.toFile());
                var ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
}
