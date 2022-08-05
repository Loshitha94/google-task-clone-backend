package lk.ijse.dep8.tasks.service.custom;

import lk.ijse.dep8.tasks.dto.UserDTO;

import javax.servlet.http.Part;

public interface UserService {
    boolean existsUser(String userIdOrEmail);

    UserDTO registerUser(Part picture,
                         String appLocation,
                         UserDTO user
                         );
    UserDTO getUser(String userIdOrEmail);

    void deleteUSer(String userId, String appLocation);

    void updateUser(UserDTO user, Part picture,
                    String appLocation);
}
