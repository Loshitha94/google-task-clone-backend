package lk.ijse.dep8.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "task_list")
public class TaskList implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    @ManyToOne
    private User user;

    public TaskList(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
