package org.africalib.gallery.africabackend.controller;

import org.africalib.gallery.africabackend.entity.Item;
import org.africalib.gallery.africabackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/api/items")
    public List<Item> getItems(){
//        List<String> items =new ArrayList<>();
//        items.add("alpha");
//        items.add("beta");
//        items.add("gamma");
        System.out.println("컨트롤러 매서드");
        List<Item> items = itemRepository.findAll();
        return items;
    }

}
