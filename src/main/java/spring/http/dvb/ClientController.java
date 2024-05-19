package spring.http.dvb;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api/dvb")
@CrossOrigin
public class ClientController {
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/client")
    public String startPage(){
        return "dvb/client";
    }

    @PostMapping("/client")
    public String Gateway(@RequestParam("xmlBody") String xmlBody){
        System.out.println(xmlBody);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("xmlBody", xmlBody);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/api/dvb/receiver", request, String.class);

        System.out.println("Response from receiver: " + response.getBody());

        return "redirect:receiver";
    }
}

