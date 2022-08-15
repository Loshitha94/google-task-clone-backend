package lk.ijse.dep8.tasks.service.custom.impl;

import lk.ijse.dep8.tasks.dao.DAOFactory;
import lk.ijse.dep8.tasks.dao.SuperDAO;
import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.entity.User;
import lk.ijse.dep8.tasks.service.custom.UserService;
import lk.ijse.dep8.tasks.service.exception.FailedExecutionException;
import lk.ijse.dep8.tasks.service.util.EntityDTOMapper;
import lk.ijse.dep8.tasks.service.util.JPAUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.EntityManager;
import javax.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final Logger logger= Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public boolean existsUser(String userIdOrEmail) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            UserDAO userDAO = DAOFactory.getInstance().getDAO(em, DAOFactory.DAOTypes.USER);
            return userDAO.existsUserByEmailOrId(userIdOrEmail);

        }finally {
            em.close();
        }
    }

    @Override
    public UserDTO registerUser(Part picture, String appLocation, UserDTO user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            user.setId(UUID.randomUUID().toString());

            if (picture!=null){
                user.setPicture(user.getPicture() + user.getId());
            }
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

            UserDAO dao = DAOFactory.getInstance().getDAO(em, DAOFactory.DAOTypes.USER);

            User userEntity = EntityDTOMapper.getUser(user);
            User savedUser = dao.save(userEntity);

            user = EntityDTOMapper.getUserDTO(savedUser);
            if (picture != null) {
                Path path = Paths.get(appLocation, "uploads");
                if (Files.notExists(path)) {
                    Files.createDirectory(path);
                }

                String picturePath = path.resolve(user.getId()).toAbsolutePath().toString();
                picture.write(picturePath);
            }

            em.getTransaction().commit();
            return user;
        } catch (Throwable t) {
            if (em != null && em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            throw new FailedExecutionException("Failed to save the user", t);
        }finally {
            em.close();
        }
    }

    @Override
    public UserDTO getUser(String userIdOrEmail) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            UserDAO userDAO = DAOFactory.getInstance().getDAO(em, DAOFactory.DAOTypes.USER);
            Optional<User> userWrapper = userDAO.findUserByIdOrEmail(userIdOrEmail);
            return EntityDTOMapper.getUserDTO(userWrapper.orElse(null));
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteUSer(String userId, String appLocation) {

    }

    @Override
    public void updateUser(UserDTO user, Part picture, String appLocation) {

    }
}
