package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.point.PointInput;
import moovin.springdemo.controllers.dto.response.point.PointResponse;
import moovin.springdemo.domain.Point;

import java.util.Optional;

public interface PointService {
    Optional<Point> getPoint(Integer id);

    PointResponse createPoint(PointInput pointInput);

    Optional<Point> updatePoint(Integer id, Point point);

    Optional<Integer> deletePoint(Integer id);
}
