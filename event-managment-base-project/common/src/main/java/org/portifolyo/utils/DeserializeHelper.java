package org.portifolyo.utils;

import org.portifolyo.requests.eventservice.OrganizatorRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;

public class DeserializeHelper {

    public static Object desarialize(byte[] data) {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();

        } catch (IOException | ClassNotFoundException | RuntimeException ignored) {
            return null;
        }
    }

}
