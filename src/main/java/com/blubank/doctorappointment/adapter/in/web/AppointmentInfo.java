package com.blubank.doctorappointment.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentInfo {
    @NotNull
    private Long timeId;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
}