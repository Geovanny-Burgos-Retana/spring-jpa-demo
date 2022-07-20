package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.point.PointInput;
import moovin.springdemo.controllers.dto.response.point.PointResponse;
import moovin.springdemo.domain.Point;
import moovin.springdemo.repository.PointRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PointServiceImpl implements PointService {
    private final static ModelMapper modelMapper = new ModelMapper();
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
    public PointResponse createPoint(PointInput pointInput) {
        PointResponse pointResponse = new PointResponse();
        try {
            Point point = modelMapper.map(pointInput, Point.class);
            pointResponse.setPoint(pointRepository.saveAndFlush(point));
            pointResponse.setResult(Boolean.TRUE);
            pointResponse.setStatus("SUCCESS");
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()
            ).log(Level.SEVERE, null, ex);
        }
        return pointResponse;
    }

    @Override
    public PointResponse updatePoint(Integer id, PointInput pointInput) {
        PointResponse pointResponse = new PointResponse();
        if (pointRepository.findById(id).isPresent()) {
            Point point = modelMapper.map(pointInput, Point.class);
            pointResponse.setPoint(pointRepository.saveAndFlush(point));
            pointResponse.setResult(Boolean.TRUE);
            pointResponse.setStatus("SUCCESS");
        } else {
            pointResponse.setResult(Boolean.TRUE);
            pointResponse.setStatus("NOTFOUNDPOINT");
        }
        return pointResponse;
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
