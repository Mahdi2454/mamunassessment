package com.mamun.task.assessment.contorller;

import com.mamun.task.assessment.model.SenderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    @GetMapping(value="/demo")
    public SenderResponse adminEndPoint() {
        return new SenderResponse("Hello From Admin");
    }
    @GetMapping(value="/manager")
    public SenderResponse managerEndPoint() {
        return new SenderResponse("Hello From Manager");
    }
}
