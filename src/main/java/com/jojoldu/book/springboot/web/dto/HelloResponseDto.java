package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Getter
//@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;

    public HelloResponseDto(String val1, int val2){
        name = val1;
        amount = val2;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

}
