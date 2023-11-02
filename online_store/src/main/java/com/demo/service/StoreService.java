package com.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.demo.entities.Store;
import com.demo.model.StoreDTO;

public interface StoreService {
	StoreDTO creatStore(Store store);

	StoreDTO updateStore(@Valid int sid, Store store);

	List<StoreDTO> getAllStore();

	StoreDTO getStoreById(int id);

//	String assignProductToStore(int pid, int sid);

	String deleteStore(int id);

}
