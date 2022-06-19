package com.example.TeacherManagement.exception;

import org.springframework.http.HttpStatus;

public class MyException extends Throwable {
    private static final String CERTIFICATE_DETAIL_NOT_FOUND_MSG_KEY = "CertificateDetailNotFound";
    private static final String CERTIFICATE_DETAIL_NOT_FOUND_MSG = "Certificate Detail Not Found";

    private static final String INVALID_MONTH_MSG_KEY = "InvalidMonth";
    private static final String INVALID_MONTH_MSG = "Invalid Month";

    private static final String CERTIFICATE_DETAIL_ID_NOT_FOUND_MSG_KEY = "CertificateDetailIdNotFound";
    private static final String CERTIFICATE_DETAIL_ID_NOT_FOUND_MSG = "Certificate Detail Id Not Found";

    private static final String CERTIFICATE_ID_NOT_FOUND_MSG_KEY = "CertificateIdNotFound";
    private static final String CERTIFICATE_ID_NOT_FOUND_MSG = "Certificate Id Not Found";

    private static final String CLASS_ID_NOT_FOUND_MSG_KEY = "ClassIdNotFound";
    private static final String CLASS_ID_NOT_FOUND_MSG = "Class Id Not Found";

    private static final String TEACHER_CODE_NOT_FOUND_MSG_KEY = "TeacherCodeNotFound";
    private static final String TEACHER_CODE_NOT_FOUND_MSG = "Teacher Code Not Found";

    private static final String CONTRACT_ID_NOT_FOUND_MSG_KEY = "ContractIdNotFound";
    private static final String CONTRACT_ID_NOT_FOUND_MSG = "Contract Id Not Found";

    private static final String NATIONALITY_ID_NOT_FOUND_MSG_KEY = "NationalityIdNotFound";
    private static final String NATIONALITY_ID_NOT_FOUND_MSG = "Nationality Id Not Found";

    private static final String COUNTRY_CODE_NOT_FOUND_MSG_KEY = "CountryCodeNotFound";
    private static final String COUNTRY_CODE_NOT_FOUND_MSG = "Country Code Not Found";

    private static final String ASSIGNMENT_DETAIL_ID_NOT_FOUND_MSG_KEY = "AssignmentDetailIdNotFound";
    private static final String ASSIGNMENT_DETAIL_ID_NOT_FOUND_MSG = "Assignment Detail Id Not Found";

    private static final String PAYMENT_ID_NOT_FOUND_MSG_KEY = "PaymentIdNotFound";
    private static final String PAYMENT_ID_NOT_FOUND_MSG = "Payment Id Not Found";

    private static final String TEACHER_ID_NOT_FOUND_MSG_KEY = "TeacherIdNotFound";
    private static final String TEACHER_ID_NOT_FOUND_MSG = "Teacher Id Not Found";

    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static ResponseException InvalidMonth() {
        return badRequest(INVALID_MONTH_MSG_KEY, INVALID_MONTH_MSG);
    }

    public static ResponseException CertificateDetailIdNotFound() {
        return notFound(CERTIFICATE_DETAIL_ID_NOT_FOUND_MSG_KEY, CERTIFICATE_DETAIL_ID_NOT_FOUND_MSG);
    }

    public static ResponseException CertificateIdNotFound() {
        return notFound(CERTIFICATE_ID_NOT_FOUND_MSG_KEY, CERTIFICATE_ID_NOT_FOUND_MSG);
    }

    public static ResponseException ClassIdNotFound() {
        return notFound(CLASS_ID_NOT_FOUND_MSG_KEY, CLASS_ID_NOT_FOUND_MSG);
    }

    public static ResponseException TeacherCodeNotFound() {
        return notFound(TEACHER_CODE_NOT_FOUND_MSG_KEY, TEACHER_CODE_NOT_FOUND_MSG);
    }

    public static ResponseException TeacherIdNotFound() {
        return notFound(TEACHER_ID_NOT_FOUND_MSG_KEY, TEACHER_ID_NOT_FOUND_MSG);
    }

    public static ResponseException ContractIdNotFound() {
        return notFound(CONTRACT_ID_NOT_FOUND_MSG_KEY, CONTRACT_ID_NOT_FOUND_MSG);
    }

    public static ResponseException NationalityIdNotFound() {
        return notFound(NATIONALITY_ID_NOT_FOUND_MSG_KEY, NATIONALITY_ID_NOT_FOUND_MSG);
    }

    public static ResponseException CountryCodeNotFound() {
        return notFound(COUNTRY_CODE_NOT_FOUND_MSG_KEY, COUNTRY_CODE_NOT_FOUND_MSG);
    }

    public static ResponseException AssignmentDetailIdNotFound() {
        return notFound(ASSIGNMENT_DETAIL_ID_NOT_FOUND_MSG_KEY, ASSIGNMENT_DETAIL_ID_NOT_FOUND_MSG);
    }

    public static ResponseException PaymentIdNotFound() {
        return notFound(PAYMENT_ID_NOT_FOUND_MSG_KEY, PAYMENT_ID_NOT_FOUND_MSG);
    }


}
