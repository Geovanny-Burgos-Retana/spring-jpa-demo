package moovin.springdemo.repository;

import moovin.springdemo.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findPointsByContactsId(Integer contactId);
}
