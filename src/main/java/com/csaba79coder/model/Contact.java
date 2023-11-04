package com.csaba79coder.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String name;
    private String phoneNumber;

    public static Contact createContact(String name, String phoneNumber) {
        return new Contact(name, phoneNumber);
    }
}
