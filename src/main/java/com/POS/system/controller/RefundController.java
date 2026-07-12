package com.POS.system.controller;

import com.POS.system.payload.dto.RefundDto;
import com.POS.system.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws Exception {
        RefundDto refund =refundService.createRefund(refundDto);
        return ResponseEntity.ok(refund);
    }

    @GetMapping
    public ResponseEntity<List<RefundDto>> getALlRefunds() throws Exception {
        List<RefundDto> refunds =refundService.getAllRefunds();
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<RefundDto>> getRefundsByCashier(@PathVariable Long id) throws Exception {
        List<RefundDto> refunds =refundService.getRefundByCashier(id);
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<RefundDto>> getRefundsByBranch(@PathVariable Long id) throws Exception {
        List<RefundDto> refunds =refundService.getRefundByBranch(id);
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/shift/{id}")
    public ResponseEntity<List<RefundDto>> getRefundsByShift(@PathVariable Long id) throws Exception {
        List<RefundDto> refunds =refundService.getRefundByShiftReport(id);
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/cashier/{cashierId}/range/")
    public ResponseEntity<List<RefundDto>> getRefundsByCashierAndDateRange(@PathVariable Long cashierId,
                                                                           @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                           @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime end) throws Exception {
        List<RefundDto> refunds =refundService.getRefundByCashierAndDateRange(cashierId,start,end);
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundsById(@PathVariable Long id) throws Exception {
        RefundDto refund =refundService.getRefundById(id);
        return ResponseEntity.ok(refund);
    }
}
