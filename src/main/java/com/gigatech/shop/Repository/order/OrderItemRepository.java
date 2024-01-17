package com.gigatech.shop.Repository.order;
// Repozytorium OrderItemRepository odpowiedzialne za operacje na encji OrderItem.
import com.gigatech.shop.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Brak dodatkowych metod specyficznych dla repozytorium OrderItemRepository.
    // Korzysta z gotowych operacji CRUD dla encji OrderItem.
}
