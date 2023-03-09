package br.com.neki.skillList.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.skillList.dto.UserSkillDTO.UserInsertDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class AuthenticationController {

    // @ApiOperation("User login")
    // @ApiResponses(value = {
    // @ApiResponse(code = 200, 
    //         message = "Response Headers", 
    //         responseHeaders = {
    //             @ResponseHeader(name = "authorization", 
    //                     description = "Bearer <JWT value here>"),

    //         })  
    // })
    @PostMapping("/login")
    @Operation(summary = "Sign In Service", description = "Sign In Service", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully Singned In!", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", ref = "BadRequest"),
        @ApiResponse(responseCode = "401", ref = "badcredentials"),
        @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
        @ApiResponse(responseCode = "500", ref = "internalServerError")
})
    public void theFakeLogin(@RequestBody UserInsertDTO loginRequestModel)
    {
        throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
    }
    
}
