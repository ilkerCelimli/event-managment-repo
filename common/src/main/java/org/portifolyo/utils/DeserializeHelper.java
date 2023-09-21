package org.portifolyo.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeHelper {

    public static <T> T desarialize(byte[] data) {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (T) ois.readObject();

        } catch (IOException | ClassNotFoundException | RuntimeException ignored) {
            return null;
        }
    }

}
