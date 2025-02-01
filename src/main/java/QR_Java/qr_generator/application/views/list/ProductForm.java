package QR_Java.qr_generator.application.views.list;

import QR_Java.qr_generator.application.data.Product;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.function.Consumer;

@UIScope
public class ProductForm extends FormLayout {
    private TextField name = new TextField("Product Name");
    private TextField batchNo = new TextField("Batch No");
    private TextField serialNo = new TextField("Serial No");
    private NumberField price = new NumberField("Price");
    private DatePicker expiryDate = new DatePicker("Expiry Date");

    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Delete");
    private Button cancelButton = new Button("Cancel");

    private Binder<Product> binder = new Binder<>(Product.class);
    private Product product;

    private Consumer<Product> saveListener;
    private Consumer<Product> deleteListener;

    public ProductForm() {
        configureBinder();
        add(configureInput(), configureButtons());
        clearForm();
    }

    private void configureBinder() {
        binder.bindInstanceFields(this);
        binder.forField(name).asRequired("Product Name is required").bind(Product::getName, Product::setName);
        binder.forField(batchNo).asRequired("Batch No is required").bind(Product::getBatchNo, Product::setBatchNo);
        binder.forField(serialNo).asRequired("Serial No is required").bind(Product::getSerialNo, Product::setSerialNo);
        binder.forField(price).asRequired("Price is required").bind(Product::getPrice, Product::setPrice);
        binder.forField(expiryDate).asRequired("Expiry Date is required").bind(Product::getExpiryDate, Product::setExpiryDate);
    }

    private HorizontalLayout configureButtons() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);

        saveButton.addClickListener(event -> saveProduct());
        deleteButton.addClickListener(event -> deleteProduct());
        cancelButton.addClickListener(event -> clearForm());

        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private HorizontalLayout configureInput() {
        return new HorizontalLayout(
                new VerticalLayout(name, batchNo, serialNo),
                new VerticalLayout(price, expiryDate)
        );
    }

    public void setProduct(Product product) {
        this.product = product;
        binder.readBean(product);
    }

    private void saveProduct() {
        if (product == null) {
            product = new Product();
        }

        if (binder.writeBeanIfValid(product)) {
            saveListener.accept(product);
            clearForm();
        }
    }

    private void deleteProduct() {
        if (product != null && deleteListener != null) {
            deleteListener.accept(product);
            clearForm();
        }
    }

    private void clearForm() {
        binder.readBean(new Product());
        product = null;
    }

    public void setSaveListener(Consumer<Product> listener) {
        this.saveListener = listener;
    }

    public void setDeleteListener(Consumer<Product> listener) {
        this.deleteListener = listener;
    }
}