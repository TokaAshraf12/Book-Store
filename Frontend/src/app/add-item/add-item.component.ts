import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { BookRequest } from "../dto/data";
import { AuthService } from "../services/auth/auth.service";
import { ProductService } from "../services/product/product.service";

@Component({
  selector: "app-add-item",
  templateUrl: "./add-item.component.html",
  styleUrls: ["./add-item.component.css"],
})
export class AddItemComponent {
  constructor(
    private productService: ProductService,
    private router: Router,
    private authService: AuthService
  ) {}

  imageToBeUploaded!: File;
  image: any;

  public onFileChange(event: any) {
    const files = event.target.files;
    if (files.length === 0) return;
    this.imageToBeUploaded = files[0];

    // No need for that check _ already handled ..
    // const mimeType = files[0].type;
    // if (mimeType.match(/image\/*/) == null) {
    //     return;
    // }

    const reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.image = reader.result;
    };
  }

  public addItem() {
    const title = (<HTMLInputElement>document.getElementById("title")).value;
    const price: number = parseFloat(
      (<HTMLInputElement>document.getElementById("price")).value
    );
    const category = (<HTMLInputElement>document.getElementById("c")).value;
    const description = (<HTMLInputElement>document.getElementById("desc"))
      .value;
    const noOfCopies = (<HTMLInputElement>document.getElementById("quantity"))
      .value;
    const threshold = (<HTMLInputElement>document.getElementById("threshold"))
      .value;
    const publicationYear = (<HTMLInputElement>document.getElementById("Year"))
      .value.substring(5);
    const authors = (<HTMLInputElement>(
      document.getElementById("author")
    )).value.split("&");
    const publisher = (<HTMLInputElement>document.getElementById("publisher"))
      .value;

    const book: BookRequest = {
      title: title,
      price: price,
      category: category,
      noOfCopies: Number(noOfCopies),
      description: description,
      manager: this.authService.getUserEmail(),
      threshold: Number(threshold),
      publicationYear: publicationYear,
      authors: authors,
      publisher: publisher,
    };
    console.log(
      `Book => ${book.title} \n${book.price} \n${book.description} \n${book.price} \n${book.authors} \nYear: ${book.publicationYear}`
    );

    const formParams = new FormData();
    formParams.append(
      "imageFile",
      new Blob([this.imageToBeUploaded], {
        type: "multipart/form-data",
      }),
      this.imageToBeUploaded.name
    );
    formParams.append(
      "product",
      new Blob([JSON.stringify(book)], {
        type: "application/json",
      })
    );

    this.productService.createProduct(formParams).subscribe(
      () => {
        this.router
          .navigateByUrl("user/add-item", { skipLocationChange: true })
          .then(() =>
            this.router.navigate(["home"], {
              queryParams: { inHome: "true" },
            })
          );
      },
      () => {
        console.log("YARABBBBBBBBBBB");
      }
    );
  }
}
