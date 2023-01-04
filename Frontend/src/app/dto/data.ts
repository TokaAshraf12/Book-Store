export interface RegisterRequest {
  firstName: string;
  lastName: string;
  password: string;
  email: string;
  gender: string;
  phoneNumber: string;
  dateOfBirth: string;
  isManager: boolean;
}

export interface ProfileInfoResponse {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  dateOfBirth: string;
  gender: string;
}

export interface LoginRequest {
  email: String;
  password: String;
}

export interface RefreshToken {
  refreshToken: string;
  email: string;
}

export interface AuthenticationResponse {
  authenticationToken: String;
  refreshToken: String;
  expiresAt: String;
  email: String;
}

export interface BookRequest {
  title: string;
  price: number;
  category: string;
  noOfCopies: number;
  threshold: number;
  publicationYear: string;
  authors: Array<string>;
  publisher: string;
  manager: string;
  description: string;
}

// Specific Book All Details
export interface BookResponse {
  ISBN: number;
  title: string;
  author: string;
  publisher: string;
  year: string;
  price: number;
  category: string;
  noOfCopies: number;
  threshold: number;
  publicationYear: string;
  authors: Array<string>;
  manager: string;
  description: string;
  image: any;
  isOwner: boolean;
}

// Product All Info
export interface ProductAllInfo {
  productId: number;
  title: string;
  isbn: number;
  author: string;
  publisher: string;
  year: string;
  price: number;
  category: string;
  inStock: number;
  description: string;
  image: any;
  owner: string;
  createdDate: string;
  isOwner: boolean;
}

// Product Specific Details For Grabbing All Produts
export interface ProductSpecificDetails {
  productId: number;
  title: string;
  isbn: number;
  author: string;
  publisher: string;
  year: string;
  description: string;
  price: number;
  inStock: number;
  image: any;
}

export interface Cart {
  productId: number;
  title: string;
  isbn: number;
  author: string;
  publisher: string;
  year: string;
  price: number;
  category: string;
  inStock: number;
  description: string;
  image: any;
  quantity: number;
  totalPrice: number;
}

// Checkout
export interface CheckoutRequest {
  customer: string;
  products: Array<CheckoutProductInfo>;
}

export interface CheckoutProductInfo {
  productId: number;
  quantity: number;
}

// Edit Product
export interface ProductEdit {
  productId: number;
  title: string;
  isbn: number;
  author: string;
  publisher: string;
  year: string;
  price: number;
  category: string;
  inStock: number;
  description: string;
}


// Publisher All Info
export interface PublisherRequest {
  publisherName: string;
  address: string;
  phoneNumber: string;
}

export interface order{
   orderId:number;
   ISBN:string;
   bookTitle:string;
   Quantity:number;
}

// Edit User
export interface UserEdit {
  email:string;
  firstName:string;
  lastName :string;
  password:string;
  phoneNumber:string;
}
