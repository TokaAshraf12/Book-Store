import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';
import { PublisherService } from '../services/publisher/publisher.service';
import { UserService } from '../services/user/user.service';
import { PublisherRequest } from '../dto/data';
import { identifierName } from '@angular/compiler';
@Component({
  selector: 'app-add-publisher',
  templateUrl: './add-publisher.component.html',
  styleUrls: ['./add-publisher.component.css']
})
export class AddPublisherComponent {
  constructor(
    private publisherService: PublisherService,
    private router: Router,
    private authService: AuthService
  ) {}
  public addPublisher() {
    const publisherName = (<HTMLInputElement>document.getElementById('Name')).value;
    const address = (<HTMLInputElement>document.getElementById('Address')).value;
    const phoneNumber = (<HTMLInputElement>document.getElementById('num')).value;
    const publisher: PublisherRequest = {
      publisherName: publisherName,
      address: address,
      phoneNumber: phoneNumber,
    };

    };

}
