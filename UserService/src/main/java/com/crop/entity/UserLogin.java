package com.crop.entity;








import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@ToString
public class UserLogin{
   
    @Email(message = "Plz Enter Correct Email Address...")
    private String emailId;
    @Size(min=8,max=8,message="Plz Enter Correct password")
    private String password;


   public UserLogin (UserLogin userLoginDTO) {
    this.emailId=userLoginDTO.getEmailId();
    this.password=userLoginDTO.getPassword();
}
}