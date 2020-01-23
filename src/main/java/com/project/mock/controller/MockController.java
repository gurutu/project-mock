package com.project.mock.controller;

import com.project.mock.DataModal.ApiData;
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
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

/**
 * @author Pranav
 * @apiNote Comman Controller
 */
@RestController
public class MockController {

    @Autowired
    private ApiDataServiceImpl apiDataService;

    /**
     * @implNote PostAPI CAll for User
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/**", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> postDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request){
       System.out.println(request.getRequestURI());
        return map;
    }

    @RequestMapping(value = "/**", method = RequestMethod.PUT, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> putDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return map;
    }
    @RequestMapping(value = "/**", method = RequestMethod.PATCH, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> patchDataToDatabase(@RequestBody Map<String,String> map, HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return map;
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET, headers="Accept=*/*", produces = { "application/json" })
    public String getDataToData(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }

    @RequestMapping(value = "/**", method = RequestMethod.DELETE, headers="Accept=*/*", produces = { "application/json" })
    public String deleteDataToData(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }

    @RequestMapping(value = "/**", method = RequestMethod.HEAD, headers="Accept=*/*", produces = { "application/json" })
    public String headApi(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }

    @RequestMapping(value = "/**", method = RequestMethod.TRACE, headers="Accept=*/*", produces = { "application/json" })
    public String traceApi(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS, headers="Accept=*/*", produces = { "application/json" })
    public String optionApi(HttpServletRequest request){
        System.out.println("Pranav "+apiDataService.count());
        return "Hello";
    }


    /**
     * @implNote For UI Application Api
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/project-mock/save-data", method = RequestMethod.POST, headers="Accept=*/*", produces = { "application/json" })
    public Map<String,String> sendDataToDatabase(@RequestBody List<ApiData> map, HttpServletRequest request){
//        List<ApiData> list = map.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
//                .map(e -> new ApiData(e.getKey(), e.getValue())).collect(Collectors.toList());
//        for(Map.Entry<String, String> entry:map.entrySet()){
//                 entry.getKey()
//        }
System.out.println();
        map.forEach(e->{apiDataService.save(e);});
        // List<?> list=map.entrySet().stream().map(e -> new ArrayList<ApiData>(e.getKey(), e.getValue());
                        //.collect(Collectors.)
    //forEach((e1)->{System.out.println(e1.getKey()+e1.getValue());});


        return new HashMap<>();
    }
//    @RequestMapping(value = "/project-mock/**", method = RequestMethod.GET, headers="Accept=*/*", produces = { "application/json" })
//    public String getData(HttpServletRequest request){
//        System.out.println("Pranav "+apiDataService.count());
//        return "Hello";
//    }





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
