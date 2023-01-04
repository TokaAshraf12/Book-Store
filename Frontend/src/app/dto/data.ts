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

// Book going to server
export interface BookRequest {
  isbn: string;
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
  bookId: number;
  isbn: string;
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

// Book Specific Details For Grabbing All Books
export interface BookSpecificDetails {
  bookId: number;
  isbn: string;
  title: string;
  description: string;
  price: number;
  noOfCopies: number;
  image: any;
}

export interface Cart {
  bookId: number;
  title: string;
  isbn: string;
  author: string;
  publisher: string;
  publicationYear: string;
  price: number;
  category: string;
  noOfCopies: number;
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

// Edit Book :)
export interface ProductEdit {
  bookId: number;
  isbn: string;
  title: string;
  authors: Array<string>;
  publisher: string;
  publicationYear: string;
  price: number;
  category: string;
  noOfCopies: number;
  description: string;
}

// Publisher All Info
export interface PublisherRequest {
  name: string;
  address: string;
  phone: string;
}

export interface order {
  orderId: number;
  ISBN: string;
  bookTitle: string;
  Quantity: number;
}
