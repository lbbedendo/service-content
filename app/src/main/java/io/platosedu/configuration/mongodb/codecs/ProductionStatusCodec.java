package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Content;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class ProductionStatusCodec implements Codec<Content.ProductionStatus> {
    @Override
    public Content.ProductionStatus decode(BsonReader reader, DecoderContext decoderContext) {
        return Content.ProductionStatus.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Content.ProductionStatus value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Content.ProductionStatus> getEncoderClass() {
        return Content.ProductionStatus.class;
    }
}
