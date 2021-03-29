package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Question {

    private QuestionId id;
    private String description;
    private String explanation;
    private List<Option> options;
    private List<Collection.CollectionId> collections;
    private Type type;
    private Status status;
    private Level level;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TenantId tenantId;

    public enum Level {
        EASY(1),
        MEDIUM(2),
        HARD(3);

        private final int level;

        Level(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    public enum Status {
        LIBERADA,
        CANCELADA,
        ERRO_CADASTRAL,
        ERRO_CONCEITUAL,
        PENDENTE
    }

    public enum Type {
        SINGLE,
        MULTIPLE,
        TEXT
    }

    @Data
    @NoArgsConstructor
    public static class Option {

        private OptionId id;
        private String text;
        private Boolean correct;

        public Option(OptionId id, String text, Boolean correct) {
            this.id = id;
            this.text = text;
            this.correct = correct;
        }

        public Option(String text, Boolean correct) {
            this.text = text;
            this.correct = correct;
        }

        @Value
        public static class OptionId {
            String value;
        }
    }

    @Value
    public static class QuestionId {
        String value;
    }
}
