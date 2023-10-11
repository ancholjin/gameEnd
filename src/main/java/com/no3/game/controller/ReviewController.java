package com.no3.game.controller;

import com.no3.game.dto.ItemDTO;
import com.no3.game.dto.PageRequestDTO;
import com.no3.game.dto.PageResultDTO;
import com.no3.game.dto.ReviewDTO;
import com.no3.game.entity.Review;
import com.no3.game.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/review/")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/register")
    public void register(){

        log.info("register get..");

    }


    @PostMapping("/register")
    public String registerPost(ReviewDTO reviewDTO, RedirectAttributes redirectAttributes){

        log.info("register post reviewDTO: " + reviewDTO);

        Long id = reviewService.register(reviewDTO);

        redirectAttributes.addFlashAttribute("msg", id);

        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);

        PageResultDTO<ReviewDTO, Review> result = reviewService.getList(pageRequestDTO);

        model.addAttribute("result", result);

    }

    @GetMapping(value = {"/read","/modify"})
    public void read(Long id,
                     @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                     Model model){

        log.info("read id:  " + id);

        ReviewDTO reviewDTO = reviewService.get(id);

        model.addAttribute("dto",reviewDTO );

    }

    @PostMapping("/remove")
    public String remove(Long id, RedirectAttributes redirectAttributes){

        log.info("remove: " + id);

        reviewService.remove(id);

        redirectAttributes.addFlashAttribute("msg","removed");


        return "redirect:/review/list";
    }


    @PostMapping("/modify")
    public String modifyPost(ReviewDTO reviewDTO,
                             PageRequestDTO pageRequestDTO,
                             RedirectAttributes redirectAttributes){

        log.info("modify post reviewDTO: " + reviewDTO);

        Long id = reviewService.modify(reviewDTO);

        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/review/read";
    }

    @GetMapping("/")
    public String index(){
        return"redirect:/review/list";
    }



}











