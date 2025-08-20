package com.learning.accounts.controller;


import com.learning.accounts.constants.AccountConstants;
import com.learning.accounts.dto.*;
import com.learning.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Validated
public class AccountsController {

    private final IAccountService accountService;
    @Value("${build.version:unknown}")
    private String buildVersion;

    //@Autowired
    private final Environment environment;

    @Autowired
    private CustomerInfoDetails customerInfoDetails;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam
                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                    String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateAccount(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = accountService.updateCustomerAndAccountDetails(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Fetch build version details REST API",
            description = "Rest API to fetch current project build version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/version")
    public ResponseEntity<String> getVersionDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("java.version", "21"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CustomerInfoDetails> getContactDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(customerInfoDetails);
    }

    /**
     * This is practice of Tesco interview, ignore this
     */
    @PostMapping("/slots/book")
    public ResponseEntity<ResponseDto> bookSlot(@RequestBody SlotRequest slotRequest) {
        boolean isBooked = accountService.bookSlot(slotRequest);
        if (isBooked) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
