package moovin.springdemo.services;

import moovin.springdemo.domain.Point;
import moovin.springdemo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Optional<Point> getPoint(Integer id) {
        return pointRepository.findById(id);
    }

    @Override
    public Optional<Point> createPoint(Point point) {
        return Optional.of(pointRepository.saveAndFlush(point));
    }

    @Override
    public Optional<Point> updatePoint(Integer id, Point point) {
        if (pointRepository.findById(id).isPresent()) {
            return Optional.of(pointRepository.saveAndFlush(point));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> deletePoint(Integer id) {
        if (pointRepository.findById(id).isPresent()) {
            pointRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }
}
