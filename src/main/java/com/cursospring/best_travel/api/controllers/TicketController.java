package com.cursospring.best_travel.api.controllers;

import com.cursospring.best_travel.api.models.request.TicketRequest;
import com.cursospring.best_travel.api.models.responses.TicketResponse;
import com.cursospring.best_travel.infraestructure.abstract_services.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.create(request));
    }
}
