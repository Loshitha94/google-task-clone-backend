package lk.ijse.dep8.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements SuperEntity{
    @Id
    private String id;
    private String email;
    private String password;
    @Column (name = "full_name")
    private String fullName;
    @Column(name = "profile_pic")
    private String profilePic;
}
