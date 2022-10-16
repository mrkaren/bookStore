package am.itspace.bookstore.controller;

import am.itspace.bookstore.entity.Book;
import am.itspace.bookstore.entity.Order;
import am.itspace.bookstore.security.CurrentUser;
import am.itspace.bookstore.service.BookService;
import am.itspace.bookstore.service.OrderService;
import am.itspace.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;

    @GetMapping("/order/new")
    public String newOrder(@RequestParam("bookId") int bookId,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Book> byId = bookService.findById(bookId);
        if (byId.isEmpty()) {
            return "redirect:/";
        }
        Order order = Order.builder()
                .book(byId.get())
                .user(currentUser.getUser())
                .orderDateTime(new Date())
                .amount(byId.get().getPrice())
                .build();
        orderService.save(order);
        return "redirect:/myOrders";
    }
}
