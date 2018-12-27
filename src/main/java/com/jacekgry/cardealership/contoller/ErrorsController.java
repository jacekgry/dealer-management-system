package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.error.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorsController {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityException(DataIntegrityViolationException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", e.getRootCause().getMessage());
        e.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleThrowable(Throwable t) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", t.getMessage());
        t.printStackTrace();
        return modelAndView;
    }
}
