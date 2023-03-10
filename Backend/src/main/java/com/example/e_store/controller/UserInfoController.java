package com.example.e_store.controller;

import com.example.e_store.dto.BookSpecificDetails;
import com.example.e_store.dto.ProfileInfoResponse;
import com.example.e_store.dto.UserEdit;
import com.example.e_store.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/profile/{email}"
    )
    public ResponseEntity<ProfileInfoResponse> getUserInfo(@PathVariable String email) {
        log.info("Getting User Info {}" + email);
        return ResponseEntity.ok().body(userInfoService.getUserInfo(email));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/owned/products/{email}"
    )
    public ResponseEntity<List<BookSpecificDetails>> getUserOwnerProducts(@PathVariable String email) {
        log.info("Getting User Owned Products");
        return ResponseEntity.ok().body(userInfoService.getUserOwnerProducts(email));
    }

    /*
     * Comment It For Now :)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/purchased/products/{email}"
    )
    public ResponseEntity<List<BookSpecificDetails>> getUserPurchasedProducts(@PathVariable String email) {
        log.info("Getting User Purchased Products");
        return ResponseEntity.ok().body(userInfoService.getUserPurchasedProducts(email));
    }
    */

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/edit"
    )
    public ResponseEntity<?> editUser(@RequestBody UserEdit userEdit) {
        userInfoService.editUserInfo(userEdit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/promote/{email}"
    )
    public ResponseEntity<?> promoteUser(@PathVariable String email) {
        log.info("yarab: el sabr w se7a w ra7ma w eny ang7 el sandy ba2a eh el araf dah {}", email);
        userInfoService.promoteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
