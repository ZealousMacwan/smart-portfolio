package studio.zealous.smartportfolio.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean status;
    private String message;
    private Object data; // This can be null or a specific type depending on your needs
}