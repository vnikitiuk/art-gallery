package org.viktoriianikitiuk.artoffreedom.dao;

import org.springframework.data.repository.CrudRepository;
import org.viktoriianikitiuk.artoffreedom.Painting;

public interface PaintingRepository extends CrudRepository<Painting, Long>{
}