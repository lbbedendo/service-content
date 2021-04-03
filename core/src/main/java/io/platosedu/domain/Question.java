package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Question {

    private UUID id;
    private String description;
    private String explanation;
    private List<Option> options;
    private List<UUID> collections;
    private Type type;
    private Status status;
    private Level level;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

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

        private UUID id;
        private String text;
        private Boolean correct;

        public Option(UUID id, String text, Boolean correct) {
            this.id = id;
            this.text = text;
            this.correct = correct;
        }

        public Option(String text, Boolean correct) {
            this.text = text;
            this.correct = correct;
        }
    }
}
