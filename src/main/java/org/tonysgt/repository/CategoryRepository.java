package org.tonysgt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.tonysgt.entities.Category;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
}
