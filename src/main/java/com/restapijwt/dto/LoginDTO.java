package com.restapijwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    //    @NotEmpty  //
//    @NotBlank //
//    @Email(regexp = "(.+)@gmail\\.com$", message = "Email emas!") //sabr tilayman gmail.com tekshirildi
//    @Email
    @NotBlank(message = "Nomini kiritish shart")
    //String lar un
    @Size(min = 3, message = "Nomi 3tadn kam bo'lmasin")
    private String name;


    //field berilmasayam bo'sh berilsayam ishladi
    @NotBlank(message = "Parolni togri kiriting") @Length(min = 2, message = "Uzunlik man aytgandek emas")
//    @Pattern(regexp = "\\+998[0-9]{2}", message = "Topolmadim!")
    private String password;
}
