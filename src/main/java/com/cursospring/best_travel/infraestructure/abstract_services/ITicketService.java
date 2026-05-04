package com.cursospring.best_travel.infraestructure.abstract_services;

import com.cursospring.best_travel.api.models.request.TicketRequest;
import com.cursospring.best_travel.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{
    BigDecimal findPrice(Long flyId);

}
