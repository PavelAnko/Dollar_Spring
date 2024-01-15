package ua.kiev.prog.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private Utils utils;
    @Autowired
    private CurrencyUnitDao currencyUnitDao;
    @Autowired
    DateConverter dateConverter;

    @GetMapping("/")
    public String onIndex() {
        return "index";
    }
    @PostMapping("/choice")
    public String onLogin(Model model,
                          @RequestParam String choice,
                          @RequestParam String startData,
                          @RequestParam String finishData,
                          @RequestParam String targetData) {
        List<CurrencyUnit> list = utils.getCurrencyStatistic(
                dateConverter.prepareStringDate(startData),
                dateConverter.prepareStringDate(finishData),
                "usd");
        currencyUnitDao.deleteAll();
        currencyUnitDao.saveAll(list);
        switch (choice) {
            case "1":
                model.addAttribute("result", currencyUnitDao.getAverageRate());
                model.addAttribute("message", "Average currency");
                break;
            case "2":
                model.addAttribute("result",currencyUnitDao.findByDate(dateConverter.convertStringToDate(targetData)).getRate());
                model.addAttribute("message", "Currency from date");
                break;
            case "3":
                model.addAttribute("result", currencyUnitDao.getMaxRate());
                model.addAttribute("message", "Max currency");
                break;
        }
        model.addAttribute("startData",dateConverter.prepareStringDate(startData));
        model.addAttribute("finishData",dateConverter.prepareStringDate(finishData));
        model.addAttribute("targetData",dateConverter.prepareStringDate(targetData));
        return "result";
    }
}
