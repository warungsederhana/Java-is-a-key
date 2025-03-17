package programmerzamannow.springmvc.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.springmvc.models.CreatePersonRequest;

@Controller
public class PersonController {

    @PostMapping(path = "/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createPerson(@ModelAttribute @Valid CreatePersonRequest request) {
        System.out.println(request);
        return new StringBuilder().append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName()).append(" ")
                .append("with email ").append(request.getEmail()).append(" ")
                .append("and phone ").append(request.getPhone())
                .append(" with address ").append(request.getAddress().getStreet())
                .append(", ").append(request.getAddress().getCity())
                .append(", ").append(request.getAddress().getCountry())
                .append(" ").append(request.getAddress().getPostalCode())
                .toString();
    }
}
