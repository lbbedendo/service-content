package io.platosedu.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ContentTest {
    @Test
    public void getExamTypes_onlyExamContentTypes_whenReturningAllExamContentTypes() {
        Assertions.assertThat(Content.Type.getExamTypes())
                .isNotEmpty()
                .allMatch(Content.Type::isExam);
    }
}