package lk.ijse.dep.service;

import lk.ijse.dep.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> findAllItems();

    ItemDTO findItem(String code);

    void saveItem(String code, ItemDTO itemDTO);

    boolean updateItem(String code, ItemDTO itemDTO);

    boolean deleteItem(String code);

    long ItemsCount();
    
}
