package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demo.entities.Product;
import com.demo.entities.Store;
import com.demo.model.StoreDTO;
import com.demo.repositories.ProductRepository;
import com.demo.repositories.StoreRepository;
import com.demo.service.StoreService;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepository;
	
	@Override
    public StoreDTO creatStore(Store store) {
        Store savedStore = storeRepository.save(store);
        return convertToDTO(savedStore);
    }

    @Override
    public StoreDTO updateStore(int sid, Store store) {
        Optional<Store> existingStore = storeRepository.findById(sid);
        if (existingStore.isPresent()) {
            Store updatedStore = existingStore.get();
            updatedStore.setStoreName(store.getStoreName());
            updatedStore.setAddress(store.getAddress());
            updatedStore.setProducts(store.getProducts());
            Store savedStore = storeRepository.save(updatedStore);
            return convertToDTO(savedStore);
        }
        return null;
    }

    @Override
    public List<StoreDTO> getAllStore() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override  
    public StoreDTO getStoreById(int id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(this::convertToDTO).orElse(null);
    }

    @Override
    public String deleteStore(int id) {
        storeRepository.deleteById(id);
        return "Store deleted successfully.";
    }

    private StoreDTO convertToDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(store.getStoreId());
        storeDTO.setStoreName(store.getStoreName());
        storeDTO.setAddress(store.getAddress());
        storeDTO.setProducts(store.getProducts());
        return storeDTO;
    }
	
	

}
