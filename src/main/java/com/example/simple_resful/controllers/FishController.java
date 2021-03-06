package com.example.simple_resful.controllers;

import com.example.simple_resful.form.AccountForm;
import com.example.simple_resful.form.AddBoardForm;
import com.example.simple_resful.form.FeedFishForm;
import com.example.simple_resful.models.Account;
import com.example.simple_resful.models.Board;
import com.example.simple_resful.models.FishControl;
import com.example.simple_resful.service.AccountService;
import com.example.simple_resful.service.BoardService;
import com.example.simple_resful.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FishController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private TestService testService;

    private final static Logger logger = LoggerFactory.getLogger(FishController.class);

    private List<FishControl> fishControls = new ArrayList<>();

//    @GetMapping(name = "test")
//    public String runTest() {
//        testService.deleteAccount();
//        return "/";
//    }


    // ======== registration ========
    // can create another controller class for cleaner
    // refer to: https://memorynotfound.com/spring-security-user-registration-example-thymeleaf/
    @GetMapping(value = "registration")
    public ModelAndView openRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        AccountForm accountForm = new AccountForm();
        modelAndView.addObject("accountForm", accountForm);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    // @Valid se validate cac constraint added trong class (@NotEmply, @FieldMatch, ... )roi moi nhay vo function
    // Neu khong add @Valid thi cac constraint kia se 0 duoc check
    @PostMapping(value = "registration")
    public String registrationAccount(
            @ModelAttribute("accountForm") @Valid AccountForm accountForm,
            BindingResult result) {
        Account existAccount = accountService.getAccount(accountForm.getUsername());
        if (existAccount != null) {
            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        accountService.save(accountForm);
        return "redirect:/registration?success";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String openHomePage(Model model) {
        model.addAttribute("welcomeMessage", "Hello sanh");
        model.addAttribute("fishControls", fishControls);
        return "/home";
    }

    // ======== control ========
    @GetMapping(value = "/control")
    public ModelAndView openFishControlPage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Board> accountBoards = accountService.getAccountBoards(authentication.getName());
        modelAndView.addObject("accountBoards", accountBoards);
        modelAndView.setViewName("control");
        return modelAndView;
    }

    @GetMapping(value = "/control/addBoard")
    public ModelAndView openAddBoardPage() {
        ModelAndView modelAndView = new ModelAndView();
        AddBoardForm addBoardForm = new AddBoardForm();
        modelAndView.addObject("addBoardForm", addBoardForm);
        modelAndView.setViewName("addBoard");
        return modelAndView;
    }

    @PostMapping(value = "/control/addBoard")
    public String addBoard(
            @ModelAttribute("addBoardForm") @Valid AddBoardForm addBoardFrom,
            BindingResult result) {

        /* TODO cho nay sai. vi neu board co san roi, phai kiem tra co
           accountBoard khong chu khong phai kiem tra co board hay 0
           ## Test bang cach lay account nao chua co board da co san, add board da co san do vo*/
        Board boardExist = boardService.getBoard(addBoardFrom.getBoardMac());
        if (boardExist != null) {
            result.rejectValue("boardMac", null, "This board is already created");
        }
        if (result.hasErrors()) {
            return "/addBoard";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        addBoardFrom.setAdminName(authentication.getName());

        boardService.save(addBoardFrom);
        return "redirect:/control";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String openAboutPage(Model model) {
        return "/about";
    }

    // TODO use this to create error for login form
    // http://www.nakov.com/blog/2016/08/05/creating-a-blog-system-with-spring-mvc-thymeleaf-jpa-and-mysql/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLoginPage(Model model) {
        return "/login";
    }

    @GetMapping("error/403")
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        String errorMsg = "You are not authorized for the requested data.";
        modelAndView.addObject("errorMsg", errorMsg);
        modelAndView.setViewName("error/403");
        return modelAndView;
    }
//
//    // For controller
//
//    @RequestMapping(value = "/feedNow", method = RequestMethod.GET)
//    public String feedNow(Model model) {
//        logger.info("### feed now");
//        model.addAttribute("infoMessage", "feeded fish");
//        return "control";
//    }
//
//    @RequestMapping(value = "feedAfterTime", method = RequestMethod.POST)
//    public String feedAfterTime(Model model, @ModelAttribute(value = "FeedFishForm")FeedFishForm feedFishForm) {
//        String milligram = feedFishForm.getMilligram();
//        String time = feedFishForm.getTime();
//        logger.info("milligram: " + milligram + ", time: " + time);
//        /* ham nay phai dung redirect thi moi duoc*/
//        // model.addAttribute("infoMessage", "milligram: " + milligram + ", time: " + time);
//        // return "redirect:/fish/fish_control";
//
//        fishControls.add(new FishControl(milligram, time));
//        return "redirect:/home";
//    }
}
