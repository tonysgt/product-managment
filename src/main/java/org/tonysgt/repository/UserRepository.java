package org.tonysgt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.tonysgt.entities.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
