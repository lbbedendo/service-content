package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Question;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class QuestionTypeCodec implements Codec<Question.Type> {
    @Override
    public Question.Type decode(BsonReader reader, DecoderContext decoderContext) {
        return Question.Type.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Question.Type value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Question.Type> getEncoderClass() {
        return Question.Type.class;
    }
}
