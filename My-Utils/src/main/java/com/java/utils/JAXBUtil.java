package com.java.utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author azapeta
 */
public class JAXBUtil {

    private JAXBUtil() {
    }

    public static <T> T unmarshal(Class<T> cls, File inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        if (jaxbContext == null) {
            throw new JAXBException("No esta configurado este tipo de esquema XSD.");
        }
        Unmarshaller unmarshall = jaxbContext.createUnmarshaller();
        JAXBElement<T> jbElement;
        jbElement = unmarshall.unmarshal(new StreamSource(inputStream), cls);
        return jbElement.getValue();
    }
}
