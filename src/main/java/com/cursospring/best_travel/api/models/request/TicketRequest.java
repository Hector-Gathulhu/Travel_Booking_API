package com.cursospring.best_travel.api.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//Request DTOs are made to request some information to the user and execute any of the CRUD requests.
//In this example we request to the user to give his ID and a ID fly in order to CRUD a ticket
//Example, we'll use TicketResponse in a Create request. Requesting just below two parameters and avoid any other unnecessary parameter
public class TicketRequest implements Serializable {

    private String idClient;    
    private Long idFly;
}
