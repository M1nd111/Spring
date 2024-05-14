package spring.http.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.dataBase.repository.CompanyRepository;
import spring.dto.UserReadDto;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/hello")
    public String hello(
            @RequestParam("age") Integer age,
            @RequestHeader("accept") String accept,
            @CookieValue("JSESSIONID") String jsessionId,
            Model model,
            UserReadDto userReadDto){
        model.addAttribute("user", userReadDto);
        return "/hello";
    }


    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto userReadDto){
        return "/bye";
    }

}



//@RequestMapping(value = "/hello", method = RequestMethod.GET)
//public ModelAndView hello(ModelAndView mv,
//                          HttpServletRequest request,
//                          CompanyRepository companyRepository,
//                          @RequestParam("age") Integer age,
//                          @RequestHeader("accept") String accept,
//                          @CookieValue("JSESSIONID") String jsessionId){
//    mv.setViewName("/hello");
//    mv.addObject("user", new UserReadDto(1L, "Vlad"));
//    return mv;
//}
//
//
//@RequestMapping(value = "/bye", method = RequestMethod.GET)
//public ModelAndView bye(ModelAndView mv,
//                        @SessionAttribute("user") UserReadDto userReadDto){
//    mv.setViewName("/bye");
//    return mv;
//}