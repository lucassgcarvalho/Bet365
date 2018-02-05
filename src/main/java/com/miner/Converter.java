package com.miner;
import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Lucas Carvalho
 *
 */
public class Converter implements Serializable {
	private static final long serialVersionUID = 1L;
    private static ObjectReader reader;
    private static ObjectWriter writer;

    /**
     * @param json
     * @return
     * @throws IOException
     */
    public static MenuRaiz fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    /**
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String toJsonString(MenuRaiz obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    @SuppressWarnings("deprecation")
	private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        reader = mapper.reader(MenuRaiz.class);
        writer = mapper.writerFor(MenuRaiz.class);
    }

    /**
     * @return ObjectReader
     */
    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    /**
     * @return ObjectWriter
     */
    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}