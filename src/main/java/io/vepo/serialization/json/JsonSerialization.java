package io.vepo.serialization.json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class JsonSerialization {
    public static void main(String[] args) throws Exception {
        System.out.println("Serializing");
        serialize(new SimpleJsonObject("Value 1", 1, 5, BigDecimal.valueOf(2.5)), Paths.get(".", "value-1.json"));
        serialize(new SimpleJsonObject("Value 2", 10, null, BigDecimal.valueOf(5.7)), Paths.get(".", "value-2.json"));

        System.out.println("Deserializing");
        System.out.println(deserialize(SimpleJsonObject.class, Paths.get(".", "value-1.json")));
        System.out.println(deserialize(SimpleJsonObject.class, Paths.get(".", "value-2.json")));
    }

    private static <T> T deserialize(Class<T> objClass, Path inputFile) throws Exception {
        Jsonb jsonb = JsonbBuilder.create();
        try (FileInputStream fis = new FileInputStream(inputFile.toFile())) {
            return jsonb.fromJson(fis, objClass);
        }
    }

    private static <T> void serialize(T obj, Path outputFile) throws Exception {
        System.out.println("Saving object=" + obj + " to file=" + outputFile.toAbsolutePath());
        Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        try (FileOutputStream fos = new FileOutputStream(outputFile.toFile())) {
            jsonb.toJson(obj, fos);
        }
    }
}
