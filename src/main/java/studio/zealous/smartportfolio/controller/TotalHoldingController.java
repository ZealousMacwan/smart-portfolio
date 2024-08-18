package studio.zealous.smartportfolio.controller;

import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.zealous.smartportfolio.GetAllHoldings;
import studio.zealous.smartportfolio.dto.restresponse.holding.TotalHoldingResponseDto;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;
import studio.zealous.smartportfolio.service.TotalHoldingService;
import studio.zealous.smartportfolio.util.converter.TotalHoldingConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/totalholding")
public class TotalHoldingController {

    @Autowired
    private GetAllHoldings getAllHoldings;

    @Autowired
    private TotalHoldingService totalHoldingService;

    @Autowired
    private TotalHoldingConverter totalHoldingConverter;

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<TotalHoldingResponseDto>> getTotalHoldings(@RequestParam String angelUserId) throws SmartAPIException, IOException {
        List<TotalHolding> totalHolding = totalHoldingService.getTotalHolding(angelUserId);

        List<TotalHoldingResponseDto> totalHoldingResponseDtos = totalHolding.stream().map(holding -> totalHoldingConverter.toResponseDto(holding)).toList();

        return ResponseEntity.ok(totalHoldingResponseDtos);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> testEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        return ResponseEntity.ok(response);
    }
}
