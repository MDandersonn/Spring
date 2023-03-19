package org.africalib.gallery.africabackend.controller;

import org.africalib.gallery.africabackend.entity.Cart;
import org.africalib.gallery.africabackend.entity.Item;
import org.africalib.gallery.africabackend.repository.CartRepository;
import org.africalib.gallery.africabackend.repository.ItemRepository;
import org.africalib.gallery.africabackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/api/cart/items")//특정사용자의 장바구니 아이템목록을 가져오는거
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);//여기까지하면 아이템아이디나 멤버아이디만 가져올수있음
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).collect(Collectors.toList());//Cart에서 아이템아이디를 추출하여 리스트형태로
        List<Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/cart/items/{itemId}")//장바구니에 담는거
    public  ResponseEntity pushCartItem(@PathVariable("itemId") int itemId,
                                   @CookieValue(value="token",required = false) String token){
        //특정 아이템을 담았을때
        if(!jwtService.isValid(token) ){ //토큰이 비어있거나 만료됬거나 토큰값이 유효하지않으면
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId= jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        if(cart ==null){//장바구니에 추가한적이 없다면
            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }
        return new ResponseEntity<>(HttpStatus.OK);//이미 장바구니에 있으면
    }
    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
