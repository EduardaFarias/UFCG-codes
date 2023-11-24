package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.interesseSabor.InteresseSaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.interesseSabor.InteresseSaborBuscaService;
import com.ufcg.psoft.commerce.service.interesseSabor.InteresseSaborCriaService;
import com.ufcg.psoft.commerce.service.interesseSabor.InteresseSaborExcluirService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/interesses", produces = MediaType.APPLICATION_JSON_VALUE
)
public class InteresseSaborV1RestController {
    InteresseSaborCriaService interesseSaborCriaService;

    InteresseSaborExcluirService interesseSaborExcluirService;

    InteresseSaborBuscaService interesseSaborBuscaService;

    @PostMapping("/post")
    ResponseEntity<?> criarInteresse(
            @RequestBody @Valid InteresseSaborPostPutRequestDTO interesseSaborPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interesseSaborCriaService.criar(interesseSaborPostPutRequestDTO));
    }

    @GetMapping("/get-all")
    ResponseEntity<?> buscarTodosInteresses(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(interesseSaborBuscaService.buscar());
    }

    @DeleteMapping("{id}/delete")
    ResponseEntity<?> excluir(
            @PathVariable Long id
    ){
        interesseSaborExcluirService.excluir(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}