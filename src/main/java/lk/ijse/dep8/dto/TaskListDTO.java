package lk.ijse.dep8.dto;

import jakarta.json.bind.annotation.JsonbTransient;

public class TaskListDTO {
    private Integer id;
    private String title;
    @JsonbTransient
    private String userId;


    public TaskListDTO() {

    }

    public TaskListDTO(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public TaskListDTO(Integer id, String title, String userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TaskListDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
