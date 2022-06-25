package moovin.springdemo.controllers;

import moovin.springdemo.controllers.dto.PointResultDTO;
import moovin.springdemo.domain.Point;
import moovin.springdemo.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/point")
public class PointController {
    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PointResultDTO> createPoint(@Valid @RequestBody Point point,
                                                      Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        PointResultDTO pointResultDTO = new PointResultDTO();
        if (errors.hasErrors()) {
            pointResultDTO.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(pointResultDTO, httpStatus);
        }
        // Si pasa el if anterior significa el json enviado al endpoint tiene una estructura válida
        Optional<Point> pointCreated = pointService.createPoint(point);
        // Validación de que el Optional tenga un contacto
        if (pointCreated.isEmpty()) {
            pointResultDTO.setMessage("Point not created");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(pointResultDTO, httpStatus);
        }
        // Si pasa el if anterior significa que el optional tiene un objeto punto
        pointResultDTO.setPoint(pointCreated.get());
        pointResultDTO.setMessage("OK");
        return new ResponseEntity<>(pointResultDTO, httpStatus);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PointResultDTO> getPoint(@PathVariable("id") Integer id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PointResultDTO pointResultDTO = new PointResultDTO();
        Optional<Point> contact = pointService.getPoint(id);
        // Validación de que el Optional tenga un contacto
        if (contact.isEmpty()) {
            pointResultDTO.setMessage("There is no point");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(pointResultDTO, httpStatus);
        }
        // Establecemos los datos en nuestro DTO de respuesta
        pointResultDTO.setPoint(contact.get());
        pointResultDTO.setMessage("OK");
        return new ResponseEntity<>(pointResultDTO, httpStatus);
    }
}
