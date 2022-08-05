package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.service.custom.UserService;

import javax.servlet.http.Part;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final Logger logger= Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public boolean existsUser(String userIdOrEmail) {
        return false;
    }

    @Override
    public UserDTO registerUser(Part picture, String appLocation, UserDTO user) {
        return null;
    }

    @Override
    public UserDTO getUser(String userIdOrEmail) {
        return null;
    }

    @Override
    public void deleteUSer(String userId, String appLocation) {

    }

    @Override
    public void updateUser(UserDTO user, Part picture, String appLocation) {

    }
}
