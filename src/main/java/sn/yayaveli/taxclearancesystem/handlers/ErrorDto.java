package sn.yayaveli.taxclearancesystem.handlers;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.yayaveli.taxclearancesystem.exceptions.ErrorCodes;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;

    private int code;
    private String message;
    private List<String> errors = new ArrayList<>();
}
