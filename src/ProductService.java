import java.util.ArrayList;
import java.util.List;

/*
��ǰ ���� ----------------------------------------------------------------
*/
interface PdInterface {
    List<Product> productList = CacheData.allProductList;

    List<Product> getList(ProductType productType);
}

// �Է¹��� ProductType�� �������� ����Ʈ ����
class ProductService implements PdInterface {
    @Override
    public List<Product> getList(ProductType productType) {
        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if (product.getType().equals(productType)               // �ش��ϴ� productType
                    && product.getP_count()>product.getP_stock()    // ������ ����� ���� ��
                    && product.getSaleflag()                       // �Ǹż��� true
            ) {
                result.add(product);
            }
        }

        return result;
    }
}