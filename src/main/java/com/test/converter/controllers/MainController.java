package com.test.converter.controllers;

import com.test.converter.entetities.ConvertingHistory;
import com.test.converter.entetities.User;
import com.test.converter.entetities.ValCurs;
import com.test.converter.entetities.Valute;
import com.test.converter.repo.HistoryRepo;
import com.test.converter.repo.ValCursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class MainController {
    @Autowired
    private final ValCursRepo valCursRepo;

    @Autowired
    private final HistoryRepo historyRepo;

    public MainController(ValCursRepo valCursRepo, HistoryRepo historyRepo) {
        this.valCursRepo = valCursRepo;
        this.historyRepo = historyRepo;
    }
private String result="";
    @GetMapping
    public String indexGet(@RequestParam(defaultValue = "") String result,
                           @RequestParam(defaultValue = "") String lv1,
                           @RequestParam(defaultValue = "") String lv2,
                           @AuthenticationPrincipal User user,Model model) throws JAXBException, IOException {
        String date =new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        model.addAttribute("date",date);
        if(valCursRepo.findByDate(date)==null) {
                JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ValCurs valute= (ValCurs) jaxbUnmarshaller.unmarshal(new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req="+new SimpleDateFormat("dd/MM/yyyy").format(new Date())).openStream());
            if(valute!=null)valCursRepo.save(valute);

        }
        List<ConvertingHistory> clist=historyRepo.findAllByUser(user);
        model.addAttribute("result",result);
        model.addAttribute("list",valCursRepo.findByDate(date).getValutes());
        model.addAttribute("lv1","");
        model.addAttribute("lv2","");
        model.addAttribute("user",user);
        model.addAttribute("clist",clist);
        return "index";
    }

    @PostMapping
    public String convert(@RequestParam String listValue1,
                          @RequestParam String listValue2,
                          @RequestParam String countFrom,
                          @AuthenticationPrincipal User user, Model model){
        Valute v1 = new Valute(),v2 = new Valute();
        String date =new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        List<Valute> list = valCursRepo.findByDate(date).getValutes();
        for (Valute v:list){
            if(v.getName().equals(listValue1)){ v1=v; break;}
        }
        for (Valute v:list){
            if(v.getName().equals(listValue2)){ v2=v; break;}
        }
        result=String.valueOf(Double.parseDouble(countFrom)*Double.parseDouble(v1.getValue().replace(",","."))/v1.getNominal()/Double.parseDouble(v2.getValue().replace(",","."))*v2.getNominal());
        if(user!=null){
            ConvertingHistory convertingHistory=new ConvertingHistory();
        convertingHistory.setUser(user);
        convertingHistory.setTime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        convertingHistory.setLog(countFrom +" "+v1.getCharCode()+ " = "+ result +" "+v2.getCharCode());
        historyRepo.save(convertingHistory);
        }
        List<ConvertingHistory> clist=historyRepo.findAllByUser(user);
        model.addAttribute("lv1",v1.getName());
        model.addAttribute("lv2",v2.getName());
        model.addAttribute("result",result);
        model.addAttribute("list",valCursRepo.findByDate(date).getValutes());
        model.addAttribute("date",date);
        model.addAttribute("user",user);
        model.addAttribute("clist",clist);
        return "index";
    }

}
