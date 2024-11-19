package com.maxsoft.datingbackend.profile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class ProfileController {

    @GetMapping("/info")
    public String getStatus() {
        return "Profiles API is working";
    }

}
