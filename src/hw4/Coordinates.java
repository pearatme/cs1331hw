package hw4;

public class Coordinates {
    final double latitude, longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof Coordinates other))
            return false;
        if (other == this)
            return true;
        return other.longitude == this.longitude && other.latitude == this.latitude;
    }

    @Override
    public String toString() {
        return String.format("latitude: %5.2f, longitude: %5.2f", latitude, longitude);
    }

}
