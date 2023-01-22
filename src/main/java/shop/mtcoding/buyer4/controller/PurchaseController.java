package shop.mtcoding.buyer4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer4.dto.PurchaseAllDto;
import shop.mtcoding.buyer4.model.PurchaseRepository;
import shop.mtcoding.buyer4.model.User;
import shop.mtcoding.buyer4.model.UserRepository;
import shop.mtcoding.buyer4.service.PurchaseService;

@Controller
public class PurchaseController {

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchase/insert")
    public String insert(int productId, int count) {
        // 유저 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        int res = purchaseService.구매하기(principal.getId(), productId, count);
        if (res == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }

    @GetMapping("/purchase")
    public String purchase(Model model) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        List<PurchaseAllDto> purchaseList = purchaseRepository.findByUserId(principal.getId());
        model.addAttribute("purchaseList", purchaseList);
        return "purchase/list";
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return "redirect:/notfound";
        }

        int res = purchaseService.삭제하기(id);
        if (res == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/purchase";
    }
}
