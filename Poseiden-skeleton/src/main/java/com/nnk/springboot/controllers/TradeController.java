package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
 * this controller manages the display of the list of trade, add, update and delete trade
 */
@Log4j2
@Controller
public class TradeController {
    @Autowired
    private TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> tradeList = tradeService.findAll();
        model.addAttribute("trades", tradeList);
        log.info("load List of trade");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade,@RequestParam(value = "erroraccount",defaultValue = "")String erroraccount,
                          @RequestParam(value = "errortype",defaultValue = "")String errortype,Model model) {
        model.addAttribute("erroraccount",erroraccount);
        model.addAttribute("errortype",errortype);
        log.info("Load view add Trade");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, @RequestParam(value="action",required = true)String action,
                           BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (trade.getAccount().isBlank()||trade.getType().isBlank()){
            if (trade.getAccount().isBlank()){
                redirectAttributes.addAttribute("erroraccount","Account is mandatory");
                log.error("Account is mandatory");
            }
            if (trade.getType().isBlank()){
                redirectAttributes.addAttribute("errortype","Type is mandatory");
                log.error("type is mandatory");
            }
            return "redirect:/trade/add";
        }else {
            if (action.equals("cancel")){
               trade.setAccount("");
               trade.setType("");
               trade.setBuyQuantity(0);
                log.info("cancel entry");
                return  "trade/add";
            }
            if (action.equals("add")){
                tradeService.save(trade);
                log.info("add new trade : "+trade.toString());
                redirect ="redirect:/trade/list";
            }
        }
        return redirect;
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,@RequestParam(value = "erroraccount",defaultValue = "")String erroraccount,
                                 @RequestParam(value = "errortype",defaultValue = "")String errortype, Model model) {
        Trade trade = tradeService.findById(id);
        model.addAttribute("trade",trade);
        model.addAttribute("erroraccount",erroraccount);
        model.addAttribute("errortype",errortype);
        log.info("load view for update Trade");
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (trade.getAccount().isBlank()||trade.getType().isBlank()){
            if (trade.getAccount().isBlank()){
                redirectAttributes.addAttribute("erroraccount","Account is mandatory");
                log.error("Account is mandatory");
            }
            if (trade.getType().isBlank()){
                redirectAttributes.addAttribute("errortype","Type is mandatory");
                log.error("type is mandatory");
            }
            return "redirect:/trade/update/{id}";
        }else {
            if (action.equals("cancel")){
                log.info("cancel entry");
                return  "redirect:/trade/update/"+id;
            }
            if (action.equals("update")){
                tradeService.update(trade,id);
                log.info("update Trade : "+trade.toString());
                redirect ="redirect:/trade/list";
            }
        }
        return redirect;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.delete(id);
        log.info("delete trade with id : "+id);
        return "redirect:/trade/list";
    }
}
