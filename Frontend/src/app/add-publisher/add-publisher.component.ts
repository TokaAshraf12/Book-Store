import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { PublisherService } from "../services/publisher/publisher.service";
import { PublisherRequest } from "../dto/data";
@Component({
  selector: "app-add-publisher",
  templateUrl: "./add-publisher.component.html",
  styleUrls: ["./add-publisher.component.css"],
})
export class AddPublisherComponent {
  constructor(private publisherService: PublisherService, private router: Router) {}

  public addPublisher() {
    const publisherName = (<HTMLInputElement>document.getElementById("Name"))
      .value;
    const address = (<HTMLInputElement>document.getElementById("Address"))
      .value;
    const phoneNumber = (<HTMLInputElement>document.getElementById("num"))
      .value;
    const publisher: PublisherRequest = {
      name: publisherName,
      address: address,
      phone: phoneNumber,
    };

    this.publisherService.addPublisher(publisher).subscribe(
      () => {
        console.log("Hello Success!!");
        this.router.navigateByUrl("/home");
      }
    )
  }
}
