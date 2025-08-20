package com.learning.accounts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public class BookingSlotRepository {
    Map<String, List<int[]>> bookingSlotsRepo=new HashMap<>();

    public void bookSlot(String storeId,int[]newSlot){
        bookingSlotsRepo.putIfAbsent(storeId,new ArrayList<>(List.of(newSlot)));

    }
    public Map<String,List<int[]>> getBookingSlotsRepo(){
        return bookingSlotsRepo;
    }

}
