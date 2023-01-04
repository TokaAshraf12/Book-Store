import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { UserService } from '../services/user/user.service';
import { PublisherService } from '../services/publisher/publisher.service';
import { PublisherRequest } from '../dto/data';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{
  ordersList:any;
  loggin!: boolean;
  isManager!: Boolean;
  disable: boolean=true;
  valid:boolean= true;
  constructor(
    private authService: AuthService,
    private publisherService: PublisherService
  ) {}
  ngOnInit(): void {
    this.loggin = this.authService.isLoggedIn();
    this.showOrders();
    if (this.loggin) this.isManagerSubscribe();
  } 

  private showOrders() {
    this.ordersList=[{ orderId : 12,
      ISBN:"1452",
      bookTitle:"bassant ela el haweya",
      Quantity:"2000"
    },{
      orderId : 567,
      ISBN:"1452",
      bookTitle:"Fareeda yarab tenga7",
      Quantity:"2500"
    },{
      orderId : 478,
      ISBN:"1552",
      bookTitle:"yatra ya masey",
      Quantity:"20"
    },
    {
      orderId : 785,
      ISBN:"342",
      bookTitle:"l;ksf",
      Quantity:"882"
    }]
  }
 
  public confirm(event: MouseEvent, id:any) {
    console.log("off ba2a: "+event+" id: "+id);

    (event.target as HTMLButtonElement).disabled = true;
  }


  private isManagerSubscribe() {
    this.authService.isManager().subscribe((res) => {
      this.isManager = res;
      console.log('Is Manager => ' + this.isManager);
    });
  }
}
