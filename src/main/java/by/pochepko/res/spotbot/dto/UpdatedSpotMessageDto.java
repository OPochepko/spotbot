package by.pochepko.res.spotbot.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

public class UpdatedSpotMessageDto {
    @NotNull
    String message;

    public UpdatedSpotMessageDto() {
    }

    public UpdatedSpotMessageDto(@NotNull String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatedSpotMessageDto)) return false;
        UpdatedSpotMessageDto that = (UpdatedSpotMessageDto) o;
        return Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
