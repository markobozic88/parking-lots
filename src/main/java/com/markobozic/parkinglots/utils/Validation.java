package com.markobozic.parkinglots.utils;

public class Validation {

    private Validation() {
    }

    public static double validateLatitude(final String latitude) {
        try {
            return Double.parseDouble(latitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("latitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("latitude does not contain a parsable double!");
        }
    }

    public static double validateLongitude(final String longitude) {
        try {
            return Double.parseDouble(longitude);
        } catch (NullPointerException npe) {
            throw new NullPointerException("longitude can not be null!");
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("longitude does not contain a parsable double!");
        }
    }
}
