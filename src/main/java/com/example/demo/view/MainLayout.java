package com.example.demo.view;

import com.example.demo.view.CreditRegistration.CreditRegistrationForm;
import com.example.demo.view.client.ClientListView;
import com.example.demo.view.credit.CreditView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink clientListLink = new RouterLink("Clients List", ClientListView.class);
        RouterLink creditListLink = new RouterLink("Credits List", CreditView.class);
        RouterLink creditRegistrationLink = new RouterLink("Credit Registration", CreditRegistrationForm.class);
        clientListLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                clientListLink,
                creditListLink,
                creditRegistrationLink
        ));
    }
}