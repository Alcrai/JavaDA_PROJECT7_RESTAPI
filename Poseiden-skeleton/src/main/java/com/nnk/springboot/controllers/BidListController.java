package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * BidList Controller
 * this controller manages the display of the list of bid, add, update and delete Bid
 */

@Log4j2
@Controller
public class BidListController {
    @Autowired
    private BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidList",bidLists);
        log.info("load List of Bid");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, @RequestParam(value = "erroraccount",defaultValue = "")String erroraccount,@RequestParam(value = "errortype",defaultValue = "")String errortype, Model model) {
        model.addAttribute("erroraccount", erroraccount);
        model.addAttribute("errortype", errortype);
        log.info("Load view add Bid");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, @RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
      if (bid.getAccount().isBlank()||bid.getType().isBlank()){
          if (bid.getAccount().isBlank()){
              redirectAttributes.addAttribute("erroraccount","Account is mandatory");
              log.error("Account is mandatory");
          }
          if (bid.getType().isBlank()){
              redirectAttributes.addAttribute("errortype","Type is mandatory");
              log.error("Type is mandatory");
          }
          return "redirect:/bidList/add";
      }else {
        if (action.equals("cancel")){
            bid.setAccount("");
            bid.setType("");
            bid.setBidQuantity(0.0);
            log.info("cancel entry");
            return  "bidList/add";
        }
        if (action.equals("add")){
            bidListService.save(bid);
            log.info("add new Bid : "+bid.toString());
            redirect ="redirect:/bidList/list";
        }
      }
        return redirect;
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,@RequestParam(value = "erroraccount",defaultValue = "")String erroraccount,@RequestParam(value = "errortype",defaultValue = "")String errortype,Model model) {
        BidList bid = bidListService.findById(id);
        model.addAttribute("bidList", bid);
        model.addAttribute("erroraccount", erroraccount);
        model.addAttribute("errortype", errortype);
        log.info("load view for update Bid");
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (bidList.getAccount().isBlank()||bidList.getType().isBlank()){
            if (bidList.getAccount().isBlank()){
                redirectAttributes.addAttribute("erroraccount","Account is mandatory");
                log.error("Account is mandatory");
            }
            if (bidList.getType().isBlank()){
                redirectAttributes.addAttribute("errortype","Type is mandatory");
                log.error("Type is mandatory");
            }
            return "redirect:/bidList/update/{id}";
        }else {
            if (action.equals("cancel")){
                log.info("cancel entry");
                return  "redirect:/bidList/update/"+id;
            }
            if (action.equals("update")){
                bidListService.update(bidList,id);
                log.info("update Bid : "+bidList.toString());
                redirect ="redirect:/bidList/list";
            }
        }
        return redirect;
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.delete(id);
        log.info("delete bid with id : "+id);
        return "redirect:/bidList/list";
    }
}
