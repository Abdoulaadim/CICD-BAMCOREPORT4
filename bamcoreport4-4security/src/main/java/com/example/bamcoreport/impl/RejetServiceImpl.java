package com.example.bamcoreport.impl;

import com.example.bamcoreport.config.SecurityConstants;
import com.example.bamcoreport.model.dto.RejetDto;
import com.example.bamcoreport.model.dto.RejetParUtilisateurDto;
import com.example.bamcoreport.model.entity.Rejet;
import com.example.bamcoreport.model.entity.Role;
import com.example.bamcoreport.model.entity.User;
import com.example.bamcoreport.repository.RejetRepository;
import com.example.bamcoreport.repository.RoleRepository;
import com.example.bamcoreport.repository.UserRepository;
import com.example.bamcoreport.service.RejetService;


import io.jsonwebtoken.Jwts;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RejetServiceImpl implements RejetService {

    @Autowired
    RejetRepository rejetRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<Rejet> getAllRejet() {

        return rejetRepository.findAll();
    }

    @Override
    public RejetDto createRejet(Rejet rejet) {

        Rejet rejetentity = modelMapper.map(rejet, Rejet.class);
        Rejet newRejet = rejetRepository.save(rejetentity);
        RejetDto rejetDto = modelMapper.map(newRejet, RejetDto.class);

        return rejetDto;
    }


    @Override
    public Rejet updateRejet(long id, Rejet rejetRequest) {


        Rejet rejet = rejetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rejet" + id));


        rejet.setFlowType(rejetRequest.getFlowType());
        rejet.setRejectNature(rejetRequest.getRejectNature());
        rejet.setEntity(rejetRequest.getEntity());
        rejet.setDeclarationDate(rejetRequest.getDeclarationDate());
        rejet.setAgencyCode(rejetRequest.getAgencyCode());
        rejet.setUserRegistrationNumber(rejetRequest.getUserRegistrationNumber());
        rejet.setBu(rejetRequest.getBu());
        rejet.setSu(rejetRequest.getSu());
        rejet.setRegionalDelegation(rejetRequest.getRegionalDelegation());
        rejet.setSubDelegationType(rejetRequest.getSubDelegationType());
        rejet.setSubDelegationName(rejetRequest.getSubDelegationName());
        rejet.setCliFileCode(rejetRequest.getCliFileCode());
        rejet.setClientCode(rejetRequest.getClientCode());
        rejet.setRib(rejetRequest.getRib());
        rejet.setGravity(rejetRequest.getGravity());
        rejet.setZoneCode(rejetRequest.getZoneCode());
        rejet.setIsWrongField(rejetRequest.getIsWrongField());
        rejet.setErrorCode(rejetRequest.getErrorCode());
        rejet.setErrorLabel(rejetRequest.getErrorLabel());
        rejet.setIsRequestTaken(rejetRequest.getIsRequestTaken());
        rejet.setActionDetail(rejetRequest.getActionDetail());
        rejet.setFile(rejetRequest.getFile());
        rejet.setTakenBy(rejetRequest.getTakenBy());


        return rejetRepository.save(rejet);
    }

    @Override
    public void deleteRejet(long id) {

        Rejet rejet = rejetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rejet" + id));

        rejetRepository.delete(rejet);

    }

    @Override
    public Rejet getRejetById(long id) {
        Optional<Rejet> result = rejetRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Role" + id);
        }
    }

    @Override
    public String filter(String tokenb) {


        String token = tokenb.replace(SecurityConstants.TOKEN_PREFIX, "");
        String username = Jwts.parser()
                .setSigningKey(SecurityConstants.TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            if (userEntity != null) {
                long id = userEntity.getId();
                String role = roleRepository.findrole(id);
                return role;
            }
        }


        return "not definded";


    }

    @Override
    public int totalRejet() {

        int Nombre_Total = (int) rejetRepository.findAll().stream().count();
        return Nombre_Total;
    }

    @Override
    public List<RejetParUtilisateurDto> rejetPuser() {

        List<RejetParUtilisateurDto> rejetParUser =rejetRepository.nobreparuser();
        return rejetParUser;
    }


}
