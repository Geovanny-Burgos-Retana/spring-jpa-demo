package moovin.springdemo.controllers;

import moovin.springdemo.controllers.dto.PointResultDTO;
import moovin.springdemo.controllers.dto.general.point.PointInput;
import moovin.springdemo.controllers.dto.response.point.PointResponse;
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
    public ResponseEntity<PointResponse> createPoint(@Valid @RequestBody PointInput pointInput,
                                                     Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        PointResponse pointResponse = new PointResponse();
        if (errors.hasErrors()) {
            pointResponse.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity<>(pointResponse, httpStatus);
        }
        try {
            pointResponse = pointService.createPoint(pointInput);
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(pointResponse, httpStatus);
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

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PointResponse> updatePoint(@PathVariable Integer id,
                                                     @Valid @RequestBody PointInput pointInput,
                                                     Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        PointResponse pointResponse = new PointResponse();
        if (errors.hasErrors()) {
            pointResponse.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(pointResponse, httpStatus);
        }
        try {
            pointResponse = pointService.updatePoint(id, pointInput);
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(pointResponse, httpStatus);
    }
}
