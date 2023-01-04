import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { CartService } from "../cart/cart.service";
import { ProductService } from "../services/product/product.service";
import { Cart } from "../dto/data";
@Component({
  selector: "app-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.css"],
})
export class ProductComponent {
  isOwner: boolean = false;
  product: any;
  noPriceEditing: boolean = true;
  noInStockEditing: boolean = true;
  noCategoryEdit: boolean = true;
  noTitleEdit: boolean = true;
  noDescriptionEditing: boolean = true;
  noPublicationYearEdit: boolean = true;
  noPublisherEdit: boolean = true;
  noAuthorEdit: boolean = true;
  noIsbnEdit: boolean = true;

  toCart: Cart = {
    bookId: 0,
    title: "",
    isbn: "",
    author: "",
    publisher: "",
    publicationYear: "",
    price: 0,
    category: "",
    noOfCopies: 0,
    description: "",
    image: undefined,
    quantity: 0,
    totalPrice: 0,
  };

  constructor(
    private productService: ProductService,
    private router: Router,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.product = this.productService.getAllInfo();
    console.log(`Product All Info => ${this.product.authors}`);
    this.isOwner = this.product.isOwner;
  }

  editPrice() {
    this.noPriceEditing = false;
  }
  exitEditPrice() {
    this.noPriceEditing = true;
    this.product.price = (<HTMLInputElement>(
      document.getElementById("price_after_edit")
    )).value;
  }

  editInStock() {
    this.noInStockEditing = false;
  }
  exitEditInStock() {
    this.noInStockEditing = true;
    this.product.noOfCopies = (<HTMLInputElement>(
      document.getElementById("inStock_after_edit")
    )).value;
  }

  editCategory() {
    this.noCategoryEdit = false;
  }
  exitEditCategory() {
    this.noCategoryEdit = true;
    this.product.category = (<HTMLInputElement>(
      document.getElementById("cat_after_edit")
    )).value;
  }

  editTitle() {
    this.noTitleEdit = false;
  }
  exitEditTitle() {
    this.noTitleEdit = true;
    this.product.title = (<HTMLInputElement>(
      document.getElementById("title_after_edit")
    )).value;
  }

  editISBN() {
    this.noIsbnEdit = false;
  }
  exitEditIsbn() {
    this.noIsbnEdit = true;
    this.product.isbn = (<HTMLInputElement>(
      document.getElementById("isbn_after_edit")
    )).value;
  }

  editAuthor() {
    this.noAuthorEdit = false;
  }
  exitEditAuthor() {
    this.noAuthorEdit = true;
    console.log(
      "Authors => " +
        (<HTMLInputElement>document.getElementById("author_after_edit")).value
    );
    this.product.authors = (<HTMLInputElement>(
      document.getElementById("author_after_edit")
    )).value.split(",");
  }

  editPublisher() {
    this.noPublisherEdit = false;
  }
  exitEditPublisher() {
    this.noPublisherEdit = true;
    this.product.publisher = (<HTMLInputElement>(
      document.getElementById("publisher_after_edit")
    )).value;
  }

  editPublicationYear() {
    this.noPublicationYearEdit = false;
  }
  exitEditPublicationYear() {
    this.noPublicationYearEdit = true;
    this.product.publicationYear = (<HTMLInputElement>(
      document.getElementById("year_after_edit")
    )).value;
  }

  editDescription() {
    this.noDescriptionEditing = false;
  }
  exitEditDescription() {
    this.noDescriptionEditing = true;
    this.product.description = (<HTMLInputElement>(
      document.getElementById("desc_after_edit")
    )).value;
  }

  backHome() {
    this.router.navigateByUrl("home", { state: { logged: true } });
  }

  addToCart() {
    this.toCart = this.product;
    this.toCart.quantity = 1;
    this.toCart.totalPrice = this.toCart.quantity * this.toCart.price;

    this.cartService.storageCart(this.toCart);
    let ele = document.getElementById("to-cart");
    if (ele) {
      ele.style.display = "block";
      ele.style.color = "black";
      ele.style.paddingLeft = "10px";
    }
  }

  saveChanges() {
    console.log(`
    Product Info:
    isbn: ${this.product.isbn},
    title: ${this.product.title},
    authors: ${this.product.authors},
    publisher: ${this.product.publisher},
    price: ${this.product.price},
    category: ${this.product.category},
    description: ${this.product.description},
    publicationYear: ${this.product.publicationYear},
    noOfCopies: ${this.product.noOfCopies}
    `);

    this.productService
      .editProduct({
        bookId: this.product.bookId,
        isbn: this.product.isbn,
        title: this.product.title,
        authors: this.product.authors,
        publisher: this.product.publisher,
        price: this.product.price,
        category: this.product.category,
        description: this.product.description,
        publicationYear: this.product.publicationYear,
        noOfCopies: this.product.noOfCopies,
      })
      .subscribe(() => {
        console.log("Done Editing Product!!");
      });
  }
}
