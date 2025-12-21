package ifba.edu.hospital.exception.classes;

public class DoctorInactiveException extends RuntimeException {
    public DoctorInactiveException(String message) {
        super(message);
    }
}