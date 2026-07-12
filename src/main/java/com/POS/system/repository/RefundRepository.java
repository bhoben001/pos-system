package com.POS.system.repository;

import com.POS.system.Model.Refund;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByCashierIdAndCreatedAtBetween(Long cashierId,
                                                  LocalDateTime from,
                                                  LocalDateTime to);

    List<Refund> findByCashierId(Long cashierId);

    List<Refund> findByShiftReportId(Long cashierId);

    List<Refund> findByBranchId(Long branchId);



}
