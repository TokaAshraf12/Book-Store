import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CartService } from "../cart/cart.service";
import { CheckoutRequest } from "../dto/data";
import { AuthService } from "../services/auth/auth.service";
import { CheckoutService } from "../services/checkout/checkout.service";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"],
})
export class CheckoutComponent implements OnInit {
  rExp: RegExp =
    /(^4[0-9]{12}(?:[0-9]{3})?$)|(^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$)|(3[47][0-9]{13})|(^3(?:0[0-5]|[68][0-9])[0-9]{11}$)|(^6(?:011|5[0-9]{2})[0-9]{12}$)|(^(?:2131|1800|35\d{3})\d{11}$)/g;
  validCard: boolean = false;
  constructor(
    private cartService: CartService,
    private authService: AuthService,
    private checkoutService: CheckoutService,
    private router: Router
  ) {}

  ngOnInit(): void {}
  checked: boolean = false;

  checkout() {
    const cartProducts: CheckoutRequest = {
      customer: this.authService.getUserEmail(),
      books: this.cartService.getCheckoutProducts(),
    };
    this.checkValidation();
    if (this.validCard) {
      console.log("Cart Products To Be Checkout => ", cartProducts);
      this.checkoutService.sendCheckoutProducts(cartProducts).subscribe(() => {
        console.log("We Checkout, Babe .. HeHe");
        this.cartService.clearCart();
        this.checked = true;
      });
    } else {
      let ele = document.getElementById("Number-Valid");
      if (ele) {
        ele.style.display = "block";
        ele.style.color = "red";
        ele.style.paddingLeft = "180px";
      }
    }
  }

  getInformation() {
    if (
      (<HTMLInputElement>document.getElementById("fname")).value === "" ||
      (<HTMLInputElement>document.getElementById("email")).value === "" ||
      (<HTMLInputElement>document.getElementById("adr")).value === "" ||
      (<HTMLInputElement>document.getElementById("city")).value === "" ||
      (<HTMLInputElement>document.getElementById("state")).value === "" ||
      (<HTMLInputElement>document.getElementById("zip")).value === "" ||
      (<HTMLInputElement>document.getElementById("cname")).value === "" ||
      (<HTMLInputElement>document.getElementById("ccnum")).value === "" ||
      (<HTMLInputElement>document.getElementById("expmonth")).value === "" ||
      (<HTMLInputElement>document.getElementById("expyear")).value === "" ||
      (<HTMLInputElement>document.getElementById("cvv")).value === ""
    ) {
      return false;
    } else {
      return true;
    }
  }

  backHome() {
    this.router.navigateByUrl("home", { state: { logged: true } });
  }
  checkValidation() {
    let ele = document.getElementById("Number-Valid");
    if (ele) ele.style.display = "none";
    this.validCard = this.rExp.test(
      (<HTMLInputElement>document.getElementById("ccnum")).value
    );
    console.log(this.validCard);
  }
}
