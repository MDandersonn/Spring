package org.africalib.gallery.africabackend.repository;

import org.africalib.gallery.africabackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
