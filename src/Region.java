public class Region {
    String name;
    double x_min;
    double y_min;
    double x_max;
    double y_max;

    Region(String name,double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        this.name = name;
        if (x1 == x2) {
            if (x1 < x3) {
                x_min = x1;
                x_max = x3;
            } else if (x1 > x3) {
                x_min = x3;
                x_max = x1;
            }
            if (y1 < y2) {
                y_min = y1;
                y_max = y2;
            } else if (y1 > y2) {
                y_min = y2;
                y_max = y1;
            }
        } else if (x1 == x3) {
            if (x1 < x2) {
                x_min = x1;
                x_max = x2;
            } else if (x1 > x2) {
                x_min = x2;
                x_max = x1;
            }
            if (y1 < y3) {
                y_min = y1;
                y_max = y3;
            } else if (y1 > y3) {
                y_min = y3;
                y_max = y1;

            }
        }
    }

    boolean isInRegion( double x, double y) {
        if (x > x_max || x < x_min || y > y_max || y < y_min) {
            return false;
        }
        return true;
    }

    int containsPoint(double x, double y, int mod) {
        if (mod == 0) {
            if (x >= x_min && x <= x_max)
                return 0;
            else if (x > x_max) {
                return +1;
            }
//            else if (x < x_min) {
//                return -1;
//            }
            else {
                return -1;
            }
        }
        else {
            if (y >= y_min && y <= y_max)
                return 0;
            else if (y > y_max) {
                return +1;
            }
//            else if (y < y_min) {
//                return -1;
//            }
            else {
                return -1;
            }
        }
    }

}
