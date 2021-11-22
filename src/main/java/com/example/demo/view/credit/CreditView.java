package com.example.demo.view.credit;

import com.example.demo.view.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@Route(value="credits", layout = MainLayout.class)
@PageTitle("CRM")
@PermitAll
public class CreditView extends VerticalLayout {
}
