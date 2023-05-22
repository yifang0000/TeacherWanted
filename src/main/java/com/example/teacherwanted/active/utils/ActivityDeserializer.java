package com.example.teacherwanted.active.utils;

import com.example.teacherwanted.active.model.Active;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Base64;

public class ActivityDeserializer extends JsonDeserializer<Active> {
    @Override
    public Active deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        JsonNode photoNode = node.get("activityPhoto");
        String base64String = photoNode.asText();

        byte[] activityPhoto = Base64.getDecoder().decode(base64String);

        Active active = new Active();
        active.setActivityPhoto(activityPhoto);

        return active;
    }

}





