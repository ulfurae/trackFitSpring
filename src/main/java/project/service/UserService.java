package project.service;

import project.persistence.entities.User;

public interface UserService {



    /**
     * Save a {@link User}
     * @param user {@link User} to be saved
     * @return {@link User} that was saved
     */
    User save(User user);

    /**
     * Find all {@link User}s with {@link String username}
     * @param username {@link String}
     * @return All {@link User}s with the {@link String username} passed
     */
    User findByUsername(String username);

    User getLoggedInUser();

    User loggedInUser = null;

}
