package spring.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.dataBase.repository.UserRepository;
import spring.dataBase.repository.entity.Role;
import spring.dataBase.repository.entity.User;
import spring.dto.PageResponse;
import spring.dto.UserFilter;
import spring.dto.UserReadDto;
import spring.service.CompanyService;
import spring.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
        Page<UserReadDto> page =  userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }
    /*@GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
        if(filter.firstname() != null) model.addAttribute("users", userService.findAll(filter, pageable));
        else model.addAttribute("users", userService.findAll());
        return "user/users";
    }*/

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable("id") Long id){
        UserReadDto user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());

        return "user/user";
    }
    @PostMapping
    public String save(@ModelAttribute @Validated UserReadDto userDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        userService.save(userDto);
        User user = userRepository.findByUsername(userDto.getUsername());
        return "redirect:/users/" + user.getId();
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated UserReadDto userReadDto){
        System.out.println(userReadDto);
        userService.update(id, userReadDto);
        return "redirect:/users/{id}";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserReadDto userReadDto){
        model.addAttribute("user", userReadDto);
        System.out.println(model.getAttribute("user"));
        System.out.println(userReadDto);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }



}
