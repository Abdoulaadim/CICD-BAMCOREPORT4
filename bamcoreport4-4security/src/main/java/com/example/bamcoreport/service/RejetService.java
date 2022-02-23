package com.example.bamcoreport.service;

import com.example.bamcoreport.model.dto.RejetDto;
import com.example.bamcoreport.model.dto.RejetParUtilisateurDto;
import com.example.bamcoreport.model.entity.Rejet;


import java.io.IOException;
import java.util.List;

public interface RejetService {

    List<Rejet> getAllRejet();

    RejetDto createRejet(Rejet rejetDto);

    Rejet updateRejet(long id, Rejet rejet);

    void deleteRejet(long id);

    Rejet getRejetById(long id);

    String filter(String tokenb);

    int totalRejet();

    List<RejetParUtilisateurDto> rejetPuser() ;
}
