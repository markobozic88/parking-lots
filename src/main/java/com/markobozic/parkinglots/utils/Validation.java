package com.markobozic.parkinglots.utils;

public class Validation {

    private Validation() {
    }

    public static double validateLatitude(final String latitude) {
        try {
            return Double.parseDouble(latitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("Latitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Latitude does not contain a parsable double!");
        }
    }

    public static double validateLongitude(final String longitude) {
        try {
            return Double.parseDouble(longitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("Longitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Longitude does not contain a parsable double!");
        }
    }

    public static double validateRadius(final String radius) {
        try {
            return Double.parseDouble(radius);
        } catch (NullPointerException npe) {
            throw new NullPointerException("Radius can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Radius does not contain a parsable double!");
        }
    }
}
