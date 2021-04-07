package com.rbkmoney.proxy.mocketbank.mpi.controller;

import com.rbkmoney.proxy.mocketbank.mpi.handler.mpi20.CardHandler;
import com.rbkmoney.proxy.mocketbank.mpi.model.mpi20.*;
import com.rbkmoney.proxy.mocketbank.mpi.service.CardStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpi20")
public class Mpi20Controller {

    private final List<CardHandler> cardHandlers;
    private final CardStorage cardStorage;

    @PostMapping(value = "/prepare")
    public PreparationResponse prepare(@RequestBody PreparationRequest preparationRequest) {
        PreparationResponse preparationResponse = cardHandlers.stream()
                .filter(c -> c.isHandle(preparationRequest.getPan()))
                .findFirst()
                .map(c -> c.prepareHandle(preparationRequest))
                .orElseThrow();
        cardStorage.save(preparationRequest.getPan(), preparationResponse.getTransactionId());
        return preparationResponse;
    }

    @PostMapping(value = "/auth")
    public AuthenticationResponse auth(@RequestBody AuthenticationRequest authenticationRequest) {
        return cardHandlers.stream()
                .filter(c -> c.isHandle(authenticationRequest.getPan()))
                .findFirst()
                .map(c -> c.authHandle(authenticationRequest))
                .orElseThrow();
    }

    @PostMapping(value = "/result")
    public ResultResponse result(@RequestBody ResultRequest resultRequest) {
        return cardHandlers.stream()
                .filter(c -> c.isHandle(cardStorage.getCard(resultRequest.getTransactionId())))
                .findFirst()
                .map(c -> c.resultHandle(resultRequest))
                .orElseThrow();
    }

    @RequestMapping(value = "/acs", method = RequestMethod.POST)
    public ModelAndView formAcs(@RequestParam(value = "creq") String creq,
                                @RequestParam(value = "termUrl") String termUrl) {
        log.info("Form ACS 2.0 input params: creq {}, termUrl {}", creq, termUrl);
        ModelAndView model = new ModelAndView();
        if (!termUrl.startsWith("https://")) {
            log.warn("Form ACS 2.0, wrong termUrl {}", termUrl);
            model.setViewName("empty");
            return model;
        }
        model.setViewName("acs_2.0_form");
        model.addObject("action", termUrl);
        model.addObject("pan", "XXXX XXXX XXXX XXXX");
        model.addObject("cres", "cres_value");
        log.info("Form ACS 2.0 show the form");
        return model;
    }

}
