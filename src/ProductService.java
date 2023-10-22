import java.util.ArrayList;
import java.util.List;

/*
��ǰ ���� ----------------------------------------------------------------
*/
interface PdInterface {
    List<Product> allProductList = CacheData.allProductList;
    List<MasterRc> masterRcList = CacheData.masterRcList;

    List<Product> getList(ProductType productType);
    List<MasterRc> getListMasterRc(ProductType productType);
}

// �Է¹��� ProductType�� �������� ����Ʈ ����
class ProductService implements PdInterface {
    @Override
    public List<Product> getList(ProductType productType) {
        List<Product> result = new ArrayList<>();

        for (Product product : allProductList) {
            if (product.getType().equals(productType)               // �ش��ϴ� productType
                    && product.getP_count()>product.getP_stock()    // ������ ����� ���� ��
                    && product.getSaleflag()                       // �Ǹż��� true
            ) {
                result.add(product);
            }
        }

        return result;
    }

    @Override
    public List<MasterRc> getListMasterRc(ProductType productType) {
        List<MasterRc> result = new ArrayList<>();

        for (MasterRc masterRc : masterRcList) {
            if (masterRc.getType().equals(productType)               // �ش��ϴ� productType
                    && masterRc.getSaleflag()                       // �Ǹż��� true
            ) {
                result.add(masterRc);
            }
        }

        return result;
    }
}