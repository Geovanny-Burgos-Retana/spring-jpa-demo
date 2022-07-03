package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.point.PointInput;
import moovin.springdemo.controllers.dto.response.point.PointResponse;
import moovin.springdemo.domain.Point;
import moovin.springdemo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public PointResponse createPoint(PointInput pointInput) {
        PointResponse pointResponse = new PointResponse();
        try {
            Point point = mapDtoToEntity(pointInput);
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
            pointResponse.setPoint(pointRepository.saveAndFlush(mapDtoToEntity(pointInput)));
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

    private Point mapDtoToEntity(PointInput pointInput) {
        Point point = new Point();
        if (pointInput.getId() != null && pointInput.getId() > 0) {
            Optional<Point> pointFind = pointRepository.findById(pointInput.getId());
            point = pointFind.orElse(point);
        }
        point.setId(pointInput.getId());
        point.setLatitude(pointInput.getLatitude());
        point.setLongitude(pointInput.getLongitude());
        point.setAddress(pointInput.getAddress());
        if (point.getContacts() == null) {
            point.setContacts(new ArrayList<>());
        }
        Point finalPoint = point;
        pointInput.getContacts().forEach(contact -> {
            finalPoint.getContacts().add(contact);
            if (contact.getPoints() == null) {
                contact.setPoints(new ArrayList<>());
            }
            contact.getPoints().add(finalPoint);
        });
        return finalPoint;
    }
}
