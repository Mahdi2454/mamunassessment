import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import {MenuItem} from 'primeng-lts/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private keycloakService: KeycloakService) { }
  items: MenuItem[] = [];

    ngOnInit() {
        this.items = [
            {label: 'Dataflow', routerLink: ['/admin']},
            {label: 'Administration', routerLink: ['/manager']},
            {label: 'Logout', icon: 'pi pi-fw pi-power-off', command: (event) => {
           this.keycloakService.logout();
          }}

        ];
    }

}
