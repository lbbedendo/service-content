package io.platosedu.configuration.mongodb.codecs;

import io.platosedu.domain.Question;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import javax.inject.Singleton;

@Singleton
public class QuestionStatusCodec implements Codec<Question.Status> {
    @Override
    public Question.Status decode(BsonReader reader, DecoderContext decoderContext) {
        return Question.Status.valueOf(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, Question.Status value, EncoderContext encoderContext) {
        writer.writeString(value.name());
    }

    @Override
    public Class<Question.Status> getEncoderClass() {
        return Question.Status.class;
    }
}
