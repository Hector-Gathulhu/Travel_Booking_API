package com.cursospring.best_travel.infraestructure.services;

import com.cursospring.best_travel.api.models.request.TicketRequest;
import com.cursospring.best_travel.api.models.responses.TicketResponse;
import com.cursospring.best_travel.infraestructure.abstract_services.ITicketService;

import java.util.UUID;

public class TicketService implements ITicketService {
    @Override
    public TicketResponse create(TicketRequest request) {
        return null;
    }

    @Override
    public TicketResponse read(UUID uuid) {
        return null;
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
