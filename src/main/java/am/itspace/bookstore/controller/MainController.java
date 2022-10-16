package am.itspace.bookstore.controller;

import am.itspace.bookstore.entity.Book;
import am.itspace.bookstore.entity.User;
import am.itspace.bookstore.entity.UserRole;
import am.itspace.bookstore.exception.DuplicateResourceException;
import am.itspace.bookstore.security.CurrentUser;
import am.itspace.bookstore.service.BookService;
import am.itspace.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        List<Book> last20Books = bookService.findLast20Books();
        modelMap.addAttribute("books", last20Books);
        return "index";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserRole() == UserRole.ADMIN) {
                return "redirect:/admin";
            }else if(user.getUserRole() == UserRole.USER){
                return "redirect:/";
            }
        }
        return "redirect:/";
    }



}
