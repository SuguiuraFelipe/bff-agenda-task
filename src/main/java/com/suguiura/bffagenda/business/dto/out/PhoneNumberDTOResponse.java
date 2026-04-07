package com.suguiura.bffagenda.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberDTOResponse {

    private Long id;
    private String number;
}
