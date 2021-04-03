package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Link;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class ModalityTypeCodec implements Codec<Link.Modality.Type> {
    @Override
    public Link.Modality.Type decode(BsonReader reader, DecoderContext decoderContext) {
        return Link.Modality.Type.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Link.Modality.Type value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Link.Modality.Type> getEncoderClass() {
        return Link.Modality.Type.class;
    }
}
