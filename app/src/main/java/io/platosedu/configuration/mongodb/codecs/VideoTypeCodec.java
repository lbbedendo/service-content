package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Content;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class VideoTypeCodec implements Codec<Content.VideoType> {
    @Override
    public Content.VideoType decode(BsonReader reader, DecoderContext decoderContext) {
        return Content.VideoType.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Content.VideoType value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Content.VideoType> getEncoderClass() {
        return Content.VideoType.class;
    }
}
