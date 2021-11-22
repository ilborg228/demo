package com.example.demo.view.credit;

import com.example.demo.entity.CreditEntity;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.example.demo.entity.BankEntity;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CreditForm extends FormLayout {

    private CreditEntity credit;

    TextField percent = new TextField("Percent");
    TextField limit = new TextField("Limit");
    ComboBox<BankEntity> bank = new ComboBox<>("Bank");
    Binder<CreditEntity> binder = new BeanValidationBinder<>(CreditEntity.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public CreditForm(List<BankEntity> banks) {
        addClassName("credit-form");
        binder.forField ( this.percent )
                .withNullRepresentation ( "" )
                .withConverter ( new StringToDoubleConverter( Double.valueOf ( 0 ), "double only" ) )
                .bind ( CreditEntity:: getPercent, CreditEntity:: setPercent );
        binder.forField ( this.limit )
                .withNullRepresentation ( "" )
                .withConverter ( new StringToDoubleConverter( Double.valueOf ( 0 ), "double only" ) )
                .bind ( CreditEntity:: getLimit, CreditEntity:: setLimit );
        binder.bindInstanceFields(this);
        bank.setItems(banks);
        bank.setItemLabelGenerator(BankEntity::getName);
        add(percent, limit,bank,createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, credit)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setCredit(CreditEntity credit) {
        this.credit = credit;
        binder.readBean(credit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(credit);
            fireEvent(new SaveEvent(this, credit));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class CreditFormEvent extends ComponentEvent<CreditForm> {
        private CreditEntity credit;

        protected CreditFormEvent(CreditForm source, CreditEntity credit) {
            super(source, false);
            this.credit = credit;
        }

        public CreditEntity getCredit() {
            return credit;
        }
    }

    public static class SaveEvent extends CreditFormEvent {
        SaveEvent(CreditForm source, CreditEntity credit) {
            super(source, credit);
        }
    }

    public static class DeleteEvent extends CreditFormEvent {
        DeleteEvent(CreditForm source, CreditEntity credit) {
            super(source, credit);
        }
    }

    public static class CloseEvent extends CreditFormEvent {
        CloseEvent(CreditForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
