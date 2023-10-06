package com.no3.game.repository;

import com.no3.game.entity.Item;
import com.no3.game.entity.ItemImg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;


@SpringBootTest
public class ItemRepoTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertItem() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            Item item = Item.builder()
                    .title("상품명...." +i)
                    .price(222)
                    .detail("상새설명"+i)
                    .build();
            System.out.println("------------------------------------------");
            itemRepository.save(item);
            int count = (int)(Math.random() * 5) + 1;
            for(int j = 0; j < count; j++){
                ItemImg itemImg = ItemImg.builder()
                        .uuid(UUID.randomUUID().toString())
                        .item(item)
                        .imgName("test"+j+".jpg").build();
                itemImageRepository.save(itemImg);
            }
            System.out.println("===========================================");
        });
    }

    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "id"));

        Page<Object[]> result = itemRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll() {

        List<Object[]> result = itemRepository.getItemWithAll(4L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }

}
