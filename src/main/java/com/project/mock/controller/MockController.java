package com.project.mock.controller;

import com.project.mock.Service.ApiDataService;
import com.project.mock.Service.ApiDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class MockController {

    @Autowired
    private ApiDataServiceImpl apiDataService;

    @RequestMapping(value = "/**", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> sendDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request){
  System.out.println(request.getRequestURI());


        return map;
    }
    @RequestMapping(value = "/**", method = RequestMethod.GET, headers="Accept=*/*", produces = { "application/json" })
    public String getData(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }

    @RequestMapping(value = "/js/**", method = RequestMethod.GET)
    public byte[] getJSFile(HttpServletRequest request)  {
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }

    @RequestMapping(value = "/css/**", method = RequestMethod.GET)
    public byte[] getCSSFile(HttpServletRequest request){
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }
    @RequestMapping(value = "/img/**", method = RequestMethod.GET)
    public byte[] getImageFile(HttpServletRequest request) {
        Path path = Paths.get(".\\src\\main\\resources\\static\\"+request.getRequestURI());
        byte[] bytes=null;
        try{bytes = Files.readAllBytes(path);
        }catch (Exception e){}
        return bytes;
    }
}
