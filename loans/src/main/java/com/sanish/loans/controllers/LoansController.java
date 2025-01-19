package com.sanish.loans.controllers;

import com.sanish.loans.constants.LoansConstants;
import com.sanish.loans.dto.ErrorResponseDto;
import com.sanish.loans.dto.LoansContactInfoDto;
import com.sanish.loans.dto.LoansDto;
import com.sanish.loans.dto.ResponseDto;
import com.sanish.loans.entities.Loans;
import com.sanish.loans.services.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="Loans Microservice Endpoints",
        description = "Create, Read, Update, Delete Loan details inside Aero Bank application"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    private final ILoansService loansService;

    private final Environment environment;

    private final LoansContactInfoDto loansContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    public LoansController(ILoansService loansService,
                           Environment environment,
                           LoansContactInfoDto loansContactInfoDto) {
        this.loansService = loansService;
        this.environment = environment;
        this.loansContactInfoDto = loansContactInfoDto;
    }

    @Operation(
            summary = "Create Loan",
            description = "REST API endpoint to create new Loan for a customer inside Aero Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewLoan(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                         String mobileNumber) {
        loansService.createLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details",
            description = "REST API endpoint to fetch Customer's Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                         String mobileNumber) {
        LoansDto fetchedLoanDetails = loansService.getLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetchedLoanDetails);
    }

    @Operation(
            summary = "Update Loan Details",
            description = "REST API endpoint to update Customer's Loan details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
        boolean entryUpdated = loansService.updateLoan(loansDto);

        if(!entryUpdated) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Delete Loan Details",
            description = "REST API endpoint to delete Customer Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                             String mobileNumber) {
        boolean entryDeleted = loansService.deleteLoan(mobileNumber);

        if(!entryDeleted) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Get Build Information",
            description = "REST API endpoint to fetch build information of microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get Java Version Information",
            description = "REST API endpoint to fetch Java version used for microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
    }

    @Operation(
            summary = "Get Contact Information",
            description = "REST API endpoint to fetch Contact information of microservice developer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}
