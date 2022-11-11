package com.coditas.EventManagement.controller;


import com.coditas.EventManagement.entities.EventCategory;
import com.coditas.EventManagement.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {

     @Autowired
     EventCategoryService eventCategoryService;


     @PostMapping("/createCategory")
     public ResponseEntity createTool(@RequestBody EventCategory eventCategory) {
          try {
               return new ResponseEntity(Optional.of(eventCategoryService.createEventCategory(eventCategory)), HttpStatus.CREATED);
          } catch (Exception exception) {
               return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
          }
     }


     @DeleteMapping("/deleteCategory/{categoryId}")
     public ResponseEntity deleteEventCategory(@PathVariable String categoryId) {
          try {
               eventCategoryService.deleteEventCategory(Long.parseLong(categoryId));
               return new ResponseEntity(HttpStatus.OK);
          } catch (Exception exception) {
               return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
          }
     }

     @PutMapping("/updateEventCategory")
     public ResponseEntity updateEventCategory(@RequestBody EventCategory eventCategory) {
          try {
               return new ResponseEntity(Optional.of(eventCategoryService.updateEventCategory(eventCategory)), HttpStatus.OK);
          } catch (Exception exception) {
               return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
          }
     }

     @GetMapping("/getEventCategories")
     public ResponseEntity getEventCategory() {
          try {
               List<EventCategory> eventCategories = eventCategoryService.fetchAllEventCategory();
               if (eventCategories.size() > 0) {
                    return new ResponseEntity(eventCategories, HttpStatus.OK);
               } else {
                    return new ResponseEntity(null, HttpStatus.NO_CONTENT);
               }
          } catch (Exception exception) {
               return new ResponseEntity(HttpStatus.UNAUTHORIZED);
          }
     }





}
