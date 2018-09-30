package lk.ijse.dep.controller;

import lk.ijse.dep.dto.ItemDTO;
import lk.ijse.dep.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    private String handleMyExceptions(RuntimeException ex){
//        return ex.getMessage();
//    }

    @GetMapping
    public List<ItemDTO> findAllItems(){
        return itemService.findAllItems();
    }

    @GetMapping("/{code}")
    public ItemDTO findItemById(@PathVariable("code") String code){
        return itemService.findItem(code);
    }

    @PutMapping("/{code}")
    public void saveItem(@PathVariable("code") String code,@RequestBody ItemDTO itemDTO){
        itemService.saveItem(code,itemDTO);
    }

    @PostMapping
    public boolean updatItem(@PathVariable("code") String code, ItemDTO itemDTO){
        return itemService.updateItem(code, itemDTO);
    }

    @DeleteMapping("/{code}")
    public boolean deleteItem(@PathVariable("code") String code){
        return itemService.deleteItem(code);
    }

    @GetMapping(params = {"action=count"})
    public long getCustomersCount(){
        return itemService.ItemsCount();
    }

}
