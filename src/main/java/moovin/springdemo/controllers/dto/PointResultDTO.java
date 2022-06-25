package moovin.springdemo.controllers.dto;

import moovin.springdemo.domain.Point;

public class PointResultDTO {
    private String message;
    private Point point;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
