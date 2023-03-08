package br.com.neki.skillList.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.skillList.dto.UserSkillDTO.UserInsertDTO;

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
    public void theFakeLogin(@RequestBody UserInsertDTO loginRequestModel)
    {
        throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
    }
    
}
