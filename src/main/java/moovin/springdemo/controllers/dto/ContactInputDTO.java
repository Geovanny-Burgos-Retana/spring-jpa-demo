package moovin.springdemo.controllers.dto;

import javax.validation.constraints.NotNull;

public class ContactInputDTO {
    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactInputDTO{" +
                "id=" + id +
                '}';
    }
}
