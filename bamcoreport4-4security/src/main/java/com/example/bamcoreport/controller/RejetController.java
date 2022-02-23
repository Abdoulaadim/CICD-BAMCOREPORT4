package com.example.bamcoreport.controller;

import com.example.bamcoreport.config.SecurityConstants;
import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.RejetDto;
import com.example.bamcoreport.model.dto.RejetParUtilisateurDto;
import com.example.bamcoreport.model.entity.Rejet;
import com.example.bamcoreport.service.RejetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/rejet")
public class RejetController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RejetService rejetService;


//    @GetMapping
//    public List<RejetDto> getAllRejets() {
//
//        return rejetService.getAllRejet().stream().map(role -> modelMapper.map(role, RejetDto.class))
//                .collect(Collectors.toList());
//    }


    @GetMapping
    public List<RejetDto> getListRejets(HttpServletRequest req) {

        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        String roletoken = rejetService.filter(token);
        System.out.println(roletoken);


        return rejetService.getAllRejet().stream().map(role -> modelMapper.map(role, RejetDto.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RejetDto> getRejetById(@PathVariable(name = "id") Long id) {

        Rejet role = rejetService.getRejetById(id);
        RejetDto roleResponse = modelMapper.map(role, RejetDto.class);
        return ResponseEntity.ok().body(roleResponse);
    }

//    @PostMapping
//    public ResponseEntity<RejetDto> createRejet(@RequestBody RejetDto rejetDto) {
//
//
//        // convert DTO to entity
//        Rejet rejetRequest = modelMapper.map(rejetDto, Rejet.class);
//
//        RejetDto rejet = rejetService.createRejet(rejetRequest);
//
//        // convert entity to DTO
//        RejetDto rejetResponse = modelMapper.map(rejet, RejetDto.class);
//
//        return new ResponseEntity<RejetDto>(rejetResponse, HttpStatus.CREATED);
//    }


    @PostMapping
    public ResponseEntity<RejetDto> createRejet(@RequestBody Rejet rejetDto, HttpServletRequest req) throws Exception {
        String token = req.getHeader(SecurityConstants.HEADER_STRING);

        String role = rejetService.filter(token);


        if (role.equals("admin") || role.equals("superadmin")) {
            RejetDto rj = rejetService.createRejet(rejetDto);
            return ResponseEntity.ok(rj);
        } else {
            throw new Exception("you dont have any permission for this request");
        }

    }


    //    @PutMapping("/{id}")
//    public ResponseEntity<RejetDto> updateRejet(@PathVariable long id, @RequestBody RejetDto rejetDto) {
//
//        Rejet rejetRequest = modelMapper.map(rejetDto, Rejet.class);
//        Rejet rejet = rejetService.updateRejet(id, rejetRequest);
//        RejetDto rejtResponse = modelMapper.map(rejet, RejetDto.class);
//        return ResponseEntity.ok().body(rejtResponse);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<RejetDto> updateRejet(@PathVariable long id, @RequestBody RejetDto rejetDto, HttpServletRequest req) throws Exception {
        String token = req.getHeader(SecurityConstants.HEADER_STRING);

        String role = rejetService.filter(token);
        if (role.equals("admin") || role.equals("superadmin")) {
            Rejet rejetRequest = modelMapper.map(rejetDto, Rejet.class);
            Rejet rejet = rejetService.updateRejet(id, rejetRequest);
            RejetDto rejtResponse = modelMapper.map(rejet, RejetDto.class);
            return ResponseEntity.ok().body(rejtResponse);
        } else {
            throw new Exception("you dont have any permission for this request");
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRejet(@PathVariable(name = "id") Long id) {
        rejetService.deleteRejet(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Rejet deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/total")
    public ResponseEntity<Integer> totalRejet() throws Exception {
        int Nombre_Total = rejetService.totalRejet();
        return ResponseEntity.ok(Nombre_Total);

    }



    @GetMapping("/ParUser")
    public ResponseEntity<List<RejetParUtilisateurDto>> RejetParUser()  {

        List<RejetParUtilisateurDto> rejparnom = rejetService.rejetPuser();
        return ResponseEntity.ok(rejparnom);

    }
}
