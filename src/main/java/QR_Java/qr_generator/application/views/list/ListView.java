package QR_Java.qr_generator.application.views.list;

import QR_Java.qr_generator.application.data.Product;
import QR_Java.qr_generator.application.service.ProductService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PageTitle("Product Registration")
@UIScope
public class ListView extends VerticalLayout {
    private Grid<Product> productGrid = new Grid<>(Product.class);
    private ProductForm form;
    private final ProductService productService;

    @Autowired
    public ListView(ProductService productService) {
        this.productService = productService;
        configureGrid();
        configureForm();

        setSizeFull();
        add(getViewContent());

        updateGrid();
    }

    private HorizontalLayout getViewContent() {
        HorizontalLayout layout = new HorizontalLayout(form, productGrid);
        layout.setWidthFull();
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }

    private void configureGrid() {
        productGrid.setSizeFull();
        productGrid.setColumns("name", "batchNo", "serialNo", "price", "expiryDate");

        productGrid.asSingleSelect().addValueChangeListener(event -> form.setProduct(event.getValue()));
    }

    private void configureForm() {
        form = new ProductForm();

        form.setSaveListener(this::saveProduct);
        form.setDeleteListener(this::deleteProduct);

        form.setWidth("30rem");
    }

    private void saveProduct(Product product) {
        if (!productService.getAllProducts().contains(product)) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
        updateGrid();
    }

    private void deleteProduct(Product product) {
        productService.deleteProduct(product);
        updateGrid();
    }

    private void updateGrid() {
        productGrid.setItems(productService.getAllProducts());
    }
}