package lk.ijse.dep.service.impl;

import lk.ijse.dep.dto.ItemDTO;
import lk.ijse.dep.entity.Item;
import lk.ijse.dep.repository.ItemRepository;
import lk.ijse.dep.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDTO> findAllItems() {
        var items = itemRepository.findAll();
        var dtos = new ArrayList<ItemDTO>();
        items.forEach(i ->{
            dtos.add(new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()));
        });
        return dtos;
    }

    @Override
    public ItemDTO findItem(String code) {
        var item = itemRepository.findById(code).get();
        return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public void saveItem(String code, ItemDTO itemDTO) {
        if (!code.equals(itemDTO.getCode())){
            throw new RuntimeException("Item Codes are mismatched");
        }
        itemRepository.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO) {
        if (itemRepository.existsById(code)){
            saveItem(code,itemDTO);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteItem(String code) {
        itemRepository.deleteById(code);
        return true;
    }

    @Override
    public long ItemsCount() {
        return itemRepository.count();
    }
}
