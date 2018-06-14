package com.juanmanuelruizfernandez.service;

import com.juanmanuelruizfernandez.model.Ticket;

import java.io.IOException;
import java.util.List;

public interface StoreService {

	List<Ticket > getStoreTickets( String storeID ) throws IOException;
}
