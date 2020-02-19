package by.pochepko.res.spotbot.dto;


import com.google.common.base.Objects;

public class SpotMessageDto {
    public SpotMessageDto(String message){
        this.message = message;
    }
    public SpotMessageDto(String location, String message){
        this.location = location;
        this.message = message;
    }
    public SpotMessageDto(){};

    private String location;

    private String message;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(o instanceof SpotMessageDto)) return false;
        SpotMessageDto that = (SpotMessageDto) o;
        return Objects.equal(location, that.location) &&
                Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(location, message);
    }
}
