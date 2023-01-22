package shop.mtcoding.buyer4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer4.model.Product;
import shop.mtcoding.buyer4.model.ProductRepository;
import shop.mtcoding.buyer4.model.Purchase;
import shop.mtcoding.buyer4.model.PurchaseRepository;
import shop.mtcoding.buyer4.model.User;

@Service
public class PurchaseService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Transactional
    public int 구매하기(int principalId, int productId, int count) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }

        if (count > product.getQty()) {
            return -1;
        }

        int res = productRepository.updateById(product.getName(), product.getPrice(), product.getQty() - count,
                productId);
        if (res != 1) {
            return -1;
        }

        int res2 = purchaseRepository.insert(principalId, productId, count);
        if (res2 != 1) {
            return -1;
        }

        return 1;
    }

    @Transactional
    public int 삭제하기(int purchaseId) {
        // 리스트에 있는지 확인
        Purchase purchase = purchaseRepository.findById(purchaseId);
        if (purchase == null) {
            return -1;
        }

        // 재고반영
        int productId = purchase.getProductId();
        Product product = productRepository.findById(productId);
        int res = productRepository.updateById(product.getName(), product.getPrice(),
                product.getQty() + purchase.getCount(),
                productId);
        if (res != 1) {
            return -1;
        }

        // 상품목록에서 삭제
        int res2 = purchaseRepository.deleteById(purchaseId);
        if (res2 != 1) {
            return -1;
        }

        return 1;

    }
}
