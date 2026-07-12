package com.POS.system.Mapper;

import com.POS.system.Model.Refund;
import com.POS.system.payload.dto.RefundDto;

public class RefundMapper {
    public static RefundDto toDto (Refund refund) {
return RefundDto.builder()
        .id(refund.getId())
        .order(OrderMapper.toDto(refund.getOrder()))
        .reason(refund.getReason())
        .amount(refund.getAmount())
        .cashierName(refund.getCashier().getFullName())
        .branchId(refund.getBranch().getId())
        .shiftReportId(refund.getShiftReport()!=null?refund.getShiftReport().getId():null)
        .paymentType(refund.getPaymentType())
        .createdAt(refund.getCreatedAt())
        .build();
    }
}
