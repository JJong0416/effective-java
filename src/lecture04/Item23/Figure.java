package lecture04.Item23;

class Figure {

    enum Shape { RECTANGLE, CIRCLE };

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // 다음 필드들은 모양이 사각형일때만 쓰인다.
    double length;
    double width;

    // 다음 필드는 모양이 원일때만 쓰인다.
    double radius;

    // 원 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // 모양에 따른 넓이 구하기
    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;

            case CIRCLE:
                return Math.PI * (radius * radius);

            default:
                throw new AssertionError(shape);
        }
    }
}