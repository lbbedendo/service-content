package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Content;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class ContentTypeCodec implements Codec<Content.Type> {
    @Override
    public Content.Type decode(BsonReader reader, DecoderContext decoderContext) {
        return Content.Type.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Content.Type value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Content.Type> getEncoderClass() {
        return Content.Type.class;
    }
}
