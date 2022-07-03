package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.point.PointInput;
import moovin.springdemo.controllers.dto.response.point.PointResponse;
import moovin.springdemo.domain.Point;

import java.util.Optional;

public interface PointService {
    Optional<Point> getPoint(Integer id);

    PointResponse createPoint(PointInput pointInput);

    PointResponse updatePoint(Integer id, PointInput pointInput);

    Optional<Integer> deletePoint(Integer id);
}
