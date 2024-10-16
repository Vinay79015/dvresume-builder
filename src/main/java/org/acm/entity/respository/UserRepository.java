package org.acm.entity.respository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acm.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
