package com.henri.devflow.util;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonTools
{
    private static final Logger LOGGER = Logger.getLogger(JsonTools.class);
    
    public static final String EMPTY_OBJECT = "{}";
    
    private JsonTools()
    { /* Prevent Instantiation */ }
    
    public static String toJson(Object source)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(source);
        }
        catch (JsonProcessingException e)
        {
            LOGGER.warn(e);
            return "";
        }
    } 
    
    public static <T> T fromJson(String src, Class<T> valueType)
    {
        if (src == null)
        {
            LOGGER.warn("null source");
            return null;
        }
        
        try
        {
            return new ObjectMapper().readValue(src, valueType);
        }
        catch (IOException e)
        {
            LOGGER.warn(e);
            return null;
        }
    }
    
    public static <T> T fromJson(String src, TypeReference<T> valueType)
    {
        if (src == null)
        {
            LOGGER.warn("null source");
            return null;
        }
        
        try
        {
            return new ObjectMapper().readValue(src, valueType);
        }
        catch (IOException e)
        {
            LOGGER.warn(e);
            return null;
        }
    }
    
    public static <T extends Collection<T>, U> T toArray(String src, Class<U> valueType, Class<T> collectonType)
    {
        if (src == null)
        {
            LOGGER.warn("null source");
            return null;
        }
        
        try
        {
            final ObjectMapper mapper = new ObjectMapper();
            
            return mapper.readValue(src, mapper.getTypeFactory().constructCollectionType(collectonType, valueType));
        }
        catch (IOException e)
        {
            LOGGER.warn(e);
            return null;
        }
    }
    
    
}
