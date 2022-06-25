package moovin.springdemo.services;

import moovin.springdemo.domain.Point;

import java.util.Optional;

public interface PointService {
    Optional<Point> getPoint(Integer id);

    Optional<Point> createPoint(Point point);

    Optional<Point> updatePoint(Integer id, Point point);

    Optional<Integer> deletePoint(Integer id);
}
