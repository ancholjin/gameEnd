package com.no3.game.repository;

import com.no3.game.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemImageRepository  extends JpaRepository<ItemImg, Long> {
}
