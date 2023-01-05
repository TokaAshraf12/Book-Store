import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { AuthService } from "../services/auth/auth.service";
import { PublisherService } from "../services/publisher/publisher.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-publisher-list",
  templateUrl: "./publisher-list.component.html",
  styleUrls: ["./publisher-list.component.css"],
})
export class PublisherListComponent implements OnInit {
  publisherList: any;
  loggin!: boolean;
  isManager!: Boolean;

  constructor(
    private authService: AuthService,
    private publisherService: PublisherService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loggin = this.authService.isLoggedIn();
    this.showPublishers();
    if (this.loggin) this.isManagerSubscribe();
  }

  private showPublishers() {
    this.publisherService.getAllProducts().subscribe((res) => {
      this.publisherList = res;
    });
    /*
    this.publisherList = [
      {
        publisherName: "Bassant yasser salah",
        address: "3 ibrahimia",
        phoneNumber: "01271400542",
      },
      {
        publisherName: "Fareeda Ragab",
        address: "59 edfou ibrahimia",
        phoneNumber: "01271400542",
      },
      {
        publisherName: "Yrab el rahma",
        address: "3 ibrahimia",
        phoneNumber: "01271442",
      },
    ];
    */
    console.log(this.publisherList);
  }

  private isManagerSubscribe() {
    this.authService.isManager().subscribe((res) => {
      this.isManager = res;
      console.log("Is Manager => " + this.isManager);
    });
  }
  backHome() {
    this.router.navigateByUrl("home", { state: { logged: true } });
  }
}
