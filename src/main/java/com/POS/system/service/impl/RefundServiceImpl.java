package com.POS.system.service.impl;

import com.POS.system.Mapper.RefundMapper;
import com.POS.system.Model.Branch;
import com.POS.system.Model.Order;
import com.POS.system.Model.Refund;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.RefundDto;
import com.POS.system.repository.OrderRepository;
import com.POS.system.repository.RefundRepository;
import com.POS.system.service.RefundService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    @Override
    public RefundDto createRefund(RefundDto refundDto) throws Exception {
        User cashier=userService.getCurrentUser();
        Order order=orderRepository.findById(refundDto.getOrderId()).orElseThrow(()->new Exception("order not found"));
        Branch branch=order.getBranch();

        Refund refund=Refund.builder()
                .cashier(cashier)
                .branch(branch)
                .order(order)
                .reason(refundDto.getReason())
                .amount(refundDto.getAmount())
                .build();

        Refund savedRefund=refundRepository.save(refund);
        return RefundMapper.toDto(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefunds() {
        return refundRepository.findAll().stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId){
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime start, LocalDateTime end) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId,start,end).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId){
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RefundDto getRefundById(Long id) throws Exception {
        return RefundMapper.toDto(refundRepository.findById(id).orElseThrow(()->new Exception("refund not found")));
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);

    }
}
