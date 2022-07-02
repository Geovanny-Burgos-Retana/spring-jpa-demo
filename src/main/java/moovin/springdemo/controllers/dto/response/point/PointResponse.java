package moovin.springdemo.controllers.dto.response.point;

import moovin.springdemo.controllers.dto.response.BaseResponse;
import moovin.springdemo.domain.Point;

public class PointResponse extends BaseResponse {
    private Point point;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
