package sn.yayaveli.taxclearancesystem.exceptions;

public enum ErrorCodes {
    DECLARATION_NOT_FOUND(1000),
    DECLARATION_NOT_VALID(1001),
    DECLARANT_NOT_FOUND(2000),
    DECLARANT_NOT_VALID(2001),
    // TODO complete the rest of the error code
    PAIEMENT_NOT_FOUND(3000),
    PAIEMENT_NOT_VALID(3001);


    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
